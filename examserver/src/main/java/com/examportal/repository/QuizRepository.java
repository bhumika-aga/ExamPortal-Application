package com.examportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.model.quiz.Category;
import com.examportal.model.quiz.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
	public List<Quiz> findByCategory(Category category);

	public List<Quiz> findByActive(Boolean b);

	public List<Quiz> findByCategoryAndActive(Category category, Boolean b);
}