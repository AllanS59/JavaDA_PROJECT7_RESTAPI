package com.nnk.springboot.domain;

import java.sql.Timestamp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurvePointTests {

	@Test
	public void curvePointTest() {
		CurvePoint curvePoint = new CurvePoint();
		Timestamp tm = new Timestamp(2000);
		 
		 curvePoint.setCurveId(1);
		 curvePoint.setAsOfDate(tm);
		 curvePoint.setTerm(2);
		 curvePoint.setValue(3);
		 curvePoint.setCreationDate(tm);
		 
		 Assert.assertTrue( curvePoint.getCurveId() == 1);
		 Assert.assertEquals(curvePoint.getAsOfDate(), tm);
		 Assert.assertTrue( curvePoint.getTerm() == 2);
		 Assert.assertTrue( curvePoint.getValue() == 3);
		 Assert.assertEquals(curvePoint.getCreationDate(), tm);
		
	}
	
}
