package com.nsx.pilotemybox.widget;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.actions.MoveBy;
import com.badlogic.gdx.scenes.scene2d.actions.RotateBy;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

public class LoadingWidget extends WidgetGroup{
	final static float ROATION_SPEED = 5;
	
	
	public LoadingWidget( TextureAtlas _Atlas){
		super("LoadingWidget");
		Image img = new Image( _Atlas.findRegion("loading"));
		img.width = img.width/2;
		img.height = img.height/2;
		this.height = img.height;
		this.width = img.width;
		this.addActor(img);
	}
	
	@Override
	public void act(float delta){
		this.rotation += ROATION_SPEED;
		this.originX = this.width/2;
		this.originY = this.height/2;
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
