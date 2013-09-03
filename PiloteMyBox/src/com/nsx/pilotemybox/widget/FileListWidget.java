package com.nsx.pilotemybox.widget;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

public class FileListWidget  extends WidgetGroup{
	List<String> _CurrFolder;
	TextureAtlas _Atlas;
	Group _MainContainer;
	float _Yorig;
	float _YMainContainer;
	float _Ydelta;
	
	public FileListWidget(FileExplorerWidget few, float h) {
		_CurrFolder = new ArrayList<String>();
		_Atlas = few._Atlas;
		this.width = few.width;
		this.height = h;
		_MainContainer = new ListWidget();
		this.addActor(_MainContainer);
		_MainContainer.width = this.width;
	}

	public void setPath(List<String> lst){
		int i = 0;
		_MainContainer.clear();
		
		//--recuperation de la liste des fichier du repertoire passé
		
		_CurrFolder.clear();
		_CurrFolder.add("C:");
		_CurrFolder.add("D:");
		_CurrFolder.add("Plop3");
		_CurrFolder.add("Plop4");
		_CurrFolder.add("Plop5");
		_CurrFolder.add("Plop6");
		_CurrFolder.add("Plop7");
		_CurrFolder.add("Plop8");
		_CurrFolder.add("Plop3");
		_CurrFolder.add("Plop4");
		_CurrFolder.add("Plop5");
		_CurrFolder.add("Plop6");
		_CurrFolder.add("Plop7");
		_CurrFolder.add("Plop8");
		
		_MainContainer.height = 0;
		
		//-- Calcul taille
		for(Iterator<String> itr = _CurrFolder.iterator();itr.hasNext();){
			String tmps = itr.next();
			FileListItemWidget tmp = new FileListItemWidget(_Atlas,tmps,this);
			_MainContainer.height += tmp.height;
		}
		
		_MainContainer.y = this.height - _MainContainer.height;
		
		//-- ajout des element
		for(Iterator<String> itr = _CurrFolder.iterator();itr.hasNext();){
			String tmps = itr.next();
			System.out.println("Adding: "+tmps);
			FileListItemWidget tmp = new FileListItemWidget(_Atlas,tmps,this);
			_MainContainer.addActor(tmp);
			tmp.y = _MainContainer.height - (i+1)*tmp.height;
			i++;
		}
	}
	
	@Override
	public void touchDragged(float x, float y, int pointer) {
		//-- gestion du scrolling
		System.out.println(_MainContainer.y);
		if( (y - _Ydelta)>0 && _MainContainer.y<0 || (y - _Ydelta)<0 && _MainContainer.y>this.height-_MainContainer.height){
			//System.out.println(""+_MainContainer.y);
			_MainContainer.y = _YMainContainer + (y - _Yorig) ;
		}else{
			_Yorig = y;
			_YMainContainer = _MainContainer.y;
		}
		_Ydelta = y;
	}
	
	@Override
	public void touchUp(float x, float y, int pointer){
		
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer){
		_Yorig = y;
		_Ydelta = y;
		_YMainContainer = _MainContainer.y;
		return true;
	}
	
	@Override
	public float getPrefWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getPrefHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

}
