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
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;

import com.chat.gui.Gui;

public class ClientSideConnection {

	public static void main(String[] args) throws IOException, InterruptedException {
		
	final PipedOutputStream output = new PipedOutputStream();
	final PipedInputStream  input  = new PipedInputStream(output);
	final BufferedInputStream bis = new BufferedInputStream(input);
	final StringBuilder sb = new StringBuilder();
	StringBuilder dataFromSb = new StringBuilder();
	final StringBuilder informationSendedToGui = new StringBuilder();
	StringBuilder wholeText = new StringBuilder();
	Message backData = new Message();
	Flag flag2 = new Flag();
	Flag flag3 = new Flag();
	Flag flag4 = new Flag();
	Flag flag5 = new Flag();
	Flag socket = new Flag();
	StringBuilder lockSocket = new StringBuilder();
	StringBuilder lock = new StringBuilder();
	StringBuilder lockw2 = new StringBuilder();
	StringBuilder lock3 = new StringBuilder();
	StringBuilder lock4 = new StringBuilder();
	StringBuilder lock5 = new StringBuilder();
	StringBuilder lock6 = new StringBuilder();
	StringBuilder lock7 = new StringBuilder();
	StringBuilder lock8 = new StringBuilder();
	ExitCheck exit = new ExitCheck();
	final Gui gui = new Gui();
	
	Thread th = new Thread(new Runnable() {
			public void run() {
        	
				gui.createAndShowGUI(output);
        	
        }
		});
	th.setDaemon(true);
	th.start();
	
	Thread readingFromGui = new Thread(new Runnable() {
		@Override
		public void run() {
			Deque<Character> queue = new ArrayDeque<Character>(6);
			int data;
			StringBuilder word= new StringBuilder();
			try {
				while ((data = bis.read()) > 0) {
					
					char sign = (char)data;
					queue.addLast(sign);
					sb.append((char) data);
					if(queue. size() == 6){
						for(int i=1; i<=6;i++){
							char c = queue.pollFirst();
							word.append(c);
							if(i>1){
								queue.addLast(c);
							}
						}
						
						if(word.toString().equals("@$yy@$")){
							synchronized(lock6){
								dataFromSb.append(sb.toString());
								flag4.setStatus(true);
								word.delete(0, word.length());
								queue.clear();
								}
							while(sb.length()>0){
							synchronized(lock7){
								try {
									lock7.wait(100);
								}
								catch (InterruptedException e) {
									
									e.printStackTrace();
								}
							}
							}
							
						}
						else{
						word.delete(0, word.length());
						}
					}
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	});
	readingFromGui.setDaemon(true);
	readingFromGui.start();	
            
	Thread readingFromSocket = new Thread(new Runnable() {
			Socket s1 = null;

			@Override
			public void run() {
				try {
					
					OutputStream outputStream = null;
					@SuppressWarnings("unchecked")
					final BufferedReader br;

					 s1 = new Socket("localhost", 6000);
					outputStream = s1.getOutputStream();
					br = new BufferedReader(new InputStreamReader(
							s1.getInputStream()));
					final PrintWriter writer = new PrintWriter(
							new OutputStreamWriter(outputStream));
					

					Thread readingDataFromSocket = new Thread(new Runnable() {

						@Override
						public void run() {
							int data;
							Deque<Character> queue = new ArrayDeque<Character>(6);
							StringBuilder word = new StringBuilder();
							try {
								while ((data = br.read()) != -1) {
									char sign = (char) data;
									informationSendedToGui.append((char) data);
									queue.addLast(sign);
									if(queue.size() == 6){
										for(int i =1 ; i<=6;i++){
											char c = queue.pollFirst();
											word.append(c);
											if(i>1){
												queue.addLast(c);
											}
										}
										if(word.toString().equals("@$yy@$")){
											synchronized(lock4){
												
											flag5.setStatus(true);
											}
											word.delete(0,word.length());
											queue.clear();
											while(informationSendedToGui.length() > 0){
												synchronized(lock8){
													try {
														lock8.wait(100);
													} catch (InterruptedException e) {
														e.printStackTrace();
													}
												}
											}
											
											
										}
										else{
											word.delete(0,word.length());
										}
									}
									
									
								}
							} catch (IOException e) {
								e.printStackTrace();
								
							}

						}

					});

					readingDataFromSocket.setDaemon(true);
					readingDataFromSocket.start();

					Runnable sendingDataToGui = new Runnable() {

						@Override
						public void run() {
							
							while (true) {
									
								while (flag5.isStatus() == false) {
									synchronized (lock) {
										try {
											lock.wait(50);
											
										} catch (InterruptedException e) {

											e.printStackTrace();
										}
									}

								}
								
								
								synchronized(lock4){
									
									String insertText = informationSendedToGui.substring(1, informationSendedToGui.length() - 6);
									wholeText.append(insertText);
									gui.insertText(wholeText.toString());
									
								
									String extraction = informationSendedToGui.toString().
										substring(
												informationSendedToGui.length() - 10 ,
												informationSendedToGui.length());
						
						
									exit.setText(extraction);
									informationSendedToGui.delete(0,informationSendedToGui.length());
									exit.setStatus(true);
									flag5.setStatus(false);
									sb.delete(0, sb.length());
									backData.resetMessage();
								}			
							}
						}

					};

					Thread threadSendingDataToGui = new Thread(sendingDataToGui);
					threadSendingDataToGui.setDaemon(true);
					threadSendingDataToGui.start();

					Thread sendMessageOK = new  Thread(new Runnable() {

						@Override
						public void run() {

							while (true) {
								while (exit.isStatus() == false) {
									
									synchronized (lock3) {
										try {
											lock3.wait(50);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}	
								}
									
								synchronized(lock4){
										
									if(exit.getText().equalsIgnoreCase("exit@$yy@$") && (flag3.isStatus() == true)) {
										writer.print("end\n");
										writer.flush();
										flag2.setStatus(true);
										exit.setStatus(false);
										
									} else {
										writer.print("ok\n");
										exit.setStatus(false);
										writer.flush();
										
									}
									}
									
								}

					}
						

					});
					sendMessageOK.setDaemon(true);
					sendMessageOK.start();

					while (flag2.isStatus() == false) {
						synchronized (lock5) {
							try {
								lock5.wait(100);
								
							 }catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					s1.close();
					socket.setStatus(true);
					

				} catch (IOException e) {
					e.printStackTrace();
					try {
						s1.close();
						socket.setStatus(true);
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
				}
				
			}

		});
    readingFromSocket.setDaemon(true);
	readingFromSocket.start();
					
				
	Thread writingToSocket = new Thread(new Runnable() {
			public void run() {
				Socket s2;
				try {
					s2 = new Socket("localhost", 7000);

					OutputStream outputStream2;

					outputStream2 = s2.getOutputStream();

					PrintWriter bw = new PrintWriter(new OutputStreamWriter(
							outputStream2));

					Runnable writingDataToSocket = new Runnable() {
						public void run() {
							
							StringBuilder lock898 = new StringBuilder();
							while (true) {

								while (flag4.isStatus()== false ) {
									
									try {
										synchronized (lockw2) {
											lockw2.wait(100);
										}
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								flag4.setStatus(false);
								
								
								synchronized(lock6){
								
								backData.addElement(dataFromSb.toString());
								
								String infoExit = backData.getText().substring(
										backData.getSize()- 10,
										backData.getSize());
								
								if(infoExit.equals("exit@$yy@$")){
									flag3.setStatus(true);
								}
								
									
								bw.print(backData.getText());
								dataFromSb.delete(0, dataFromSb.length());
								bw.flush();
								
								
								}
								while(backData.getSize() > 0){
									synchronized(lock898){
										try {
											lock898.wait(100);
										} catch (InterruptedException e) {
											
											e.printStackTrace();
										}
									}
								}
								}
							
						};

					};
					Thread threadWritingDataToSocket = new Thread(
							writingDataToSocket);
					threadWritingDataToSocket.setDaemon(true);
					threadWritingDataToSocket.start();
						while(socket.status != true){
							synchronized(lockSocket){
								try {
									lockSocket.wait(200);
								} catch (InterruptedException e) {
									
									e.printStackTrace();
								}
							}
						}
						s2.close();
						
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
	writingToSocket.setDaemon(true);	
	writingToSocket.start();
	readingFromSocket.join();
	
	
	}
	
}
