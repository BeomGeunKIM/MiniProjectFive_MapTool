package com.kh.mini.Manager;

import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;

import com.kh.mini.Model.vo.GameObject.CameraClass;
import com.kh.mini.Model.vo.GameObject.ToolSampleObject;
import com.kh.mini.Model.vo.GameObject.Monster.ToolBoss;
import com.kh.mini.Model.vo.GameObject.Monster.ToolMonster;
import com.kh.mini.Model.vo.GameObject.Monster.ToolMonster1;
import com.kh.mini.Model.vo.GameObject.Monster.ToolMonster2;
import com.kh.mini.Model.vo.GameObject.Monster.ToolMonster3;
import com.kh.mini.Model.vo.GameObject.Monster.ToolMonster4;
import com.kh.mini.Model.vo.GameObject.Monster.ToolMonster5;

public class ToolMonsterManager implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -991933461412003861L;

	ArrayList<ToolMonster> list = new ArrayList<ToolMonster>();
	
	int sampleIndex = -1;
	transient PointerInfo pointerInfo;
	
	ToolSampleObject[] monsterSample = new ToolSampleObject[6];
	
	transient TileManager tm;
	
	private transient CameraClass cam;
	
	public void Init() {		
		for(int i = 0; i < monsterSample.length; i++) {
			monsterSample[i] = new ToolSampleObject();
			if(i == 5) {
				monsterSample[i].setPosition(1620, 1000);
			} else {
				monsterSample[i].setPosition(1620, i * 160 + (128 / 2));
			}
			monsterSample[i].init();			
		}
		
		monsterSample[0].setImage("images\\Monster\\Sprite-Mon3 1.png");
		monsterSample[1].setImage("images\\Monster\\Sprite-Mon4 1.png");
		monsterSample[2].setImage("images\\Monster\\Sprite-Mon5 15.png");
		monsterSample[3].setImage("images\\Monster\\Sprite-Mon2_Front 1.png");
		monsterSample[4].setImage("images\\Monster\\Group 26.png");
		monsterSample[5].setImage("images\\Monster\\4F Boss_2 2.png");
	}
	
	public void setMonster(int x, int y) {
	
		ToolMonster temp = null;
		switch(sampleIndex) {
		case 0:
			temp = new ToolMonster1();
			break;
		case 1:
			temp = new ToolMonster2();
			break;
		case 2:
			temp = new ToolMonster3();
			break;
		case 3:
			temp = new ToolMonster4();
			break;
		case 4:
			temp = new ToolMonster5();
			break;
		case 5:
			temp = new ToolBoss();
			break;
		}
		
		temp.setCam(cam);		
		temp.setPath(monsterSample[sampleIndex].getPath());
		temp.init();
		temp.setPosition(x, y);
		
		list.add(temp);
	}
	
	public void update() {
		pointerInfo	= MouseInfo.getPointerInfo();
		
		//if(KeyManager.Instance().oncebuttonDown(MouseEvent.BUTTON1)) {
		if(KeyManager.Instance().stayButtonDown(MouseEvent.BUTTON1)) {
			for(int i = 0; i < monsterSample.length; i++) {
				if(monsterSample[i].isMouseCollision(pointerInfo.getLocation().x, pointerInfo.getLocation().y)) {					
					sampleIndex = i;
					break;
				}
			}
			
		}
		
		if(sampleIndex == -1) {
			return;	
		}
		tm.setSampleIndex(-1);
		//stayButtonDown
		if(KeyManager.Instance().stayButtonDown(MouseEvent.BUTTON1)) {
		//if(KeyManager.Instance().oncebuttonDown(MouseEvent.BUTTON1)) {	
			System.out.println("儅撩");
			int mx = pointerInfo.getLocation().x;
			int my = pointerInfo.getLocation().y;
			
			
			System.out.println("mx : " + mx + " my : " + my);
			boolean isColMonster = false;
			
			for(int i = 0; i < monsterSample.length; i++) {
				if(monsterSample[i].isMouseCollision(mx, my)) {
					//System.out.println("關給");
					isColMonster = true;
					System.out.println(i);
					break;
				}
			}
			//setMonster(pointerInfo.getLocation().x, pointerInfo.getLocation().y);			
			if(list.size() > 0) {					
				System.out.println("餌檜鍔 : " + list.size());
				for(ToolMonster tmm : list) {
					if(tmm != null) {
						if(tmm.isMouseCollision(mx, my)) {
							//System.out.println("關給");
							isColMonster = true;
							break;
						}
					}
				}												
			} 
			
			if(isColMonster == false)
			{
				//System.out.println("儅撩");
				setMonster(mx, my);
			}
		}
		
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < monsterSample.length; i++) {
			monsterSample[i].render(g);			
		}
		
		if(list.size() > 0) {
			for(ToolMonster tm : list) {
				tm.render(g);
			}
		}
		
	}
	
	public void setSampleIndex(int idx) {
		this.sampleIndex = idx;
	}
	
	public void setTileManager(TileManager tm) {
		this.tm = tm;
	}
	
	public void setCam(CameraClass cam) {
		this.cam = cam;
	}
}
