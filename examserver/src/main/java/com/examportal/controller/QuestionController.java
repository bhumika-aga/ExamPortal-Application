package com.examportal.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examportal.model.quiz.Question;
import com.examportal.model.quiz.Quiz;
import com.examportal.service.QuestionService;
import com.examportal.service.QuizService;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private QuizService quizService;

	@PostMapping("/")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
		return ResponseEntity.ok(questionService.addQuestion(question));
	}

	@GetMapping("/{questionId}")
	public Question getQuestion(@PathVariable Long questionId) {
		return questionService.getQuestion(questionId);
	}

	@GetMapping("/")
	public ResponseEntity<?> getAllQuestions() {
		return ResponseEntity.ok(questionService.getAllQuestions());
	}

	@PutMapping("/")
	public Question updateQuestion(@RequestBody Question question) {
		return questionService.updateQuestion(question);
	}

	@DeleteMapping("/{questionId}")
	public void deleteQuestion(@PathVariable Long questionId) {
		questionService.deleteQuestion(questionId);
	}

	@GetMapping("/quiz/{quizId}")
	public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable Long quizId) {
		Quiz quiz = quizService.getQuiz(quizId);
		Set<Question> questions = quiz.getQuestions();
		List<Question> list = new ArrayList<>(questions);
		if (list.size() > Integer.parseInt(quiz.getNoOfQUestions())) {
			list = list.subList(0, Integer.parseInt(quiz.getNoOfQUestions() + 1));
		}

		list.forEach((q) -> {
			q.setAnswer("");
		});

		Collections.shuffle(list);
		return ResponseEntity.ok(list);
	}

	@GetMapping("/quiz/all/{quizId}")
	public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable Long quizId) {
		Quiz quiz = new Quiz();
		quiz.setqId(quizId);
		Set<Question> questionsOfQuiz = questionService.getQuestionofQuiz(quiz);
		return ResponseEntity.ok(questionsOfQuiz);
	}

	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evaluateQuiz(@RequestBody List<Question> questions) {
		System.out.println(questions);
		double marksGot = 0;
		int correctAnswers = 0;
		int attempted = 0;
		for (Question q : questions) {
			Question question = questionService.get(q.getQuestionId());
			if (question.getAnswer().equals(q.getGivenAnswer())) {
				correctAnswers++;
				double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks()) / questions.size();
				marksGot += marksSingle;
			}

			if (q.getGivenAnswer() != null) {
				attempted++;
			}
		}

		Map<String, Object> map = Map.of("marksGot", marksGot, "correctAnswers", correctAnswers, "attempted",
				attempted);
		return ResponseEntity.ok(map);
	}
}