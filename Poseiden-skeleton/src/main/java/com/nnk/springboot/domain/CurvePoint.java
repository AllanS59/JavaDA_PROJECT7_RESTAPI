package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Entity
@Table(name = "curvepoint")
public class CurvePoint {
	 @Id
	 @GeneratedValue(strategy= GenerationType.AUTO)
	 private Integer Id;
	 @NotBlank(message = "must not be null")
	 private int CurveId;
	 private Timestamp asOfDate;
	 private double term;
	 private double value;
	 private Timestamp creationDate;
	 
	 
	public CurvePoint(int CurveId, double term, double value) {
		this.CurveId = CurveId;
		this.term = term;
		this.value = value;
	}
	
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public int getCurveId() {
		return CurveId;
	}
	public void setCurveId(int curveId) {
		CurveId = curveId;
	}
	public Timestamp getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(Timestamp asOfDate) {
		this.asOfDate = asOfDate;
	}
	public double getTerm() {
		return term;
	}
	public void setTerm(double term) {
		this.term = term;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	 
}
