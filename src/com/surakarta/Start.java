package com.surakarta;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Start {

	public static void main(String[] args) {
		EventQueue.invokeLater(() ->
		{
			//MyFrame frame = new MyFrame();
			FirstDialog frame = new FirstDialog();
			
			frame.setLocationRelativeTo(null);            //����
			frame.setTitle("��ʼ");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			
		});
	}

}
