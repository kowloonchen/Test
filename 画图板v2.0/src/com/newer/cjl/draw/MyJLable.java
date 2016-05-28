package com.newer.cjl.draw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;

public class MyJLable extends JLabel {

	public void paint(Graphics g) {
		super.paint(g);

		Dimension dim = getSize();
		int width = dim.width;
		int height = dim.height;
		
		g.setColor(Color.WHITE);
		g.drawRect(1, 1, width-3, height-3);
		

	}

}
