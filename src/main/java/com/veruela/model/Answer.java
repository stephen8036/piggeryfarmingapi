package com.veruela.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Double maleData;
	
	private Double femaleData;
	
	private Double singleData;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getMaleData() {
		return maleData;
	}
	public void setMaleData(Double maleData) {
		this.maleData = maleData;
	}
	public Double getFemaleData() {
		return femaleData;
	}
	public void setFemaleData(Double femaleData) {
		this.femaleData = femaleData;
	}
	public Double getSingleData() {
		return singleData;
	}
	public void setSingleData(Double singleData) {
		this.singleData = singleData;
	}
	
	
	

}
