package com.hms2.Repositry;

import com.hms2.Entity.Property;
import com.hms2.Entity.Review;
import com.hms2.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {


  List<Review> findByUser(User user);
  Review findByPropertyAndUser(Property property, User user);
  List<Review> findByProperty(Property property);
}