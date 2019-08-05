package com.surakarta;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

//棋盘
public class MyPanel extends JPanel{

	//36个点
	public Point[][] points= {
		{new Point(140, 140),new Point(210,140),new Point(280,140),new Point(350,140),new Point(420, 140),new Point(490,140)},
		{new Point(140, 210),new Point(210,210),new Point(280,210),new Point(350,210),new Point(420, 210),new Point(490,210)},
		{new Point(140, 280),new Point(210,280),new Point(280,280),new Point(350,280),new Point(420, 280),new Point(490,280)},
		{new Point(140, 350),new Point(210,350),new Point(280,350),new Point(350,350),new Point(420, 350),new Point(490,350)},
		{new Point(140, 420),new Point(210,420),new Point(280,420),new Point(350,420),new Point(420, 420),new Point(490,420)},
		{new Point(140, 490),new Point(210,490),new Point(280,490),new Point(350,490),new Point(420, 490),new Point(490,490)},
	};
	//棋盘状态
	public int state[][] = {
		{-1,-1,-1,-1,-1,-1},
		{-1,-1,-1,-1,-1,-1},
		{0,0,0,0,0,0},
		{0,0,0,0,0,0},
		{1,1,1,1,1,1},
		{1,1,1,1,1,1}
	};
	//标记所选棋子的可落子点，1位为可落子
	public int step[][] = {
			{0,0,0,0,0,0},
			{0,0,0,0,0,0},
			{0,0,0,0,0,0},
			{0,0,0,0,0,0},
			{0,0,0,0,0,0},
			{0,0,0,0,0,0},
	};
	
	public ArrayList<Chess> chessList = new ArrayList<Chess>();
	public ArrayList<Step> stepList = new ArrayList<Step>();
	
	public MyPanel() {
		super();
	}

	//棋盘重绘
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		setSize(630,630);
		setBackground(Color.LIGHT_GRAY);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(6.0f));
		
		//绘制6条横线
		for(int i = 0; i < 6; i++) {
			if(i == 0 || i == 5)
				g2.setColor(Color.yellow);
			else if(i == 1 || i == 4)
				g2.setColor(Color.blue);
			else
				g2.setColor(Color.green);
			g2.drawLine(points[i][0].x, points[i][0].y, points[i][5].x, points[i][5].y);
		}
		//绘制6条竖线
		for(int i = 0; i < 6; i ++) {
			if(i == 0 || i == 5)
				g2.setColor(Color.yellow);
			else if(i == 1 || i == 4)
				g2.setColor(Color.blue);
			else
				g2.setColor(Color.green);
			g2.drawLine(points[0][i].x, points[0][i].y, points[5][i].x, points[5][i].y);
		}
		
		//8个3/4圆
		g2.setColor(Color.green);
		g2.drawArc(0, 0, 280, 280, 0, 270);
		g2.drawArc(350, 0, 280, 280, -90, 270);
		g2.drawArc(0, 350, 280, 280, 90, 270);
		g2.drawArc(350, 350, 280, 280, 180, 270);
		g2.setColor(Color.blue);
		g2.drawArc(70, 70, 140, 140, 0, 270);
		g2.drawArc(420, 70, 140, 140, -90, 270);
		g2.drawArc(70, 420, 140, 140, 90, 270);
		g2.drawArc(420, 420, 140, 140, 180, 270);
		
		//绘制棋子
		chessList.clear();
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(state[i][j] == -1) {
					Chess chess = new Chess(Color.black, points[i][j],i+1,j+1);
					chessList.add(chess);
					g2.setColor(chess.color);
					g2.fillOval(points[i][j].x-chess.radius, points[i][j].y-chess.radius, chess.radius*2, chess.radius*2);
				}
				else if(state[i][j] == 1){
					Chess chess1 = new Chess(Color.red, points[i][j],i+1,j+1);
					chessList.add(chess1);
					g2.setColor(chess1.color);
					g2.fillOval(points[i][j].x-chess1.radius, points[i][j].y-chess1.radius, chess1.radius*2, chess1.radius*2);
				}
				if(step[i][j] == 1) {
					Step step = new Step(points[i][j], i+1, j+1);
					stepList.add(step);
					g2.setColor(step.color);
					g2.fillOval(points[i][j].x-step.radius, points[i][j].y-step.radius, step.radius*2, step.radius*2);
				}
			}
		}
		
	}
	
}









