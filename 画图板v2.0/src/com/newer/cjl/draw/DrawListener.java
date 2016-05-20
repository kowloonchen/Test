package com.newer.cjl.draw;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JPanel;

/**
 * 绘图的监听器，实现鼠标监听器接口 实现接口就必须要将接口中的方法全部重写
 * 
 * @author kowloon
 * 
 */
public class DrawListener implements MouseListener {
	/** 鼠标按下和释放时候的坐标 */
	private int x1, y1, x2, y2;
	private JPanel drawPanel;
	private ButtonGroup shapeGroup;
	private Graphics g;
	private int type = 1;// 0表示直线 1表示矩形 2表示椭圆

	public DrawListener(JPanel dp, ButtonGroup bg1) {
		drawPanel = dp;
		shapeGroup = bg1;
	}

	/** 鼠标按下的监听方法 */
	public void mousePressed(MouseEvent e) {
		// 鼠标按下准备绘制图形之前，先获得允许绘制的区域Graphics
		g = drawPanel.getGraphics();

		// 获得被选中的单选按钮
		ButtonModel bm = shapeGroup.getSelection();
		// 获得按钮的唯一标识
		String str = bm.getActionCommand();
		//根据动作命令设置要绘制的形状
		if (str.equals("0")) {
			type = 0;
		} else if (str.equals("1")) {
			type = 1;
		} else if (str.equals("2")) {
			type = 2;
		}

		x1 = e.getX();
		y1 = e.getY();
	}

	/** 鼠标松开的监听方法 */
	public void mouseReleased(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		if (type == 0) {
			g.drawLine(x1, y1, x2, y2);
		} else if (type == 1) {
			g.drawRect(x1 < x2 ? x1 : x2, y1 < y2 ? y1 : y2, x1 < x2 ? x2 - x1
					: x1 - x2, y1 < y2 ? y2 - y1 : y1 - y2);
		} else if (type == 2) {
			g.drawOval(x1 < x2 ? x1 : x2, y1 < y2 ? y1 : y2, x1 < x2 ? x2 - x1
					: x1 - x2, y1 < y2 ? y2 - y1 : y1 - y2);
		}
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}
}
