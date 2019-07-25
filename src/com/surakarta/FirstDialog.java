package com.surakarta;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class FirstDialog extends JFrame implements ActionListener{
	
	private JButton startButton;
	
	public FirstDialog() {
		setSize(440, 270);
		this.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("华文琥珀", Font.PLAIN, 30));
		lblNewLabel.setText("苏拉卡尔塔");
		
		startButton = new JButton("New button");
		startButton.setText("开始游戏");
		startButton.addActionListener(this);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(142, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(startButton)
							.addGap(32)))
					.addGap(133))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(46)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addGap(43)
					.addComponent(startButton)
					.addContainerGap(66, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		
	}

	//开始游戏按钮监听
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startButton) {
			MainDialog mainDialog = new MainDialog();
			mainDialog.setVisible(true);
			this.dispose();
		}
		
	}
}
