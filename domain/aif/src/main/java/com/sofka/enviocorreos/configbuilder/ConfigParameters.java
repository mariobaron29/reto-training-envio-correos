package com.sofka.enviocorreos.configbuilder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ConfigParameters {

    private final String componentName;
    private final String serviceName;
    private final String operation;
    private final String routingKey;
    private final String source;
    private final String emailSender;
    private final String emailSenderName;
    private final String emailContentType;
    private final String requestEndpoint;

}
