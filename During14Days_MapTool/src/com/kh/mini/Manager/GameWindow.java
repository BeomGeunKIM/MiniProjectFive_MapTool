package com.kh.mini.Manager;

import java.awt.Graphics;

import javax.swing.JFrame;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GameWindow extends JFrame {

	//BufferedImage fImg;
	//BufferedImage bImg;
	
	BufferStrategy bs;
	
	ImageClass img;
	public void Init() {
		
	}
	
	int frame = 0;
	int count = 0;
	
	public void paint(Graphics g) {

	}
	
	public void update(Graphics g) {
		System.out.println("¾÷µ« È£Ãâ");
		paint(g);
	}
	
	public void destroy() {
		
	}
	
	
	
}
