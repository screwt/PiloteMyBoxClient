package com.nsx.pilotemybox;

import android.content.Context;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
//import com.badlogic.gdx.tests.utils.GdxTest;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.nsx.pilotemybox.utils.Discovery;

public class PiloteMyBox extends Game {
	public static final int CONNECT_SCREEN = 1;
	public static final int MAIN_SCREEN = 2;
	
	Context _Context;
	Discovery _Discovery;
	TextureAtlas _Atlas;
	ConnectScreen _CScreen;
	MainScreen _MScreen;
	
	public PiloteMyBox(Discovery disc) {
		_Discovery = disc;
	}

	@Override
	public void create() {
		_Atlas = new TextureAtlas(Gdx.files.internal("data/PMB.pack"));
		_CScreen = new ConnectScreen(_Discovery,this,_Atlas);
		this.setScreen(CONNECT_SCREEN,"");
	}
	
	protected boolean isGameScreen(){
    	return false;
	}
	
	public void setScreen(int scr, String srvadr){
		switch(scr){
		case CONNECT_SCREEN:
			this.setScreen(_CScreen);
			_CScreen.handleInput();
			break;
		case MAIN_SCREEN:
			_MScreen = new MainScreen(srvadr,_Atlas,this);
			this.setScreen(_MScreen);
			_MScreen.handleInput();
			break;
		}
	}

	@Override
	public void dispose() {
		
	}

	

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	

}
