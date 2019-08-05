package com.surakarta;

import java.awt.Color;
import java.awt.Point;

//可落子的选择
public class Step {
	public Color color;
	public Point position;
	public int radius;
	public int x,y;
	
	Step(Point p, int x, int y) {
		this.color = Color.gray;
		this.position = p;
		this.x = x;
		this.y = y;
		radius = 15;
	}
}
