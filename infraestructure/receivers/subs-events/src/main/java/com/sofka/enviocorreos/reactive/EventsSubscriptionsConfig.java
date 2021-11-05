package com.sofka.enviocorreos.reactive;


import com.sofka.enviocorreos.EmailController;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.api.domain.DomainEvent;
import org.reactivecommons.async.api.HandlerRegistry;
import org.reactivecommons.async.impl.config.annotations.EnableMessageListeners;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.reactivecommons.async.api.HandlerRegistry.register;

@Configuration
@EnableMessageListeners
@RequiredArgsConstructor
public class EventsSubscriptionsConfig {

    private final EventMapper eventMapper;
    private final EmailController controller;
    private static final String ROUTING_KEY_CREATED = "tareas.job.created";
    private static final String ROUTING_KEY_SCHEDULED = "tareas.job.scheduled";
    private static final String ROUTING_KEY_EXECUTED = "tareas.job.executed";

    @Bean
    public HandlerRegistry eventSubscriptions() {
        return register()
                .listenEvent(ROUTING_KEY_CREATED,
                    event -> controller.processJobEvent(eventMapper.buildCanonicalIn(event.getData())),
                    DomainEvent.class)
                .listenEvent(ROUTING_KEY_SCHEDULED,
                    event -> controller.processJobEvent(eventMapper.buildCanonicalIn(event.getData())),
                    DomainEvent.class)
                .listenEvent(ROUTING_KEY_EXECUTED,
                        event -> controller.processJobEvent(eventMapper.buildCanonicalIn(event.getData())),
                        DomainEvent.class);
    }

}

