package com.resend.examples.domains;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.domains.model.CreateDomainOptions;
import com.resend.services.domains.model.CreateDomainResponse;
import com.resend.services.domains.model.Domain;
import com.resend.services.domains.model.ListDomainsResponse;
import com.resend.services.domains.model.VerifyDomainResponse;

public class DomainsExample {

    private static final String API_KEY = "re_123456789";

    public static void main(String[] args) throws ResendException {
        createDomain();
        listDomains();
        getDomain("4dd369bc-aa82-4ff3-97de-514ae3000ee0");
        verifyDomain("4dd369bc-aa82-4ff3-97de-514ae3000ee0");
        removeDomain("d91cd9bd-1176-453e-8fc1-35364d380206");
    }

    public static void createDomain() throws ResendException {
        Resend resend = new Resend(API_KEY);

        CreateDomainOptions options = CreateDomainOptions.builder()
                .name("example.com")
                .build();

        CreateDomainResponse response = resend.domains().create(options);

        System.out.println("Domain created:");
        System.out.println("  ID: " + response.getId());
        System.out.println("  Name: " + response.getName());
        System.out.println("  Status: " + response.getStatus());
        System.out.println("  Region: " + response.getRegion());
    }

    public static void createDomainWithRegion() throws ResendException {
        Resend resend = new Resend(API_KEY);

        CreateDomainOptions options = CreateDomainOptions.builder()
                .name("example.com")
                .region("eu-west-1")
                .build();

        CreateDomainResponse response = resend.domains().create(options);

        System.out.println("Domain created in EU region:");
        System.out.println("  ID: " + response.getId());
        System.out.println("  Region: " + response.getRegion());
    }

    public static void listDomains() throws ResendException {
        Resend resend = new Resend(API_KEY);

        ListDomainsResponse response = resend.domains().list();

        System.out.println("Domains:");
        response.getData().forEach(domain ->
            System.out.println("  - " + domain.getName() + " (Status: " + domain.getStatus() + ")")
        );
    }

    public static void getDomain(String domainId) throws ResendException {
        Resend resend = new Resend(API_KEY);

        Domain domain = resend.domains().get(domainId);

        System.out.println("Domain details:");
        System.out.println("  ID: " + domain.getId());
        System.out.println("  Name: " + domain.getName());
        System.out.println("  Status: " + domain.getStatus());
        System.out.println("  Created at: " + domain.getCreatedAt());
    }

    public static void verifyDomain(String domainId) throws ResendException {
        Resend resend = new Resend(API_KEY);

        VerifyDomainResponse response = resend.domains().verify(domainId);

        System.out.println("Domain verification initiated: " + response.getObject());
    }

    public static void removeDomain(String domainId) throws ResendException {
        Resend resend = new Resend(API_KEY);

        resend.domains().remove(domainId);

        System.out.println("Domain removed: " + domainId);
    }
}
