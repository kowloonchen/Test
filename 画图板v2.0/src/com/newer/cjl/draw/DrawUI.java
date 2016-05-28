package com.newer.cjl.draw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

		// 设置窗口的布局规则为流式边框布局
		BorderLayout border = new BorderLayout();
		jf.setLayout(border);

		// **********************三个主要面板*******************************//
		// 创建三个面板对象
		JPanel left = new JPanel();
		JPanel center = new JPanel();
		JPanel foot = new JPanel();

		// 设置面板大小和背景色
		Dimension leftDim = new Dimension(80, 1);
		left.setPreferredSize(leftDim);
		left.setBackground(new Color(220, 220, 220));
		// 给左边的面板设置边框
		LineBorder line = new LineBorder(Color.GRAY);
		left.setBorder(line);

		center.setBackground(Color.GRAY);

		Dimension footDim = new Dimension(1, 80);
		foot.setPreferredSize(footDim);
		foot.setBackground(new Color(220, 220, 220));

		jf.add(left, BorderLayout.WEST);
		jf.add(center, BorderLayout.CENTER);
		jf.add(foot, BorderLayout.SOUTH);

		// ********************绘图面板*********************************//

		// 创建一个用来绘图的面板
		JPanel drawPanel = new JPanel();
		Dimension drawPanelDim = new Dimension(400, 300);
		drawPanel.setPreferredSize(drawPanelDim);
		drawPanel.setBackground(Color.WHITE);

		// 指定center面板的布局为流式布局左对齐
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT);
		center.setLayout(flow);

		// 将绘图面板加到中间面板上
		center.add(drawPanel);

		// *****************左边形状选择面板************************************//
		// 创建形状按钮组
		ButtonGroup shapeGroup = new ButtonGroup();
		for (int i = 0; i < 16; i++) {
			// 创建单选按钮
			JRadioButton btn = new JRadioButton();
			// 设置的作命令[唯一标识]
			btn.setActionCommand(i + "");
			// 添加到分组
			shapeGroup.add(btn);

			URL imgUrl = DrawUI.class.getResource("images/draw" + i + ".jpg");
			ImageIcon icon = new ImageIcon(imgUrl);

			URL imgUrl1 = DrawUI.class
					.getResource("images/draw" + i + "-1.jpg");
			ImageIcon icon1 = new ImageIcon(imgUrl1);

			URL imgUrl2 = DrawUI.class
					.getResource("images/draw" + i + "-2.jpg");
			ImageIcon icon2 = new ImageIcon(imgUrl2);

			URL imgUrl3 = DrawUI.class
					.getResource("images/draw" + i + "-3.jpg");
			ImageIcon icon3 = new ImageIcon(imgUrl3);

			// 给单选按钮贴图片
			btn.setIcon(icon);
			btn.setRolloverIcon(icon1);
			btn.setPressedIcon(icon2);
			btn.setSelectedIcon(icon3);

			// 添加到左边面板
			left.add(btn);

			if (i == 6) {// 默认选中第6个按钮
				btn.setSelected(true);
			}
		}

		// *****************底部颜色选择和状态栏面板************************************//
		// 将底部面板定义为边框布局
		foot.setLayout(new BorderLayout());
		// 颜色选择面板
		JPanel colorPanel = new JPanel();
		colorPanel.setBackground(new Color(220, 220, 220));
		foot.add(colorPanel, BorderLayout.CENTER);

		// -------------------颜色选择区域----------------------------//

		colorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel frontLabel = new JLabel();
		JLabel backLabel = new JLabel();

		frontLabel.setPreferredSize(new Dimension(55, 55));
		frontLabel.setBackground(Color.GREEN);
		// 是否允许显示背景色
		frontLabel.setOpaque(true);

		backLabel.setPreferredSize(new Dimension(55, 55));
		backLabel.setBackground(Color.MAGENTA);
		// 是否允许显示背景色
		backLabel.setOpaque(true);

		colorPanel.add(frontLabel);
		colorPanel.add(backLabel);

		// 面板
		JPanel selectPanel = new JPanel();
		selectPanel.setPreferredSize(new Dimension(255, 55));
		colorPanel.add(selectPanel);

		//创建一个选择颜色的监听器
		ColorListener clis = new ColorListener(frontLabel,backLabel);
		//基本颜色数组
		Color[] colors = { Color.BLACK, Color.BLUE, Color.CYAN,
				Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY,
				Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED,
				Color.WHITE, Color.YELLOW, new Color(128, 255, 0),
				new Color(64, 128, 128), new Color(64, 64, 128),
				new Color(0, 64, 128), new Color(64, 0, 128),
				new Color(128, 0, 64), new Color(0, 64, 192) };
		for (int i = 0; i < colors.length; i++) {
			MyJLable colorLabel = new MyJLable();
			colorLabel.setPreferredSize(new Dimension(20, 20));
			colorLabel.setBackground(colors[i]);
			colorLabel.setOpaque(true);
			//设置边框
			colorLabel.setBorder(new LineBorder(Color.LIGHT_GRAY));
			colorLabel.addMouseListener(clis);
			selectPanel.add(colorLabel);

		}

		// ---------------------------------------------//
		// 状态栏面板
		StatePanel statePanel = new StatePanel();
		// 指定底部的高度
		statePanel.setPreferredSize(new Dimension(1, 20));
		statePanel.setBackground(new Color(235, 235, 235));
		foot.add(statePanel, BorderLayout.SOUTH);

		jf.setVisible(true);

		// 创建监听器对象
		DrawListener dlis = new DrawListener(drawPanel, shapeGroup, statePanel,
				frontLabel, backLabel);
		// 给drawPanel添加鼠标监听器
		drawPanel.addMouseListener(dlis);
		drawPanel.addMouseMotionListener(dlis);
	}
}
