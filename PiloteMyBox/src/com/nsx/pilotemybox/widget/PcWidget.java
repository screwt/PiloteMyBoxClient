package com.nsx.pilotemybox.widget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.actions.MoveBy;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.nsx.pilotemybox.MainScreen;
import com.nsx.pilotemybox.PiloteMyBox;



public class PcWidget extends WidgetGroup {
	
	final static float LEFT_PADDIN = 20;
	final static float IMG_SCALE = 0.70f;
	final static float SPACER_X = 5;

	
	TextureAtlas _Atlas;
	PiloteMyBox _Pmb;
	boolean _HasFocus;
	
	
	public PcWidget(TextureAtlas a, String adr, float width, PiloteMyBox pmb) {
		super(adr);
		_HasFocus = false;
		_Pmb = pmb;
		_Atlas = a;
		Image img_bg = new Image( _Atlas.findRegion("pc_bg"));
		img_bg.height = img_bg.height*(width/img_bg.width);
		img_bg.width = width;
	
		
		this.width = img_bg.width;
		this.height = img_bg.height;
		
		Image img = new Image( _Atlas.findRegion("pc"));
		img.width = img.width*(this.height*IMG_SCALE/img.height);
		img.height = this.height*IMG_SCALE;
		img.y = (this.height-img.height)/2;
		img.x = LEFT_PADDIN;
		
		Label.LabelStyle lbls = new Label.LabelStyle();
		lbls.font = new BitmapFont(Gdx.files.internal("data/ll.fnt"), false);
			
		Label l = new Label(adr,lbls);
		l.x = img.width + img.x + SPACER_X;
		l.y = (this.height-l.height)/2;
		this.addActor(img_bg);
		this.addActor(l);
		this.addActor(img);
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
	
	@Override
	public boolean touchDown(float x, float y, int pointer){
		System.out.println("touchDown "+this.name);
		return false;
	}

	@Override
	public void touchUp(float x, float y, int pointer){
	}
	
	@Override
	public void touchDragged(float x, float y, int pointer) {
	}
	
}
