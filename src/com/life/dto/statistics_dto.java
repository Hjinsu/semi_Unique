package com.life.dto;

import java.util.Date;

public class statistics_dto {
	private String statistics_id;
	private float statistics_calorie;
	private int statistics_spend;
	private Date statistics_date;
	private String statistics_days;
	
	public String getStatistics_days() {
		return statistics_days;
	}
	public void setStatistics_days(String statistics_days) {
		this.statistics_days = statistics_days;
	}
	public String getStatistics_id() {
		return statistics_id;
	}
	public void setStatistics_id(String statistics_id) {
		this.statistics_id = statistics_id;
	}
	public float getStatistics_calorie() {
		return statistics_calorie;
	}
	public void setStatistics_calorie(float statistics_calorie) {
		this.statistics_calorie = statistics_calorie;
	}
	public int getStatistics_spend() {
		return statistics_spend;
	}
	public void setStatistics_spend(int statistics_spend) {
		this.statistics_spend = statistics_spend;
	}
	public Date getStatistics_date() {
		return statistics_date;
	}
	public void setStatistics_date(Date statistics_date) {
		this.statistics_date = statistics_date;
	}
}
