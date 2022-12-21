package com.examportal.service;

import java.util.List;
import java.util.Set;

import com.examportal.model.quiz.Category;
import com.examportal.model.quiz.Quiz;

public interface QuizService {

	public Quiz addQuiz(Quiz quiz);

	public Quiz updateQuiz(Quiz quiz);

	public Set<Quiz> getAllQuiz();

	public Quiz getQuiz(Long quizId);

	public void deleteQuiz(Long quizId);

	public List<Quiz> getQuizzesOfCategory(Category category);

	public List<Quiz> getActiveQuizzes();

	public List<Quiz> getActiveQuizzesOfCategory(Category category);
}