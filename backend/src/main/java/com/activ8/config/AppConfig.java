package com.activ8.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.activ8.eventbus.EventBus;

@Configuration
public class AppConfig {

    @Bean
    public EventBus eventBus() {
        return new EventBus();
    }
}
