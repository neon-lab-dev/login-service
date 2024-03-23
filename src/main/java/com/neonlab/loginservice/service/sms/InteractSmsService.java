package com.neonlab.loginservice.service.sms;


import com.neonlab.common.dto.SMSProvider;
import com.neonlab.common.dto.SmsRequest;

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
