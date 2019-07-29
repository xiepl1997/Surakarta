package com.surakarta;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

import javax.swing.JButton;

//棋子定义
public class Chess{
	public Color color;  //颜色
	public Point position;//位置（像素位置）
	//public int g; //定义棋子在外圈还是内圈，2为外圈，1位内圈
	public int radius; //定义棋子半径，本程序中设置为30px
	public int x;//在棋盘中的行数
	public int y;//在棋盘中的列数
	
	public Chess(Color c, Point p, int x, int y) {
		this.color = c;
		this.position = p;
		this.x = x;
		this.y = y;
		radius = 30;
		
	}
}










