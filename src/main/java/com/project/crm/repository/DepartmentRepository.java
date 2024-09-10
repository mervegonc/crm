package com.project.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.crm.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
