package com.life.dto;

import java.util.Date;

public class health_dto {
	private String health_id;
	private int health_weight;
	private String health_food;
	private float health_amount;
	private float health_kcal;
	private Date health_regdate;
	
	public String getHealth_id() {
		return health_id;
	}
	public void setHealth_id(String health_id) {
		this.health_id = health_id;
	}
	public int getHealth_weight() {
		return health_weight;
	}
	public void setHealth_weight(int health_weight) {
		this.health_weight = health_weight;
	}
	public String getHealth_food() {
		return health_food;
	}
	public void setHealth_food(String health_food) {
		this.health_food = health_food;
	}
	
	public float getHealth_amount() {
		return health_amount;
	}
	public void setHealth_amount(float health_amount) {
		this.health_amount = health_amount;
	}
	public Date getHealth_regdate() {
		return health_regdate;
	}
	public void setHealth_regdate(Date health_regdate) {
		this.health_regdate = health_regdate;
	}
	public float getHealth_kcal() {
		return health_kcal;
	}
	public void setHealth_kcal(float health_kcal) {
		this.health_kcal = health_kcal;
	}
	
	
	
	
}
