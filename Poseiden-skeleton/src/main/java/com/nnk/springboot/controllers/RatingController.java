package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
public class RatingController {

	private static final Logger LOG = LogManager.getLogger(RatingController.class);

	@Autowired
	private RatingService ratingService;

	@RequestMapping("/rating/list")
	public String home(Model model) {
		List<Rating> listRating = ratingService.getAllRating();
		model.addAttribute("listRating", listRating);
		return "rating/list";
	}

	@GetMapping("/rating/add")
	public String addRatingForm(Rating rating) {
		return "rating/add";
	}

	@PostMapping("/rating/validate")
	public String validate(@Valid Rating rating, BindingResult result, Model model) {
		// If no errors in data provided by user, save data and go back to 'list' page
		if (!result.hasErrors()) {
			ratingService.saveRating(rating);
			model.addAttribute("listRating", ratingService.getAllRating());
			LOG.info("Rating created. Id=" + rating.getId());
			return "redirect:/rating/list";
		}
		// else stay on the current page
		LOG.info("Error during Rating creation. Rating is not created");
		return "rating/add";
	}

	@GetMapping("/rating/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Rating rating = ratingService.getRating(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
		model.addAttribute("rating", rating);
		return "rating/update";
	}

	@PostMapping("/rating/update/{id}")
	public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result,
			Model model) {
		// If errors in data provided by user, stay on current page
		if (result.hasErrors()) {
			LOG.info("Error during update of Rating (Id=" + id + "). Not updated");
			return "rating/update";
		}
		// If no error, save data into database and go back to 'List' page
		rating.setId(id);
		ratingService.saveRating(rating);
		model.addAttribute("listRating", ratingService.getAllRating());
		LOG.info("Rating (Id=" + id + ") is updated");
		return "redirect:/rating/list";
	}

	@GetMapping("/rating/delete/{id}")
	public String deleteRating(@PathVariable("id") Integer id, Model model) {
		ratingService.deleteRating(id);
		LOG.info("Rating (Id=" + id + ") is deleted");
		return "redirect:/rating/list";
	}
}
