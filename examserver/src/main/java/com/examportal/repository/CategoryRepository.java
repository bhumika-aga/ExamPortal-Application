package com.examportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examportal.model.quiz.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}