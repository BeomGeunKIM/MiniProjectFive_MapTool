package com.kh.mini.Model.vo.GameObject.Monster;

import java.awt.Graphics;

import com.kh.mini.Manager.ImageClass;
import com.kh.mini.Model.vo.GameObject.CameraClass;

public class ToolMonster5 extends ToolMonster {

	CameraClass cam;
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		img = new ImageClass();
		img.Init(imgPath);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		img.setPosition(x - cam.getX(), y - cam.getY());
		img.render(g);
	}
	
	@Override
	public void setPosition(double x, double y) {
		super.setPosition(x, y);
		this.makeCenterRect(x, y, img.getImage().getWidth(), img.getImage().getHeight());
		img.setPosition(left, top);
	}

	public void setCam(CameraClass cam) {
		this.cam = cam;
	}
	
}
