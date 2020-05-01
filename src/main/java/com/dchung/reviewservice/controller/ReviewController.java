package com.dchung.reviewservice.controller;

import com.dchung.reviewservice.data.Review;
import com.dchung.reviewservice.data.repo.ReviewRepository;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("reviews")
public class ReviewController {

  @Autowired
  private ReviewRepository reviewRepository;

  @GetMapping(value= "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Review> getReview(@PathVariable("id") Long id) {
    System.out.println("Review Service: getReview("+id+")");
    Optional<Review> reviewWrapper = reviewRepository.findById(id);
    if(!reviewWrapper.isPresent()) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity(reviewWrapper.get(),HttpStatus.OK);
  }

  @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getAllReviews() {
    System.out.println("Review Service: getAllReviews()");
    Iterable<Review> reviews = reviewRepository.findAll();
    if( Iterables.size(reviews) == 0 ) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity(reviews,HttpStatus.OK);
  }

  @GetMapping(
      value="restaurant/{restaurant_id}",
      produces= MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getAllReviewsForRestaurant(
      @PathVariable("restaurant_id") Long restaurant_id) {
    System.out.println("Review Service: getAllReviewsForRestaurant("+restaurant_id+")");
    Iterable<Review> reviews = reviewRepository.findByRestaurant(restaurant_id);
    if( Iterables.size(reviews) == 0 ) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity(reviews,HttpStatus.OK);
  }

  @PostMapping(consumes = "application/json")
  public ResponseEntity<Void> insertReReview(
      @RequestBody Review review,
      UriComponentsBuilder builder) {
    System.out.println("Review Service: insertReReview("+review.getRating()+")");
    Review r = reviewRepository.save(review);
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(builder.path("/restaurants/{id}").
        buildAndExpand(r.getReview_id()).toUri());
    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }


}
