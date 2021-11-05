package com.sofka.enviocorreos.domain.canonical.event;


import com.sofka.enviocorreos.domain.entity.event.JobEvent;
import reactor.core.publisher.Mono;

public interface EventCanonicalRepository {

    Mono<JobEvent> save(JobEvent data);
    Mono<JobEvent> findById(String id);


}
