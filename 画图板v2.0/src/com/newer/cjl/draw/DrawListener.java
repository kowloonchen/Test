package com.newer.cjl.draw;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * 绘图的监听器，实现鼠标监听器接口 实现接口就必须要将接口中的方法全部重写
 * 
 * @author kowloon
 * 
 */
public class DrawListener implements MouseListener, MouseMotionListener {
	/** 鼠标按下和释放时候的坐标 */
	private int x1, y1, x2, y2;
	private JPanel drawPanel;
	private ButtonGroup shapeGroup;
	private StatePanel statePanel;
	private Graphics g;
	private int type = 1;
	private JLabel frontLabel;
	private JLabel backLabel;
	
	private Color frontColor = Color.BLACK;//前景色
	private Color backColor = Color.RED;//背景色 
	
	
	URL url = DrawListener.class.getResource("images/cursorPen.gif");
	ImageIcon icon = new ImageIcon(url);
	Image img = icon.getImage();
	Point hotSpot = new Point(31, 31);
	
	Toolkit tk = Toolkit.getDefaultToolkit();
	

	public DrawListener(JPanel dp, ButtonGroup bg1, StatePanel sp,JLabel fl,JLabel bl) {
		drawPanel = dp;
		shapeGroup = bg1;
		statePanel = sp;
		frontLabel = fl;
		backLabel = bl;
	}

	/** 鼠标按下的监听方法 */
	public void mousePressed(MouseEvent e) {
		// 鼠标按下准备绘制图形之前，先获得允许绘制的区域Graphics
		g = drawPanel.getGraphics();
		
		//确定前景色和背景色
		frontColor = frontLabel.getBackground();
		backColor = backLabel.getBackground();
		
		//获得按下的是鼠标的左键还是右键
		int button = e.getButton();
		//确定要绘制的颜色
		if(button==MouseEvent.BUTTON1){//如果按下的是左键
			g.setColor(frontColor);
		}else if(button==MouseEvent.BUTTON3){
			g.setColor(backColor);
		}else{
			g.setColor(frontColor);
		}
		
		
		x1 = e.getX();
		y1 = e.getY();
	}

	/** 鼠标松开的监听方法 */
	public void mouseReleased(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		if (type == 10) {
			g.drawLine(x1, y1, x2, y2);
		} else if (type == 12) {
			g.drawRect(x1 < x2 ? x1 : x2, y1 < y2 ? y1 : y2, x1 < x2 ? x2 - x1
					: x1 - x2, y1 < y2 ? y2 - y1 : y1 - y2);
		} else if (type == 14) {
			g.drawOval(x1 < x2 ? x1 : x2, y1 < y2 ? y1 : y2, x1 < x2 ? x2 - x1
					: x1 - x2, y1 < y2 ? y2 - y1 : y1 - y2);
		}
	}

	public void mouseEntered(MouseEvent e) {
		// 获得被选中的单选按钮
		ButtonModel bm = shapeGroup.getSelection();
		// 获得按钮的唯一标识
		String str = bm.getActionCommand();
		System.out.println("str:" + str);
		// 将纯数字字符串转成int
		type = Integer.parseInt(str);

		// 鼠标进入绘制区域，就根据要绘制的形状改变光标的形状
		Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
		if (type == 10) {
			cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
		} else if (type == 6) {
			cursor = tk.createCustomCursor(img, hotSpot, "pen");
		}
		drawPanel.setCursor(cursor);

	}

	public void mouseExited(MouseEvent e) {
		statePanel.setMoveStr("");
		statePanel.setDragStr("");
		// 让statePanel执行paint方法
		statePanel.repaint();
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		String str = (x - x1) + "," + (y - y1);
		statePanel.setDragStr(str);
		statePanel.repaint();

	}

	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		String str = x + "," + y;
		statePanel.setMoveStr(str);
		// 让statePanel执行paint方法
		statePanel.repaint();

	}
}
