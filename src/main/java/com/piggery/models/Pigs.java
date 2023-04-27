package com.piggery.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.envers.Audited;

@Entity
@Audited(withModifiedFlag = true)
public class Pigs {
	
	@Id
	@SequenceGenerator(name = "pigSeqGen", sequenceName = "pigSeq", initialValue = 1001)
	@GeneratedValue(generator = "pigSeqGen")
	private Long id;

	private String name;
	private Double weight;
	private String gender;
	private String breed;
	private String status;
	private String weightMonitoring;
	private String weightDifference;
	private LocalDate date_modified = LocalDate.now();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDate_modified() {
		return date_modified;
	}

	public void setDate_modified(LocalDate date_modified) {
		this.date_modified = date_modified;
	}

	public String getWeightMonitoring() {
		return weightMonitoring;
	}

	public void setWeightMonitoring(String weightMonitoring) {
		if(weightMonitoring == null) {
			this.weightMonitoring = this.weight+",";
		}else {
			this.weightMonitoring = weightMonitoring;
		}
		
	}

	public String getWeightDifference() {
		return weightDifference;
	}

	public void setWeightDifference(String weightDifference) {
		if(weightDifference == null) {
			this.weightDifference = "0.0";
		}else {
			this.weightDifference = weightDifference;
		}
	}
	
	
}
