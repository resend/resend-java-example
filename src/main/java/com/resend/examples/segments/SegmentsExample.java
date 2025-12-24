package com.resend.examples.segments;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.segments.model.CreateSegmentOptions;
import com.resend.services.segments.model.CreateSegmentResponseSuccess;
import com.resend.services.segments.model.GetSegmentResponseSuccess;
import com.resend.services.segments.model.ListSegmentsResponseSuccess;
import com.resend.services.segments.model.RemoveSegmentResponseSuccess;

public class SegmentsExample {

    private static final String API_KEY = "re_123456789";

    public static void main(String[] args) throws ResendException {
        createSegment();
        listSegments();
        getSegment("78261eea-8f8b-4381-83c6-79fa7120f1cf");
        removeSegment("78261eea-8f8b-4381-83c6-79fa7120f1cf");
    }

    public static void createSegment() throws ResendException {
        Resend resend = new Resend(API_KEY);

        CreateSegmentOptions options = CreateSegmentOptions.builder()
                .name("Registered Users")
                .build();

        CreateSegmentResponseSuccess response = resend.segments().create(options);

        System.out.println("Segment created:");
        System.out.println("  ID: " + response.getId());
        System.out.println("  Name: " + response.getName());
    }

    public static void listSegments() throws ResendException {
        Resend resend = new Resend(API_KEY);

        ListSegmentsResponseSuccess response = resend.segments().list();

        System.out.println("Segments:");
        response.getData().forEach(segment ->
            System.out.println("  - " + segment.getName() + " (ID: " + segment.getId() + ")")
        );
    }

    public static void getSegment(String segmentId) throws ResendException {
        Resend resend = new Resend(API_KEY);

        GetSegmentResponseSuccess response = resend.segments().get(segmentId);

        System.out.println("Segment details:");
        System.out.println("  ID: " + response.getId());
        System.out.println("  Name: " + response.getName());
    }

    public static void removeSegment(String segmentId) throws ResendException {
        Resend resend = new Resend(API_KEY);

        RemoveSegmentResponseSuccess response = resend.segments().remove(segmentId);

        System.out.println("Segment removed: " + response.getId());
    }
}
