package com.surakarta;

import javax.swing.JFrame;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.function.IntPredicate;

import javax.annotation.processing.RoundEnvironment;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.xml.ws.AsyncHandler;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Point;
import java.awt.Robot;

public class MainDialog extends JFrame implements MouseListener {

	public MyPanel myPanel;
	public JLabel playStatus;

	// 先手标志，1为人先手，0为电脑先手
	public int WhoFirst;

	// 定义棋子的八个移动方向
	public final int move[][] = { { 1, 1 }, { 1, 0 }, { 1, -1 }, { -1, -1 }, { -1, 0 }, { -1, -1 }, { 0, 1 },
			{ 0, -1 } };

	// 棋盘棋子位置，-1位黑子，1为红子
	public int[][] state_temp = { { -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 }, { 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1 } };
	public int[][] step_temp = { { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 } };

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
		playStatus.setFont(new Font("黑体", Font.BOLD, 19));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup().addContainerGap(85, Short.MAX_VALUE)
										.addComponent(lblNewLabel_1).addGap(18))
								.addGroup(gl_panel_1.createSequentialGroup().addGap(37)
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(red_points_text, 112, 112, 112)
								.addComponent(red_positon_text, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
						.addGap(82))
				.addGroup(gl_panel_1.createSequentialGroup()
						.addComponent(playStatus, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(38, Short.MAX_VALUE)));
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
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(black_points_text, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE).addGap(80)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(37).addGroup(gl_panel
						.createParallelGroup(Alignment.BASELINE).addComponent(black_points_text).addComponent(label_1))
						.addGap(232)));
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
			myPanel.state[1][0] = 0;
			myPanel.state[2][0] = -1;
		}
	}

	Chess chess;// 所选的棋子
	Step step;// 所选的可落子位
	// int turn = 0; // 标记当前是选子还是落子，0为选子，1为落子
	// 鼠标点击监听

	@Override
	public void mouseClicked(MouseEvent e) {

		int mins = Integer.MAX_VALUE;
		for (int i = 0; i < myPanel.chessList.size(); i++) {// 检测点击的是否是棋子
			Chess chess1 = myPanel.chessList.get(i);
			if (chess1.color == Color.red) {
				float x = Math.abs(e.getX() - chess1.position.x);
				float y = Math.abs(e.getY() - chess1.position.y);
				float z = (float) Math.sqrt(x * x + y * y);// 计算鼠标点击到每个chess的距离
				if (z < 42) {
					red_positon_text.setText("第" + chess1.x + "行,第" + chess1.y + "列");
					playStatus.setText("您已选择棋子，请决定下一步。");
					chess = chess1;
					setStep();
					myPanel.repaint();
					return;
				}
			}
		}

		mins = Integer.MAX_VALUE;
		for (int i = 0; i < myPanel.stepList.size(); i++) {// 检测点击的是否是可落子位
			step = myPanel.stepList.get(i);
			float x = Math.abs(e.getX() - step.position.x);
			float y = Math.abs(e.getY() - step.position.y);
			float z = (float) Math.sqrt(x * x + y * y);// 计算鼠标点击到每个step的距离
			if (z < 21) {
				myPanel.state[step.x - 1][step.y - 1] = 1;
				myPanel.state[chess.x - 1][chess.y - 1] = 0;
				for (int j = 0; j < 6; j++) {
					for (int k = 0; k < 6; k++)
						myPanel.step[j][k] = 0;
				}
				
				myPanel.stepList.clear();
				for (int j = 0; j < 6; j++) {
					for (int k = 0; k < 6; k++)
						myPanel.step[j][k] = 0;
				}

				playStatus.setText("您已落子，请再选择棋子。");

				AI();

				myPanel.repaint();
				getGrade();
				CheckWinner();
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

		//
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++)
				myPanel.step[i][j] = 0;
		}
		if (chess.x - 1 - 1 >= 0) {
			if (myPanel.state[chess.x - 1 - 1][chess.y - 1] != 1)
				myPanel.step[chess.x - 1 - 1][chess.y - 1] = 1;
		}
		if (chess.x - 1 + 1 <= 5) {
			if (myPanel.state[chess.x - 1 + 1][chess.y - 1] != 1)
				myPanel.step[chess.x - 1 + 1][chess.y - 1] = 1;
		}
		if (chess.y - 1 - 1 >= 0) {
			if (myPanel.state[chess.x - 1][chess.y - 1 - 1] != 1)
				myPanel.step[chess.x - 1][chess.y - 1 - 1] = 1;
		}
		if (chess.y - 1 + 1 <= 5) {
			if (myPanel.state[chess.x - 1][chess.y - 1 + 1] != 1)
				myPanel.step[chess.x - 1][chess.y - 1 + 1] = 1;
		}
		if (chess.x - 1 - 1 >= 0 && chess.y - 1 - 1 >= 0) {
			if (myPanel.state[chess.x - 1 - 1][chess.y - 1 - 1] != 1)
				myPanel.step[chess.x - 1 - 1][chess.y - 1 - 1] = 1;
		}
		if (chess.x - 1 + 1 <= 5 && chess.y - 1 - 1 >= 0) {
			if (myPanel.state[chess.x - 1 + 1][chess.y - 1 - 1] != 1)
				myPanel.step[chess.x - 1 + 1][chess.y - 1 - 1] = 1;
		}
		if (chess.x - 1 - 1 >= 0 && chess.y - 1 + 1 <= 5) {
			if (myPanel.state[chess.x - 1 - 1][chess.y - 1 + 1] != 1)
				myPanel.step[chess.x - 1 - 1][chess.y - 1 + 1] = 1;
		}
		if (chess.x - 1 + 1 <= 5 && chess.y - 1 + 1 <= 5) {
			if (myPanel.state[chess.x - 1 + 1][chess.y - 1 + 1] != 1)
				myPanel.step[chess.x - 1 + 1][chess.y - 1 + 1] = 1;
		}

		// up = down = left = right = 0;
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++){
				state_temp[i][j] = myPanel.state[i][j];
			}
		}
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++){
				step_temp[i][j] = myPanel.step[i][j];
			}
		}
		fly_dfs(chess.x - 1, chess.y - 1, 0, 1, 0);
		//myPanel.state = state_temp.clone();
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++){
				myPanel.step[i][j] = step_temp[i][j];
			}
		}
	}

	// int up = 0, down = 0, left = 0, right = 0;// 上下左右经过圆轨道标记，0表示未经过圆轨，1反之
	int flag = 0;// 经过圆轨标志，0未经过，1经过
	// 搜寻所选棋子的飞行吃子走位（深度优先）
	// direction为搜索路线方向，0四个方向，1上，2下，3左，4右，type为棋子类型:1玩家\2电脑

	public void fly_dfs(int x, int y, int direction, int type, int flag) {

		// 四个角
		if ((x == 0 && y == 0) || (x == 0 && y == 5) || (x == 5 && y == 0) || (x == 5 && y == 5)) {
			return;
		}

		// 如果是敌方棋子且经过圆弧
		if (state_temp[x][y] == -type && flag == 1) {
			step_temp[x][y] = 1;
			return;

		} else if (state_temp[x][y] == -type) {
			return;
		}

		// 如果是己方棋子
		if (state_temp[x][y] == type && direction != 0) {
			return;
		}

		// 起始点
		if (direction == 0) {

			// 如果是切点的情况
			if (x == 0 && y == 1) {
				fly_dfs(1, 0, 4, type, 1);
			} else if (x == 0 && y == 2) {
				fly_dfs(2, 0, 4, type, 1);
			} else if (x == 0 && y == 3) {
				fly_dfs(2, 5, 3, type, 1);
			} else if (x == 0 && y == 4) {
				fly_dfs(1, 5, 3, type, 1);
			} else if (x == 1 && y == 0) {
				fly_dfs(0, 1, 2, type, 1);
			} else if (x == 2 && y == 0) {
				fly_dfs(0, 2, 2, type, 1);
			} else if (x == 3 && y == 0) {
				fly_dfs(5, 2, 1, type, 1);
			} else if (x == 4 && y == 0) {
				fly_dfs(5, 1, 1, type, 1);
			} else if (x == 1 && y == 5) {
				fly_dfs(0, 4, 2, type, 1);
			} else if (x == 2 && y == 5) {
				fly_dfs(0, 3, 2, type, 1);
			} else if (x == 3 && y == 5) {
				fly_dfs(5, 3, 1, type, 1);
			} else if (x == 4 && y == 5) {
				fly_dfs(5, 4, 1, type, 1);
			} else if (x == 5 && y == 1) {
				fly_dfs(4, 0, 4, type, 1);
			} else if (x == 5 && y == 2) {
				fly_dfs(3, 0, 4, type, 1);
			} else if (x == 5 && y == 3) {
				fly_dfs(3, 5, 3, type, 1);
			} else if (x == 5 && y == 4) {
				fly_dfs(4, 5, 3, type, 1);
			}

			// 上
			if (x - 1 >= 0) {
				fly_dfs(x - 1, y, 1, type, flag);
			}

			// 下
			if (x + 1 <= 5) {
				fly_dfs(x + 1, y, 2, type, flag);
			}

			// 左
			if (y - 1 >= 0) {
				fly_dfs(x, y - 1, 3, type, flag);
			}

			// 右
			if (y + 1 <= 5) {
				fly_dfs(x, y + 1, 4, type, flag);
			}
		} else {

			// 如果是切点
			if (x == 0 && y == 1 && direction == 1) {
				fly_dfs(1, 0, 4, type, 1);
			} else if (x == 0 && y == 2 && direction == 1) {
				fly_dfs(2, 0, 4, type, 1);
			} else if (x == 0 && y == 3 && direction == 1) {
				fly_dfs(2, 5, 3, type, 1);
			} else if (x == 0 && y == 4 && direction == 1) {
				fly_dfs(1, 5, 3, type, 1);
			} else if (x == 1 && y == 0 && direction == 3) {
				fly_dfs(0, 1, 2, type, 1);
			} else if (x == 2 && y == 0 && direction == 3) {
				fly_dfs(0, 2, 2, type, 1);
			} else if (x == 3 && y == 0 && direction == 3) {
				fly_dfs(5, 2, 1, type, 1);
			} else if (x == 4 && y == 0 && direction == 3) {
				fly_dfs(5, 1, 1, type, 1);
			} else if (x == 1 && y == 5 && direction == 4) {
				fly_dfs(0, 4, 2, type, 1);
			} else if (x == 2 && y == 5 && direction == 4) {
				fly_dfs(0, 3, 2, type, 1);
			} else if (x == 3 && y == 5 && direction == 4) {
				fly_dfs(5, 3, 1, type, 1);
			} else if (x == 4 && y == 5 && direction == 4) {
				fly_dfs(5, 4, 1, type, 1);
			} else if (x == 5 && y == 1 && direction == 2) {
				fly_dfs(4, 0, 4, type, 1);
			} else if (x == 5 && y == 2 && direction == 2) {
				fly_dfs(3, 0, 4, type, 1);
			} else if (x == 5 && y == 3 && direction == 2) {
				fly_dfs(3, 5, 3, type, 1);
			} else if (x == 5 && y == 4 && direction == 2) {
				fly_dfs(4, 5, 3, type, 1);
			}

			// 上
			if (direction == 1) {
				if (x - 1 >= 0) {
					fly_dfs(x - 1, y, direction, type, flag);
				}
			}

			// 下
			if (direction == 2) {
				if (x + 1 <= 5) {
					fly_dfs(x + 1, y, direction, type, flag);
				}
			}

			// 左
			if (direction == 3) {
				if (y - 1 >= 0) {
					fly_dfs(x, y - 1, direction, type, flag);
				}
			}

			// 右
			if (direction == 4) {
				if (y + 1 <= 5) {
					fly_dfs(x, y + 1, direction, type, flag);
				}
			}
		}
	}

	ArrayList<Point> startPoints = new ArrayList<Point>(); // 用于存储每个遍历的点
	ArrayList<Point> points_list = new ArrayList<Point>(); // 用来存储最优选择点
	ArrayList<Integer> score_list = new ArrayList<Integer>(); // 用来存储每个电脑棋子的最优选择点的预估分

	
	// 机器AI
	public void AI() {

		startPoints.clear();
		points_list.clear();
		score_list.clear();
		
		int x = 0;
		int y = 0;

		int maxs = Integer.MIN_VALUE;
		int f = 0;
		int score = 0;

		// 遍历所有的电脑棋子
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				
				
				if (myPanel.state[i][j] == -1) {
					for(int m = 0; m < 6; m++) {
						for(int n = 0; n < 6; n++) {
							state_temp[m][n] = myPanel.state[m][n];
						}
					}
					
					for (int m = 0; m < 6; m++) {
						for (int n = 0; n < 6; n++)
							step_temp[m][n] = 0;
					}
					
					search1(i, j);
				}
			}
		}
		
		for(int i = 0; i < score_list.size(); i++) {
			System.out.println(score_list.get(i));
			System.out.println(startPoints.get(i).x+" "+startPoints.get(i).y);
			System.out.println(points_list.get(i).x+" "+points_list.get(i).y);
			if(score_list.get(i) > maxs) {
				maxs = score_list.get(i);
				f = i;
			}
		}
		
		myPanel.state[points_list.get(f).x][points_list.get(f).y] = -1;
		myPanel.state[startPoints.get(f).x][startPoints.get(f).y] = 0;
		
//		System.out.println(points_list.get(f).x + " " + points_list.get(f).y);
//		System.out.println(startPoints.get(f).x + " " + startPoints.get(f).y);
//		for(int i = 0; i < 6; i++) {
//			for(int j = 0; j < 6; j++) {
//				System.out.print(myPanel.state[i][j]+" ");
//			}
//			System.out.print("\n");
//		}
	}

	// 电脑玩家对弈走法模拟，返回（X，Y）棋子的最佳着棋
	public void search1(int x, int y) {

		int maxs = Integer.MIN_VALUE;
		int score = 0;
		int xx = 0, yy = 0;
		// 回合模拟
		setStep1(x, y, -1);
		
		
		int[][] s1 = new int[6][6];
		int[][] s2 = new int[6][6];
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++){
				s1[i][j] = state_temp[i][j];
			}
		}
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++){
				s2[i][j] = step_temp[i][j];
			}
		}
//		
//		for(int i = 0;i < 6; i++) {
//			for(int j = 0; j < 6; j++) {
//				System.out.print(step_temp[i][j]+" ");
//			}
//			System.out.print("\n");
//		}

		//遍历可落子点
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				
				for(int a1 = 0; a1 < 6; a1++) {
					for(int a2 = 0; a2 < 6; a2++){
						state_temp[a1][a2] = s1[a1][a2];
					}
				}
				for(int a1 = 0; a1 < 6; a1++) {
					for(int a2 = 0; a2 < 6; a2++){
						step_temp[a1][a2] = s2[a1][a2];
					}
				}
				
				if(step_temp[i][j] == 1) {
					
					score = 0;
					if(state_temp[i][j] == 1) { //吃子
						
						score += 2;
						state_temp[i][j] = -1;
						state_temp[x][y] = 0;
					}
					else { //不吃子
						score ++;
						state_temp[i][j] = -1;
						state_temp[x][y] = 0;
					}
					
					//int[][] a = state_temp.clone();
					int[][] a = new int[6][6];
					for(int a1 = 0; a1 < 6; a1++) {
						for(int a2 = 0; a2 < 6; a2++){
							a[a1][a2] = state_temp[a1][a2];
						}
					}
					
					//模拟玩家
					for(int m = 0; m < 6; m++) {
						for(int n = 0; n < 6; n++) {
							
							//state_temp = a.clone();
							for(int a1 = 0; a1 < 6; a1++) {
								for(int a2 = 0; a2 < 6; a2++){
									state_temp[a1][a2] = a[a1][a2];
								}
							}
							
							if(state_temp[m][n] == 1) {
								
								for(int x1 = 0; x1 < 6; x1++) {
									for(int y1 = 0; y1 < 6; y1++) {
										step_temp[x1][y1] = 0;
									}
								}
								
								int score1 = score;
								setStep1(m, n, 1);
								
//								int[][] s3 = state_temp.clone();
//								int[][] s4 = step_temp.clone();
								int[][] s3 = new int[6][6];
								int[][] s4 = new int[6][6];
								for(int a1 = 0; a1 < 6; a1++) {
									for(int a2 = 0; a2 < 6; a2++){
										s3[a1][a2] = state_temp[a1][a2];
									}
								}
								for(int a1 = 0; a1 < 6; a1++) {
									for(int a2 = 0; a2 < 6; a2++){
										s4[a1][a2] = step_temp[a1][a2];
									}
								}
								
								for(int o = 0; o < 6; o++) {
									for(int p = 0; p < 6; p++) {
										
//										state_temp = s3.clone();
//										step_temp = s4.clone();
										for(int a1 = 0; a1 < 6; a1++) {
											for(int a2 = 0; a2 < 6; a2++){
												state_temp[a1][a2] = s3[a1][a2];
											}
										}
										for(int a1 = 0; a1 < 6; a1++) {
											for(int a2 = 0; a2 < 6; a2++){
												step_temp[a1][a2] = s4[a1][a2];
											}
										}
										
										if(step_temp[o][p] == 1) {
											//吃子
											if(state_temp[o][p] == -1) {
												score1 -= 2;
											}
											else {
												score1 ++;
											}
										}
									}
								}
								if(score1 > score1) {
									score = score1;
								}
							}
							
						}
					}
					if(score > maxs) {
						xx = i;
						yy = j;
						maxs = score;
					}
				}
			}
		}
		
		startPoints.add(new Point(x, y));
		points_list.add(new Point(xx, yy));
		score_list.add(maxs);
		System.out.println(maxs);
	}

	// ai 将某个棋子可走的点标注出来
	public void setStep1(int x, int y, int type) {

		if (x - 1 >= 0) {
			if (state_temp[x - 1][y] != type)
				step_temp[x - 1][y] = 1;
		}
		if (x + 1 <= 5) {
			if (state_temp[x + 1][y] != type)
				step_temp[x + 1][y] = 1;
		}
		if (y - 1 >= 0) {
			if (state_temp[x][y - 1] != type)
				step_temp[x][y - 1] = 1;
		}
		if (y + 1 <= 5) {
			if (state_temp[x][y + 1] != type)
				step_temp[x][y + 1] = 1;
		}
		if (x - 1 >= 0 && y - 1 >= 0) {
			if (state_temp[x - 1][y - 1] != type)
				step_temp[x - 1][y - 1] = 1;
		}
		if (x + 1 <= 5 && y - 1 >= 0) {
			if (state_temp[x + 1][y - 1] != type)
				step_temp[x + 1][y - 1] = 1;
		}
		if (x - 1 >= 0 && y + 1 <= 5) {
			if (state_temp[x - 1][y + 1] != type)
				step_temp[x - 1][y + 1] = 1;
		}
		if (x + 1 <= 5 && y + 1 <= 5) {
			if (state_temp[x + 1][y + 1] != type)
				step_temp[x + 1][y + 1] = 1;
		}

		fly_dfs(x, y, 0, type, 0);

	}

	// 检测当前分数
	public void getGrade() {
		int computer_grade = 12;
		int human_grade = 12;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++)
				if (myPanel.state[i][j] == -1) {
					human_grade -= 1;
				} else if (myPanel.state[i][j] == 1) {
					computer_grade -= 1;
				}
		}
		black_points_text.setText(computer_grade + "");
		red_points_text.setText(human_grade + "");
	}

	// 获胜检查
	public void CheckWinner() {
		if (black_points_text.getText().equals("12")) {
			int res = JOptionPane.showConfirmDialog(null, "电脑获胜！很遗憾，你落败了！是否再来一局？", "提示", JOptionPane.YES_NO_OPTION);
			if (res == JOptionPane.YES_OPTION) {
				FirstDialog firstDialog = new FirstDialog();
				firstDialog.setVisible(true);
				this.dispose();
			} else {
				return;
			}
		}
		if (red_points_text.getText().equals("12")) {
			int res = JOptionPane.showConfirmDialog(null, "你获胜啦！是否再来一局？", "提示", JOptionPane.YES_NO_OPTION);
			if (res == JOptionPane.YES_OPTION) {
				FirstDialog firstDialog = new FirstDialog();
				firstDialog.setVisible(true);
				this.dispose();
			} else {
				return;
			}
		}
	}

}
