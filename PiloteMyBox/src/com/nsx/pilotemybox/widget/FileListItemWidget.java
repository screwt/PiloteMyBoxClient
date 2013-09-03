package com.nsx.pilotemybox.widget;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

public class FileListItemWidget extends WidgetGroup{
	final static float LEFT_PADDING = 20;
	
	TextureAtlas _Atlas;
	
	public FileListItemWidget(TextureAtlas a, String name, FileListWidget flw) {
		_Atlas = a;
		this.width = flw.width;
		
		//-- image de fond des elements
		Image img_bg = new Image( _Atlas.findRegion("fileitem_bg"));
		img_bg.height = img_bg.height*(this.width/img_bg.width);
		img_bg.width = this.width;
		this.addActor(img_bg);
		this.height = img_bg.height;
		//-- label
		Label.LabelStyle lbls = new Label.LabelStyle();
		lbls.font = new BitmapFont(Gdx.files.internal("data/ll_18.fnt"), false);
		Label lbl = new Label(name,lbls);
		this.addActor(lbl);
		lbl.y = (this.height+-lbl.height)/2;
		lbl.x = LEFT_PADDING;
		
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
