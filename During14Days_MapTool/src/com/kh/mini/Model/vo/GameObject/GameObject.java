package com.kh.mini.Model.vo.GameObject;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;

import com.kh.mini.Manager.KeyManager;

public abstract class GameObject {

	protected double left;
	protected double top;
	protected double right;
	protected double bottom;
	
	protected double x;
	protected double y;
	
	protected double radius;
	protected static final KeyManager key = KeyManager.Instance();
	
	public abstract void init();
	public abstract void update();
	public abstract void render(Graphics g);
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setRect(double left, double top, double right, double bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;		
	}
	
	public void makeCenterRect(double x, double y, int width, int height) {
		this.x = x;
		this.y = y;
		
		int halfWidth = width / 2;
		int halfHeight = height / 2;
		
		radius = Math.abs((halfWidth * halfWidth) + (halfHeight * halfHeight));
		
		setRect(x - halfWidth, y - halfHeight, x + halfWidth, y + halfHeight);
	}
	
	public void makeCirCle(double x, double y, double radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		
		setRect((int)(x - radius), (int)(y - radius), (int)(x + radius), (int)(y + radius));
	}
	
	public boolean isMouseCollision(int x, int y) {
		boolean isCollision = false;
		
		if(x > left && x < right && y < bottom && y > top) {
			isCollision = true;
			System.out.println("마우스 X : " + x);
			System.out.println("마우스 Y : " + x);
//			System.out.println("좌 : " + left);
//			System.out.println("우 : " + right);
//			System.out.println("상 : " + top);
//			System.out.println("하 : " + bottom);
			//System.out.println("충돌감지!");
		}
		
		return isCollision;
	}
	
	public boolean isCollisionRectToRect(GameObject other) {
		
		if(left > other.right || right < other.left) {
			return false;
		}
		
		if(top > other.bottom || bottom < other.top) {
			return false;
		}
		
		return true;
	}
	
	public boolean isCollisionCirToRect(GameObject other) {		
		if(isCollisionCirToCir(other)) {
			if(isCollisionRectToRect(other)) {
				return true;
			}
		}		
		return false;
	}
	
	public boolean isCollisionCirToCir(GameObject other) {
		if(getDistacne(other) < radius + other.radius) {
			return true;
		}		
		return false;
	}
	
	public double getDistacne(GameObject other) {		
		double x = this.x - other.getX();
		double y = this.y - other.getY();
		
		double dist = Math.sqrt((x * x) + (y * y));
		
		return dist;
	}
	
	public void setPosition(double x, double y) {
		
		//System.out.println(x + ", " + y);
		this.x = x;
		this.y = y;

	}
}
