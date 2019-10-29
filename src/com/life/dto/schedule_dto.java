package com.life.dto;

import java.util.Date;

public class schedule_dto {
	private int schedule_no;
	private String schedule_id;
	private String schedule_title;
	private String schedule_content;
	private String schedule_date;
	private Date schedule_regdate;
	private int schedule_board_no;
	public int getSchedule_no() {
		return schedule_no;
	}
	public void setSchedule_no(int schedule_no) {
		this.schedule_no = schedule_no;
	}
	public String getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(String schedule_id) {
		this.schedule_id = schedule_id;
	}
	public String getSchedule_title() {
		return schedule_title;
	}
	public void setSchedule_title(String schedule_title) {
		this.schedule_title = schedule_title;
	}
	public String getSchedule_content() {
		return schedule_content;
	}
	public void setSchedule_content(String schedule_content) {
		this.schedule_content = schedule_content;
	}
	public String getSchedule_date() {
		return schedule_date;
	}
	public void setSchedule_date(String schedule_date) {
		this.schedule_date = schedule_date;
	}
	public Date getSchedule_regdate() {
		return schedule_regdate;
	}
	public void setSchedule_regdate(Date schedule_regdate) {
		this.schedule_regdate = schedule_regdate;
	}
	public int getSchedule_board_no() {
		return schedule_board_no;
	}
	public void setSchedule_board_no(int schedule_board_no) {
		this.schedule_board_no = schedule_board_no;
	}
	
	
}
