package com.neonlab.loginservice.apis;

import com.neonlab.loginservice.dto.Authenticationdto;
import com.neonlab.loginservice.dto.VerificationReqDto;
import com.neonlab.loginservice.util.EmailUtil;
import com.neonlab.loginservice.util.OtpUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SignupApi implements SignUPInterface {

    @Autowired
    private EmailUtil emailUtil;
    //@Autowired
    // private PhoneNoUtil phoneNoUtil;
    @Autowired
    private OtpUtil otpUtil;

    //need to autowired the repo as well

    @Transactional
    public String register(VerificationReqDto verificationReqDto) {

        String otp = otpUtil.generateOtp(); //will generate otp using otputil
        try {
            // Send OTP to email
            emailUtil.sendOtpEmail(verificationReqDto.getEmail(), otp);
            // Send OTP to phone number
            //phoneNoUtil.sendOtpMobile(verificationReqDto.getPhoneNo(), otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send OTP, please try again");
        }
        /*
            SecurityProperties.User user = new SecurityProperties.User(); // have some queries
            user.setName(VerificationReqDto.getName());
            user.setEmail(VerificationReqDto.getEmail());
            user.setPhoneNumber(VerificationReqDto.getPhoneNumber()); // Set phone number
            user.setPassword(VerificationReqDto.getPassword());
            user.setOtp(otp);
            user.setOtpGeneratedTime(LocalDateTime.now());
            // userRepository.save(user);
            return "User registration successful";*/
        return null;
    }

    @Override
    public String authenticate(Authenticationdto authenticationdto) {
        return null;
    }
}



