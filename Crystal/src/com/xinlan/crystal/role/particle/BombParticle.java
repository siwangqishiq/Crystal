package com.xinlan.crystal.role.particle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.xinlan.crystal.role.CoreData;
import com.xinlan.crystal.screen.GameScreen;

/**
 * 方块消失 粒子效果
 * 
 * @author panyi
 * 
 */
public class BombParticle {
	private GameScreen context;

	private ParticleEffect blueParticle;// 蓝粒子
	private ParticleEffect temBlueParticle;
	private ParticleEffectPool bluePool;// 蓝色粒子池 产生蓝色粒子

	private Array<ParticleEffect> particleList = new Array<ParticleEffect>();// 存贮所有粒子的容器

	public BombParticle(GameScreen context) {
		this.context = context;
		load();
	}

	/**
	 * 载入资源
	 */
	private void load() {
		blueParticle = new ParticleEffect();
		blueParticle.load(Gdx.files.internal("particle/blue_particle.p"),
				Gdx.files.internal("particle"));
		bluePool = new ParticleEffectPool(blueParticle, 10, 15);
	}

	/**
	 * 绘制 更新逻辑
	 * 
	 * @param batch
	 * @param delta
	 */
	public void draw(SpriteBatch batch, float delta) {
		/**
		 * 绘制粒子
		 */
		for (int i = 0; i < particleList.size; i++) {
			particleList.get(i).draw(batch, delta);
		}
		/**
		 * 将死亡的粒子移除
		 */
		ParticleEffect temparticle;
		for (int i = 0; i < particleList.size; i++) {
			temparticle = particleList.get(i);
			if (temparticle.isComplete()) {
				particleList.removeIndex(i);
			}
		} // end for i
	}

	/**
	 * 加入待显示的粒子
	 * 
	 * @param type
	 * @param posX
	 * @param posY
	 */
	public void addParticle(int type, int row, int col) {
		float posX = CoreData.PAD + CoreData.CUBE_WIDTH * col
				+ (CoreData.CUBE_WIDTH >> 1);
		float posY = GameScreen.SC_HEIGHT
				- (CoreData.CUBE_BORN_Y + CoreData.CUBE_HEIGHT * row - (CoreData.CUBE_HEIGHT >> 1));
		ParticleEffect addParticle = bluePool.obtain();
		System.out.println(posX+"     "+posY);
		addParticle.setPosition(posX, posY);
		particleList.add(addParticle);
	}

	public void dispose() {
		bluePool.clear();
		particleList.clear();
	}
}// end class
