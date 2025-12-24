package com.resend.examples.topics;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.topics.model.CreateTopicOptions;
import com.resend.services.topics.model.CreateTopicResponseSuccess;
import com.resend.services.topics.model.GetTopicResponseSuccess;
import com.resend.services.topics.model.ListTopicsResponseSuccess;
import com.resend.services.topics.model.RemoveTopicResponseSuccess;

public class TopicsExample {

    private static final String API_KEY = "re_123456789";

    public static void main(String[] args) throws ResendException {
        createTopic();
        listTopics();
        getTopic("b6d24b8e-af0b-4c3c-be0c-359bbd97381e");
        removeTopic("b6d24b8e-af0b-4c3c-be0c-359bbd97381e");
    }

    public static void createTopic() throws ResendException {
        Resend resend = new Resend(API_KEY);

        CreateTopicOptions options = CreateTopicOptions.builder()
                .name("Weekly Newsletter")
                .defaultSubscription("opt_in")
                .build();

        CreateTopicResponseSuccess response = resend.topics().create(options);

        System.out.println("Topic created:");
        System.out.println("  ID: " + response.getId());
    }

    public static void createTopicWithDescription() throws ResendException {
        Resend resend = new Resend(API_KEY);

        CreateTopicOptions options = CreateTopicOptions.builder()
                .name("Product Updates")
                .defaultSubscription("opt_out")
                .description("Get notified about new product features and updates")
                .build();

        CreateTopicResponseSuccess response = resend.topics().create(options);

        System.out.println("Topic with description created:");
        System.out.println("  ID: " + response.getId());
    }

    public static void listTopics() throws ResendException {
        Resend resend = new Resend(API_KEY);

        ListTopicsResponseSuccess response = resend.topics().list();

        System.out.println("Topics:");
        response.getData().forEach(topic ->
            System.out.println("  - " + topic.getName() + " (ID: " + topic.getId() + ")")
        );
    }

    public static void getTopic(String topicId) throws ResendException {
        Resend resend = new Resend(API_KEY);

        GetTopicResponseSuccess response = resend.topics().get(topicId);

        System.out.println("Topic details:");
        System.out.println("  ID: " + response.getId());
        System.out.println("  Name: " + response.getName());
    }

    public static void removeTopic(String topicId) throws ResendException {
        Resend resend = new Resend(API_KEY);

        RemoveTopicResponseSuccess response = resend.topics().remove(topicId);

        System.out.println("Topic removed: " + response.getId());
    }
}
