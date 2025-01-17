package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "Rating")
public class Rating {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer Id;
	private String moodysRating;
	private String sandRating;
	private String fitchRating;
	private int orderNumber;
	
	public Rating() {
	}
	
	public Rating(String moodysRating, String sandRating, String fitchRating, int orderNumber) {
		this.moodysRating = moodysRating;
		this.sandRating = sandRating;
		this.fitchRating = fitchRating;
		this.orderNumber = orderNumber;
	}
	
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getMoodysRating() {
		return moodysRating;
	}
	public void setMoodysRating(String moodysRating) {
		this.moodysRating = moodysRating;
	}
	public String getSandRating() {
		return sandRating;
	}
	public void setSandRating(String sandRating) {
		this.sandRating = sandRating;
	}
	public String getFitchRating() {
		return fitchRating;
	}
	public void setFitchRating(String fitchRating) {
		this.fitchRating = fitchRating;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	
}
