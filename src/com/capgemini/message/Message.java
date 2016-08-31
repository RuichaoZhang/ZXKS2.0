package com.capgemini.message;

public class Message {
	private String state;
	
	private String content;
	
	public Message() {
		super();
	}

	public Message(String state, String content) {
		super();
		this.state = state;
		this.content = content;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
