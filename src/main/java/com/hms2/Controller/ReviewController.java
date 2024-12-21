package com.hms2.Controller;

import com.hms2.Entity.Review;
import com.hms2.Entity.User;
import com.hms2.Service.ReviewService;

import com.hms2.payload.ReviewDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

private ReviewService reviewService;


    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    //Add review property
    //http://localhost:8080/api/v1/review?propertyId

    @PostMapping
    public ResponseEntity<ReviewDto> createReview
            (@RequestBody ReviewDto reviewDto,
             @RequestParam long propertyId,
             @AuthenticationPrincipal User user) {
        ReviewDto reviewDto1 = reviewService.createReview(reviewDto, propertyId, user);
          return  new ResponseEntity<>(reviewDto1,HttpStatus.CREATED);

    }
    //View all reviews for a particular user
   //http://localhost:8080/api/v1/review/user/review

    @GetMapping("/user/review")
    public  ResponseEntity<List<Review>>viewMyReviews(
            @AuthenticationPrincipal User user
    ){
        List<Review> review = reviewService.viewMyReviews(user);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

}
