package com.xinlan.crystal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Resource {
	private static Resource instance;
	public TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("data/mark.pack"));
	public TextureRegion gameBgTexture;
	public Sprite sp;
	
	public static Resource getInstance() {
		if (instance == null) {
			instance = new Resource();
		}
		return instance;
	}
	
	public Resource() {
		reInit();
	}

	public void reInit() {
		dispose();
		
		gameBgTexture = (TextureRegion)atlas.findRegion("gamebg");
		sp = atlas.createSprite("ic_launcher");
	}

	public void dispose() {
		atlas.dispose();
	}
}//end class
