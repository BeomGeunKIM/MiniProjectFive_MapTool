package com.kh.mini.Manager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

public class KeyManager implements KeyListener, MouseListener {

	private static KeyManager inst;
	
	private HashMap<Integer, Boolean> keyMap = new HashMap<Integer, Boolean>();
	
	private HashMap<Integer, Boolean> mouseMap = new HashMap<Integer, Boolean>();
	
	private boolean isMouseEnter = false;
	private int x;
	private int y;
	
	public static KeyManager Instance() {
		
		if(inst != null) {
			return inst;	
		} else {
			inst = new KeyManager();
			return inst;
		}
	}

	boolean isUp = true;
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(keyMap.containsKey(e.getKeyCode()) == false || keyMap.get(e.getKeyCode()) == false)
		{
			keyMap.put(e.getKeyCode(), true);	
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		if(keyMap.containsKey(e.getKeyCode()) == true && keyMap.get(e.getKeyCode()) == true)
		{
			isUp = true;
			keyMap.put(e.getKeyCode(), false);
		}		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {		
	
	}	
	
	public boolean stayKeyDown(int keyCode) {
		boolean isPressKey = false;
		
		if(keyMap.containsKey(keyCode) == true && keyMap.get(keyCode) == true) {
			isPressKey = true;
		}
		
		return isPressKey;
	}
	
	public boolean onceKeyDown(int keyCode) {
		boolean isPressKey = false;
		
		if(keyMap.containsKey(keyCode) && keyMap.get(keyCode) == true && isUp == true) {
			isPressKey = true;
			keyMap.put(keyCode, false);			
			isUp = false;
		}
		
		return isPressKey;
	}
	
	public boolean keyUp(int keyCode) {
		boolean isUpKey = false;
		
		if(keyMap.containsKey(keyCode) == true && keyMap.get(keyCode) == false) {
			isUpKey = true;
		}	
		
		return isUpKey;
	}
	
	public boolean stayButtonDown(int keyCode) {
		boolean isPressBtn = false;
		if(mouseMap.containsKey(keyCode) == true && mouseMap.get(keyCode) == true) {			
			isPressBtn = true;
		}				
		
		return isPressBtn;		
	}
	
	public boolean oncebuttonDown(int keyCode) {
		boolean isPressKey = false;
		if(mouseMap.containsKey(keyCode) && mouseMap.get(keyCode) == true && isUp == true) {
			isPressKey = true;
			mouseMap.put(keyCode, false);			
			isUp = false;
		}
		
		return isPressKey;
	}
	
	public boolean buttonUp(int keyCode) {
		boolean isUpBtn = false;
		
		if(mouseMap.get(keyCode) == false) {
			isUpBtn = true;
		}		
		
		return isUpBtn;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		int eventCode = e.getButton();
		
		if(eventCode == e.BUTTON1 || eventCode ==e.BUTTON2 || eventCode == e.BUTTON3) {
			if(mouseMap.containsKey(eventCode) == false || mouseMap.get(eventCode) == false)
			{				
				mouseMap.put(eventCode, true);	
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		int eventCode = e.getButton();		
		if(eventCode == e.BUTTON1 || eventCode == e.BUTTON2 || eventCode == e.BUTTON3) {
			if(mouseMap.get(eventCode) == true)
			{
				isUp = true;
				mouseMap.put(eventCode, false);	
			}
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		isMouseEnter = true;
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		isMouseEnter = false;
		//x = e.getX();
		//y = e.getY();
	}
	
	public boolean IsMouseEnter() {
		return isMouseEnter;
	}
	
	public int getMouseX() {
		return x;
	}
	
	public int getMouseY() {
		return y;
	}
	
}

