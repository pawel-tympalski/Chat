package com.chat.exception;

public class ClientCloseConnection extends Exception {

	public ClientCloseConnection(String info){
		super(info);
	}
	
	public void printError(){
		System.out.println(getMessage());
	}
}
