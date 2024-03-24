package com.neonlab.loginservice.apis;

import com.neonlab.common.dto.AuthUserDto;
import com.neonlab.common.dto.VerificationReqDto;
import com.neonlab.common.utilities.StringUtil;
import com.neonlab.common.utilities.UUIDEncryptor;
import com.neonlab.loginservice.entity.AuthUser;
import com.neonlab.loginservice.entity.User;
import com.neonlab.loginservice.repository.AuthUserRepository;
import com.neonlab.loginservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class SignUpApi {

    private final UserRepository userRepository;
    private final AuthUserRepository authUserRepository;

    public AuthUserDto process(VerificationReqDto verificationReqDto){
        User user = new User();
        user.setCreatedAt(new Date());
        user.setModifiedAt(new Date());
        user.setEmail(verificationReqDto.getEmail());
        user.setPrimaryPhoneNo(verificationReqDto.getPhoneNo());
        // check for primary and secondary in login flow
        user = userRepository.save(user);


        AuthUser authUser = new AuthUser();
        authUser.setAuthType("OTP");
        authUser.setUserId(user.getId());
        authUser.setCreatedAt(new Date());
        authUser.setModifiedAt(new Date());
        if (!StringUtil.isNullOrEmpty(verificationReqDto.getPhoneNo())) {
            authUser.setPhoneVerified(true);
            authUser.setUserName(verificationReqDto.getPhoneNo());
        }
        if (!StringUtil.isNullOrEmpty(verificationReqDto.getEmail())) {
            authUser.setEmailVerified(true);
            authUser.setUserName(verificationReqDto.getEmail());
        }
        String jwt = "";
        try {
            jwt = UUIDEncryptor.encryptUUID(user.getId());
        } catch (Exception e) {
            log.error("Error while encryting uuid");
            throw new RuntimeException(e.getMessage());
        }
        authUser.setJWTtoken(jwt);
        authUser = authUserRepository.save(authUser);
        AuthUserDto authUserDto = new AuthUserDto();
        BeanUtils.copyProperties(authUser, authUserDto);
        return authUserDto;
    }
}
