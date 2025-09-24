package dev.tiagosilva;

import dev.tiagosilva.dto.*;
import dev.tiagosilva.controller.*;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.List;

import static io.javalin.apibuilder.ApiBuilder.*;


public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        var app = Javalin.create(config -> {
                    config.router.mount(router -> {})
                        .apiBuilder(() -> {
                            path("auth", () -> {
                                UserController ctrl = new UserController() {};
                                path("login", () -> {
                                    post(ctx -> {
                                        try {
                                            UserResponseDTO userDTO = ctrl.login(ctx.bodyAsClass(UserRequestDTO.class));
                                            ctx.status(200);
                                            ctx.json(userDTO);
                                        } catch (Exception e) {
                                            logger.error("Error during login", e);
                                            ctx.status(401);
                                            ctx.json("Invalid username or password");
                                        }
                                    });
                                });
                                path("register", () -> {
                                    post(ctx -> {
                                        boolean result = ctrl.register(ctx.bodyAsClass(UserRequestDTO.class));
                                        if (result) {
                                            ctx.status(201);
                                            ctx.json("User created successfully");
                                        }
                                        else {
                                            ctx.status(400);
                                            ctx.json("Error creating user");
                                        }
                                    });
                                });
                            });
                            path("address", () -> {
                                AddressController ctrl = new AddressController() {};
                                path("", () -> {
                                    get(ctx -> {
                                        List<AddressResponseDTO> addressDTOList = ctrl.get();
                                        ctx.status(200);
                                        ctx.json(addressDTOList);
                                    });
                                    post(ctx -> {
                                        boolean addressDTO = ctrl.create(ctx.bodyAsClass(AddressRequestDTO.class));
                                        ctx.json(addressDTO);
                                    });
                                });
                                path("{id}", () -> {
                                    get(ctx -> {
                                        AddressResponseDTO addressDTO = ctrl.get(Long.valueOf(ctx.pathParam("id")));
                                        ctx.status(200);
                                        ctx.json(addressDTO);
                                    });
                                    put(ctx -> {
                                        boolean addressDTO = ctrl.update(Long.valueOf(ctx.pathParam("id")), ctx.bodyAsClass(AddressRequestDTO.class));
                                        ctx.status(200);
                                        ctx.json(addressDTO);
                                    });
                                    delete(ctx -> {
                                        boolean result = ctrl.delete(Long.valueOf(ctx.pathParam("id")));
                                        if (result) {
                                            ctx.status(200);
                                            ctx.json("Ok");
                                        }
                                        else {
                                            ctx.status(400);
                                            ctx.json("Error");
                                        }
                                    });
                                });
                            });
                            path("product", () -> {
                                ProductController ctrl = new ProductController() {};
                                path("", () -> {
                                    get(ctx -> {
                                        List<ProductResponseDTO> productDTOList = ctrl.get();
                                        ctx.status(200);
                                        ctx.json(productDTOList);
                                    });
                                    post(ctx -> {
                                        boolean result = ctrl.create(ctx.bodyAsClass(ProductRequestDTO.class));
                                        if (result) {
                                            ctx.status(201);
                                            ctx.json("Product created successfully");
                                        }
                                        else {
                                            ctx.status(400);
                                            ctx.json("Product already exists");
                                        }
                                    });
                                });
                                path("{id}", () -> {
                                    get(ctx -> {
                                        ProductResponseDTO productDTO = ctrl.get(Long.valueOf(ctx.pathParam("id")));
                                        ctx.status(200);
                                        ctx.json(productDTO);
                                    });
                                    put(ctx -> {
                                        boolean productDTO = ctrl.update(Long.valueOf(ctx.pathParam("id")), ctx.bodyAsClass(ProductRequestDTO.class));
                                        ctx.status(200);
                                        ctx.json(productDTO);
                                    });
                                    delete(ctx -> {
                                        boolean result = ctrl.delete(Long.valueOf(ctx.pathParam("id")));
                                        if (result) {
                                            ctx.status(200);
                                            ctx.json("Ok");
                                        }
                                        else {
                                            ctx.status(400);
                                            ctx.json("Error");
                                        }
                                    });
                                });
                            });
                            path("order", () -> {
                                OrderController ctrl = new OrderController() {};
                                path("", () -> {
                                    get(ctx -> {
                                        List<OrderResponseDTO> oderDTOList = ctrl.get();
                                        ctx.status(200);
                                        ctx.json(oderDTOList);
                                    });
                                    post(ctx -> {
                                        OrderResponseDTO orderDTO = ctrl.create(ctx.bodyAsClass((Type) OrderRequestDTO.class));
                                        ctx.json(orderDTO);
                                    });
                                });
                                path("{id}", () -> {
                                    get(ctx -> {
                                        OrderResponseDTO orderDTO = ctrl.get(Long.valueOf(ctx.pathParam("id")));
                                        ctx.status(200);
                                        ctx.json(orderDTO);
                                    });
                                    put(ctx -> {
                                        OrderResponseDTO orderDTO = ctrl.update(Long.valueOf(ctx.pathParam("id")), ctx.bodyAsClass(OrderResponseDTO.class));
                                        ctx.status(200);
                                        ctx.json(orderDTO);
                                    });
                                    delete(ctx -> {
                                        boolean result = ctrl.delete(Long.valueOf(ctx.pathParam("id")));
                                        if (result) {
                                            ctx.status(200);
                                            ctx.json("Ok");
                                        }
                                        else {
                                            ctx.status(400);
                                            ctx.json("Error");
                                        }
                                    });
                                });
                            });
                            path("payment", () -> {
                                PaymentController ctrl = new PaymentController() {};
                                path("", () -> {
                                    get(ctx -> {
                                        List<PaymentResponseDTO> paymentDTOList = ctrl.get();
                                        ctx.status(200);
                                        ctx.json(paymentDTOList);
                                    });
                                    post(ctx -> {
                                        PaymentResponseDTO paymentDTO = ctrl.create(ctx.bodyAsClass(PaymentResponseDTO.class));
                                        ctx.json(paymentDTO);
                                    });
                                });
                                path("{id}", () -> {
                                    get(ctx -> {
                                        PaymentResponseDTO paymentDTO = ctrl.get(Long.valueOf(ctx.pathParam("id")));
                                        ctx.status(200);
                                        ctx.json(paymentDTO);
                                    });
                                    put(ctx -> {
                                        PaymentResponseDTO paymentDTO = ctrl.update(Long.valueOf(ctx.pathParam("id")), ctx.bodyAsClass(PaymentResponseDTO.class));
                                        ctx.status(200);
                                        ctx.json(paymentDTO);
                                    });
                                    delete(ctx -> {
                                        boolean result = ctrl.delete(Long.valueOf(ctx.pathParam("id")));
                                        if (result) {
                                            ctx.status(200);
                                            ctx.json("Ok");
                                        }
                                        else {
                                            ctx.status(400);
                                            ctx.json("Error");
                                        }
                                    });
                                });
                            });
                            path("report", () -> {});
                        });
                })
                .get("/status", ctx -> ctx.result("API is running..."))
                .start(8000);
    }
}