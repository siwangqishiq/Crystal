package com.xinlan.crystal.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.xinlan.crystal.GameInstance;
import com.xinlan.crystal.Resource;
import com.xinlan.crystal.role.Background;

public class GameScreen extends DefaultScreen {
	OrthographicCamera cam;//ÉãÏñ»ú
	public SpriteCache cache = new SpriteCache();//cache»­±Ê
	public SpriteBatch batch = new SpriteBatch();//»­±Ê
	
	public Background mBackground;
	
	TextureRegion rg ;

	public GameScreen(Game game) {
		super(game);
		
		cache.getProjectionMatrix().setToOrtho2D(0, 0, 800, 480);
		batch.getProjectionMatrix().setToOrtho2D(0, 0, 800, 480);
		
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
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render(float delta) {
		delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.update();
		
		Gdx.gl.glDisable(GL20.GL_BLEND);
		cache.setProjectionMatrix(cam.combined);
		
		cache.begin();
		mBackground.draw(cache);
		cache.end();

		batch.setProjectionMatrix(cam.combined);
		
		batch.begin();
		//TODO
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		cache.dispose();
	}
}// end class
