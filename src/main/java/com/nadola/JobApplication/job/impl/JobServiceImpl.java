package com.nadola.JobApplication.job.impl;

import com.nadola.JobApplication.job.Job;
import com.nadola.JobApplication.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Used the service annotation at the implementation phase****
@Service
public class JobServiceImpl implements JobService {

    private List<Job> jobs = new ArrayList<>();

    //Declaring the variable that will be used to set Job IDs
    private Long nextId = 1L;
    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        //automatically incrementing the ID so that it isnt set everytime and it doesnt take null values
        job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public Job getJobById(Long id) {
        for (
                Job job: jobs
        ) {
            if (job.getId().equals(id)){
                return job;
            }

        }
        return null;
    }
}
