package com.chat.connection;

public class Counter {
	 volatile private  int number;

	synchronized public int getNumber() {
		return number;
	}

	synchronized public void setNumber(int value) {
		number = value;
	}
	
	synchronized public void increment(){
		number += 1;
	}
	
	synchronized public void decrement(){
		number -= 1; 
	}
	
}
