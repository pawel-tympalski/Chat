import java.io.IOException;

import org.junit.Test;

import com.chat.connection.ClientSideConnection;
import com.chat.connection.ServerSideConnection;
import com.chat.connection.TestClientSideConnection;
import com.chat.connection.TestClientSideConnection2;
import com.chat.connection.TestClientSideConnection3;

public class TestChat {

	@Test
	public void test1() throws InterruptedException, IOException {
		StringBuilder sb = new StringBuilder();
		
		Thread out= new Thread(new Runnable(){

			@Override
			public void run() {
				Thread server = new Thread(new Runnable(){

					@Override
					public void run() {
						
							try {
								ServerSideConnection.main(null);
							} catch (InterruptedException e) {
								
								e.printStackTrace();
							}
				
					}
					
				});
				
				server.start();
				
			}
			
		});
		out.start();
		out.join();
		
		Thread clients = new Thread(new Runnable(){

			@Override
			public void run() {
				for(int i=1; i<7;i++){
					new Thread(new Runnable(){

						@Override
						public void run() {
							
							
							try {
								ClientSideConnection.main(null);
							} catch (IOException e) {
								
								e.printStackTrace();
							} catch (InterruptedException e) {
								
								e.printStackTrace();
							}
							}
						
					}).start();
					
				}
			}
			
		});
		clients.start();
		clients.join();
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				
				try {
					TestClientSideConnection.main(null);
				} catch (IOException e) {
					
					e.printStackTrace();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			
		}).start();
		while(true){
			synchronized(sb){
				sb.wait(100);
			}
		}
		
	}

	@Test
	public void test2() throws InterruptedException, IOException {
		StringBuilder sb = new StringBuilder();
		
		Thread out = new Thread(new Runnable(){

			@Override
			public void run() {
				Thread server = new Thread(new Runnable(){

					@Override
					public void run() {
						
						
						try {
							ServerSideConnection.main(null);
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
						
					}
					
				});
				
				server.start();
				
			}
			
		});
		out.start();
		out.join();
		
		Thread clients = new Thread(new Runnable(){

			@Override
			public void run() {
				for(int i=1; i<15;i++){
					new Thread(new Runnable(){

						@Override
						public void run() {
							
							
							try {
								ClientSideConnection.main(null);
							} catch (IOException e) {
								
								e.printStackTrace();
							} catch (InterruptedException e) {
								
								e.printStackTrace();
							}
							}
						
					}).start();
					
				}
			}
			
		});
		clients.start();
		clients.join();
		new Thread(new Runnable(){

			@Override
			public void run() {
				
				try {
					TestClientSideConnection.main(null);
				} catch (IOException e) {
					
					e.printStackTrace();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			
		}).start();
		new Thread(new Runnable(){

			@Override
			public void run() {
			
				try {
					TestClientSideConnection2.main(null);
				} catch (IOException e) {
					
					e.printStackTrace();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			
		}).start();
		while(true){
			synchronized(sb){
				sb.wait(100);
			}
		}
		
	}

	@Test
	public void test3_diconnect_Clients() throws InterruptedException, IOException {
		StringBuilder sb = new StringBuilder();
		
		Thread out = new Thread(new Runnable(){

			@Override
			public void run() {
				Thread server = new Thread(new Runnable(){

					@Override
					public void run() {
						
						try {
							ServerSideConnection.main(null);
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
						
					}
					
				});
				
				server.start();
				
			}
			
		});
		out.start();
		out.join();
		
		Thread clients = new Thread(new Runnable(){

			@Override
			public void run() {
				for(int i=1; i<7;i++){
					new Thread(new Runnable(){

						@Override
						public void run() {
							
							
							try {
								ClientSideConnection.main(null);
							} catch (IOException e) {
								
								e.printStackTrace();
							} catch (InterruptedException e) {
								
								e.printStackTrace();
							}
							}
						
					}).start();
					
				}
			}
			
		});
		clients.start();
		clients.join();
		new Thread(new Runnable(){

			@Override
			public void run() {
				
				try {
					TestClientSideConnection.main(null);
				} catch (IOException e) {
					
					e.printStackTrace();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			
		}).start();
		
		new Thread(new Runnable(){

			@Override
			public void run() {
			
				try {
					TestClientSideConnection3.main(null);
				} catch (IOException e) {
					
					e.printStackTrace();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			
		}).start();
		
		new Thread(new Runnable(){

			@Override
			public void run() {
			
				try {
					TestClientSideConnection3.main(null);
				} catch (IOException e) {
					
					e.printStackTrace();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			
		}).start();
		
		
		new Thread(new Runnable(){

			@Override
			public void run() {
			
				try {
					TestClientSideConnection2.main(null);
				} catch (IOException e) {
					
					e.printStackTrace();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			
		}).start();
		while(true){
			synchronized(sb){
				sb.wait(100);
			}
		}
		
	}
	
}
