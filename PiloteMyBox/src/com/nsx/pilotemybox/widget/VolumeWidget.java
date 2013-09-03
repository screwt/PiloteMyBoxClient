package com.nsx.pilotemybox.widget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.actions.RotateBy;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.nsx.pilotemybox.PiloteMyBox;
import com.nsx.pilotemybox.utils.MyMessage;
import com.nsx.pilotemybox.utils.SendMessageTask;

public class VolumeWidget extends WidgetGroup{
	public static final int GAME_VIEWPORT_WIDTH = Gdx.app.getGraphics().getWidth();
	public static final int GAME_VIEWPORT_HEIGHT = Gdx.app.getGraphics().getHeight();
	final static float DRAGGING_TRIGER = 100;
	final static float DRAGGING_STEP = 20;
	final static float ROTATE_OFFSET = 5;
	final static float SPEED_SPEED = 0.005f;
	float _Yorig;
	boolean _DraggingTriggerReached;
	float _DraggingNextStep;
	float _Ylast;
	String _Srvadr;
	PiloteMyBox _Pmb;
	TextureAtlas _Atlas;
	Image _Round;
	
	public VolumeWidget(TextureAtlas a, String srvadr,PiloteMyBox pmb) {
		super("VolumeWidget");
		_Srvadr = srvadr;
		_Pmb = pmb;
		_Atlas = a;
		
		//-- fleche
		Image img = new Image( _Atlas.findRegion("widget_bg"));
		this.addActor(img);
		this.width = img.width;
		this.height = img.height;
		img.x = (this.width-img.width)/2;
		img.y = (this.height-img.height)/2;
		
		//-- icone centrale
		Image img2 = new Image( _Atlas.findRegion("volume_center"));
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
		
		_DraggingTriggerReached = false;
		_DraggingNextStep = 0;
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
	public void touchDragged(float x, float y, int pointer){	
		if (Math.abs(y-_Yorig)>DRAGGING_TRIGER || _DraggingTriggerReached){
			
			_DraggingTriggerReached = true;
			//-- changement du volume
			if((y-_Yorig)-_DraggingNextStep>DRAGGING_STEP){
				_DraggingNextStep += DRAGGING_STEP;
				new SendMessageTask(new MyMessage(_Srvadr,7777,"4 \n"),_Pmb).run();
				//System.out.println("VOLUMESTEPUP! _DraggingNextStep:"+_DraggingNextStep);
			}else if(_DraggingNextStep-(y-_Yorig)>DRAGGING_STEP){
				_DraggingNextStep -= DRAGGING_STEP;
				new SendMessageTask(new MyMessage(_Srvadr,7777,"9 \n"),_Pmb).run();
				//System.out.println("VOLUMESTEPDOWN! _DraggingNextStep:"+_DraggingNextStep);
			}
			
			//-- rotation du cercle brisé
			if (y-_Ylast>0){
				_Round.action((RotateBy.$(ROTATE_OFFSET,SPEED_SPEED)));
			}else if(y-_Ylast<0) {
				_Round.action((RotateBy.$(-ROTATE_OFFSET,SPEED_SPEED)));
			}
			_Ylast = y;
		}
	}
	
	@Override
	public void touchUp(float x, float y, int pointer){
		_DraggingTriggerReached = false;
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer){
		System.out.println("VolumeWidget touchDown "+pointer);
		_DraggingNextStep = 0;
		_Yorig = y;
		return true;
	}
}
