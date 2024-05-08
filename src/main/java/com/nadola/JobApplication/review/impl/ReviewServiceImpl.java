package com.nadola.JobApplication.review.impl;

import com.nadola.JobApplication.company.Company;
import com.nadola.JobApplication.company.CompanyService;
import com.nadola.JobApplication.review.Review;
import com.nadola.JobApplication.review.ReviewRepository;
import com.nadola.JobApplication.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final CompanyService companyService;



    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        //get all reviews then iterate with companyId
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null)
                ;
    }
}
