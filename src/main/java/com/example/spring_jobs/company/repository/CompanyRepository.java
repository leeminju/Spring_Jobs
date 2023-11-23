package com.example.spring_jobs.company.repository;


import com.example.spring_jobs.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    Optional<Company> findByUserLoginId(String loginId);
    Optional<Company> findByCompanyName(String companyName);
}
