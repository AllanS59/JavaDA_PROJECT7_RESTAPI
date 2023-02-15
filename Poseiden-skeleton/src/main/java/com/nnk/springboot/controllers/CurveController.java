package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;

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
public class CurveController {

	private static final Logger LOG = LogManager.getLogger(CurveController.class);

	@Autowired
	private CurvePointService curvePointService;

	@RequestMapping("/curvePoint/list")
	public String home(Model model) {
		List<CurvePoint> listCurvePoint = curvePointService.getAllCurvePoint();
		model.addAttribute("listCurvePoint", listCurvePoint);
		return "curvePoint/list";
	}

	@GetMapping("/curvePoint/add")
	public String addBidForm(CurvePoint bid) {
		return "curvePoint/add";
	}

	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
		// If no errors in data provided by user, save data and go back to 'list' page
		if (!result.hasErrors()) {
			curvePointService.saveCurvePoint(curvePoint);
			model.addAttribute("listCurvePoint", curvePointService.getAllCurvePoint());
			LOG.info("CurvePoint created. Id=" + curvePoint.getId());
			return "redirect:/curvePoint/list";
		}
		// else stay on the current page
		LOG.info("Error during CurvePoint creation. CurvePoint is not created");
		return "curvePoint/add";
	}

	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		CurvePoint curvePoint = curvePointService.getCurvePoint(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
		model.addAttribute("curvePoint", curvePoint);
		return "curvePoint/update";
	}

	@PostMapping("/curvePoint/update/{id}")
	public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
			Model model) {
		// If errors in data provided by user, stay on current page
		if (result.hasErrors()) {
			LOG.info("Error during update of CurvePoint (Id=" + id + "). Not updated");
			return "curvePoint/update";
		}
		// If no error, save data into database and go back to 'List' page
		curvePoint.setId(id);
		curvePointService.saveCurvePoint(curvePoint);
		model.addAttribute("listCurvePoint", curvePointService.getAllCurvePoint());
		LOG.info("CurvePoint (Id=" + id + ") is updated");
		return "redirect:/curvePoint/list";
	}

	@GetMapping("/curvePoint/delete/{id}")
	public String deleteBid(@PathVariable("id") Integer id, Model model) {
		curvePointService.deleteCurvePoint(id);
		LOG.info("CurvePoint (Id=" + id + ") is deleted");
		return "redirect:/curvePoint/list";
	}
}
