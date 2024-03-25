package com.neonlab.loginservice.service.email;


import com.neonlab.common.enums.EmailProvider;

public class EmailServiceFactory {

    public static EmailService getEmailService(EmailProvider provider) {
        if (EmailProvider.DEFAULT.equals(provider)) {
            return new DefaultEmailService();
        } else {
            throw new IllegalArgumentException("Invalid email provider: " + provider);
        }
    }
}
