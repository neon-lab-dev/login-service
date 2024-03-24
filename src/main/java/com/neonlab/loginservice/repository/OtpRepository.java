package com.neonlab.loginservice.repository;

import com.neonlab.loginservice.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
@Repository
public interface OtpRepository extends JpaRepository<Otp, String> {

    Otp findFirstByCommunicatedToAndStatusOrderByCreatedAtDesc(String communicatedTo, String status);
=======
import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, String> {

    Optional<Otp> findFirstByCommunicatedToAndStatusOrderByCreatedAtDesc(String communicatedTo, String status);

    Optional<Otp> findFirstByCommunicatedToAndStatusAndPurposeOrderByCreatedAtDesc(String communicatedTo, String status, String purpose);
>>>>>>> stash

}
