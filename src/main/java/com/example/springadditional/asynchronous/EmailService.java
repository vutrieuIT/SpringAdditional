package com.example.springadditional.asynchronous;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class EmailService {

    @Async // Đánh dấu phương thức này là bất đồng bộ
    public CompletableFuture<String> sendEmail(String email, String message) {
        // Mô phỏng thời gian gửi email
        try {
            for (int i = 0; i< 3; i++){
                Thread.sleep(1000);
                System.out.printf("send mail: %d\n", i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String result = "Email sent to " + email + " with message: " + message;
        return CompletableFuture.completedFuture(result);
    }
}
