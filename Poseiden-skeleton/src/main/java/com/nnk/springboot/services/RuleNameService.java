package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@Service
public class RuleNameService {

	@Autowired
	private RuleNameRepository ruleNameRepository;
	
	
	public Optional<RuleName> getRuleName (final int Id) {
		return ruleNameRepository.findById(Id);
	}
	
	public List<RuleName> getAllRuleName() {
		return ruleNameRepository.findAll();
	}
	
	public void deleteRuleName (final int Id) {
		ruleNameRepository.deleteById(Id);
	}
	
	public RuleName saveRuleName (RuleName ruleName) {
		RuleName savedRuleName = ruleNameRepository.save(ruleName);
		return savedRuleName;
	}
}
