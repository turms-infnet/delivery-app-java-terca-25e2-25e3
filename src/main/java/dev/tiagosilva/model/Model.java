package dev.tiagosilva.model;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.*;

public abstract class Model<T extends Model<T>> {
    protected long id;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    protected String csvFileName;
    protected List<String> excludeFields = new ArrayList<>();

    public Model(String csvFileName, List<String> excludeFields) {
        this.csvFileName = csvFileName;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        this.excludeFields.add("csvFileName");
        this.excludeFields.add("excludeFields");
        this.excludeFields.addAll(excludeFields);
    }

    public Model(String csvFileName) {
        this.csvFileName = csvFileName;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        this.excludeFields.add("csvFileName");
        this.excludeFields.add("excludeFields");
    }

    public long getId() { return id; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return createdAt; }


    public String toCsv() {
        this.id = new Random().nextLong(1, Long.MAX_VALUE);
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        Class<T> clazz = (Class<T>) this.getClass();
        Field[] fp = clazz.getSuperclass().getDeclaredFields();
        Field[] f = clazz.getDeclaredFields();
        List<String> values = new ArrayList<>();

        for(Field field: fp) {
            try {
                if (!excludeFields.contains(field.getName())) {
                    field.setAccessible(true);
                    Object value = field.get(this);
                    values.add(value.toString());
                }
            } catch (IllegalAccessException e) {}
        }

        for(Field field: f) {
            try {
                if (!excludeFields.contains(field.getName())) {
                    field.setAccessible(true);
                    Object value = field.get(this);
                    values.add(value.toString());
                }
            } catch (IllegalAccessException e) {}
        }

        return String.join(",", values);
    }

    public <T extends Model<T>> String csvHeader() {
        Class<T> clazz = (Class<T>) this.getClass();
        Field[] fp = clazz.getSuperclass().getDeclaredFields();
        Field[] f = clazz.getDeclaredFields();
        List<String> headers = new ArrayList<>();

        for(Field field: fp) {
            if (!excludeFields.contains(field.getName())) {
                headers.add(field.getName());
            }
        }

        for(Field field: f) {
            if (!excludeFields.contains(field.getName())) {
                headers.add(field.getName());
            }
        }

        return String.join(",", headers);
    }

    public void save() {
        boolean fileExists = Files.exists(Paths.get(csvFileName));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFileName, true))) {
            if (!fileExists) {
                writer.write(csvHeader());
                writer.newLine();
            }
            writer.write(toCsv());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public T fromCsv(String csvLine) {
        return null;
    }

    // Listar todos
    public List<T> list() {
        try {
            return Files.lines(Paths.get(csvFileName))
                    .skip(1)
                    .map(this::fromCsv)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public List<T> list(String field, String value, boolean exact) {
        List<T> all = list();
        return all.stream().filter(obj -> {
            String fieldValue = getFieldValue(obj, field);
            if (fieldValue == null) return false;
            return exact ? fieldValue.equals(value) : fieldValue.contains(value);
        }).collect(Collectors.toList());
    }

    public T find(long id) {
        return list().stream().filter(obj -> obj.getId() == id).findFirst().orElse(null);
    }

    public T find(String field, String value) {
        return list(field, value, true).stream().findFirst().orElse(null);
    }
    public boolean update(long id, T object) {
        List<T> all = list();
        boolean updated = false;
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId() == id) {
                all.set(i, object);
                updated = true;
                break;
            }
        }
        if (updated) {
            writeAll(all);
        }
        return updated;
    }

    public boolean delete(long id) {
        List<T> all = list();
        boolean removed = all.removeIf(obj -> obj.getId() == id);
        if (removed) {
            writeAll(all);
        }
        return removed;
    }

    private void writeAll(List<T> all) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFileName))) {
            writer.write(csvHeader());
            writer.newLine();
            for (T obj : all) {
                writer.write(obj.toCsv());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFieldValue(T obj, String field) {
        try {
            var f = obj.getClass().getDeclaredField(field);
            f.setAccessible(true);
            Object value = f.get(obj);
            return value != null ? value.toString() : null;
        } catch (Exception e) {
            return null;
        }
    }
}
