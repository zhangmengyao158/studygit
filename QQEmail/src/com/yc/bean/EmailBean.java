package com.yc.bean;

public class EmailBean {
	private String emailId;
	private String fromId;
	private String toId;
	private String title;
	private String con;
	private String email_date;
	private String email_send;
	private String email_drop;
	private String inbox_type;
	private String partition_type;
	private String emailA1;
	private String emailA2;
	private String emailA3;
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	public String getToId() {
		return toId;
	}
	public void setToId(String toId) {
		this.toId = toId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCon() {
		return con;
	}
	public void setCon(String con) {
		this.con = con;
	}
	public String getEmail_date() {
		return email_date;
	}
	public void setEmail_date(String email_date) {
		this.email_date = email_date;
	}
	public String getEmail_send() {
		return email_send;
	}
	public void setEmail_send(String email_send) {
		this.email_send = email_send;
	}
	public String getEmail_drop() {
		return email_drop;
	}
	public void setEmail_drop(String email_drop) {
		this.email_drop = email_drop;
	}
	public String getInbox_type() {
		return inbox_type;
	}
	public void setInbox_type(String inbox_type) {
		this.inbox_type = inbox_type;
	}
	public String getPartition_type() {
		return partition_type;
	}
	public void setPartition_type(String partition_type) {
		this.partition_type = partition_type;
	}
	public String getEmailA1() {
		return emailA1;
	}
	public void setEmailA1(String emailA1) {
		this.emailA1 = emailA1;
	}
	public String getEmailA2() {
		return emailA2;
	}
	public void setEmailA2(String emailA2) {
		this.emailA2 = emailA2;
	}
	public String getEmailA3() {
		return emailA3;
	}
	public void setEmailA3(String emailA3) {
		this.emailA3 = emailA3;
	}
	public EmailBean(String emailId, String fromId, String toId, String title,
			String con, String email_date, String email_send,
			String email_drop, String inbox_type, String partition_type,
			String emailA1, String emailA2, String emailA3) {
		super();
		this.emailId = emailId;
		this.fromId = fromId;
		this.toId = toId;
		this.title = title;
		this.con = con;
		this.email_date = email_date;
		this.email_send = email_send;
		this.email_drop = email_drop;
		this.inbox_type = inbox_type;
		this.partition_type = partition_type;
		this.emailA1 = emailA1;
		this.emailA2 = emailA2;
		this.emailA3 = emailA3;
	}
	public EmailBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "EmailBean [emailId=" + emailId + ", fromId=" + fromId
				+ ", toId=" + toId + ", title=" + title + ", con=" + con
				+ ", email_date=" + email_date + ", email_send=" + email_send
				+ ", email_drop=" + email_drop + ", inbox_type=" + inbox_type
				+ ", partition_type=" + partition_type + ", emailA1=" + emailA1
				+ ", emailA2=" + emailA2 + ", emailA3=" + emailA3 + "]";
	}
	

}
