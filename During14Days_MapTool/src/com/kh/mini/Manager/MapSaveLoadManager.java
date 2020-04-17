package com.kh.mini.Manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.kh.mini.Model.vo.GameObject.ToolTile;
import com.kh.mini.Model.vo.GameObject.Monster.ToolMonster;

public class MapSaveLoadManager {

	TileManager tm;
	
	public void mapSave(ToolTile[] tArr) {
		
		ObjectOutputStream oos = null;
		
		ArrayList<ToolTile> list = new ArrayList<ToolTile>();
		
		for(int i = 0; i < tArr.length; i++) {
			list.add(tArr[i]);
		}
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream("MapsSample.dat"));
			oos.writeObject(list);
			oos.flush();
			System.out.println("파일저장완료");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void mapSave(ArrayList<ToolMonster> list) {

		ObjectOutputStream oos = null;
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream("MapsSample.dat"));
			oos.writeObject(list);
			oos.flush();
			System.out.println("파일저장완료");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ToolTile[] mapLoad() {
		
		ObjectInputStream ois = null;
		ToolTile[] forLoad = new ToolTile[36];
		ArrayList<ToolTile> list = new ArrayList<ToolTile>();
		
		try {
			ois = new ObjectInputStream(new FileInputStream("MapsSample.dat"));
			try {
				list = (ArrayList<ToolTile>) ois.readObject();
				for(int i = 0; i < list.size(); i++) {
					forLoad[i] = list.get(i);
//					System.out.println(list.get(i).getPath());
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return forLoad;
	}
	
	//맵로드 클릭시 바로 로드 안되는 문제발생..
	//가능한지 테스트중
	public void mapLoadInGame() {
		
		tm = new TileManager();
		
		ObjectInputStream ois = null;
		ToolTile[] forLoad = new ToolTile[60];
		ArrayList<ToolTile> list = new ArrayList<ToolTile>();
		
		try {
			ois = new ObjectInputStream(new FileInputStream("MapsSample.dat"));
			try {
				list = (ArrayList<ToolTile>) ois.readObject();
				for(int i = 0; i < list.size(); i++) {
					forLoad[i] = list.get(i);
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//forLoad tArr에 넣기 //실패..
		for(int i = 0; i < tm.tArr.length; i++) {
			forLoad[i].init();
			forLoad[i].setImageLoad();
			tm.tArr[i] = forLoad[i];
		}
		
	}
}
