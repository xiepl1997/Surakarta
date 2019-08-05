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
	//public int roadtype;//定义棋子当前所在的轨道，-2既不在蓝色轨道也不再绿色轨道，-1在绿色轨道，0既在蓝色轨道也在绿色轨道，1在蓝色轨道
	
	public Chess(Color c, Point p, int x, int y) {
		this.color = c;
		this.position = p;
		this.x = x;
		this.y = y;
		radius = 30;
		//this.roadtype = setRoadType();
	}
	//获得棋子的所在轨道
	public int getRoadType() {
		
		int roadtype = 0;//定义棋子当前所在的轨道，-2既不在蓝色轨道也不再绿色轨道，-1在绿色轨道，0既在蓝色轨道也在绿色轨道，1在蓝色轨道
		//在四个角
		if((this.x==1&&this.y==1)||(this.x==1&&this.y==6)||(this.x==6&&this.y==1)||(this.x==6&&this.y==6)) {
			return -2;
		}
		//蓝色横线
		if(this.x==2||this.x==5) {
			roadtype += 1;
		}
		//绿色横线
		if(this.x==3||this.x==4) {
			roadtype += -1;
		}
		//蓝色竖线
		if(this.y==2||this.y==5) {
			if(roadtype != 1)
				roadtype += 1;
		}
		//绿色竖线
		if(this.y==3||this.y==4) {
			if(roadtype != -1)
				roadtype += -1;
		}
		return roadtype;
	}
}










