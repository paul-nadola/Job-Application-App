package com.nadola.JobApplication.job;

import java.util.List;

//to promote loose coupling and modularity change from class to interface
//Define the methods
public interface JobService {
    List<Job> findAll();
    void createJob(Job job);

    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
