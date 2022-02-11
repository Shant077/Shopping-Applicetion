package com.example.demo.repository;

import com.example.demo.model.CommentsAndRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsAndRateRepository extends JpaRepository<CommentsAndRate, Long> {
    List<CommentsAndRate> findFeedbackByProductsId(Long id);
}
