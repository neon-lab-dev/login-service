package com.neonlab.loginservice.service;

import com.neonlab.common.annotations.Loggable;
import com.neonlab.common.constants.GlobalConstants;
import com.neonlab.common.dto.SignUpRequest;
import com.neonlab.common.dto.UserDto;
import com.neonlab.common.entities.AuthUser;
import com.neonlab.common.entities.User;
import com.neonlab.common.enums.VerificationMode;
import com.neonlab.common.expectations.InvalidInputException;
import com.neonlab.common.expectations.ServerException;
import com.neonlab.common.services.AuthUserService;
import com.neonlab.common.services.UserService;
import com.neonlab.common.utilities.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.neonlab.common.constants.GlobalConstants.ERROR_OCCURED;

@Slf4j
@Service
@Loggable
@RequiredArgsConstructor
public class SignupService {

    private final UserService userService;
    private final AuthUserService authUserService;

    public UserDto registerUser(SignUpRequest request) throws ServerException {
        try {
            var auth = authUserService.fetchById(request.getAuthId());
            var user = userService.createUser(request);
            auth.setUserId(user.getId());
            authUserService.save(auth);
            return UserDto.parse(user);
        } catch (InvalidInputException e) {
            log.warn(ERROR_OCCURED, e.getMessage());
            throw new ServerException(String.format("The user %s is not authenticated or the authId is incorrect.", request.getName()));
        }
    }

    public boolean isAlreadySignedUp(SignUpRequest request){
        try{
            var user = userService.fetchByPrimaryPhoneNo(request.getPrimaryPhoneNo());
            return true;
        } catch (InvalidInputException e) {
            log.warn(ERROR_OCCURED, e.getMessage());
            return false;
        }
    }


}
