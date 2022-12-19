package com.examportal.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.model.quiz.Quiz;
import com.examportal.repository.QuizRepository;
import com.examportal.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizRepository quizRepository;

	@Override
	public Quiz addQuiz(Quiz quiz) {
		return quizRepository.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		return quizRepository.save(quiz);
	}

	@Override
	public Set<Quiz> getAllQuiz() {
		return new HashSet<>(quizRepository.findAll());
	}

	@Override
	public Quiz getQuiz(Long quizId) {
		return quizRepository.findById(quizId).get();
	}

	@Override
	public void deleteQuiz(Long quizId) {
		quizRepository.deleteById(quizId);
	}
}