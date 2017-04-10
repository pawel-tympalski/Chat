package com.chat.connection;



public class Message {
	volatile String text = "";
	
	synchronized public void addElement(String text){
		this.text = text;
	}
	
	synchronized public String getText(){
		
		return text;
		
	};
	
	
	synchronized void resetMessage(){
		text = "";
	}
	
	int getSize(){
		return text.length();
	}
}
