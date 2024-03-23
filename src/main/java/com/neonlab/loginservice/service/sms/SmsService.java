package com.neonlab.loginservice.service.sms;

import com.neonlab.common.dto.SMSProvider;
import com.neonlab.common.dto.SmsRequest;
import org.springframework.stereotype.Service;

@Service
public interface SmsService {

    public void sendSMS(SmsRequest request);

    public SMSProvider getProvider();

}
