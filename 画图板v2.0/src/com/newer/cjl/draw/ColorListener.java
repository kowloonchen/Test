package com.newer.cjl.draw;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

/**
 * 颜色选择的监听器
 * 
 * @author kowloon
 * 
 */
public class ColorListener implements MouseListener {
	private JLabel frontLabel;
	private JLabel backLabel;

	public ColorListener(JLabel fl, JLabel bl) {
		frontLabel = fl;
		backLabel = bl;
	}

	public void mouseReleased(MouseEvent e) {
		// 获得被选中控件的背景颜色
		Object obj = e.getSource();
		if (obj instanceof JLabel) {
			JLabel label = (JLabel) obj;
			Color color = label.getBackground();

			int button = e.getButton();
			if (button == MouseEvent.BUTTON1) {
				frontLabel.setBackground(color);
			} else if (button == MouseEvent.BUTTON3) {
				backLabel.setBackground(color);
			}

		}

	}

	public void mouseEntered(MouseEvent e) {
		// 获得事件源对象[事件是发生在哪一个控件上的，这个控件就是事件源]
		// 由于这个监听器只加给了JLabel对象，所以事件源对象一定是JLabel类型的
		// 所以一定可以强制转成JLabel类型
		// 如果不能确定对象的具体类型，就需要判断对象的类型
		Object obj = e.getSource();
		if (obj instanceof JLabel) {
			JLabel label = (JLabel) obj;
			label.setBorder(new LineBorder(Color.BLUE));
		}

	}

	public void mouseExited(MouseEvent e) {
		Object obj = e.getSource();
		if (obj instanceof JLabel) {
			JLabel label = (JLabel) obj;
			label.setBorder(new LineBorder(Color.LIGHT_GRAY));
		}
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

}
