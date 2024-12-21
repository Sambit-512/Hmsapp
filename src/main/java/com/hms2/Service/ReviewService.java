package com.hms2.Service;

import com.hms2.Entity.Property;
import com.hms2.Entity.Review;
import com.hms2.Entity.User;
import com.hms2.Repositry.PropertyRepository;
import com.hms2.Repositry.ReviewRepository;

import com.hms2.payload.ReviewDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {


  private ReviewRepository reviewRepository;
    private PropertyRepository propertyRepository;
    private ModelMapper modelMapper;
    public ReviewService(ReviewRepository reviewRepository, PropertyRepository propertyRepository, ModelMapper modelMapper) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;

        this.modelMapper = modelMapper;
    }

   Review mapToEntity(ReviewDto reviewDto){
       Review review = modelMapper.map(reviewDto, Review.class);
       return review;
   }
       ReviewDto mapToDto(Review review){
           ReviewDto reviewDto = modelMapper.map(review, ReviewDto.class);
           return reviewDto;
       }




    public ReviewDto createReview(ReviewDto reviewDto, long propertyId, User user) {

        Property property = propertyRepository.findById(propertyId).orElseThrow
                (() -> new RuntimeException("Could not find property :" + propertyId));

        Review reviewStatus = reviewRepository.findByPropertyAndUser(property, user);
        if (reviewStatus != null) {
            Review review = mapToEntity(reviewDto);
            review.setProperty(property);
            review.setUser(user);
            Review save = reviewRepository.save(review);
            ReviewDto reviewDto1 = mapToDto(save);
            return reviewDto1;
        }
        return null;
    }
    public List<Review> viewMyReviews(User user) {
        List<Review> opUser = reviewRepository.findByUser(user);
        List<Review> review = opUser.stream().collect(Collectors.toList());
        return review;
    }
}
