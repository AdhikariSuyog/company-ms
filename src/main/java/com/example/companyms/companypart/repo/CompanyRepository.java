package com.example.companyms.companypart.repo;


import com.example.companyms.companypart.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
