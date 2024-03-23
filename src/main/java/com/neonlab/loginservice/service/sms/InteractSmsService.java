package com.neonlab.loginservice.service.sms;

import com.neonlab.loginservice.model.enums.SMSProvider;
import com.neonlab.loginservice.model.enums.SmsRequest;

public class InteractSmsService implements SmsService{
    @Override
    public void sendSMS(SmsRequest request) {
        // interact specific methods
    }

    @Override
    public SMSProvider getProvider() {
        return SMSProvider.INTERAKT;
    }
}
