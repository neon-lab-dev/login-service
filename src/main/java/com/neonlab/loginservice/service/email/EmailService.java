package com.neonlab.loginservice.service.email;

import com.neonlab.loginservice.model.enums.EmailProvider;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    public void sendMail();

    public EmailProvider getProvider();
}
