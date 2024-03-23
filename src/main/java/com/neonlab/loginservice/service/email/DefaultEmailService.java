package com.neonlab.loginservice.service.email;

import com.neonlab.common.dto.EmailProvider;
import org.springframework.stereotype.Component;

@Component
public class DefaultEmailService implements EmailService {
    @Override
    public void sendMail() {

    }

    @Override
    public EmailProvider getProvider() {
        return EmailProvider.DEFAULT;
    }

}
