package com.nadola.JobApplication.company.impl;

import com.nadola.JobApplication.company.Company;
import com.nadola.JobApplication.company.CompanyRepository;
import com.nadola.JobApplication.company.CompanyService;
import com.nadola.JobApplication.job.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {

        Optional<Company> companyOptional = companyRepository.findById(id);

        //if  job optional is present you grab the object
        if (companyOptional.isPresent()) {
            Company updatedCompany = companyOptional.get();
            //setting the updated values by getting them from the values that come with the request body
            updatedCompany.setName(company.getName());
            updatedCompany.setDescription(company.getDescription());
            updatedCompany.setJobs(company.getJobs());

            //save the job
            companyRepository.save(updatedCompany);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        //checking if company exists before deletion
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }


}
