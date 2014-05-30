package com.xinlan.crystal.role;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.xinlan.crystal.Resource;
import com.xinlan.crystal.screen.GameScreen;

public class AddDump extends Sprite
{
    public static final int ADD_POS_Y=45;
    
    private GameScreen context;
    public Vector2 pos = new Vector2();

    public Sprite spriteBlue;
    public Sprite spriteRed;
    public Sprite spriteYellow;
    public Sprite spritePink;

    public AddDump(GameScreen context)
    {
        this.context = context;
        spriteBlue = new Sprite(Resource.getInstance().dumpBlue);
        spriteBlue.setSize(CoreData.CUBE_WIDTH, CoreData.CUBE_HEIGHT);

        spriteRed = new Sprite(Resource.getInstance().dumpRed);
        spriteRed.setSize(CoreData.CUBE_WIDTH, CoreData.CUBE_HEIGHT);

        spriteYellow = new Sprite(Resource.getInstance().dumpYellow);
        spriteYellow.setSize(CoreData.CUBE_WIDTH, CoreData.CUBE_HEIGHT);

        spritePink = new Sprite(Resource.getInstance().dumpPink);
        spritePink.setSize(CoreData.CUBE_WIDTH, CoreData.CUBE_HEIGHT);
    }

    public void draw(SpriteBatch batch, float delta)
    {
        if (context.game_state == GameScreen.STATE_NORMAL
                || context.game_state == GameScreen.STATE_GROWING)
        {
            spritePink.setPosition(pos.x, ADD_POS_Y);
            spritePink.draw(batch);
        }
    }
}// end class
