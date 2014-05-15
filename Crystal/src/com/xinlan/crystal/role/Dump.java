package com.xinlan.crystal.role;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.xinlan.crystal.Resource;
import com.xinlan.crystal.screen.GameScreen;

public class Dump extends Sprite
{
    private GameScreen mContext;
    private Vector2 pos = new Vector2();
    public Dump(GameScreen context)
    {
        this.mContext = context;
        this.set(Resource.getInstance().sp);
        pos.x = 100;
        pos.y = 400;
        this.setPosition(pos.x, pos.y);
    }
    
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        //this.setColor(1f, 1f, 1f, 1f);
//        this.rotate(1);
        this.translate(0.2f, 0.1f);
        this.scale(MathUtils.random(-0.05f, 0.05f));
        System.out.println(this.getX());
        //this.setSize(this.getHeight(), this.getWidth()+1);
    }
}//end class
