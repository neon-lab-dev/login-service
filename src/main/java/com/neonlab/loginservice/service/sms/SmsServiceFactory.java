package com.neonlab.loginservice.service.sms;


import com.neonlab.common.dto.SMSProvider;

public class SmsServiceFactory {
    public static SmsService getSmsService(SMSProvider provider) {
        if (SMSProvider.DEFAULT.equals(provider)) {
            return new DefaultSmsService();
        } else if (SMSProvider.INTERAKT.equals(provider)) {
            return new InteractSmsService();
        } else {
            throw new IllegalArgumentException("Invalid SMS provider: " + provider);
        }
    }
}
