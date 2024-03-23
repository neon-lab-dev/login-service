package com.neonlab.loginservice.service.sms;

import com.neonlab.loginservice.model.enums.SMSProvider;
import com.neonlab.loginservice.model.enums.SmsRequest;
import org.springframework.stereotype.Service;

@Service
public interface SmsService {

    public void sendSMS(SmsRequest request);

    public SMSProvider getProvider();

}
