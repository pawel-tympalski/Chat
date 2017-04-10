package com.chat.connection;

public class Flag {
	volatile boolean status = false;

	synchronized public boolean isStatus() {
		return status;
	}

	synchronized public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
