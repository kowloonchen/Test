package com.newer.cjl.draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class StatePanel extends JPanel{
	
	private String moveStr = "";
	private String dragStr = "";
	
	public void setMoveStr(String str){
		moveStr = str;
	}
	
	public void setDragStr(String str){
		dragStr = str;
	}
	
	
	public void paint(Graphics g) {
		super.paint(g);
		//获得面板的大小
		Dimension dim = getSize();
		int width = dim.width;
		int height = dim.height;
		
		//绘制边框
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(0, 0, width, height);
		
		//绘制分割线
		g.drawLine(width-200, 0, width-200, height);
		g.drawLine(width-100, 0, width-100, height);
		
		g.setColor(Color.BLACK);
		g.drawString("仅供学习使用", 5, 15);

		g.drawString(moveStr, width-190, 15);
		g.drawString(dragStr, width-90, 15);
		
	}

}
