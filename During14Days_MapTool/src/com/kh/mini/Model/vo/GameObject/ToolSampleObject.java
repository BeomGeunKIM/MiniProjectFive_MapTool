package com.kh.mini.Model.vo.GameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.kh.mini.Manager.ImageClass;

public class ToolSampleObject extends GameObject {

	ImageClass img;
	private String path;
	
	int wSize = 128;
	int hSize = 128;
	
	@Override
	public void init() {
		// TODO Auto-generated method stub		
		img = new ImageClass();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		img.render(g);
	}

	public int getwSize() {
		return wSize;
	}
	
	public int gethSize() {
		return hSize;
	}	
	
	public BufferedImage getImage() {
		return img.getImage();
	}

	public void setImage(String path) {
		img.Init(path);
		this.path = path;
		this.wSize = img.getWidth();
		this.hSize = img.getHeight();
		this.makeCenterRect(x, y, wSize, hSize);
		img.setPosition(left, top);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
