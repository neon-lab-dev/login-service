package com.neonlab.loginservice.service.sms;

import com.neonlab.loginservice.model.enums.SMSProvider;
import com.neonlab.loginservice.model.enums.SmsRequest;
import org.springframework.stereotype.Component;

@Component
public class DefaultSmsService implements SmsService {
    @Override
    public void sendSMS(SmsRequest request) {

    }

    @Override
    public SMSProvider getProvider() {
        return SMSProvider.DEFAULT;
    }
}
