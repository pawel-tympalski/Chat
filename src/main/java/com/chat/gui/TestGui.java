package com.chat.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PipedOutputStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TestGui {
	JLabel textLabel;
	JLabel textLabel2;
	JLabel textLabel3;
	JTextField textField;
	public JTextArea textAreaNotEditable;
	JTextArea messageTextField;
	JButton buttonSendMessage;
	PipedOutputStream stream;
	
	public  void createAndShowGUI(final PipedOutputStream stream) {
       
        JFrame frame = new JFrame("Chat");
        
        WindowAdapter adapter = new WindowAdapter(){
        	public void windowClosing(WindowEvent e){
        		try {
					stream.write((textField.getText()+ ">>" + "exit"+"@$yy@$").getBytes());
					stream.flush();
					} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
        		
        	}
        };
        
        frame.addWindowListener(adapter);
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.X_AXIS));
        
        textLabel = new JLabel("Name: ");

        textField = new JTextField();
      
        topPanel.add(textLabel);
        topPanel.add(textField);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.Y_AXIS));
        
        textAreaNotEditable = new JTextArea();
       
        textAreaNotEditable.setEditable(false);
        
               
        textLabel2 = new JLabel("Conversation ");
        
               
        centerPanel.add(textLabel2);
        centerPanel.add(textAreaNotEditable);
        
        JPanel downPanel = new JPanel();
        downPanel.setLayout(new BoxLayout(downPanel,BoxLayout.Y_AXIS));
       
        buttonSendMessage = new JButton("Send Message");
        
        messageTextField = new JTextArea();
        
      
        textLabel3 = new JLabel("Text To send ");
        
        downPanel.add(textLabel3);
        downPanel.add(messageTextField);
        downPanel.add(buttonSendMessage);
       
         
        buttonSendMessage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					stream.write((getText()+ "\n"+"@$yy@$").getBytes());
					stream.flush();
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
		
				
			}
        	
        	
        });
        
        frame.getContentPane().add(BorderLayout.NORTH, topPanel);
        frame.getContentPane().add(BorderLayout.CENTER, centerPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, downPanel);
        
        frame.pack();
        frame.setSize(500, 500);
        
        frame.setMaximumSize(new Dimension(640,400));
        
        textField.setPreferredSize(new Dimension(60, 30));
        textField.setMaximumSize(new Dimension(100,30));
        
        
        textAreaNotEditable.setMaximumSize(new Dimension(400,400));
        textAreaNotEditable.setPreferredSize(new Dimension(300,300));
         
        
        messageTextField.setMaximumSize(new Dimension(700,300));
        messageTextField.setPreferredSize(new Dimension(500,200));
        
        
        
        frame.setVisible(true);
        
        this.stream = stream;
        try {
        	for(int i =1 ; i< 10;i++){
			stream.write(("Print it 9 times "+ "\n" +"@$yy@$").getBytes());
			
			
        	}
        	stream.flush();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
        
    }
	
	String getText() {
		return textField.getText()+ ">>" + messageTextField.getText();
	}
	
	public void insertText(String text) {
			textAreaNotEditable.setText(text);
		}
	
}
