package com.resend.examples.apikeys;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.apikeys.model.CreateApiKeyOptions;
import com.resend.services.apikeys.model.CreateApiKeyResponse;
import com.resend.services.apikeys.model.ListApiKeysResponse;

public class ApiKeysExample {

    private static final String API_KEY = "re_123456789";

    public static void main(String[] args) throws ResendException {
        createApiKey();
        listApiKeys();
        removeApiKey("b6d24b8e-af0b-4c3c-be0c-359bbd97381e");
    }

    public static void createApiKey() throws ResendException {
        Resend resend = new Resend(API_KEY);

        CreateApiKeyOptions options = CreateApiKeyOptions.builder()
                .name("Production")
                .build();

        CreateApiKeyResponse response = resend.apiKeys().create(options);

        System.out.println("API Key created:");
        System.out.println("  ID: " + response.getId());
        System.out.println("  Token: " + response.getToken());
    }

    public static void createApiKeyWithSendingAccess() throws ResendException {
        Resend resend = new Resend(API_KEY);

        CreateApiKeyOptions options = CreateApiKeyOptions.builder()
                .name("Sending Only")
                .permission("sending_access")
                .domainId("your-domain-id")
                .build();

        CreateApiKeyResponse response = resend.apiKeys().create(options);

        System.out.println("API Key with sending access created:");
        System.out.println("  ID: " + response.getId());
        System.out.println("  Token: " + response.getToken());
    }

    public static void listApiKeys() throws ResendException {
        Resend resend = new Resend(API_KEY);

        ListApiKeysResponse response = resend.apiKeys().list();

        System.out.println("API Keys:");
        response.getData().forEach(apiKey ->
            System.out.println("  - " + apiKey.getName() + " (ID: " + apiKey.getId() + ")")
        );
    }

    public static void removeApiKey(String apiKeyId) throws ResendException {
        Resend resend = new Resend(API_KEY);

        resend.apiKeys().remove(apiKeyId);

        System.out.println("API Key removed: " + apiKeyId);
    }
}
