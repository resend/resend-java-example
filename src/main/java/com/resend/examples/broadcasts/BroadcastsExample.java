package com.resend.examples.broadcasts;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.broadcasts.model.CreateBroadcastOptions;
import com.resend.services.broadcasts.model.CreateBroadcastResponseSuccess;
import com.resend.services.broadcasts.model.GetBroadcastResponseSuccess;
import com.resend.services.broadcasts.model.ListBroadcastsResponseSuccess;
import com.resend.services.broadcasts.model.RemoveBroadcastResponseSuccess;
import com.resend.services.broadcasts.model.SendBroadcastOptions;
import com.resend.services.broadcasts.model.SendBroadcastResponseSuccess;

public class BroadcastsExample {

    private static final String API_KEY = "re_123456789";

    public static void main(String[] args) throws ResendException {
        createBroadcast();
        listBroadcasts();
        getBroadcast("49a3999c-0ce1-4ea6-ab68-afcd6dc2e794");
        sendBroadcast("49a3999c-0ce1-4ea6-ab68-afcd6dc2e794");
        removeBroadcast("559ac32e-9ef5-46fb-82a1-b76b840c0f7b");
    }

    public static void createBroadcast() throws ResendException {
        Resend resend = new Resend(API_KEY);

        CreateBroadcastOptions options = CreateBroadcastOptions.builder()
                .segmentId("78261eea-8f8b-4381-83c6-79fa7120f1cf")
                .from("Acme <onboarding@resend.dev>")
                .subject("Hello World")
                .html("Hi {{{FIRST_NAME|there}}}, you can unsubscribe here: {{{RESEND_UNSUBSCRIBE_URL}}}")
                .build();

        CreateBroadcastResponseSuccess response = resend.broadcasts().create(options);

        System.out.println("Broadcast created:");
        System.out.println("  ID: " + response.getId());
    }

    public static void createBroadcastWithName() throws ResendException {
        Resend resend = new Resend(API_KEY);

        CreateBroadcastOptions options = CreateBroadcastOptions.builder()
                .segmentId("78261eea-8f8b-4381-83c6-79fa7120f1cf")
                .from("Acme <onboarding@resend.dev>")
                .subject("Weekly Newsletter - December")
                .html("<h1>Weekly Newsletter</h1><p>Hi {{{FIRST_NAME|there}}},</p><p>Here's your weekly update!</p>")
                .name("December Newsletter")
                .build();

        CreateBroadcastResponseSuccess response = resend.broadcasts().create(options);

        System.out.println("Broadcast with name created:");
        System.out.println("  ID: " + response.getId());
    }

    public static void listBroadcasts() throws ResendException {
        Resend resend = new Resend(API_KEY);

        ListBroadcastsResponseSuccess response = resend.broadcasts().list();

        System.out.println("Broadcasts:");
        response.getData().forEach(broadcast ->
            System.out.println("  - ID: " + broadcast.getId() + " (Status: " + broadcast.getStatus() + ")")
        );
    }

    public static void getBroadcast(String broadcastId) throws ResendException {
        Resend resend = new Resend(API_KEY);

        GetBroadcastResponseSuccess response = resend.broadcasts().get(broadcastId);

        System.out.println("Broadcast details:");
        System.out.println("  ID: " + response.getId());
        System.out.println("  Status: " + response.getStatus());
    }

    public static void sendBroadcast(String broadcastId) throws ResendException {
        Resend resend = new Resend(API_KEY);

        SendBroadcastOptions options = SendBroadcastOptions.builder()
                .build();

        SendBroadcastResponseSuccess response = resend.broadcasts().send(options, broadcastId);

        System.out.println("Broadcast sent: " + response.getId());
    }

    public static void sendBroadcastScheduled(String broadcastId) throws ResendException {
        Resend resend = new Resend(API_KEY);

        SendBroadcastOptions options = SendBroadcastOptions.builder()
                .scheduledAt("in 1 hour")
                .build();

        SendBroadcastResponseSuccess response = resend.broadcasts().send(options, broadcastId);

        System.out.println("Broadcast scheduled: " + response.getId());
    }

    public static void removeBroadcast(String broadcastId) throws ResendException {
        Resend resend = new Resend(API_KEY);

        RemoveBroadcastResponseSuccess response = resend.broadcasts().remove(broadcastId);

        System.out.println("Broadcast removed: " + response.getId());
    }
}
