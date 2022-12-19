package com.examportal.service;

import java.util.Set;

import com.examportal.model.quiz.Quiz;

public interface QuizService {

	public Quiz addQuiz(Quiz quiz);

	public Quiz updateQuiz(Quiz quiz);

	public Set<Quiz> getAllQuiz();

	public Quiz getQuiz(Long quizId);

	public void deleteQuiz(Long quizId);
}