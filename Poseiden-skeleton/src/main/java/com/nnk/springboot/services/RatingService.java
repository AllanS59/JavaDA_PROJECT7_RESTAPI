package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@Service
public class RatingService {

	@Autowired
	private RatingRepository ratingRepository;
	
	
	public Optional<Rating> getRating (final int Id) {
		return ratingRepository.findById(Id);
	}
	
	public List<Rating> getAllRating() {
		return ratingRepository.findAll();
	}
	
	public void deleteRating (final int Id) {
		ratingRepository.deleteById(Id);
	}
	
	public Rating saveRating (Rating rating) {
		Rating savedRating = ratingRepository.save(rating);
		return savedRating;
	}
}
