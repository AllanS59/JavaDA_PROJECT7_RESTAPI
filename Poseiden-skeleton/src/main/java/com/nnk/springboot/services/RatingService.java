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
	
	/**
	 * Get a Rating object from database by ID
	 * @param Id the Id of the object to find
	 * @return Optional<Rating> the rating with correct Id, if any.
	 */
	public Optional<Rating> getRating (final int Id) {
		return ratingRepository.findById(Id);
	}
	
	/**
	 * Get all Rating objects from database
	 * @return List<Rating> list of all entities found in database
	 */
	public List<Rating> getAllRating() {
		return ratingRepository.findAll();
	}
	
	/**
	 * Delete a Rating object from database by ID
	 * @param Id the Id of the object to delete
	 */
	public void deleteRating (final int Id) {
		ratingRepository.deleteById(Id);
	}
	
	/**
	 * Save or update a Rating object into database.
	 * @param rating the Rating to save or update into database
	 * @return rating the saved Rating
	 */
	public Rating saveRating (Rating rating) {
		Rating savedRating = ratingRepository.save(rating);
		return savedRating;
	}
}
