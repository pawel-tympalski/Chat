package com.chat.connection;

public class OneThread2 {
	private static Runnable r;
	private static boolean started;
	private static StringBuilder sb;
	private OneThread2(){
		
	}
	
	public static void setThread(Runnable rr,StringBuilder s){
		if(r == null){
		r = rr;
		sb =s;
		}
	}

	public static void startThread(){
		synchronized(sb){
		if(started == false){
		new Thread(r).start();
		started= true;
		}
		}
	}
}
