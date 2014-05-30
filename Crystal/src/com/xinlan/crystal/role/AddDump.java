package com.xinlan.crystal.role;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.xinlan.crystal.Resource;
import com.xinlan.crystal.screen.GameScreen;

public class AddDump extends Sprite
{
    public static final int ADD_POS_Y = 45;

    public static final int STATUS_WAITSHOOT = 1;
    public static final int STATUS_SHOOTING = 2;

    public int status = STATUS_WAITSHOOT;

    private GameScreen context;
    public Vector2 pos = new Vector2();

    private Sprite spriteBlue;
    private Sprite spriteRed;
    private Sprite spriteYellow;
    private Sprite spritePink;

    public Sprite curSprite;

    private float dy = 15;

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

        curSprite = spriteYellow;
    }

    public void draw(SpriteBatch batch, float delta)
    {
        switch(status)
        {
            case STATUS_WAITSHOOT:
                curSprite.draw(batch);
                break;
            case STATUS_SHOOTING:
                curSprite.translateY(dy);
                curSprite.draw(batch);
                System.out.println(curSprite.getY());
                
                if (curSprite.getY() >= GameScreen.SC_HEIGHT)
                {
                    curSprite.setPosition(0, 0);
                    status = STATUS_WAITSHOOT;
                }
                break;
        }//end switch
      
    }
}// end class
