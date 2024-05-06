package com.example.companyms.companypart.service;

import com.example.companyms.companypart.exception.InvalidIdException;
import com.example.companyms.companypart.exception.NothingToUpdateException;
import com.example.companyms.companypart.model.Company;
import com.example.companyms.companypart.repo.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public ResponseEntity<List<Company>> findAll() {
        return new ResponseEntity<>(companyRepository.findAll(), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Company> createCompany(Company company) {
        return new ResponseEntity<>(companyRepository.save(company), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Company> findById(Long id) {
        return new ResponseEntity<>(companyRepository.findById(id).orElseThrow(), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Company> updateCompany(Company company, Long id) {
        if (!Company.isEmpty(company)) {
            Company companyFromDb = companyRepository.findById(id)
                    .orElseThrow(() -> new InvalidIdException("Company with id = " + id + " doesn't exist."));
            // Check if the company name is not null before trimming and set company name only if it is not empty
            Optional.ofNullable(company.getName()).map(String::trim)
                    .filter(name -> !name.isEmpty())
                    .ifPresent(companyFromDb::setName);

            Optional.ofNullable(company.getDescription()).map(String::trim)
                    .filter(desc -> !desc.isEmpty())
                    .ifPresent(companyFromDb::setDescription);

            return new ResponseEntity<>(companyRepository.save(companyFromDb), HttpStatus.OK);
        } else
            throw new NothingToUpdateException("There is nothing to update.....");
    }

    @Override
    public ResponseEntity<String> deleteCompany(Long id) {
        Optional<Company> optionalJob = companyRepository.findById(id);
        if (optionalJob.isPresent()) {
            Company company = optionalJob.get();
            companyRepository.delete(company);
            return new ResponseEntity<>("job with id: " + id + " is deleted.", HttpStatus.OK);
        }
        return new ResponseEntity<>("job with id: " + id + " doesn't exists.", HttpStatus.BAD_REQUEST);
    }

    @Override
    public Boolean existsById(Long id) {
        return companyRepository.existsById(id);
    }
}
