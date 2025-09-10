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

                            path("user", () -> {
                                UserController ctrl = new UserController() {};
                                path("", () -> {
                                    get(ctx -> {
                                        List<UserResponseDTO> userDTOList = ctrl.get();
                                        ctx.status(200);
                                        ctx.json(userDTOList);
                                    });
                                    post(ctx -> {
                                        UserResponseDTO userDTO = ctrl.create(ctx.bodyAsClass(UserResponseDTO.class));
                                        userDTO.setId(3L);
                                        ctx.json(userDTO);
                                    });
                                });
                                path("{id}", () -> {
                                    get(ctx -> {
                                        UserResponseDTO userDTO = ctrl.get(Long.valueOf(ctx.pathParam("id")));
                                        ctx.status(200);
                                        ctx.json(userDTO);
                                    });
                                    put(ctx -> {
                                        UserResponseDTO userDTO = ctrl.update(Long.valueOf(ctx.pathParam("id")), ctx.bodyAsClass(UserResponseDTO.class));
                                        ctx.status(200);
                                        ctx.json(userDTO);
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
                            path("address", () -> {
                                AddressController ctrl = new AddressController() {};
                                path("", () -> {
                                    get(ctx -> {
                                        List<AddressResponseDTO> addressDTOList = ctrl.get();
                                        ctx.status(200);
                                        ctx.json(addressDTOList);
                                    });
                                    post(ctx -> {
                                        AddressResponseDTO addressDTO = ctrl.create(ctx.bodyAsClass(AddressResponseDTO.class));
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
                                        AddressResponseDTO addressDTO = ctrl.update(Long.valueOf(ctx.pathParam("id")), ctx.bodyAsClass(AddressResponseDTO.class));
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
                                        ProductResponseDTO productDTO = ctrl.create(ctx.bodyAsClass(ProductResponseDTO.class));
                                        ctx.json(productDTO);
                                    });
                                });
                                path("{id}", () -> {
                                    get(ctx -> {
                                        ProductResponseDTO productDTO = ctrl.get(Long.valueOf(ctx.pathParam("id")));
                                        ctx.status(200);
                                        ctx.json(productDTO);
                                    });
                                    put(ctx -> {
                                        ProductResponseDTO productDTO = ctrl.update(Long.valueOf(ctx.pathParam("id")), ctx.bodyAsClass(ProductResponseDTO.class));
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