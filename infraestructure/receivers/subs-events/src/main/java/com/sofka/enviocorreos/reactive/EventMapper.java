package com.sofka.enviocorreos.reactive;


import com.sofka.enviocorreos.common.ObjectMapperDomain;
import com.sofka.enviocorreos.domain.entity.event.JobEvent;
import com.sofka.enviocorreos.domain.entity.jobexecution.JobExecution;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.api.domain.DomainEvent;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@SuppressWarnings("unchecked")
@Component
@RequiredArgsConstructor
public class EventMapper implements ObjectMapperDomain {

    private final ObjectMapper objectMapper;


    public JobEvent buildCanonicalIn(DomainEvent message) {

            return buildJobEvent((Map<String, Object>) message.getData());

    }

    public JobEvent buildJobEvent(Map<String, Object> data){
        return JobEvent.builder()
                    .jobId((String) data.get("jobId"))
                    .eventId((String) data.get("eventId"))
                    .eventName((String) data.get("eventName"))
                    .cronRegExp((String) data.get("cronRegExp"))
                    .email((String) data.get("email"))
                    .status((Boolean) data.get("status"))
                    .timeZone((String) data.get("timeZone"))
                    .url((String) data.get("url"))
                    .jobExecution(buildJobExecution((Map<String, Object>) data.get("jobExecution")))
                .build();
    }

    public JobExecution buildJobExecution(Map<String, Object> data){
        if(data == null){
            return null;
        }

        return JobExecution.builder()
                    .jobId((String) data.get("jobId"))
                    .executionTime(Long.valueOf((Integer) data.get("executionTime")))
                    .endTime(new Date((Long) data.get("endTime")))
                    .httpCode((String) data.get("httpCode"))
                    .output((String) data.get("output"))
                    .status((String) data.get("status"))
                    .id((String) data.get("id"))
                    .scheduledTime(new Date((Long) data.get("scheduledTime")))
                    .startTime(new Date((Long) data.get("startTime")))
                .build();
    }


}
