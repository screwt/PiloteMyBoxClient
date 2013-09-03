package com.nsx.pilotemybox.widget;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveBy;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.nsx.pilotemybox.PiloteMyBox;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MainWidget extends WidgetGroup{
	public static final int GAME_VIEWPORT_WIDTH = Gdx.app.getGraphics().getWidth();
	public static final int GAME_VIEWPORT_HEIGHT = Gdx.app.getGraphics().getHeight();
	float _height;
	float _width;
	int _currPos;
	int _posNumber;
	InputMultiplexer _im;
	List<WidgetGroup> _SubWdgets;
	String _Srvadr;
	PiloteMyBox _Pmb;
	
	public int get_currPos() {
		return _currPos;
	}

	public void set_currPos(int _currPos) {
		this._currPos = _currPos;
	}

	/*
	 * Constructeur
	 */
	public MainWidget(String srvadr, TextureAtlas _Atlas, InputMultiplexer im,PiloteMyBox pmb){
		super("MainWidget");
		
		_Pmb = pmb;
		_Srvadr = srvadr;
		_SubWdgets = new ArrayList<WidgetGroup>();
		
		_im = im;
		GestureDetector gl = new GestureDetector(new MyGestureListener(this));
		_im.addProcessor(gl);
		
		//-- construction du widget
		_height = height;
		_currPos = 1;
		_posNumber = 0;
		
		
		//-- ajout du premier mod
		FileExplorerWidget pw = new FileExplorerWidget(_Atlas);
		pw.x = _posNumber*GAME_VIEWPORT_WIDTH + (GAME_VIEWPORT_WIDTH-pw.width)/2;
		pw.y = (GAME_VIEWPORT_HEIGHT-pw.height)/2;
		_SubWdgets.add(pw);
		this.addActor(pw);
		_posNumber += 1;
		_width += GAME_VIEWPORT_WIDTH;
		
		
		//-- ajout du premier mod
		VolumeWidget img = new VolumeWidget(_Atlas,_Srvadr,_Pmb);
		img.x = _posNumber*GAME_VIEWPORT_WIDTH + (GAME_VIEWPORT_WIDTH-img.width)/2;
		img.y = (GAME_VIEWPORT_HEIGHT-img.height)/2;
		_SubWdgets.add(img);
		this.addActor(img);
		_posNumber += 1;
		_width += GAME_VIEWPORT_WIDTH;
		
		//-- ajout du second mod
		ShutdownWidget img2 =  new ShutdownWidget(_Atlas,_Srvadr,_Pmb);
		img2.x = _posNumber*GAME_VIEWPORT_WIDTH +(GAME_VIEWPORT_WIDTH-img2.width)/2;
		img2.y = (GAME_VIEWPORT_HEIGHT-img2.height)/2;
		img2.init();
		_SubWdgets.add(img2);
		this.addActor(img2);
		_posNumber += 1;
		_width += GAME_VIEWPORT_WIDTH;
		
		//-- ajout du second mod
		SwitchWidget img3  =  new SwitchWidget(_Atlas,_Srvadr, _Pmb);
		img3.x = _posNumber*GAME_VIEWPORT_WIDTH +(GAME_VIEWPORT_WIDTH-img3.width)/2;;
		img3.y = (GAME_VIEWPORT_HEIGHT-img3.height)/2;
		_SubWdgets.add(img3);
		this.addActor(img3);
		_posNumber += 1;
		_width += GAME_VIEWPORT_WIDTH;
	}
	
	
	public boolean canGoRight() {
		return _currPos<_posNumber;
	}

	public boolean canGoLeft() {
		return _currPos>1;
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
		_SubWdgets.get(_currPos-1).touchDown(x, y, pointer);
		return true;
	}
	
	@Override
	public void touchDragged(float x, float y, int pointer){
		_SubWdgets.get(_currPos-1).touchDragged(x, y, pointer);
	}
	
	@Override
	public void touchUp(float x, float y, int pointer){
		_SubWdgets.get(_currPos-1).touchUp(x, y, pointer);
	}
	
	
	private class MyGestureListener implements GestureDetector.GestureListener{
		public static final float FLINGING_SPEED = 0.3f;
		public static final float FLINGING_TRIGER_LIMIT = 0.3f;
		MainWidget _Mw;
		
		public MyGestureListener(MainWidget mainWidget) {
			_Mw = mainWidget;
		}

		@Override
		public boolean touchDown(int x, int y, int pointer) {
			return false;
		}

		@Override
		public boolean tap(int x, int y, int count) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean longPress(int x, int y) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean fling(float velocityX, float velocityY) {
			System.out.println("Fling!"+velocityX);
			
			//-- fling droit gauche vs fling haut bas
			if(Math.abs(velocityX)<Math.abs(velocityY)){
				return false;
			}
			//-- decalage du widget
			if(velocityX>0){
				if(_Mw.canGoLeft()){
					_Mw.set_currPos(_Mw.get_currPos()-1);
					_Mw.action((MoveTo.$(-GAME_VIEWPORT_WIDTH*(_Mw.get_currPos()-1), 0.0f,FLINGING_SPEED)));
				}
			}else{
				if(_Mw.canGoRight()){
					_Mw.action((MoveTo.$(-GAME_VIEWPORT_WIDTH*_Mw.get_currPos(), 0.0f,FLINGING_SPEED)));
					_Mw.set_currPos(_Mw.get_currPos()+1);
				}
			}
			
			return true;
		}

		@Override
		public boolean pan(int x, int y, int deltaX, int deltaY) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean zoom(float originalDistance, float currentDistance) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean pinch(Vector2 initialFirstPointer,
				Vector2 initialSecondPointer, Vector2 firstPointer,
				Vector2 secondPointer) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}


}
