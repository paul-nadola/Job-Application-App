package com.nadola.JobApplication.review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companyId);
    void addReview(Long companyId, Review review);
}
