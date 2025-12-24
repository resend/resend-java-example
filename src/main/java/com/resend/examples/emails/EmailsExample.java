package com.resend.examples.emails;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;

import java.util.List;

public class EmailsExample {

    private static final String API_KEY = "re_123456789";

    public static void main(String[] args) throws ResendException {
        sendSimpleEmail();
        sendEmailWithMultipleRecipients();
        sendEmailWithReplyTo();
        sendEmailWithCcAndBcc();
    }

    public static void sendSimpleEmail() throws ResendException {
        Resend resend = new Resend(API_KEY);

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Acme <onboarding@resend.dev>")
                .to("delivered@resend.dev")
                .subject("Hello World")
                .html("<p>It works!</p>")
                .build();

        CreateEmailResponse response = resend.emails().send(params);
        System.out.println("Email sent with ID: " + response.getId());
    }

    public static void sendEmailWithMultipleRecipients() throws ResendException {
        Resend resend = new Resend(API_KEY);

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Acme <onboarding@resend.dev>")
                .to(List.of("user1@example.com", "user2@example.com"))
                .subject("Hello to multiple recipients")
                .html("<p>This email was sent to multiple recipients!</p>")
                .build();

        CreateEmailResponse response = resend.emails().send(params);
        System.out.println("Email sent with ID: " + response.getId());
    }

    public static void sendEmailWithReplyTo() throws ResendException {
        Resend resend = new Resend(API_KEY);

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Acme <onboarding@resend.dev>")
                .to("delivered@resend.dev")
                .replyTo("support@resend.dev")
                .subject("Hello with Reply-To")
                .html("<p>Reply to this email to reach our support team!</p>")
                .build();

        CreateEmailResponse response = resend.emails().send(params);
        System.out.println("Email sent with ID: " + response.getId());
    }

    public static void sendEmailWithCcAndBcc() throws ResendException {
        Resend resend = new Resend(API_KEY);

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Acme <onboarding@resend.dev>")
                .to("delivered@resend.dev")
                .cc("cc@resend.dev")
                .bcc("bcc@resend.dev")
                .subject("Hello with CC and BCC")
                .html("<p>This email includes CC and BCC recipients!</p>")
                .build();

        CreateEmailResponse response = resend.emails().send(params);
        System.out.println("Email sent with ID: " + response.getId());
    }
}
