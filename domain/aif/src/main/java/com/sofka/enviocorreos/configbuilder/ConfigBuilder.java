package com.sofka.enviocorreos.configbuilder;

import com.sofka.enviocorreos.common.event.EventsGateway;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class ConfigBuilder {

    private final ConfigParameters configParameters;
}
