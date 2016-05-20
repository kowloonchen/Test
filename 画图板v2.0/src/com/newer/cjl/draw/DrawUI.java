package com.newer.cjl.draw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

/**
 * 画图板主窗口类
 * 
 * @author kowloon
 * 
 */
public class DrawUI {

	/**
	 * 初始化主窗口的方法
	 */
	public void initDrawUI() {
		// 创建窗口对象
		JFrame jf = new JFrame();
		jf.setTitle("画图板");
		jf.setSize(600, 500);
		jf.setDefaultCloseOperation(3);

		//设置窗口的布局规则为流式边框布局
		BorderLayout border = new BorderLayout();
		jf.setLayout(border);
		
		//**********************三个主要面板*******************************//
		//创建三个面板对象
		JPanel left = new JPanel();
		JPanel center = new JPanel();
		JPanel foot = new JPanel();
		
		//创建颜色对象
		Color c = new Color(240,240,240);
		
		//设置面板大小和背景色
		Dimension leftDim = new Dimension(80,1);
		left.setPreferredSize(leftDim);
		left.setBackground(c);
		//给左边的面板设置边框
		LineBorder line = new LineBorder(Color.GRAY);
		left.setBorder(line);
		
		center.setBackground(Color.GRAY);
		
		Dimension footDim = new Dimension(1,80);
		foot.setPreferredSize(footDim);
		foot.setBackground(c);
		
		jf.add(left,BorderLayout.WEST);
		jf.add(center,BorderLayout.CENTER);
		jf.add(foot,BorderLayout.SOUTH);
		

		//********************绘图面板*********************************//
		
		//创建一个用来绘图的面板
		JPanel drawPanel = new JPanel();
		Dimension drawPanelDim = new Dimension(400,300);
		drawPanel.setPreferredSize(drawPanelDim);
		drawPanel.setBackground(Color.WHITE);
		
		//指定center面板的布局为流式布局左对齐
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT);
		center.setLayout(flow);
		
		//将绘图面板加到中间面板上
		center.add(drawPanel);
		
		//*****************坐标形状选择面板************************************//
		//创建形状按钮组
		ButtonGroup shapeGroup = new ButtonGroup();
		String[] shapes = {"直线","矩形","椭圆"};
		for(int i=0;i<shapes.length;i++){
			//创建单选按钮
			JRadioButton btn = new JRadioButton(shapes[i]);
			//设置的作命令[唯一标识]
			btn.setActionCommand(i+"");
			//添加到分组
			shapeGroup.add(btn);
			//添加到左边面板
			left.add(btn);
			if(i==0){//默认选中第一个按钮
				btn.setSelected(true);
			}
		}
		
		
		
		jf.setVisible(true);
		
		//创建监听器对象
		DrawListener dlis = new DrawListener(drawPanel,shapeGroup);
		//给drawPanel添加鼠标监听器
		drawPanel.addMouseListener(dlis);
	}
}
