package com.kh.mini.Model.vo.GameObject.Monster;

import java.awt.Graphics;
import java.io.Serializable;

import com.kh.mini.Manager.ImageClass;
import com.kh.mini.Model.vo.GameObject.CameraClass;
import com.kh.mini.Model.vo.GameObject.GameObject;

public abstract class ToolMonster extends GameObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3288630545407917133L;
	protected transient ImageClass img;
	protected String imgPath;
	
	private double saveX;
	private double saveY;
	
	private transient CameraClass cam;
	
	@Override
	public abstract void init();

	@Override
	public abstract void update();

	@Override
	public abstract void render(Graphics g);

	public void setPath(String path) {
		this.imgPath = path;
	}
	
	public String getPath() {
		return imgPath;
	}	
	
	@Override
	public void setPosition(double x, double y) {
		super.setPosition(x, y);
		saveX = this.x;
		saveY = this.y;
		this.makeCenterRect(x, y, img.getImage().getWidth(), img.getImage().getHeight());
		img.setPosition(left, top);
	}
	
	public void imageLoad() {
		
	}
	
	public void setCam(CameraClass cam) {
		this.cam = cam;
	}
}
