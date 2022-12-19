package com.examportal.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examportal.model.quiz.Category;
import com.examportal.repository.CategoryRepository;
import com.examportal.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category addCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Set<Category> getCategories() {
		return new LinkedHashSet<>(categoryRepository.findAll());
	}

	@Override
	public Category getCategory(Long categoryId) {
		return categoryRepository.findById(categoryId).get();
	}

	@Override
	public void deleteCategory(Long categoryId) {
		categoryRepository.deleteById(categoryId);
	}
}