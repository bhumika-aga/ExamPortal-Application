package com.examportal.service;

import java.util.Set;

import com.examportal.model.quiz.Question;
import com.examportal.model.quiz.Quiz;

public interface QuestionService {

	public Question addQuestion(Question question);

	public Question updateQuestion(Question question);

	public Set<Question> getAllQuestions();

	public Question getQuestion(Long questionId);

	public void deleteQuestion(Long questionId);

	public Set<Question> getQuestionofQuiz(Quiz quiz);

	public Question get(Long questionId);
}