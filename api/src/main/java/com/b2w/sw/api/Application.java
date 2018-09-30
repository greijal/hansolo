package com.b2w.sw.api;

import com.b2w.sw.client.SWApi;
import com.b2w.sw.client.SWClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public SWClient SWClient() {
        return SWApi.build();
    }


}