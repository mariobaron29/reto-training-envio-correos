package com.sofka.enviocorreos;

import com.sofka.enviocorreos.common.event.JobCreatedEvent;
import com.sofka.enviocorreos.common.event.JobExecutedEvent;
import com.sofka.enviocorreos.common.event.JobScheduledEvent;
import com.sofka.enviocorreos.common.event.notification.HeaderFactory;
import com.sofka.enviocorreos.configbuilder.ConfigBuilder;
import com.sofka.enviocorreos.domain.entity.event.JobEvent;
import com.sofka.enviocorreos.domain.entity.jobexecution.JobFactory;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGridAPI;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import reactor.core.publisher.Mono;

@Log
@RequiredArgsConstructor
public class EmailController implements JobFactory, HeaderFactory {

    private final ConfigBuilder configBuilder;
    private String emailSubject = "";
    private String emailMessage = "";


    @Autowired
    private SendGridAPI sendGridAPI;

    public Mono<Void> processJobEvent(JobEvent event) {
        System.out.println(String.format("Processing event: \n id: %s \n Event name: %s \n Job Id: %s",event.getEventId(), event.getEventName(), event.getJobId()));

        return prepareEmailContent(event)
                .flatMap(jobEvent -> sendEmail(jobEvent)) ;
    }

    private Mono<JobEvent> prepareEmailContent(JobEvent event){
        Map<String, String> emailSubjects = new HashMap<>();
        Map<String, String> emailContents = new HashMap<>();

        emailSubjects.put(JobCreatedEvent.EVENT_NAME, String.format("Job %s created", event.getJobId()));
        emailSubjects.put(JobScheduledEvent.EVENT_NAME, String.format("Job %s scheduled", event.getJobId()));
        emailSubjects.put(JobExecutedEvent.EVENT_NAME, String.format("Job %s executed", event.getJobId()));

        emailContents.put(JobCreatedEvent.EVENT_NAME, String.format("A job with the following details has been created: \n Event Id: %s \n Job Id: %s \n Cron expression: %s \n Time zone: %s \n ",
                                                            event.getEventId() ,event.getJobId(), event.getCronRegExp(), event.getTimeZone()));
        emailContents.put(JobScheduledEvent.EVENT_NAME, String.format("A job with the following details has been scheduled: \n Event Id: %s \n Job Id: %s \n Cron expression: %s \n Time zone: %s \n ",
                                                            event.getEventId() ,event.getJobId(), event.getCronRegExp(), event.getTimeZone()));
        emailContents.put(JobExecutedEvent.EVENT_NAME, String.format("A job with the following details has been executed: \n Event Id: %s \n Job Id: %s \n Cron expression: %s \n Time zone: %s \n ",
                                                            event.getEventId() ,event.getJobId(), event.getCronRegExp(), event.getTimeZone()));

        emailSubject = emailSubjects.get(event.getEventName());
        emailMessage = emailContents.get(event.getEventName());

        return Mono.just(event);
    }

    private Mono<Void> sendEmail(JobEvent event){
        Email from = new Email("mariobaron@gmail.com", "Cron JOB notifier");
        String subject = emailSubject;
        Email to = new Email(event.getEmail());
        Content content = new Content("text/plain", emailMessage);
        Mail mail = new Mail(from, subject, to, content);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGridAPI.api(request);
            System.out.println(response.getBody());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return Mono.empty();
    }

}
