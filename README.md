# Resend with Java

This example shows how to use Resend with Java.

## Prerequisites

To get the most out of this guide, you’ll need to:

* [Create an API key](https://resend.com/api-keys)
* [Verify your domain](https://resend.com/domains)

## Instructions

1. Add the dependecy to your project:

```
implementation 'com.resend:resend-java:1.0.0'
```

Maven:

```
<dependency>
    <groupId>com.resend</groupId>
    <artifactId>resend-java</artifactId>
    <version>1.0.0</version>
</dependency>
```

2. Set the API Key via environment variable or programmatically:

Environment variables:
```sh
export RESEND_API_KEY="re_123456789"
```

Instantiate the ResendEmails with empty constructor:
```
ResendEmails emailClient = new ResendEmails();
```

Programmatically: 

```
String apiKey = "re_123";

AuthenticationProvider provider = new AuthenticationProviderStandard(apiKey);
ResendEmails emailClient = new ResendEmails(provider);
```
3. Execute the project

## License

MIT License