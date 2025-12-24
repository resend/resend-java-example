package com.resend.examples.batch;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.batch.model.CreateBatchEmailsResponse;
import com.resend.services.emails.model.CreateEmailOptions;

import java.util.Arrays;
import java.util.List;

public class BatchExample {

    private static final String API_KEY = "re_123456789";

    public static void main(String[] args) throws ResendException {
        sendBatchEmails();
    }

    public static void sendBatchEmails() throws ResendException {
        Resend resend = new Resend(API_KEY);

        CreateEmailOptions firstEmail = CreateEmailOptions.builder()
                .from("Acme <onboarding@resend.dev>")
                .to("foo@gmail.com")
                .subject("Hello World")
                .html("<h1>It works!</h1>")
                .build();

        CreateEmailOptions secondEmail = CreateEmailOptions.builder()
                .from("Acme <onboarding@resend.dev>")
                .to("bar@outlook.com")
                .subject("World Hello")
                .html("<p>It works!</p>")
                .build();

        List<CreateEmailOptions> emails = Arrays.asList(firstEmail, secondEmail);

        CreateBatchEmailsResponse response = resend.batch().send(emails);

        System.out.println("Batch emails sent:");
        response.getData().forEach(email ->
            System.out.println("  - Email ID: " + email.getId())
        );
    }
}
