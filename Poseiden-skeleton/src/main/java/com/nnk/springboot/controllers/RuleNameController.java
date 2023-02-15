package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;

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
public class RuleNameController {

	private static final Logger LOG = LogManager.getLogger(RuleNameController.class);

	@Autowired
	private RuleNameService ruleNameService;

	@RequestMapping("/ruleName/list")
	public String home(Model model) {
		List<RuleName> listRuleName = ruleNameService.getAllRuleName();
		model.addAttribute("listRuleName", listRuleName);
		return "ruleName/list";
	}

	@GetMapping("/ruleName/add")
	public String addRuleForm(RuleName bid) {
		return "ruleName/add";
	}

	@PostMapping("/ruleName/validate")
	public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
		// If no errors in data provided by user, save data and go back to 'list' page
		if (!result.hasErrors()) {
			ruleNameService.saveRuleName(ruleName);
			model.addAttribute("listRuleName", ruleNameService.getAllRuleName());
			LOG.info("RuleName created. Id=" + ruleName.getId());
			return "redirect:/ruleName/list";
		}
		// else stay on the current page
		LOG.info("Error during RuleName creation. RuleName is not created");
		return "ruleName/add";
	}

	@GetMapping("/ruleName/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		RuleName ruleName = ruleNameService.getRuleName(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
		model.addAttribute("ruleName", ruleName);
		return "ruleName/update";
	}

	@PostMapping("/ruleName/update/{id}")
	public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName, BindingResult result,
			Model model) {
		// If errors in data provided by user, stay on current page
		if (result.hasErrors()) {
			LOG.info("Error during update of RuleName (Id=" + id + "). Not updated");
			return "ruleName/update";
		}
		// If no error, save data into database and go back to 'List' page
		ruleName.setId(id);
		ruleNameService.saveRuleName(ruleName);
		model.addAttribute("listRuleName", ruleNameService.getAllRuleName());
		LOG.info("RuleName (Id=" + id + ") is updated");
		return "redirect:/ruleName/list";
	}

	@GetMapping("/ruleName/delete/{id}")
	public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
		ruleNameService.deleteRuleName(id);
		LOG.info("RuleName (Id=" + id + ") is deleted");
		return "redirect:/ruleName/list";
	}
}
