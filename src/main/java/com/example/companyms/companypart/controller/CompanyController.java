package com.example.companyms.companypart.controller;


import com.example.companyms.companypart.model.Company;
import com.example.companyms.companypart.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/company")
@Controller
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Company> findById(@PathVariable long id) {
        return companyService.findById(id);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Company>> findAll() {
        return companyService.findAll();
    }

    @PostMapping("/post")
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        return companyService.createCompany(company);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company, @PathVariable Long id) {
        return companyService.updateCompany(company, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        return companyService.deleteCompany(id);
    }

}
