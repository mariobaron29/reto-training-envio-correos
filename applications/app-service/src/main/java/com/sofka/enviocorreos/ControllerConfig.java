package com.sofka.enviocorreos;

import com.sofka.enviocorreos.common.event.EventsGateway;
import com.sofka.enviocorreos.configbuilder.ConfigBuilder;
import com.sofka.enviocorreos.configbuilder.ConfigParameters;
import lombok.extern.java.Log;
import org.reactivecommons.async.impl.config.RabbitMqConfig;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Log
@Configuration
@Import(RabbitMqConfig.class)
public class ControllerConfig {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${component.name}")
    private String componentName;

    @Value("${service.name}")
    private String serviceName;

    @Value("${operation}")
    private String operation;

    @Value("${email.sender}")
    private String emailSender;

    @Value("${email.senderName}")
    private String emailSenderName;

    @Value("${email.content.type}")
    private String emailContentType;

    @Value("${email.request.endpoint}")
    private String emailRequestEndpoint;

    @Value("${rabbit.ssl.protocol}")
    private String tls;

    @Bean
    public EmailController jobController(
            EventsGateway eventsGateway
    ) {
        return new EmailController(
                ConfigBuilder.builder()
                        .eventsGateway(eventsGateway)
                        .configParameters(ConfigParameters.builder()
                                    .componentName(componentName)
                                    .serviceName(serviceName)
                                    .operation(operation)
                                    .emailSender(emailSender)
                                    .emailSenderName(emailSenderName)
                                    .emailContentType(emailContentType)
                                    .requestEndpoint(emailRequestEndpoint)
                                .build())
                        .build()
        );
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapperImp();
    }

}
