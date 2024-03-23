package com.neonlab.loginservice.service.sms;

import com.neonlab.common.dto.SMSProvider;
import com.neonlab.common.dto.SmsRequest;
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
