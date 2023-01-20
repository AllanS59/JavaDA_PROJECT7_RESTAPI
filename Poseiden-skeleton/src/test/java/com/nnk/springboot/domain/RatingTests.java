package com.nnk.springboot.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingTests {

	@Test
	public void ratingTest() {
		Rating rating = new Rating();
		
		rating.setMoodysRating("moodysRating");
		rating.setSandRating("sandRating");
		rating.setFitchRating("fitchRating");
		rating.setOrderNumber(2);
		
		 Assert.assertEquals(rating.getMoodysRating(), "moodysRating");
		 Assert.assertEquals(rating.getSandRating(), "sandRating");
		 Assert.assertEquals(rating.getFitchRating(), "fitchRating");
		 Assert.assertTrue( rating.getOrderNumber() == 2);
	}
}
