package com.surakarta;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

public class MyPanel extends JPanel{

	//36个点
	private Point[][] points= {
			{new Point(140, 140),new Point(210,140),new Point(280,140),new Point(350,140),new Point(420, 140),new Point(490,140)},
			{new Point(140, 210),new Point(210,210),new Point(280,210),new Point(350,210),new Point(420, 210),new Point(490,210)},
			{new Point(140, 280),new Point(210,280),new Point(280,280),new Point(350,280),new Point(420, 280),new Point(490,280)},
			{new Point(140, 350),new Point(210,350),new Point(280,350),new Point(350,350),new Point(420, 350),new Point(490,350)},
			{new Point(140, 420),new Point(210,420),new Point(280,420),new Point(350,420),new Point(420, 420),new Point(490,420)},
			{new Point(140, 490),new Point(210,490),new Point(280,490),new Point(350,490),new Point(420, 490),new Point(490,490)},
	};
	
	//12个黑子位置
	public static Point[][] blackChess = {
		{new Point(140, 140),new Point(210,140),new Point(280,140),new Point(350,140),new Point(420, 140),new Point(490,140)},
		{new Point(140, 210),new Point(210,210),new Point(280,210),new Point(350,210),new Point(420, 210),new Point(490,210)}
	};
	//12个白字位置
	public static Point[][] whiteChess = {
		{new Point(140, 420),new Point(210,420),new Point(280,420),new Point(350,420),new Point(420, 420),new Point(490,420)},
		{new Point(140, 490),new Point(210,490),new Point(280,490),new Point(350,490),new Point(420, 490),new Point(490,490)},
	};
	
	public MyPanel() {
		super();
	}

	//界面绘制
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		setSize(630,630);
		setBackground(Color.LIGHT_GRAY);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(5.0f));
		
		//画六条横线
		for(int i = 0; i < 6; i++) {
			if(i == 0 || i == 5)
				g2.setColor(Color.yellow);
			else if(i == 1 || i == 4)
				g2.setColor(Color.blue);
			else
				g2.setColor(Color.green);
			g2.drawLine(points[i][0].x, points[i][0].y, points[i][5].x, points[i][5].y);
		}
		//画六条竖线
		for(int i = 0; i < 6; i ++) {
			if(i == 0 || i == 5)
				g2.setColor(Color.yellow);
			else if(i == 1 || i == 4)
				g2.setColor(Color.blue);
			else
				g2.setColor(Color.green);
			g2.drawLine(points[0][i].x, points[0][i].y, points[5][i].x, points[5][i].y);
		}
		
		//画8个3/4圆
		g.setColor(Color.green);
		g.drawArc(0, 0, 280, 280, 0, 270);
		g.drawArc(350, 0, 280, 280, -90, 270);
		g.drawArc(0, 350, 280, 280, 90, 270);
		g.drawArc(350, 350, 280, 280, 180, 270);
		g.setColor(Color.blue);
		g.drawArc(70, 70, 140, 140, 0, 270);
		g.drawArc(420, 70, 140, 140, -90, 270);
		g.drawArc(70, 420, 140, 140, 90, 270);
		g.drawArc(420, 420, 140, 140, 180, 270);
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 6; j++) {
				
			}
		}
		
	}
	
}









