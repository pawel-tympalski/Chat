package com.chat.connection;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;

import com.chat.exception.ClientCloseConnection;

public class ServerSideConnection {
	
	
	public static void main(String[] args) throws InterruptedException {
		
		try {
			final Message message = new Message();
			System.out.println("Waiting for connection");
			ServerSocket writingToSocket = new ServerSocket(6000);
			final ServerSocket readingFromSocket = new ServerSocket(7000);
			final PipedOutputStream outputReadingWriting = new PipedOutputStream();
		    final PipedInputStream  inputReadingWriting  = new PipedInputStream(outputReadingWriting);
		    final PipedOutputStream outputWritingReading = new PipedOutputStream();
		    final PipedInputStream  inputWritingReading  = new PipedInputStream(outputWritingReading);
		    BufferedInputStream buffin = new BufferedInputStream(inputReadingWriting);
			Counter counter33 = new Counter();
			Counter currentNumberOfUsers = new Counter();
			final Counter numberOfUsers = new Counter();
			Counter value200 = new Counter();
			
			StringBuilder lock1001 = new StringBuilder();
			StringBuilder lock1002 = new StringBuilder();
			StringBuilder lock1007 = new StringBuilder();
			StringBuilder lock800 = new StringBuilder();
			StringBuilder lock900 = new StringBuilder();
			Counter sending = new Counter();
			sending.setNumber(1);
			Flag status = new Flag();
			status.setStatus(false);
			Element messageElement = new Element();
			
			Runnable readingTask = new Runnable() {
			
				@Override
				public void run() {

					while (true) {
						Socket s1;
						try{
							s1 = readingFromSocket.accept();
							System.out.println("connected");
							numberOfUsers.increment();
							
							 
							Thread readingDataFromSocket = new Thread(new Runnable() {

								@Override
								public void run() {
									try{
									BufferedReader br;
									
										br = new BufferedReader(new InputStreamReader(s1.getInputStream()));
									
									final Element el = new Element();
									
									Deque<Character> queue = new ArrayDeque<Character>(6);
						
									StringBuilder lock104 = new StringBuilder();
								
								Runnable resetMessage = new Runnable(){

									@Override
									public void run() {
										while(true){
											
											if(value200.getNumber()== 200){
												messageElement.resetText();
												status.setStatus(false);
												value200.setNumber(345);
												
											}
										}
										
										
									}
									
								};
								OneThread2.setThread(resetMessage,lock1002);
								OneThread2.startThread();
									
									
									Runnable readData = new Runnable(){

									@Override
									public void run() {
										int data;
										while(true){
											try {
												while((data = inputWritingReading.read()) != -1){
													value200.setNumber(data);
													
												}
											} catch (IOException e) {
												
												e.printStackTrace();
												
											}
										}
										
									}
									
								};	
								OneThread3.setThread(readData,lock1007);
								OneThread3.startThread();
									
								
								int data = 0;

								StringBuilder word = new StringBuilder();
								try {

										while (true) {
											
											
											while ((data = br.read()) > 0) {
												
												char sign = (char) data;
												el.addToWholeText(sign);

												queue.addLast(sign);

												if (queue.size() == 6) {
													
													for (int i = 1; i <= 6; i++) {
														char c = queue.pollFirst();
														word.append(c);

														if (i > 1) {
															queue.addLast(c);
														}

													}

													
													if ((word.toString()).equals("@$yy@$")){
														
														synchronized(lock800){
														String text = el.getWholeText().toString();
														String last = text;
														
														el.setElement(last);
														
														word.delete(0, word.length());
														queue.clear();
														
															while(status.isStatus() == true){
																synchronized(lock104){
																	try {
																		
																		lock104.wait(150);
																	} catch (InterruptedException e) {
																		
																		e.printStackTrace();
																	}
																}
															}
															
																status.setStatus(true);
																messageElement.add(el.getWholeText());
																el.resetText();
																message.addElement(messageElement.getWholeText().toString());
																
																currentNumberOfUsers.setNumber(numberOfUsers.getNumber());
																outputReadingWriting.write(currentNumberOfUsers.getNumber());
																sending.setNumber(sending.getNumber() + 1);
																outputReadingWriting.write(sending.getNumber());
																outputReadingWriting.flush();
																	
														}
															
														
													} else {
														word.delete(0, word.length());
													}
												}
											}

										}

									} catch (IOException e) {
										e.printStackTrace();
									}
									
								
								} catch (IOException e1) {
									
									e1.printStackTrace();
								}	
								}
								

							});
							readingDataFromSocket.start();

						} catch (IOException e1) {
							e1.printStackTrace();
						}

					}

				}
			};
			Thread threadReadingTask = new Thread(readingTask);
			threadReadingTask.start();
						
						
						
			Runnable writingTask = new Runnable(){
				@Override
				public void run() {
				
					
				
				
				
				while(true){
					try {	
				Socket	s2 = writingToSocket.accept();
				
				Runnable writing = new Runnable() {

						@Override
						public void run(){
							try{
							OutputStream outputStream = s2.getOutputStream();	
							PrintWriter bw = new PrintWriter(new OutputStreamWriter(outputStream));
							BufferedReader 	reader = new BufferedReader(new InputStreamReader(s2.getInputStream()));
							
							StringBuilder lock333 = new StringBuilder();
							int value = 1;
							
							Runnable read = new Runnable(){

								@Override
								public void run() {
									int data;
									Counter number22=new Counter();
									number22.setNumber(3);
									while(true){
									try {
										while( (data = buffin.read()) > 0){
											
											
											if((number22.getNumber() % 2) != 0){
											
												currentNumberOfUsers.setNumber(data);
												
											}
											else {
												counter33.setNumber(data);
												
											}
											number22.increment();
											}
										
									} catch (IOException e) {
										
										e.printStackTrace();
									}
									
									}
								}
							};	
							OneThread.setThread(read, lock1001);
							OneThread.startThread();
							
							
							while (true) {
								synchronized(lock900){				
									try{
			
										
										if(currentNumberOfUsers.getNumber() > 0){
											
											if(value < counter33.getNumber()){
										
													bw.write(message.getText());
													bw.flush();
													
													while (!(reader.ready())) {
														synchronized (lock333) {
															
																lock333.wait(50);
															
														}

													}
													String text = reader.readLine();
													
													
													if (text.equalsIgnoreCase("end")) {
														throw new ClientCloseConnection("end");

													}
											
													value = value +1;
													currentNumberOfUsers.decrement();
											
											}
											
											
											
											if(currentNumberOfUsers.getNumber() == 0){
												try {
													outputWritingReading.write(200);
													outputWritingReading.flush();
												} catch (IOException e1) {
													
													e1.printStackTrace();
												}
											}
											
											
											
										}
									
									
								}catch (ClientCloseConnection e) {
									
									value = value +1;
									numberOfUsers.decrement();
									currentNumberOfUsers.decrement();	
								
									
									if(currentNumberOfUsers.getNumber() == 0){
										try {
											outputWritingReading.write(200);
									
											outputWritingReading.flush();
										
										} catch (IOException e1) {
										
											e1.printStackTrace();
										}
									}
									e.printStackTrace();
									break;
								
								}catch (InterruptedException e) {
									
									
									value = value +1;
									
									currentNumberOfUsers.decrement();
									
									e.printStackTrace();
									
									if(currentNumberOfUsers.getNumber() == 0){
										try {
											outputWritingReading.write(200);
											outputWritingReading.flush();
										} catch (IOException e1) {
										
											e1.printStackTrace();
										}
									}
									
								}catch (IOException e2) {
								
									e2.printStackTrace();
									currentNumberOfUsers.decrement();
									value = value +1;
								
									if(currentNumberOfUsers.getNumber() == 0){
										try {
											outputWritingReading.write(200);
											outputWritingReading.flush();
										} catch (IOException e) {
									
											e.printStackTrace();
										}
									}
								
								}	
							}		
									
							}
							}catch(IOException e){
								e.printStackTrace();
							}
						}//run
							

					};//Runnable end
				
			
			Thread td = new Thread(writing);
			td.setDaemon(true);
			td.start();
				//catch
				
			}catch (IOException e1) {
				
				e1.printStackTrace();
			}
				
					}//while
		
				}					
			};		
		
			Thread threadWritingTask = new Thread(writingTask);
			threadWritingTask.start();
			
		}
		
		
		 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}	


