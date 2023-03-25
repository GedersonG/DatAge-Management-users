package com.junit.testing.Repository;

import com.junit.testing.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
    abstract Optional<EmployeeEntity> findByEmail(String email);
}
