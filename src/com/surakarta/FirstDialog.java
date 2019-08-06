package com.surakarta;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.BreakIterator;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FirstDialog extends JFrame implements ActionListener{
	
	public JButton startButton;
	public JRadioButton computer_first;
	public JRadioButton player_first;
	public ButtonGroup radiobuttons;
	
	public FirstDialog() {
		setSize(440, 270);
		this.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("华文琥珀", Font.PLAIN, 30));
		lblNewLabel.setText("苏拉卡尔塔");
		
		startButton = new JButton("New button");
		startButton.setText("开始");
		startButton.addActionListener(this);
		
		computer_first = new JRadioButton("电脑先手");
		computer_first.setFont(new Font("宋体", Font.PLAIN, 12));
		
		player_first = new JRadioButton("玩家先手");
		player_first.setFont(new Font("宋体", Font.PLAIN, 12));
		
		radiobuttons = new ButtonGroup();
		radiobuttons.add(computer_first);
		radiobuttons.add(player_first);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(141)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(133))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(188)
					.addComponent(startButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(179))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(180)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(player_first, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
						.addComponent(computer_first, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(171))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(46)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(computer_first)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(player_first, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(startButton, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addGap(37))
		);
		getContentPane().setLayout(groupLayout);
		
		
	}

	//开始按钮点击监听
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startButton) {
			if(computer_first.isSelected() == true) {
			MainDialog mainDialog = new MainDialog(0);//电脑先手
				mainDialog.setVisible(true);
			}
			else if(player_first.isSelected() == true){
				MainDialog mainDialog = new MainDialog(1);//人先手
				mainDialog.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null, "请选择一方先手！");
				return;
			}
			this.dispose();
			
		}
		
	}
}
