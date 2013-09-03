package com.nsx.pilotemybox.widget;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.provider.Contacts.Groups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.nsx.pilotemybox.MainScreen;
import com.nsx.pilotemybox.PiloteMyBox;

public class ServerListWidget extends WidgetGroup{
	public static final int GAME_VIEWPORT_WIDTH = Gdx.app.getGraphics().getWidth();
	public static final int GAME_VIEWPORT_HEIGHT = Gdx.app.getGraphics().getHeight();
	
	final static float TICK_SPEED = 0.1f;
	final static float MOVE_OFFSET = 20;
	
	TextureAtlas _Atlas;
	float _NextPosition;
	Actor _CurrPc;
	PiloteMyBox _Pmb;
	
	public ServerListWidget(TextureAtlas a,float width, float height,PiloteMyBox pmb){
		this.height = height;
		this.width = width;
		_Atlas = a;
		_NextPosition = this.height;
		_Pmb = pmb;
	}
	
	public void addServer(String adr){
		PcWidget pw = new PcWidget(_Atlas,adr,this.width,_Pmb);
		pw.y = _NextPosition-pw.height;
		this.addActor(pw);
		_NextPosition -= pw.height;
	}
	
	@Override
	public float getPrefWidth() {
		return 0;
	}

	@Override
	public float getPrefHeight() {
		return 0;
	}
	
	
	@Override
	public boolean touchDown(float x, float y, int pointer){
		Iterator<Actor> i = this.getActors().iterator();
		while(i.hasNext()){
			Actor a = i.next();
			if(y>a.y && y<a.y+a.height){
				a.action((MoveTo.$(MOVE_OFFSET, a.y,TICK_SPEED)));
				_CurrPc = a;
			}else{
				a.action((MoveTo.$(0, a.y,TICK_SPEED)));
			}
		}
		return true;
	}

	@Override
	public void touchUp(float x, float y, int pointer){
		if(_CurrPc!=null){
			_CurrPc.action((MoveTo.$(0, _CurrPc.y,TICK_SPEED)));
			_Pmb.setScreen(PiloteMyBox.MAIN_SCREEN,_CurrPc.name);
			_CurrPc = null;
		}
	}
	
	@Override
	public void touchDragged(float x, float y, int pointer) {
		Iterator<Actor> i = this.getActors().iterator();
		_CurrPc = null;
		while(i.hasNext()){
			Actor a = i.next();
			if(y>a.y && y<a.y+a.height){
				a.action((MoveTo.$(MOVE_OFFSET, a.y,TICK_SPEED)));
				_CurrPc = a;
			}else{
				a.action((MoveTo.$(0, a.y,TICK_SPEED)));
			}
		}
	}

}
