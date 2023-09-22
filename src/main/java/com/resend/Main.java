package com.resend;

import com.resend.services.emails.model.Attachment;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;
import com.resend.util.FileUtils;

import java.io.IOException;


public class Main {
    public static void main(String[] args) {

        Resend resend = new Resend("re_123456789");

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
                .from("Acme <onboarding@resend.dev>")
                .to("delivered@resend.dev")
                .attachments(att)
                .html("<strong>It works!</strong")
                .subject("Hello from Java!")
                .build();

        SendEmailResponse data = resend.emails().send(sendEmailRequest);

        System.out.println(data.getId());

    }
}