package com.kh.mini.Model.vo.GameObject;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.kh.mini.Manager.KeyManager;

public class CameraClass extends GameObject {

	@Override
	public void init() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(key.stayKeyDown(KeyEvent.VK_LEFT)) {
			x -= 1f;
		}
		
		if(key.stayKeyDown(KeyEvent.VK_RIGHT)) {
			x += 1f;
		}
		
		if(key.stayKeyDown(KeyEvent.VK_DOWN)) {
			//y = 0.005;
			y += 1f;
		}
		
		if(key.stayKeyDown(KeyEvent.VK_UP)) {
			//y = 0.005;
			 y -= 1f;
		}
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
