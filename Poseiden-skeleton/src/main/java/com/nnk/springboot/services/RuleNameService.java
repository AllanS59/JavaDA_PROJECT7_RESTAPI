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
	
	/**
	 * Get a RuleName object from database by ID
	 * @param Id the Id of the object to find
	 * @return Optional<RuleName> the bidList with correct Id, if any.
	 */
	public Optional<RuleName> getRuleName (final int Id) {
		return ruleNameRepository.findById(Id);
	}
	
	/**
	 * Get all RuleName objects from database
	 * @return List<RuleName> list of all entities found in database
	 */
	public List<RuleName> getAllRuleName() {
		return ruleNameRepository.findAll();
	}
	
	/**
	 * Delete a RuleName object from database by ID
	 * @param Id the Id of the object to delete
	 */
	public void deleteRuleName (final int Id) {
		ruleNameRepository.deleteById(Id);
	}
	
	/**
	 * Save or update a RuleName object into database.
	 * @param ruleName the RuleName to save or update into database
	 * @return ruleName the saved RuleName
	 */
	public RuleName saveRuleName (RuleName ruleName) {
		RuleName savedRuleName = ruleNameRepository.save(ruleName);
		return savedRuleName;
	}
}
