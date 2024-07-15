package com.project.system.admin.repository;

import com.project.system.admin.model.DeviceApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceApplicationRepository extends JpaRepository<DeviceApplication, Long> {
}
