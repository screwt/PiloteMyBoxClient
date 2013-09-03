package com.nsx.pilotemybox.widget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.actions.RotateBy;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.nsx.pilotemybox.PiloteMyBox;
import com.nsx.pilotemybox.utils.MyMessage;
import com.nsx.pilotemybox.utils.SendMessageTask;

public class SwitchWidget extends WidgetGroup{
	public static final int GAME_VIEWPORT_WIDTH = Gdx.app.getGraphics().getWidth();
	public static final int GAME_VIEWPORT_HEIGHT = Gdx.app.getGraphics().getHeight();
	final static float DRAGGING_STEP = 40;
	final static float DRAGGING_TRIGER = 100;
	final static float ROTATE_OFFSET = 5;
	final static float SPEED_SPEED = 0.005f;
	
	float _Yorig;
	String _Srvadr;
	float _DraggingNextStep;
	boolean _DraggingTriggerReached;
	PiloteMyBox _Pmb;
	TextureAtlas _Atlas;
	Image _Round;
	float _Ylast;
	float _Xorig;
	
	public SwitchWidget(TextureAtlas a, String srvadr, PiloteMyBox pmb) {
		super("SwitchWidget");
		_Pmb = pmb;
		_Srvadr = srvadr;										
		_DraggingNextStep = 0;
		_DraggingTriggerReached = false;
		_Atlas = a;
		//-- fleche
		Image img = new Image( _Atlas.findRegion("widget_bg"));
		this.addActor(img);
		this.width = img.width;
		this.height = img.height;
		
		//-- icone centrale
		_Round = new Image( _Atlas.findRegion("switch_center"));
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
		_DraggingNextStep = 0;
		_Yorig = y;
		_Xorig = x;
		return true;
	}
	
	@Override
	public void touchDragged(float x, float y, int pointer){
		if(Math.abs(x-_Xorig)<Math.abs(y-_Yorig)){
			
			if (Math.abs(y-_Yorig)>DRAGGING_TRIGER || _DraggingTriggerReached){
				if(!_DraggingTriggerReached){
					_DraggingTriggerReached = true;
					new SendMessageTask(new MyMessage(_Srvadr,7777,"2 \n"),_Pmb).run();
				}
				
				//-- changement du volume
				if((y-_Yorig)-_DraggingNextStep>DRAGGING_STEP){
					_DraggingNextStep += DRAGGING_STEP;
					new SendMessageTask(new MyMessage(_Srvadr,7777,"7 \n"),_Pmb).run();
					//System.out.println("VOLUMESTEPUP! _DraggingNextStep:"+_DraggingNextStep);
				}else if(_DraggingNextStep-(y-_Yorig)>DRAGGING_STEP){
					_DraggingNextStep -= DRAGGING_STEP;
					new SendMessageTask(new MyMessage(_Srvadr,7777,"8 \n"),_Pmb).run();
					//System.out.println("VOLUMESTEPDOWN! _DraggingNextStep:"+_DraggingNextStep);
				}
				
				//-- rotation du cercle brisé
				if (y-_Ylast>0){
					_Round.action((RotateBy.$(ROTATE_OFFSET,SPEED_SPEED)));
				}else if(y-_Ylast<0){
					_Round.action((RotateBy.$(-ROTATE_OFFSET,SPEED_SPEED)));
				}
				_Ylast = y;
			}
		}
	}
	
	@Override
	public void touchUp(float x, float y, int pointer){
		new SendMessageTask(new MyMessage(_Srvadr,7777,"3 \n"),_Pmb).run();
		_DraggingTriggerReached = false;
	}

}
