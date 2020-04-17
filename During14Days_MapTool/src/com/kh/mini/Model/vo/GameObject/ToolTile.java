package com.kh.mini.Model.vo.GameObject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import com.kh.mini.Manager.ImageClass;

public class ToolTile extends GameObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	transient ImageClass img;
	
	private String path;
	
	private double imgX;
	private double imgY;
	
	int wSize;
	int hSize;
	
	private transient CameraClass cam;
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		img = new ImageClass();
		cam = new CameraClass();
		this.makeCenterRect(x, y, wSize, hSize);
	}
	
//	public void init(ToolTile tt) {
//		this.img = new ImageClass();
//		this.img.Init(tt.getPath());
//		this.path = tt.getPath();
//		this.imgX = tt.getImgX();
//		this.imgY = tt.getImgY();
//		this.wSize = tt.getwSize();
//		this.hSize = tt.gethSize();
//		this.makeCenterRect(tt.imgX, tt.imgY, tt.wSize, tt.hSize);
//		this.setPosition(imgX, imgY);
//	}
	
	public void init(BufferedImage img) {
		this.img = new ImageClass();
		this.img.Init(img);
		this.wSize = this.img.getWidth();
		this.hSize = this.img.getHeight();
		this.makeCenterRect(x, y, wSize, hSize);
		this.img.setPosition(left, top);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		img.setPosition(left - cam.getX(), top - cam.getY());
		img.render(g);
	}

	public int getwSize() {
		return wSize;
	}
	
	public int gethSize() {
		return hSize;
	}
	
	public void ImageChange(BufferedImage img) {
		//this.img.setImage(img);
		this.img.setImage(img);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public double getImgX() {
		return imgX;
	}

	public void setImgX(double imgX) {
		this.imgX = imgX;
	}

	public double getImgY() {
		return imgY;
	}

	public void setImgY(double imgY) {
		this.imgY = imgY;
	}
	
	@Override
	public void setPosition(double x, double y) {
		super.setPosition(x, y);
		this.imgX = x;
		this.imgY = y;
		
		this.makeCenterRect(x, y, wSize, hSize);
	}
	
	public void setImageLoad() {
		this.img = new ImageClass();		
		this.img.Init(this.path);
		this.wSize = this.img.getWidth();
		this.hSize = this.img.getHeight();
		x = imgX;
		y = imgY;
		this.makeCenterRect(x, y, wSize, hSize);
//		System.out.println("L : " + left + " T : " + top);
		img.setPosition(left, top);
	}
	
	public void setCam(CameraClass cam) {
		this.cam = cam;
	}
	
}
