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
import com.xinlan.crystal.action.TouchListener;
import com.xinlan.crystal.role.AddDump;
import com.xinlan.crystal.role.Background;
import com.xinlan.crystal.role.CoreData;
import com.xinlan.crystal.role.Cube;
import com.xinlan.crystal.role.Dump;
import com.xinlan.crystal.role.GameSound;

public final class GameScreen extends DefaultScreen {
	public static final int SC_WIDTH=480;
	public static final int SC_HEIGHT=800;
	
	public static final int STATE_NORMAL = 13;
    public int game_state = STATE_NORMAL;
	
	public OrthographicCamera cam;
	public SpriteCache cache = new SpriteCache();
	public SpriteBatch batch = new SpriteBatch();
	
	public Background mBackground;
	public Dump dump;
	public CoreData core;
	public AddDump addDump;
	public TouchListener touchListener;
	public GameSound gameSound;
	
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
		gameSound = new GameSound(this);
		mBackground = new Background(this);
		dump = new Dump(this);
		core = new CoreData(this);
		addDump = new AddDump(this);
		
		touchListener = new TouchListener(this);
		Gdx.input.setInputProcessor(touchListener);
		
		gameSound.bgMusic.play();
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
		
		cache.setProjectionMatrix(cam.combined);
		cache.begin();
		mBackground.draw(cache);
		cache.end();

		batch.setProjectionMatrix(cam.combined);
		
		batch.begin();
		//TODO
		//dump.draw(batch);
//		sp1.draw(batch);
//		sp2.draw(batch);
//		sp3.draw(batch);
//		sp4.draw(batch);
		core.draw(batch,delta);
		addDump.draw(batch, delta);
		
		batch.end();
	}
	
	@Override
	public void dispose () {
		gameSound.dispose();
		core.dispose();
		batch.dispose();
		cache.dispose();
	}
}// end class
