package com.nadola.JobApplication.job.impl;

import com.nadola.JobApplication.job.Job;
import com.nadola.JobApplication.job.JobRepository;
import com.nadola.JobApplication.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

//Used the service annotation at the implementation phase****
@Service
public class JobServiceImpl implements JobService {

    //private List<Job> jobs = new ArrayList<>(); //Switching from array to h2 database

    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    //Declaring the variable that will be used to set Job IDs
    private Long nextId = 1L;
    @Override
    public List<Job> findAll() {
//        return jobs; // the array method
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        //automatically incrementing the ID so that it isnt set everytime and it doesnt take null values
        job.setId(nextId++);
//        jobs.add(job); //the array method
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
//        for (
//                Job job: jobs
//        ) {
//            if (job.getId().equals(id)){
//                return job;
//            }
//
//        }
//        return null;
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
//        Iterator<Job> iterator = jobs.iterator();
//        while (iterator.hasNext()){
//            Job job = iterator.next();
//            if (job.getId().equals(id)){
//                iterator.remove();
//                return true;
//            }
//        }
//        return false;
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {

        Optional<Job> jobOptional = jobRepository.findById(id);
//        for (
//                Job job: jobs
//        ){
            //if  job optional is present you grab the object
            if (jobOptional.isPresent()) {
                Job job = jobOptional.get();
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setLocation(updatedJob.getLocation());
                return true;
            }
        return false;
        }

//    }
}
