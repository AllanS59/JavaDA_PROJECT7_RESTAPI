package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleNameServiceTests {

	@Autowired
	private RuleNameService ruleNameService;

	@Test
	public void ruleTest() {
		RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

		// Save
		rule = ruleNameService.saveRuleName(rule);
		Assert.assertNotNull(rule.getId());
		Assert.assertTrue(rule.getName().equals("Rule Name"));

		// Update
		rule.setName("Rule Name Update");
		rule = ruleNameService.saveRuleName(rule);
		Assert.assertTrue(rule.getName().equals("Rule Name Update"));

		// Find
		List<RuleName> listResult = ruleNameService.getAllRuleName();
		Assert.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = rule.getId();
		ruleNameService.deleteRuleName(id);
		Optional<RuleName> ruleList = ruleNameService.getRuleName(id);
		Assert.assertFalse(ruleList.isPresent());
	}
}
