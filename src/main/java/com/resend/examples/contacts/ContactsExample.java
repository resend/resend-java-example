package com.resend.examples.contacts;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.contacts.model.Contact;
import com.resend.services.contacts.model.CreateContactOptions;
import com.resend.services.contacts.model.CreateContactResponseSuccess;
import com.resend.services.contacts.model.ListContactsResponseSuccess;
import com.resend.services.contacts.model.RemoveContactResponseSuccess;
import com.resend.services.contacts.model.UpdateContactOptions;
import com.resend.services.contacts.model.UpdateContactResponseSuccess;

public class ContactsExample {

    private static final String API_KEY = "re_123456789";

    public static void main(String[] args) throws ResendException {
        createContact();
        listContacts();
        getContact("e169aa45-1ecf-4183-9955-b1499d5701d3");
        updateContact("e169aa45-1ecf-4183-9955-b1499d5701d3");
        removeContact("520784e2-887d-4c25-b53c-4ad46ad38100");
    }

    public static void createContact() throws ResendException {
        Resend resend = new Resend(API_KEY);

        CreateContactOptions options = CreateContactOptions.builder()
                .email("steve.wozniak@gmail.com")
                .firstName("Steve")
                .lastName("Wozniak")
                .unsubscribed(false)
                .build();

        CreateContactResponseSuccess response = resend.contacts().create(options);

        System.out.println("Contact created:");
        System.out.println("  ID: " + response.getId());
    }

    public static void listContacts() throws ResendException {
        Resend resend = new Resend(API_KEY);

        ListContactsResponseSuccess response = resend.contacts().list();

        System.out.println("Contacts:");
        response.getData().forEach(contact ->
            System.out.println("  - " + contact.getEmail() + " (" + contact.getFirstName() + " " + contact.getLastName() + ")")
        );
    }

    public static void getContact(String contactId) throws ResendException {
        Resend resend = new Resend(API_KEY);

        Contact contact = resend.contacts().get(contactId);

        System.out.println("Contact details:");
        System.out.println("  ID: " + contact.getId());
        System.out.println("  Email: " + contact.getEmail());
        System.out.println("  First Name: " + contact.getFirstName());
        System.out.println("  Last Name: " + contact.getLastName());
        System.out.println("  Unsubscribed: " + contact.getUnsubscribed());
    }

    public static void updateContact(String contactId) throws ResendException {
        Resend resend = new Resend(API_KEY);

        UpdateContactOptions options = UpdateContactOptions.builder()
                .id(contactId)
                .firstName("Stephen")
                .unsubscribed(true)
                .build();

        UpdateContactResponseSuccess response = resend.contacts().update(options);

        System.out.println("Contact updated: " + response.getId());
    }

    public static void removeContact(String contactId) throws ResendException {
        Resend resend = new Resend(API_KEY);

        RemoveContactResponseSuccess response = resend.contacts().remove(contactId);

        System.out.println("Contact removed: " + response.getId());
    }
}
