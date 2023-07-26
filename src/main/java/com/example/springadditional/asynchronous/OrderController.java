package com.example.springadditional.asynchronous;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class OrderController {

    private final OrderService orderService;
    private final EmailService emailService;

    @Autowired
    public OrderController(OrderService orderService, EmailService emailService) {
        this.orderService = orderService;
        this.emailService = emailService;
    }

    @GetMapping("/createOrder")
    public ResponseEntity<String> createOrder() {
        CompletableFuture<String> orderCodeFuture = CompletableFuture.supplyAsync(()-> String.valueOf(orderService.createOrderCode()));
        CompletableFuture<String> emailResultFuture = orderCodeFuture.thenCompose(orderCode -> {
            String email = "customer@example.com";
            String message = "Your order with code " + orderCode + " has been created!";
            return emailService.sendEmail(email, message);
        });

        CompletableFuture<Void> otherUser = CompletableFuture.runAsync(() -> {
            try {
                for (int i = 0; i< 5; i++){
                    Thread.sleep(1000);
                    System.out.printf("other user: %d\n", i);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // Đợi kết quả từ các tác vụ không đồng bộ
        CompletableFuture.allOf(orderCodeFuture, emailResultFuture).join();

        return ResponseEntity.ok("Order created successfully!");
    }
}