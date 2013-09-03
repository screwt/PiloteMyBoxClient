package com.nsx.pilotemybox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import com.nsx.pilotemybox.widget.MainWidget;


public class MainScreen implements Screen {
	public static final int GAME_VIEWPORT_WIDTH = Gdx.app.getGraphics().getWidth();
	public static final int GAME_VIEWPORT_HEIGHT = Gdx.app.getGraphics().getHeight();
	final static float ROTATE_OFFSET = 10;
	final static float ROTATE_SPEED = 0.01f;
	
	protected Stage _Stage;
	public WidgetGroup _All;  
	TextureAtlas _Atlas;
	InputMultiplexer _im;

	PiloteMyBox _Pmb;
	
	
	public MainScreen(String servadr,TextureAtlas a,PiloteMyBox pmb){

		_Pmb = pmb;
		_im = new InputMultiplexer();
		int width = (  GAME_VIEWPORT_WIDTH  );
		int height = ( GAME_VIEWPORT_HEIGHT);
		this._Stage = new Stage( width, height, true );
		
		//-- r&cupertion des image 
		_Atlas = a;
		//-- ajout back ground
		Image img = new Image( _Atlas.findRegion("background"));
		this._Stage.addActor(img);

		_All = new MainWidget(servadr,_Atlas,_im,_Pmb);
		_im.addProcessor(_Stage);
		
		this._Stage.addActor(_All);

	}
	
	public void handleInput(){
		Gdx.input.setInputProcessor(_im);
	}
	
	@Override
	public void render(float delta) {		
		Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
		Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
		_Stage.act( delta );
		_Stage.draw();		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	


}
