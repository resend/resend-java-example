package com.resend;

import com.resend.core.provider.AuthenticationProvider;
import com.resend.core.provider.impl.AuthenticationProviderStandard;
import com.resend.services.emails.ResendEmails;
import com.resend.services.emails.model.Attachment;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;
import com.resend.util.FileUtils;

import java.io.IOException;


public class Main {
    public static void main(String[] args) {

        String apiKey = "re_123";

        AuthenticationProvider provider = new AuthenticationProviderStandard(apiKey);
        ResendEmails emailClient = new ResendEmails(provider);

        String fileContent = null;
        try {
            fileContent = FileUtils.encodeFileToBase64("invoice.pdf");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Attachment att = Attachment.builder()
                .fileName("invoice.pdf")
                .content(fileContent)
                .build();

        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .from("Me <me@exemple.io>")
                .to("to@example", "you@example.com")
                .cc("carbon@example.com", "copy@example.com")
                .bcc("blind@example.com", "carbon.copy@example.com")
                .replyTo("reply@example.com", "to@example.com")
                .attachments(att)
                .text("Hello, world!")
                .subject("Hello from Java!")
                .build();

        SendEmailResponse ser = emailClient.sendEmail(sendEmailRequest);

        System.out.println(ser.getId());

    }
}