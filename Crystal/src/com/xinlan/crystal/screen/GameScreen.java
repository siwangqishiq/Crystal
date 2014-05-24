package com.xinlan.crystal.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.xinlan.crystal.GameInstance;
import com.xinlan.crystal.Resource;
import com.xinlan.crystal.role.Background;
import com.xinlan.crystal.role.Cube;
import com.xinlan.crystal.role.Dump;

public final class GameScreen extends DefaultScreen {
	public static final int SC_WIDTH=480;
	public static final int SC_HEIGHT=800;
	
	OrthographicCamera cam;//ÉãÏñ»ú
	public SpriteCache cache = new SpriteCache();//cache»­±Ê
	public SpriteBatch batch = new SpriteBatch();//»­±Ê
	
	public Background mBackground;
	public Dump dump;
	
	public Sprite sp1,sp2,sp3,sp4;
	
	public GameScreen(Game game) {
		super(game); 
		
		cache.getProjectionMatrix().setToOrtho2D(0, 0, SC_WIDTH, SC_HEIGHT);
		batch.getProjectionMatrix().setToOrtho2D(0, 0, SC_WIDTH, SC_HEIGHT);
		
		cam = new OrthographicCamera(GameInstance.SCREEN_WIDTH,
				GameInstance.SCREEN_HEIGHT);
		cam.position.set(GameInstance.SCREEN_WIDTH / 2,
				GameInstance.SCREEN_HEIGHT / 2, 0);
		
	}

	@Override
	public void show() {
		Resource.getInstance().reInit();
		//³õÊ¼»¯
		mBackground = new Background(this);
		dump = new Dump(this);
		
		sp1 = Resource.getInstance().dumpBlue;
		sp2 = Resource.getInstance().dumpPink;
		sp3 = Resource.getInstance().dumpRed;
		sp4 =  Resource.getInstance().dumpYellow;
		
		sp1.setSize(Cube.CUBE_WIDTH, Cube.CUBE_HEIGHT);
		sp2.setSize(Cube.CUBE_WIDTH, Cube.CUBE_HEIGHT);
		sp3.setSize(Cube.CUBE_WIDTH, Cube.CUBE_HEIGHT);
		sp4.setSize(Cube.CUBE_WIDTH, Cube.CUBE_HEIGHT);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render(float delta) {
		delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glDisable(GL20.GL_BLEND);
		cam.update();
		
		
		sp1.setPosition(0, 100);
		sp2.setPosition(60, 100);
		sp3.setPosition(120, 100);
		sp4.setPosition(180, 100);
		
		cache.setProjectionMatrix(cam.combined);
		cache.begin();
		mBackground.draw(cache);
		cache.end();

		batch.setProjectionMatrix(cam.combined);
		
		batch.begin();
		//TODO
		//dump.draw(batch);
		sp1.draw(batch);
		sp2.draw(batch);
		sp3.draw(batch);
		sp4.draw(batch);
		
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		cache.dispose();
	}
}// end class
