package com.nnk.springboot.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleNameTests {

	@Test
	public void ruleNameTest() {
		RuleName ruleName = new RuleName();
		
		ruleName.setName("name");
		ruleName.setDescription("description");
		ruleName.setJson("json");
		ruleName.setTemplate("template");
		ruleName.setSqlStr("sqlStr");
		ruleName.setSqlPart("sqlPart");
		
		Assert.assertEquals(ruleName.getName(), "name");
		Assert.assertEquals(ruleName.getDescription(), "description");
		Assert.assertEquals(ruleName.getJson(), "json");
		Assert.assertEquals(ruleName.getTemplate(), "template");
		Assert.assertEquals(ruleName.getSqlStr(), "sqlStr");
		Assert.assertEquals(ruleName.getSqlPart(), "sqlPart");
		
	}
}
