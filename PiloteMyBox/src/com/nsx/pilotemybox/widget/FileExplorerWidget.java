package com.nsx.pilotemybox.widget;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

public class FileExplorerWidget extends WidgetGroup{
	public static final int GAME_VIEWPORT_WIDTH = Gdx.app.getGraphics().getWidth();
	public static final int GAME_VIEWPORT_HEIGHT = Gdx.app.getGraphics().getHeight();
	
	TextureAtlas _Atlas;

	PathWidget _Pw;
	FileListWidget _Flw;
	
	public FileExplorerWidget(TextureAtlas a){
	
		List<String> path = new ArrayList<String>();
		path.add("C:");
		path.add("dos1");
		path.add("dos2");
		path.add("dos3");
		
		_Atlas = a;
		this.width = GAME_VIEWPORT_WIDTH;
		this.height = GAME_VIEWPORT_HEIGHT;
	
		//-- widget en haut montrant le chemin courrant
		_Pw = new PathWidget(this);
		
		//-- widget list
		_Flw = new FileListWidget(this,this.height - _Pw.height);
		
		this.addActor(_Flw);
		this.addActor(_Pw);
				
		//-- tache de récuperation des differents lecteur de la machine et meut contenu(ordre 1)
		setPath(path);

	}
	
	public void setPath(List<String> path){
		_Pw.setPath(path);
		_Pw.y = this.height - _Pw.height;
		_Flw.setPath(path);
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
