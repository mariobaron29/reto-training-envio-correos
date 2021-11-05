package com.sofka.enviocorreos.common.event.notification;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CanonicalNotification<T> {
    private Header header;
    private T data;
}
