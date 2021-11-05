package com.sofka.enviocorreos.reactive.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MessageData {

    private String eventId;
    private String eventName;

    private String jobId;
    private String url;
    private String timeZone;
    private String email;
    private String cronRegExp;
    private String status;
}
