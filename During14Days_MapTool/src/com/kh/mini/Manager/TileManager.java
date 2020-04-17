package com.kh.mini.Manager;

import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import com.kh.mini.Model.vo.GameObject.CameraClass;
import com.kh.mini.Model.vo.GameObject.ToolSampleObject;
import com.kh.mini.Model.vo.GameObject.ToolTile;

public class TileManager {

	private int wCount = 6;
	private int hCount = 6;
	
	ToolTile[] tArr = new ToolTile[wCount * hCount];
	private ToolSampleObject[] sArr = new ToolSampleObject[30];
	private ToolSampleObject saveMap;
	private ToolSampleObject loadMap;
	private ToolSampleObject resetMap;
	int sampleIndex = -1;

	PointerInfo pointerInfo;
	
	ToolMonsterManager tmm;
	MapSaveLoadManager mslM = new MapSaveLoadManager();;
	
	CameraClass cam;
	
	public void setToolTiles() {
		
		for(int i = 0; i < sArr.length; i++) {
			
			sArr[i] = new ToolSampleObject();
			
			int x = 0;
			int y = 0;

			if(i < 8) {
				x = 1108 + 64;
				y = i * 128 + (128 / 2);
			} else if(i < 16) {
				x = 1108 + 128 + 64;
				y = (i - 8) * 128 + (128 / 2);
			} else if(i < 24) {
				x = 1108 + 128 + 128 + 64;
				y = (i - 16) * 128 + (128 / 2);
			}
			
			sArr[i].setPosition(x , y);
			sArr[i].init();
			switch(i) {
			case 0:
				sArr[i].setImage("images\\MapImage\\Interior\\Tile.png");
				break;
			case 1:
				sArr[i].setImage("images\\MapImage\\Interior\\Wall.png");
				break;
			case 2:
				sArr[i].setImage("images\\MapImage\\Interior\\Wall2.png");
				break;
			case 3:
				sArr[i].setImage("images\\MapImage\\Interior\\Wall3.png");
				break;
			case 4:
				sArr[i].setImage("images\\MapImage\\Interior\\Wall3-1.png");
				break;
			case 5:
				sArr[i].setImage("images\\MapImage\\Interior\\Wall3-2.png");
				break;
			case 6:
				sArr[i].setImage("images\\MapImage\\Interior\\Wall3-3.png");
				break;
			case 7:
				sArr[i].setImage("images\\MapImage\\Interior\\Wall4.png");
				break;
			case 8:
				sArr[i].setImage("images\\MapImage\\Interior\\FlowerBed.png");
				break;
			case 9:
				sArr[i].setImage("images\\MapImage\\Interior\\FlowerBedcen.png");
				break;
				
				
			case 10: 
				sArr[i].setImage("images\\MapImage\\Outside\\concreteWall.png");
				break;
			case 11:
				sArr[i].setImage("images\\MapImage\\Outside\\crosswalk1.png");
				break;
			case 12: 
				sArr[i].setImage("images\\MapImage\\Outside\\crosswalk2.png");
				break;
			case 13:
				sArr[i].setImage("images\\MapImage\\Outside\\crosswalk3.png");
				break;
			case 14: 
				sArr[i].setImage("images\\MapImage\\Outside\\crosswalk4.png");
				break;
			case 15:
				sArr[i].setImage("images\\MapImage\\Outside\\door.png");
				break;
			case 16: 
				sArr[i].setImage("images\\MapImage\\Outside\\path tile.png");
				break;
			case 17:
				sArr[i].setImage("images\\MapImage\\Outside\\road1.png");
				break;
			case 18: 
				sArr[i].setImage("images\\MapImage\\Outside\\road2.png");
				break;
			case 19:
				sArr[i].setImage("images\\MapImage\\Outside\\window (1).png");
				break;
			}
		}
		
		for(int i = 0; i < hCount; i++) {
			for(int j = 0; j < wCount; j++) {
				ToolTile tt = new ToolTile();
				
				int w = 128;
				int h = 128;
				
				tt.setCam(cam);
				tt.setPosition(j * h + h, i *  w + w);
				tt.init(sArr[0].getImage());
				tt.setPath(sArr[0].getPath());
				tArr[j + wCount * i] = tt;				
			}
		}
		
		saveMap = new ToolSampleObject();
		saveMap.setPosition(400, 1000);
		saveMap.init();
		saveMap.setImage("images\\MapImage\\save.png");

		loadMap = new ToolSampleObject();
		loadMap.setPosition(650, 1000);
		loadMap.init();
		loadMap.setImage("images\\MapImage\\load.png");
		
		resetMap = new ToolSampleObject();
		resetMap.setPosition(150, 1000);
		resetMap.init();
		resetMap.setImage("images\\MapImage\\reset.png");
	}
	
	public void update() {
		pointerInfo	= MouseInfo.getPointerInfo();
		
		if(KeyManager.Instance().stayButtonDown(MouseEvent.BUTTON1)) {
			for(int i = 0; i < sArr.length; i++) {
				if(sArr[i].isMouseCollision(pointerInfo.getLocation().x, pointerInfo.getLocation().y)) {
					sampleIndex = i;
					System.out.println(i);
					break;
				}
			}
		}
		
		if(KeyManager.Instance().oncebuttonDown(MouseEvent.BUTTON1)) {
			if(saveMap.isMouseCollision(pointerInfo.getLocation().x, pointerInfo.getLocation().y)) {
				mslM.mapSave(tArr);
			}
			
			if(loadMap.isMouseCollision(pointerInfo.getLocation().x, pointerInfo.getLocation().y)) {
				ToolTile[] temp = new ToolTile[60];
				temp = mslM.mapLoad();
				for(int i = 0; i < temp.length; i++) {
					temp[i].init();
					temp[i].setImageLoad();
					System.out.println(temp[i].getPath());
					tArr[i] = temp[i];
				}
			}
			
			if(resetMap.isMouseCollision(pointerInfo.getLocation().x, pointerInfo.getLocation().y)) {
				this.resetMap();
			}
		}
		
			if(sampleIndex == -1)
				return;
				
			tmm.setSampleIndex(-1);
			
		if(KeyManager.Instance().stayButtonDown(MouseEvent.BUTTON1)) {
			double mx = pointerInfo.getLocation().x + cam.getX();
			double my = pointerInfo.getLocation().y + cam.getY();
			
			for(int i = 0; i < tArr.length; i++) {
				if(tArr[i].isMouseCollision((int)mx, (int)my)) {
					tArr[i].ImageChange(sArr[sampleIndex].getImage());
					tArr[i].setPath(sArr[sampleIndex].getPath());					
				}
			}
		}
		
//		mslM = new MapSaveLoadManager();
		
		
	}
	
	public void render(Graphics g) {
		
		saveMap.render(g);
		loadMap.render(g);
		resetMap.render(g);
		
		for(int i = 0; i < tArr.length; i++) {
			tArr[i].render(g);
		}
		
		for(int i = 0; i < sArr.length; i++) {
			sArr[i].render(g);
		}		
	}
	
	public void setSampleIndex(int idx) {
		this.sampleIndex = idx;
	}
	
	public void setTileManager(ToolMonsterManager tmm) {
		this.tmm = tmm;
	}
	
	public void setMapSaveLoadManager(MapSaveLoadManager mslM) {
		this.mslM = mslM;
	}
	
	public void resetMap() {
		this.setToolTiles();
	}
	
	public void setCam(CameraClass cam) {
		this.cam = cam;
	}
	
}
