package com.nsx.pilotemybox.widget;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

public class PathWidget extends WidgetGroup{
	public static final int GAME_VIEWPORT_WIDTH = Gdx.app.getGraphics().getWidth();
	public static final int GAME_VIEWPORT_HEIGHT = Gdx.app.getGraphics().getHeight();
	final static float IDENT = 20;
	List<String> _CurrPath;
	FileExplorerWidget _Few;
	TextureAtlas _Atlas;
	
	public PathWidget(FileExplorerWidget few) {
		_Few = few;
		_Atlas = few._Atlas;
		this.width = few.width;
		_CurrPath = new ArrayList<String>();

	}

	public void setPath(List<String> p){
		_CurrPath = p;
		
		this.clear();
		


		
		int i = 0;
		this.height = 0 ;
		
		//-- calcultaille
		for ( Iterator<String> itr = _CurrPath.iterator(); itr.hasNext(); ){
			String tmps = itr.next();
			PathItemWidget tmp = new PathItemWidget(_Atlas,this,tmps);
			this.height += tmp.height;
		}
		
		this.height += 10;
		//--image de font
		TextureRegion tr = new TextureRegion(_Atlas.findRegion("background"));
		int calc =  (tr.getRegionY()-((int)GAME_VIEWPORT_HEIGHT-(int) this.height));//(GAME_VIEWPORT_HEIGHT-this.height);
		tr.setRegion(tr.getRegionX(), calc, tr.getRegionWidth(), tr.getRegionHeight());//
		Image img = new Image(tr);
		this.addActor(img);
		//img.y = -GAME_VIEWPORT_HEIGHT + this.height;
		
		//-- ajout des element
		for ( Iterator<String> itr = _CurrPath.iterator(); itr.hasNext(); ){
			String tmps = itr.next();
			System.out.println("Adding:"+tmps);
			PathItemWidget tmp = new PathItemWidget(_Atlas,this,tmps);
			this.addActor(tmp);
			tmp.x = IDENT*i;
			tmp.y = this.height - (i+1)*tmp.height;
			i++;
		}
		
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
