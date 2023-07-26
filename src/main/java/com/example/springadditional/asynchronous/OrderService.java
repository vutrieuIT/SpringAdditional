package com.example.springadditional.asynchronous;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class OrderService {

    @Async // Đánh dấu phương thức này là bất đồng bộ
    public CompletableFuture<String> createOrderCode() {
        // Mô phỏng thời gian tạo mã đơn hàng
        try {
            for (int i = 0; i< 5; i++){
                Thread.sleep(1000);
                System.out.printf("create order code: %d\n", i);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String orderCode = "ORD12345";
        return CompletableFuture.completedFuture(orderCode);
    }
}
