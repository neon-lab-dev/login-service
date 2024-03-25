package com.neonlab.loginservice.apis;

import com.neonlab.common.annotations.Loggable;
import com.neonlab.common.constants.GlobalConstants;
import com.neonlab.common.dto.ApiOutput;
import com.neonlab.common.dto.LoginRequest;
import com.neonlab.common.enums.AuthPurpose;
import com.neonlab.common.enums.OtpStatus;
import com.neonlab.common.expectations.InvalidInputException;
import com.neonlab.common.expectations.ServerException;
import com.neonlab.common.services.UserService;
import com.neonlab.common.utilities.StringUtil;
import com.neonlab.loginservice.service.OtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@Loggable
@RequiredArgsConstructor
public class LoginStatusApi {

    private final OtpService otpService;
    private final UserService userService;

    private static final String ALREADY_LOGGED_IN = "User has already logged in.";
    private static final String LOGGED_IN = "loggedIn";

    public ApiOutput<?> process(LoginRequest request) throws InvalidInputException {
        validate(request);
        var user = userService.fetchEitherByPrimaryPhoneOrEmail(request.getUsername());
        try {
            otpService.fetchOtpByCommunicatedToAndStatusAndPurpose(
                    user.getPrimaryPhoneNo(), OtpStatus.VERIFIED, AuthPurpose.LOGIN.name()
            );
            return new ApiOutput<>(HttpStatus.OK.value(), ALREADY_LOGGED_IN, Map.of(LOGGED_IN, true));
        } catch (ServerException e) {
            log.warn(GlobalConstants.ERROR_OCCURRED, e.getMessage());
            return new ApiOutput<>(HttpStatus.BAD_REQUEST.value(), null, Map.of(LOGGED_IN, false));
        }
    }

    private void validate(LoginRequest request) throws InvalidInputException {
        if (StringUtil.isNullOrEmpty(request.getUsername())) {
            throw new InvalidInputException("Username is Required");
        }
        if (Objects.isNull(request.getVerified())) {
            throw new InvalidInputException("Verified is Required");
        }
    }

}
