package com.resend.examples.emails;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.Attachment;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import com.resend.util.FileUtils;

import java.io.IOException;

public class EmailWithAttachmentsExample {

    private static final String API_KEY = "re_123456789";

    public static void main(String[] args) throws ResendException {
        sendEmailWithLocalAttachment();
        sendEmailWithRemoteAttachment();
        sendEmailWithInlineImage();
    }

    public static void sendEmailWithLocalAttachment() throws ResendException {
        Resend resend = new Resend(API_KEY);

        String fileContent;
        try {
            fileContent = FileUtils.encodeFileToBase64("invoice.pdf");
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: " + e.getMessage(), e);
        }

        Attachment attachment = Attachment.builder()
                .fileName("invoice.pdf")
                .content(fileContent)
                .build();

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Acme <onboarding@resend.dev>")
                .to("delivered@resend.dev")
                .subject("Receipt for your payment")
                .html("<p>Thanks for your payment! Please find your invoice attached.</p>")
                .attachments(attachment)
                .build();

        CreateEmailResponse response = resend.emails().send(params);
        System.out.println("Email with local attachment sent with ID: " + response.getId());
    }

    public static void sendEmailWithRemoteAttachment() throws ResendException {
        Resend resend = new Resend(API_KEY);

        Attachment attachment = Attachment.builder()
                .path("https://resend.com/static/sample/invoice.pdf")
                .fileName("invoice.pdf")
                .build();

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Acme <onboarding@resend.dev>")
                .to("delivered@resend.dev")
                .subject("Receipt for your payment")
                .html("<p>Thanks for your payment! Please find your invoice attached.</p>")
                .attachments(attachment)
                .build();

        CreateEmailResponse response = resend.emails().send(params);
        System.out.println("Email with remote attachment sent with ID: " + response.getId());
    }

    public static void sendEmailWithInlineImage() throws ResendException {
        Resend resend = new Resend(API_KEY);

        Attachment inlineImage = Attachment.builder()
                .path("https://resend.com/static/sample/logo.png")
                .fileName("logo.png")
                .contentId("logo-image")
                .build();

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Acme <onboarding@resend.dev>")
                .to("delivered@resend.dev")
                .subject("Thank you for contacting us")
                .html("<p>Here is our <img src=\"cid:logo-image\"/> inline logo</p>")
                .attachments(inlineImage)
                .build();

        CreateEmailResponse response = resend.emails().send(params);
        System.out.println("Email with inline image sent with ID: " + response.getId());
    }
}
