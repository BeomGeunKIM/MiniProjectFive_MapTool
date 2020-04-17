package com.kh.mini.test;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Scrollbar;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
//import javax.swing.JScrollPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.kh.mini.Manager.*;

public class TestMain {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		GameWindow gw = new GameWindow();
		
		MaptoolScene ms = new MaptoolScene();
		ms.init();
		
		gw.setSize(2000, 1200);		
		
		
		
		gw.addKeyListener(KeyManager.Instance());
		gw.addMouseListener(KeyManager.Instance());
		
		
//		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//		scrollPane.setBounds(4, 4, 340, 340);
//		
//		gw.add(scrollPane);
//		
		gw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gw.setVisible(true);		
		
		gw.createBufferStrategy(2);
		
		while(true) {		
			//gs.update();
			ms.update();
			BufferStrategy bs = gw.getBufferStrategy();
			
			Graphics g = bs.getDrawGraphics();
			
			g.clearRect(0, 0, gw.getWidth(), gw.getHeight());
			
			gw.repaint();
			
			ms.render(g);
			
			g.dispose();
			
			bs.show();
		}
		
	}

}
