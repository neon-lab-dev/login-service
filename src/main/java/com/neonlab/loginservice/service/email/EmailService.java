package com.neonlab.loginservice.service.email;

import com.neonlab.common.enums.EmailProvider;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    public void sendMail();

    public EmailProvider getProvider();
}
