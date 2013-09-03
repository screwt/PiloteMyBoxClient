package com.nsx.pilotemybox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.nsx.pilotemybox.utils.Discovery;
import com.nsx.pilotemybox.utils.DiscoveryTask;
import com.nsx.pilotemybox.utils.MyMessage;
import com.nsx.pilotemybox.utils.SendMessageTask;
import com.nsx.pilotemybox.widget.LoadingWidget;
import com.nsx.pilotemybox.widget.ServerListWidget;

public class ConnectScreen implements Screen{
	public static final int GAME_VIEWPORT_WIDTH = Gdx.app.getGraphics().getWidth();
	public static final int GAME_VIEWPORT_HEIGHT = Gdx.app.getGraphics().getHeight();
	
	protected Stage _Stage;
	public WidgetGroup _All;  
	TextureAtlas _Atlas;
	Discovery _Discovery;
	PiloteMyBox _Pmb;
	
	public ConnectScreen(Discovery discovery, PiloteMyBox piloteMyBox, TextureAtlas a){
		_Pmb = piloteMyBox;
		_Discovery = discovery;
		
		int width = (  GAME_VIEWPORT_WIDTH  );
		int height = ( GAME_VIEWPORT_HEIGHT);
		this._Stage = new Stage( width, height, true );
		
		
		//-- r&cupertion des image
		_Atlas = a;
		//-- ajout back ground
		Image img = new Image( _Atlas.findRegion("background"));
		this._Stage.addActor(img);
		
		//-- image de chargement
		WidgetGroup w = new LoadingWidget(_Atlas);
		w.x = (GAME_VIEWPORT_WIDTH-w.width)/2;
		w.y = 10;//(GAME_VIEWPORT_HEIGHT-w.height)/2; 
		
		ServerListWidget slw = new ServerListWidget(_Atlas,GAME_VIEWPORT_WIDTH,GAME_VIEWPORT_HEIGHT,_Pmb);
		
		List<String> servers = _Discovery.getServerList();

		Iterator<String> i = servers.iterator();
		while(i.hasNext()){
			String tmp = i.next();
			slw.addServer(tmp);
		}
		
		this._Stage.addActor(slw);
		this._Stage.addActor(w);
		
		
	}
	
	public void handleInput(){
		Gdx.input.setInputProcessor(_Stage);
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
