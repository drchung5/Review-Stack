package com.dchung.reviewservice.data.repo;

import com.dchung.reviewservice.data.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository
    extends CrudRepository<Review, Long> {

  @Query("select r from Review r where r.restaurant_id=:restaurant_id")
  public List<Review> findByRestaurant(@Param("restaurant_id")Long restaurant_id);

}
