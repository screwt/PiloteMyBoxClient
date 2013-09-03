package com.nsx.pilotemybox.widget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveBy;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.actions.RotateBy;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.nsx.pilotemybox.PiloteMyBox;
import com.nsx.pilotemybox.utils.MyMessage;
import com.nsx.pilotemybox.utils.SendMessageTask;

public class ShutdownWidget extends WidgetGroup{
	public static final int GAME_VIEWPORT_WIDTH = Gdx.app.getGraphics().getWidth();
	public static final int GAME_VIEWPORT_HEIGHT = Gdx.app.getGraphics().getHeight();
	final static float ROTATE_OFFSET = 5;
	final static float SPEED_SPEED = 0.005f;
	final static float TRIGGER = 150;
	final static float TICK_SPEED = 0.1f;
	float _Yorig;
	String _Srvadr;
	PiloteMyBox _Pmb;
	TextureAtlas _Atlas;
	Image _Round;
	float _Ylast;
	Image _Poweroff;
	Image _Sleep;
	boolean _PowerB;
	boolean _SleepB;
	
	public ShutdownWidget(TextureAtlas a, String srvadr,PiloteMyBox pmb) {
		super("ShutdownWidget");
		_Srvadr = srvadr;
		_Pmb = pmb;
		_Atlas = a;
		_PowerB = false;
		_SleepB = false;
		//-- fleche
		Image img = new Image( _Atlas.findRegion("widget_bg"));
		this.addActor(img);
		this.width = img.width;
		this.height = img.height;
		
		//-- icone centrale
		Image img2 = new Image( _Atlas.findRegion("shutdown_center"));
		this.addActor(img2);
		//img2.height = img2.height*(GAME_VIEWPORT_WIDTH*0.35f/img2.width);
		//img2.width = GAME_VIEWPORT_WIDTH*0.35f;
		img2.x = (this.width-img2.width)/2;
		img2.y = (this.height-img2.height)/2;
		
		_Round = new Image( _Atlas.findRegion("volume_round"));
		this.addActor(_Round);
		_Round.x = (this.width-_Round.width)/2;
		_Round.y = (this.height-_Round.height)/2;
		_Round.originX =  _Round.width/2;
		_Round.originY =  _Round.height/2;
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
		System.out.println("ShutdownWidget touchDown "+pointer);
		_Yorig = y;
		return true;
	}
	
	@Override
	public void touchDragged(float x, float y, int pointer){		
		//-- rotation du cercle brisé
		if (y-_Ylast>0){
			_Round.action((RotateBy.$(ROTATE_OFFSET,SPEED_SPEED)));
		}else if(y-_Ylast<0){
			_Round.action((RotateBy.$(-ROTATE_OFFSET,SPEED_SPEED)));
		}
		
		if(y-_Yorig>TRIGGER){
			System.out.println("reached!");
			_Sleep.action((MoveTo.$(_Sleep.x, this.height - 10,TICK_SPEED)));
			_SleepB = true;
		}else{
			_Sleep.action((MoveTo.$(_Sleep.x, this.height + _Sleep.height -5,TICK_SPEED)));
			_SleepB = false;
		}
		
		if(y-_Yorig<-TRIGGER){
			_Poweroff.action((MoveTo.$(_Poweroff.x, - this.y ,TICK_SPEED)));
			_PowerB = true;
		}else{
			_Poweroff.action((MoveTo.$(_Poweroff.x, - this.y - _Poweroff.height,TICK_SPEED)));
			_PowerB = false;
		}
		
		_Ylast = y;
	}
	
	@Override
	public void touchUp(float x, float y, int pointer){
		if(_SleepB){
			System.out.println("Sleep!");
			_Sleep.action((MoveTo.$(_Sleep.x, this.height + _Sleep.height -5,TICK_SPEED)));
			_SleepB = false;
			new SendMessageTask(new MyMessage(_Srvadr,7777,"5 \n"),_Pmb).run();
		}else if (_PowerB){
			System.out.println("Shutdown!");
			_Poweroff.action((MoveTo.$(_Poweroff.x, - this.y - _Poweroff.height,TICK_SPEED)));
			_PowerB = false;
			new SendMessageTask(new MyMessage(_Srvadr,7777,"6 \n"),_Pmb).run();
		}
	}

	public void init() {
		//-- icon power
		_Poweroff = new Image( _Atlas.findRegion("poweroff"));
		this.addActor(_Poweroff);
		_Poweroff.x =  (this.width-_Poweroff.width)/2;
		_Poweroff.y =  - this.y - _Poweroff.height + 5;
		
		//-- icon sleep
		_Sleep = new Image( _Atlas.findRegion("sleep"));
		this.addActor(_Sleep);
		_Sleep.x = (this.width-_Sleep.width)/2;
		_Sleep.y = this.height  + _Sleep.height;
		
	}
	
}
