package com.example.reactive.spring5reactiveclient;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
public class ReactiveClientTest {

    private WebTestClient webTestClient;

    @Before
    public void setup() {
        this.webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();
    }

    @Test
    public void shouldGeteventById() {
        this.webTestClient.get()
                .uri("events/10")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody().json("{\"id\": 10}");
    }

}
