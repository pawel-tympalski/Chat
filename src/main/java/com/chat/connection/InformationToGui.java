package com.chat.connection;

public class InformationToGui {

	private String text;
	private boolean free;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isFree() {
		return free;
	}
	public void setFree(boolean free) {
		this.free = free;
	}

	public void deleteText(){
		this.text = "";
	}
}
