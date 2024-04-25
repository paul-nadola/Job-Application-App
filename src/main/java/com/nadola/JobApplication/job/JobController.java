package com.nadola.JobApplication.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//httpStatus a class enumerating all possible HTTP responses; standardization of status codes and better control of responses, error handling and mapping
@RestController
public class JobController {


    //loosely coupled, injecting the service using a constructor
    private JobService jobService;
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public List<Job> findAll(){
        return jobService.findAll();
    }

    @PostMapping("/jobs")
    public String createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return "Job added successfully";
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        if (job != null) {
            //create a response with job obj and its status code
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        //HANDLING A SEARCH FOR A JOB NOT FOUND
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
