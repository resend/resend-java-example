package com.resend.examples.webhooks;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.webhooks.model.CreateWebhookOptions;
import com.resend.services.webhooks.model.CreateWebhookResponseSuccess;
import com.resend.services.webhooks.model.GetWebhookResponseSuccess;
import com.resend.services.webhooks.model.ListWebhooksResponseSuccess;
import com.resend.services.webhooks.model.RemoveWebhookResponseSuccess;
import static com.resend.services.webhooks.model.WebhookEvent.*;

public class WebhooksExample {

    private static final String API_KEY = "re_123456789";

    public static void main(String[] args) throws ResendException {
        createWebhook();
        listWebhooks();
        getWebhook("4dd369bc-aa82-4ff3-97de-514ae3000ee0");
        removeWebhook("4dd369bc-aa82-4ff3-97de-514ae3000ee0");
    }

    public static void createWebhook() throws ResendException {
        Resend resend = new Resend(API_KEY);

        CreateWebhookOptions options = CreateWebhookOptions.builder()
                .endpoint("https://example.com/handler")
                .events(EMAIL_SENT)
                .build();

        CreateWebhookResponseSuccess response = resend.webhooks().create(options);

        System.out.println("Webhook created:");
        System.out.println("  ID: " + response.getId());
        System.out.println("  Signing Secret: " + response.getSigningSecret());
    }

    public static void createWebhookWithMultipleEvents() throws ResendException {
        Resend resend = new Resend(API_KEY);

        CreateWebhookOptions options = CreateWebhookOptions.builder()
                .endpoint("https://example.com/handler")
                .events(EMAIL_SENT, EMAIL_DELIVERED, EMAIL_BOUNCED, EMAIL_OPENED, EMAIL_CLICKED)
                .build();

        CreateWebhookResponseSuccess response = resend.webhooks().create(options);

        System.out.println("Webhook with multiple events created:");
        System.out.println("  ID: " + response.getId());
    }

    public static void listWebhooks() throws ResendException {
        Resend resend = new Resend(API_KEY);

        ListWebhooksResponseSuccess response = resend.webhooks().list();

        System.out.println("Webhooks:");
        response.getData().forEach(webhook ->
            System.out.println("  - " + webhook.getEndpoint() + " (ID: " + webhook.getId() + ")")
        );
    }

    public static void getWebhook(String webhookId) throws ResendException {
        Resend resend = new Resend(API_KEY);

        GetWebhookResponseSuccess response = resend.webhooks().get(webhookId);

        System.out.println("Webhook details:");
        System.out.println("  ID: " + response.getId());
        System.out.println("  Endpoint: " + response.getEndpoint());
    }

    public static void removeWebhook(String webhookId) throws ResendException {
        Resend resend = new Resend(API_KEY);

        RemoveWebhookResponseSuccess response = resend.webhooks().remove(webhookId);

        System.out.println("Webhook removed: " + response.getId());
    }
}
