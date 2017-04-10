package com.chat.connection;

public class Element{

	
	volatile StringBuilder wholeText = new StringBuilder();
	
	synchronized void add(StringBuilder sb){
		wholeText = sb;
	}
	
	synchronized void addToWholeText(char text){
		wholeText.append(text);
	}
	
	StringBuilder getWholeText(){
		return wholeText;
	}
	
	synchronized void resetText(){
		wholeText = new StringBuilder();
	}
	
	int getSize(){
		return wholeText.length();
	}
	
	void setToZeroElement(){
		wholeText.delete(0, getSize() - 1);
	}
	
	void setElement(String text){
		setToZeroElement();
		wholeText.append(text);
	}
}
