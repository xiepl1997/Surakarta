package com.surakarta;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Container;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class MainDialog extends JFrame{
	
	public MyPanel myPanel;
	
	public MainDialog() {
		this.setTitle("苏拉卡尔塔");
		//每个格子之间距离70
		this.setSize(1000, 689);
		this.setLocationRelativeTo(null);
		
		myPanel = new MyPanel();
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(7)
					.addComponent(myPanel, GroupLayout.PREFERRED_SIZE, 604, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(347, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(myPanel, GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
					.addGap(9))
		);
		getContentPane().setLayout(groupLayout);
		
		
	}
}

















