package com.life.dto;

import java.util.Date;

public class message_dto {
	private int message_no;
	private String message_content;
	private String message_send_id;
	private String message_receive_id;
	private Date message_regdate;
	private String message_read;
	public String getMessage_read() {
		return message_read;
	}
	public void setMessage_read(String message_read) {
		this.message_read = message_read;
	}
	public int getMessage_no() {
		return message_no;
	}
	public void setMessage_no(int message_no) {
		this.message_no = message_no;
	}
	public String getMessage_content() {
		return message_content;
	}
	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}
	public String getMessage_send_id() {
		return message_send_id;
	}
	public void setMessage_send_id(String message_send_id) {
		this.message_send_id = message_send_id;
	}
	public String getMessage_receive_id() {
		return message_receive_id;
	}
	public void setMessage_receive_id(String message_receive_id) {
		this.message_receive_id = message_receive_id;
	}
	public Date getMessage_regdate() {
		return message_regdate;
	}
	public void setMessage_regdate(Date message_regdate) {
		this.message_regdate = message_regdate;
	}
	
	
}
