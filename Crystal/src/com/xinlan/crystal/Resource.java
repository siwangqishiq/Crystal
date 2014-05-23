package com.xinlan.crystal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Resource {
	private static Resource instance;
	public TextureAtlas atlas;
	public TextureRegion gameBgTexture;
	public Sprite sp;
	
	public Sprite dumpBlue;
	public Sprite dumpRed;
	public Sprite dumpYellow;
	public Sprite dumpPink;
	
	
	public static Resource getInstance() {
		if (instance == null) {
			instance = new Resource();
		}
		return instance;
	}

	private Resource() {
		reInit();
	}

	public void reInit() {
		dispose();
		
		TextureAtlas atlas = new TextureAtlas(
				Gdx.files.internal("data/mark.pack"));
		gameBgTexture = (TextureRegion) atlas.findRegion("gamebg");
		sp = atlas.createSprite("ic_launcher");
		dumpBlue = atlas.createSprite("dump_blue");
		dumpRed = atlas.createSprite("dump_red");
		dumpYellow = atlas.createSprite("dump_yellow");
		dumpPink = atlas.createSprite("dump_pink");
	}

	public void dispose() {
		if (atlas != null)
		{
			atlas.dispose();
		}
	}
}// end class
