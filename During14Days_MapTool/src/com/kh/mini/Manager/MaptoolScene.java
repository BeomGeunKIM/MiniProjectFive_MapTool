package com.kh.mini.Manager;

import java.awt.Graphics;

import com.kh.mini.Model.vo.GameObject.CameraClass;

public class MaptoolScene extends BaseScene {

	TileManager tiles;
	ToolMonsterManager tmm;
	CameraClass cam = new CameraClass();
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		tiles = new TileManager();
		tiles.setCam(cam);
		tiles.setToolTiles();
		
		tmm = new ToolMonsterManager();
		tmm.setCam(cam);
		tmm.Init();
		
		tiles.setTileManager(tmm);
		tmm.setTileManager(tiles);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		cam.update();
		tiles.update();
		tmm.update();
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		tiles.render(g);
		tmm.render(g);
	}

}
