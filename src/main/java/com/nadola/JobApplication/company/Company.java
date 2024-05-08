package com.nadola.JobApplication.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nadola.JobApplication.job.Job;
import com.nadola.JobApplication.review.Review;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @JsonIgnore
    @OneToMany (mappedBy="company") //specify that the relationship is maintained by the said column in the job class to get rid of the relationship table
    //one company has many jobs
    private List<Job> jobs;

    //reviews mapping
    @OneToMany(mappedBy = "company")
    private List<Review> reviews;

    //constructor for the JPA
    public Company() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
