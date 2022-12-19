package com.examportal.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examportal.model.quiz.Question;
import com.examportal.model.quiz.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	Set<Question> findByQuiz(Quiz quiz);
}