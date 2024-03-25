package com.neonlab.loginservice.apis;

import com.neonlab.common.annotations.Loggable;
import com.neonlab.common.constants.GlobalConstants;
import com.neonlab.common.dto.ApiOutput;
import com.neonlab.common.dto.LoginRequest;
import com.neonlab.common.expectations.InvalidInputException;
import com.neonlab.common.services.AuthUserService;
import com.neonlab.common.services.UserService;
import com.neonlab.common.utilities.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

import static com.neonlab.common.constants.GlobalConstants.ERROR_OCCURRED;
import static com.neonlab.common.constants.GlobalConstants.TOKEN;

@Slf4j
@Service
@Loggable
@RequiredArgsConstructor
public class LoginApi {

    private final UserService userService;
    private final AuthUserService authUserService;

    public ApiOutput<?> process(LoginRequest request) throws InvalidInputException {
        validate(request);
        try {
            var user = userService.fetchEitherByPrimaryPhoneOrEmail(request.getUsername());
            var auth = authUserService.fetchLatestByUserId(user.getId());
            return new ApiOutput<>(HttpStatus.OK.value(), null, Map.of(TOKEN, auth.getJWTtoken()));
        } catch (InvalidInputException e) {
            log.warn(ERROR_OCCURRED, e.getMessage());
            return new ApiOutput<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
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
