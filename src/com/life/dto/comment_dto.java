package com.life.dto;

import java.util.Date;

public class comment_dto {
	private int comment_no_seq;
	private int comment_group_order;
	private int comment_groupno_seq;
	private int comment_board_no;
	private String comment_writer;
	private String comment_content;
	private Date comment_regdate;
	public int getComment_no_seq() {
		return comment_no_seq;
	}
	public void setComment_no_seq(int comment_no_seq) {
		this.comment_no_seq = comment_no_seq;
	}
	public int getComment_group_order() {
		return comment_group_order;
	}
	public void setComment_group_order(int comment_group_order) {
		this.comment_group_order = comment_group_order;
	}
	public int getComment_groupno_seq() {
		return comment_groupno_seq;
	}
	public void setComment_groupno_seq(int comment_groupno_seq) {
		this.comment_groupno_seq = comment_groupno_seq;
	}
	public int getComment_board_no() {
		return comment_board_no;
	}
	public void setComment_board_no(int comment_board_no) {
		this.comment_board_no = comment_board_no;
	}
	public String getComment_writer() {
		return comment_writer;
	}
	public void setComment_writer(String comment_writer) {
		this.comment_writer = comment_writer;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public Date getComment_regdate() {
		return comment_regdate;
	}
	public void setComment_regdate(Date comment_regdate) {
		this.comment_regdate = comment_regdate;
	}
}
