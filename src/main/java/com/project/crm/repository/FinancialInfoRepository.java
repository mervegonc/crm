package com.project.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.crm.entity.FinancialInfo;

@Repository
public interface FinancialInfoRepository extends JpaRepository<FinancialInfo, Integer> {
}