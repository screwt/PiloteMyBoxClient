package com.nsx.pilotemybox.widget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

public class PathItemWidget  extends WidgetGroup{
	TextureAtlas _Atlas;
	Label _lbl;
	
	public PathItemWidget(TextureAtlas a, PathWidget pw, String s) {
		_Atlas = a;
		this.width = pw.width;
		

		
		//-- label
		Label.LabelStyle lbls = new Label.LabelStyle();
		lbls.font = new BitmapFont(Gdx.files.internal("data/ll_12.fnt"), false);
		_lbl = new Label(s,lbls);
		this.height = _lbl.height;
		
		//-- image de font
		Image img = new Image( _Atlas.findRegion("pathitem_bg"));
		img.width = img.width*(this.height/img.height);
		img.height = this.height;
		this.addActor(img);
		
		this.addActor(_lbl);
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
