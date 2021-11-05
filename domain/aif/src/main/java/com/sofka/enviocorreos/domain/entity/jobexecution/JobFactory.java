package com.sofka.enviocorreos.domain.entity.jobexecution;

import com.sofka.enviocorreos.domain.canonical.event.JobEventCanonical;
import com.sofka.enviocorreos.domain.canonical.jobexecution.JobExecutionCanonical;
import com.sofka.enviocorreos.domain.entity.event.JobEvent;

public interface JobFactory {

    default JobEventCanonical buildJobEventCanonical(JobEvent event) {
        return JobEventCanonical.builder()
                    .eventId(event.getEventId())
                    .eventName(event.getEventName())
                    .jobId(event.getEventId())
                    .cronRegExp(event.getCronRegExp())
                    .email(event.getEmail())
                    .status(event.getStatus())
                    .timeZone(event.getTimeZone())
                    .url(event.getUrl())
                    .status(event.getStatus())
                .build();
    }

    default JobEvent buildJobEvent(JobEventCanonical event) {
        return JobEvent.builder()
                    .eventId(event.getEventId())
                    .eventName(event.getEventName())
                    .jobId(event.getEventId())
                    .cronRegExp(event.getCronRegExp())
                    .email(event.getEmail())
                    .status(event.getStatus())
                    .timeZone(event.getTimeZone())
                    .url(event.getUrl())
                    .status(event.getStatus())
                .build();
    }

    default JobExecutionCanonical buildJobExecution(JobExecution execution) {
        return com.sofka.enviocorreos.domain.canonical.jobexecution.JobExecutionCanonical.builder()
                    .id(execution.getId())
                    .executionTime(execution.getExecutionTime())
                    .endTime(execution.getEndTime())
                    .httpCode(execution.getHttpCode())
                    .jobId(execution.getJobId())
                    .output(execution.getOutput())
                    .scheduledTime(execution.getScheduledTime())
                    .startTime(execution.getStartTime())
                    .status(execution.getStatus())
                .build();
    }

    default JobExecution buildJobExecution(JobExecutionCanonical canonical) {
        return JobExecution.builder()
                    .id(canonical.getId())
                    .executionTime(canonical.getExecutionTime())
                    .endTime(canonical.getEndTime())
                    .httpCode(canonical.getHttpCode())
                    .jobId(canonical.getJobId())
                    .output(canonical.getOutput())
                    .scheduledTime(canonical.getScheduledTime())
                    .startTime(canonical.getStartTime())
                    .status(canonical.getStatus())
                .build();
    }

}
