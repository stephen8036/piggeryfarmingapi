package com.piggery.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="feeds_aud")
public class FeedsAuditor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String feed_prev;
	private String notes_prev;
	private Double quantity_prev;
	private Double difference;
	private Double cost_prev;
	private String date_prev;
	private String type;
	private LocalDateTime date_time_modified = LocalDateTime.now();
	
	
	public FeedsAuditor() {
		super();
	}
	public FeedsAuditor(String feed_prev, String notes_prev, Double quantity_prev, Double cost_prev, String type,Double difference) {
		super();
		this.feed_prev = feed_prev;
		this.notes_prev = notes_prev;
		this.quantity_prev = quantity_prev;
		this.cost_prev = cost_prev;
		this.type=type;
		this.difference=difference;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFeed_prev() {
		return feed_prev;
	}
	public void setFeed_prev(String feed_prev) {
		this.feed_prev = feed_prev;
	}
	public String getNotes_prev() {
		return notes_prev;
	}
	public Double getDifference() {
		return difference;
	}
	public void setDifference(Double difference) {
		this.difference = difference;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setNotes_prev(String notes_prev) {
		this.notes_prev = notes_prev;
	}
	public Double getQuantity_prev() {
		return quantity_prev;
	}
	public void setQuantity_prev(Double quantity_prev) {
		this.quantity_prev = quantity_prev;
	}
	public Double getCost_prev() {
		return cost_prev;
	}
	public void setCost_prev(Double cost_prev) {
		this.cost_prev = cost_prev;
	}
	public String getDate_prev() {
		return date_prev;
	}
	public void setDate_prev(String date_prev) {
		this.date_prev = date_prev;
	}
	public LocalDateTime getDate_time_modified() {
		return date_time_modified;
	}
	public void setDate_time_modified(LocalDateTime date_time_modified) {
		this.date_time_modified = date_time_modified;
	}
	
	
}
