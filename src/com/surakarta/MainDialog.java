package com.surakarta;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.annotation.processing.RoundEnvironment;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.Font;

public class MainDialog extends JFrame implements MouseListener {

	public MyPanel myPanel;
	public JLabel playStatus;

	// 先手标志，1为人先手，0为电脑先手
	public int WhoFirst;

	// 定义棋子的八个移动方向
	public final int move[][] = { { 1, 1 }, { 1, 0 }, { 1, -1 }, { -1, -1 }, { -1, 0 }, { -1, -1 }, { 0, 1 },
			{ 0, -1 } };

	// 棋盘棋子位置，-1位黑子，1为红子
	public int[][] board = { { -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 }, { 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1 } };
	private JTextField red_positon_text;
	private JTextField red_points_text;
	private JTextField black_points_text;

	public MainDialog(int wf) {
		this.setTitle("苏拉卡尔塔");
		// 设置窗口大小且居中
		this.setSize(1000, 689);
		this.setLocationRelativeTo(null);

		this.WhoFirst = wf;

		myPanel = new MyPanel();

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u7535\u8111",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u73A9\u5BB6",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(7)
						.addComponent(myPanel, GroupLayout.PREFERRED_SIZE, 630, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE))
						.addGap(8)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(11)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(panel, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
								.addComponent(myPanel, GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE))
						.addGap(9)));

		JLabel label = new JLabel("所选棋子坐标");
		label.setFont(new Font("宋体", Font.PLAIN, 12));

		red_positon_text = new JTextField();
		red_positon_text.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("得分");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 12));

		red_points_text = new JTextField();
		red_points_text.setColumns(10);

		playStatus = new JLabel("");
		playStatus.setForeground(Color.ORANGE);
		playStatus.setFont(new Font("黑体", Font.BOLD, 21));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup().addContainerGap(85, Short.MAX_VALUE)
										.addComponent(lblNewLabel_1).addGap(18))
								.addGroup(gl_panel_1.createSequentialGroup().addGap(37)
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(red_positon_text, GroupLayout.PREFERRED_SIZE, 122,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(red_points_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(82))
				.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup().addContainerGap()
						.addComponent(playStatus, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE).addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(53)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(label).addComponent(
								red_positon_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
						.addGap(38)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
								.addComponent(red_points_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18).addComponent(playStatus, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
						.addGap(40)));
		panel_1.setLayout(gl_panel_1);

		JLabel label_1 = new JLabel("得分");
		label_1.setFont(new Font("宋体", Font.PLAIN, 12));

		black_points_text = new JTextField();
		black_points_text.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(90)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(black_points_text,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(133, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(37)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(black_points_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1))
						.addContainerGap(232, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		;
		red_positon_text.setEditable(false);
		red_points_text.setEditable(false);
		black_points_text.setEditable(false);
		playStatus.setText("请选择棋子。");

		myPanel.addMouseListener(this);

		// 判断是否是电脑先行
		if (this.WhoFirst == 0) {
			// AI();
		}

	}

	Chess chess;// 所选的棋子
	Step step;// 所选的可落子位
	int turn = 0; // 标记当前是选子还是落子，0为选子，1为落子
	// 鼠标点击监听

	@Override
	public void mouseClicked(MouseEvent e) {

		if (turn == 0) {
			int mins = Integer.MAX_VALUE;
			for (int i = 0; i < myPanel.chessList.size(); i++) {// 检测点击的是否是棋子
				chess = myPanel.chessList.get(i);
				if (chess.color == Color.red) {
					float x = Math.abs(e.getX() - chess.position.x);
					float y = Math.abs(e.getY() - chess.position.y);
					float z = (float) Math.sqrt(x * x + y * y);// 计算鼠标点击到每个chess的距离
					if (z < 42) {
						red_positon_text.setText("第" + chess.x + "行,第" + chess.y + "列");
						playStatus.setText("您已选择棋子，请决定下一步。");
						setStep();
						myPanel.repaint();
						turn = 1;
						return;
					}
				}
			}
		} 
		else {
			int mins = Integer.MAX_VALUE;
			for (int i = 0; i < myPanel.stepList.size(); i++) {// 检测点击的是否是可落子位
				step = myPanel.stepList.get(i);
				float x = Math.abs(e.getX() - step.position.x);
				float y = Math.abs(e.getY() - step.position.y);
				float z = (float) Math.sqrt(x * x + y * y);// 计算鼠标点击到每个step的距离
				if (z < 21) {
					myPanel.state[step.x - 1][step.y - 1] = 1;
					myPanel.state[chess.x - 1][chess.y - 1] = 0;
					myPanel.stepList.clear();
					for (int j = 0; j < 6; j++) {
						for (int k = 0; k < 6; k++)
							myPanel.step[j][k] = 0;
					}
					//AI();
					myPanel.repaint();
					playStatus.setText("您已落子，请再选择棋子。");
					turn = 0;
					return;
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	// 将所选棋子可走的点标注出来
	public void setStep() {
		myPanel.stepList.clear();
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++)
				myPanel.step[i][j] = 0;
		}
		if (chess.x - 1 - 1 >= 0) {
			if (myPanel.state[chess.x - 1 - 1][chess.y - 1] == 0)
				myPanel.step[chess.x - 1 - 1][chess.y - 1] = 1;
		}
		if (chess.x - 1 + 1 <= 5) {
			if (myPanel.state[chess.x - 1 + 1][chess.y - 1] == 0)
				myPanel.step[chess.x - 1 + 1][chess.y - 1] = 1;
		}
		if (chess.y - 1 - 1 >= 0) {
			if (myPanel.state[chess.x - 1][chess.y - 1 - 1] == 0)
				myPanel.step[chess.x - 1][chess.y - 1 - 1] = 1;
		}
		if (chess.y - 1 + 1 <= 5) {
			if (myPanel.state[chess.x - 1][chess.y - 1 + 1] == 0)
				myPanel.step[chess.x - 1][chess.y - 1 + 1] = 1;
		}
		if (chess.x - 1 - 1 >= 0 && chess.y - 1 - 1 >= 0) {
			if (myPanel.state[chess.x - 1 - 1][chess.y - 1 - 1] == 0)
				myPanel.step[chess.x - 1 - 1][chess.y - 1 - 1] = 1;
		}
		if (chess.x - 1 + 1 <= 5 && chess.y - 1 - 1 >= 0) {
			if (myPanel.state[chess.x - 1 + 1][chess.y - 1 - 1] == 0)
				myPanel.step[chess.x - 1 + 1][chess.y - 1 - 1] = 1;
		}
		if (chess.x - 1 - 1 >= 0 && chess.y - 1 + 1 <= 5) {
			if (myPanel.state[chess.x - 1 - 1][chess.y - 1 + 1] == 0)
				myPanel.step[chess.x - 1 - 1][chess.y - 1 + 1] = 1;
		}
		if (chess.x - 1 + 1 <= 5 && chess.y - 1 + 1 <= 5) {
			if (myPanel.state[chess.x - 1 + 1][chess.y - 1 + 1] == 0)
				myPanel.step[chess.x - 1 + 1][chess.y - 1 + 1] = 1;
		}
		
		fly();

	}
	
	//搜寻所选棋子的吃子走位（深度优先）
	public void fly() {
		
	}
	
	//对战ai
	public void AI() {
		
	}

}
