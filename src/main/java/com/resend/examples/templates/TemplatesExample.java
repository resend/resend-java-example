package com.resend.examples.templates;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.templates.model.CreateTemplateOptions;
import com.resend.services.templates.model.CreateTemplateResponseSuccess;
import com.resend.services.templates.model.GetTemplateResponseSuccess;
import com.resend.services.templates.model.ListTemplatesResponseSuccess;

public class TemplatesExample {

    private static final String API_KEY = "re_123456789";

    public static void main(String[] args) throws ResendException {
        createTemplate();
        listTemplates();
        getTemplate("49a3999c-0ce1-4ea6-ab68-afcd6dc2e794");
        removeTemplate("34a080c9-b17d-4187-ad80-5af20266e535");
    }

    public static void createTemplate() throws ResendException {
        Resend resend = new Resend(API_KEY);

        CreateTemplateOptions options = CreateTemplateOptions.builder()
                .name("order-confirmation")
                .html("<p>Hello {{{FIRST_NAME}}},</p><p>Your order {{{ORDER_ID}}} has been confirmed!</p>")
                .subject("Order Confirmation")
                .build();

        CreateTemplateResponseSuccess response = resend.templates().create(options);

        System.out.println("Template created:");
        System.out.println("  ID: " + response.getId());
    }

    public static void listTemplates() throws ResendException {
        Resend resend = new Resend(API_KEY);

        ListTemplatesResponseSuccess response = resend.templates().list();

        System.out.println("Templates:");
        response.getData().forEach(template ->
            System.out.println("  - " + template.getName() + " (ID: " + template.getId() + ")")
        );
    }

    public static void getTemplate(String templateId) throws ResendException {
        Resend resend = new Resend(API_KEY);

        GetTemplateResponseSuccess response = resend.templates().get(templateId);

        System.out.println("Template details:");
        System.out.println("  ID: " + response.getId());
        System.out.println("  Name: " + response.getName());
    }

    public static void removeTemplate(String templateId) throws ResendException {
        Resend resend = new Resend(API_KEY);

        resend.templates().remove(templateId);

        System.out.println("Template removed: " + templateId);
    }
}
