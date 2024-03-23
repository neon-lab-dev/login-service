package com.neonlab.loginservice.repository;

import com.neonlab.loginservice.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<Otp, String> {

    Otp findFirstByCommunicatedToAndStatusOrderByCreatedAt(String communicatedTo, String status);

}
