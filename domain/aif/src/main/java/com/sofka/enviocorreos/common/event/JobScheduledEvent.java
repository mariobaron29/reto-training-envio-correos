package com.sofka.enviocorreos.common.event;


import com.sofka.enviocorreos.common.event.notification.CanonicalNotification;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class JobScheduledEvent implements Event {

    public static final String EVENT_NAME = "tareas.job.scheduled";
    private final CanonicalNotification canonical;

    @Override
    public String name() {
        return EVENT_NAME;
    }

    @Override
    public Object getData() {
        return this.canonical;
    }
}
