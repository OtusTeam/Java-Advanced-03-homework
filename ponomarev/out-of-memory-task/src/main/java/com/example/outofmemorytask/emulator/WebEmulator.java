package com.example.outofmemorytask.emulator;

import com.example.outofmemorytask.model.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @author Anton Ponomarev on 13.05.2024
 * @project Java-Advanced-homework
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class WebEmulator {

    @Autowired
    private Executor executor;

    public void runThreads() {
        CompletableFuture.runAsync(() -> {
            HttpClient client = HttpClient.newHttpClient();
            while (true) {
                try {
                    UserDto userDto = new UserDto("User-" + UUID.randomUUID(), "Data-" + UUID.randomUUID());
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create("http://localhost:8080/register"))
                            .header("Content-Type", MediaType.APPLICATION_JSON.toString())
                            .POST(HttpRequest.BodyPublishers.ofString(new ObjectMapper().writeValueAsString(userDto)))
                            .build();
                    log.info("Sending request: {}", request.toString());

                    HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
                    log.info("HTTP Response: {}", httpResponse);
                    Thread.sleep(10);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }, executor);
    }
}
