/*package com.neonlab.loginservice.util;

import com.mysql.cj.protocol.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhoneNoUtil {

    public void sendOtpMobile(String phoneNumber, String otp) {
        // Initialize your mobile messaging service client (e.g., Twilio, Nexmo)
        // Replace ACCOUNT_SID, AUTH_TOKEN, and FROM_PHONE_NUMBER with your actual credentials
        Interakt.init("ACCOUNT_SID", "AUTH_TOKEN");

        // Replace TO_PHONE_NUMBER with the recipient's phone number
        String toPhoneNumber = "phoneNo"; // Example: "+1234567890"

        // Build the message body with OTP and verification link
        String messageBody = String.format(
                "Your OTP is %s. Click link to verify: http://example.com/verify-account?otp=%s",
                otp, otp
        );

        // Send the message using the mobile messaging service
        Message message = Message.creator(
                        new PhoneNumber(toPhoneNumber),
                        new PhoneNumber("FROM_PHONE_NUMBER"), // Replace with your Twilio/serviceProvider phone number
                        messageBody)
                .create();

        // Optional: You can log or handle the message creation response
        System.out.println("Message SID: " + message.getSid());
    }

}*/
