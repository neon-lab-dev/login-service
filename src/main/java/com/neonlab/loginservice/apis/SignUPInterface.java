package com.neonlab.loginservice.apis;

import com.neonlab.loginservice.dto.Authenticationdto;
import com.neonlab.loginservice.dto.VerificationReqDto;

public interface SignUPInterface {
    String register(VerificationReqDto verificationReqDto);

    String authenticate(Authenticationdto authenticationdto);


}
