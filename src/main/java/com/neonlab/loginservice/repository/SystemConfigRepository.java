package com.neonlab.loginservice.repository;

import com.neonlab.loginservice.entity.SystemConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemConfigRepository extends JpaRepository<SystemConfig, String> {

    SystemConfig findByKey(String key);
}
