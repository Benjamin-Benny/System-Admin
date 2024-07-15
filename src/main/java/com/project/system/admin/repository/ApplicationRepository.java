package com.project.system.admin.repository;

import com.project.system.admin.model.Applications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Applications, Long> {
    Applications findByName(String name);
}
