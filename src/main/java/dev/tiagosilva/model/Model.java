package dev.tiagosilva.model;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.lang.reflect.*;

@Getter
@Setter
class Model<T extends Model<T>> {
    protected String csvFileName;
    protected Long id;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    protected List<String> excludedFields = new ArrayList<>();

    public Model(String csvFileName) {
        this.csvFileName = csvFileName;
        excludedFields.add("csvFileName");
        excludedFields.add("excludedFields");
    }

    public Model(String csvFileName, List<String> excludedFields) {
        this.csvFileName = csvFileName;

        this.excludedFields.add("csvFileName");
        this.excludedFields.add("excludedFields");
        this.excludedFields.addAll(excludedFields);
    }

    public String toCSV() {
        try {
            Field[] superFields = this.getClass().getSuperclass().getDeclaredFields();
            Field[] fields = this.getClass().getDeclaredFields();
            List<String> values = new ArrayList<>();

            for(Field f : superFields) {
                if(!excludedFields.contains(f.getName())) {
                    f.setAccessible(true);
                    Object v = f.get(this);
                    values.add(v == null ? "" : v.toString());
                }
            }
            for (Field f : fields) {
                if(!excludedFields.contains(f.getName())){
                    f.setAccessible(true);
                    Object v = f.get(this);
                    values.add(v == null ? "" : v.toString());
                }
            }

            return String.join(",", values);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao serializar para CSV", e);
        }
    }

    public <T extends Model<T>> String csvHeader(Class<T> clazz) {
        Field[] superFields = clazz.getSuperclass().getDeclaredFields();
        Field[] fields = clazz.getDeclaredFields();
        List<String> names = new ArrayList<>();
        for (Field f : superFields) {
            if(!excludedFields.contains(f.getName())){
                names.add(f.getName());
            }
        }
        for (Field f : fields) {
            if(!excludedFields.contains(f.getName())) {
                names.add(f.getName());
            }
        }
        return String.join(",", names);
    }

    public T fromCSV(String csvLine) {
        try {
            String[] parts = csvLine.split(",");
            Class<?> clazz = this.getClass();
            List<Field> allFields = new ArrayList<>();
            for (Field f : clazz.getSuperclass().getDeclaredFields()) {
                if (!excludedFields.contains(f.getName())) {
                    allFields.add(f);
                }
            }
            allFields.addAll(Arrays.asList(clazz.getDeclaredFields()));

            T instance = (T) clazz.getDeclaredConstructor().newInstance();
            for (int i = 0; i < allFields.size() && i < parts.length; i++) {
                Field field = allFields.get(i);
                field.setAccessible(true);
                Class<?> type = field.getType();
                String value = parts[i];
                if (type == Long.class || type == long.class) {
                    field.set(instance, value.isEmpty() ? null : Long.parseLong(value));
                } else if (type == Integer.class || type == int.class) {
                    field.set(instance, value.isEmpty() ? null : Integer.parseInt(value));
                } else if (type == LocalDateTime.class) {
                    field.set(instance, value.isEmpty() ? null : LocalDateTime.parse(value));
                } else if (type == Float.class || type == float.class) {
                    field.set(instance, value.isEmpty() ? null : Float.parseFloat(value));
                } else {
                    field.set(instance, value);
                }
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao desserializar do CSV: " + e.getMessage());
        }
    }

    public String getFieldValue(String field) {
        try {
            Field f = this.getClass().getDeclaredField(field);
            f.setAccessible(true);
            Object v = f.get(this);
            return v == null ? null : v.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public void save() throws IOException {
        this.createdAt = LocalDateTime.now();
        Random random = new Random();

        File file = new File(csvFileName);
        boolean writeHeader = !file.exists() || file.length() == 0;
        try (FileWriter fw = new FileWriter(csvFileName, true); BufferedWriter bw = new BufferedWriter(fw)) {
            if (writeHeader) {
                bw.write(csvHeader(this.getClass()));
                bw.newLine();
            }
            List<T> all = list();
            if(!all.isEmpty()) {
                this.id = countRows() + 1;
            } else {
                this.id = 1L;
            }
            bw.write(this.toCSV());
            bw.newLine();
        }
    }

    public void update(Long id, T obj) throws IOException {
        obj.updatedAt = LocalDateTime.now();

        List<T> all = list();
        try (FileWriter fw = new FileWriter(csvFileName, false); BufferedWriter bw = new BufferedWriter(fw)) {
            for (T m : all) {
                if (m.getId().equals(id)) {
                    bw.write(obj.toCSV());
                } else {
                    bw.write(m.toCSV());
                }
                bw.newLine();
            }
        }
    }

    public void delete(Long id) throws IOException {
        List<T> all = list();
        try (FileWriter fw = new FileWriter(csvFileName, false); BufferedWriter bw = new BufferedWriter(fw)) {
            for (T m : all) {
                if (!m.getId().equals(id)) {
                    bw.write(m.toCSV());
                    bw.newLine();
                }
            }
        }
    }

    public Long countRows() throws IOException {
        try (FileReader fr = new FileReader(csvFileName); BufferedReader br = new BufferedReader(fr)) {
            return br.lines().count() - 1;
        } catch (FileNotFoundException e) {
            return 0L;
        }
    }

    public List<T> list() throws IOException {
        List<T> result = new ArrayList<>();
        try (FileReader fr = new FileReader(csvFileName); BufferedReader br = new BufferedReader(fr)) {
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (first) { first = false; continue; } // pula header
                result.add(fromCSV(line));
            }
        }
        return result;
    }

    public List<T> list(String field, String filter, boolean exact) throws IOException {
        return list().stream().filter(m -> {
            String value = m.getFieldValue(field);
            if (value == null) return false;
            if (exact) {
                return value.equals(filter);
            } else {
                return value.contains(filter);
            }
        }).collect(Collectors.toList());
    }

    public T find(Long id) throws IOException {
        return list().stream().filter(m -> m.id.equals(id)).findFirst().orElse(null);
    }

    public T find(String field, String value) throws IOException {
        return list().stream().filter(m -> {
            String v = m.getFieldValue(field);
            return v != null && v.equals(value);
        }).findFirst().orElse(null);
    }
}
