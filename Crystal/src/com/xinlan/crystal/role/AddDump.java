package com.xinlan.crystal.role;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
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
    public int add_type=-1;//add类型

    private float dy = 20;

    public AddDump(GameScreen context)
    {
        this.context = context;
        spriteBlue = new Sprite(Resource.getInstance().dumpBlue);
        spriteBlue.setSize(CoreData.CUBE_WIDTH, CoreData.CUBE_HEIGHT);
        spriteBlue.setPosition(-100, -100);
        
        spriteRed = new Sprite(Resource.getInstance().dumpRed);
        spriteRed.setSize(CoreData.CUBE_WIDTH, CoreData.CUBE_HEIGHT);
        spriteRed.setPosition(-100, -100);
        
        spriteYellow = new Sprite(Resource.getInstance().dumpYellow);
        spriteYellow.setSize(CoreData.CUBE_WIDTH, CoreData.CUBE_HEIGHT);
        spriteYellow.setPosition(-100, -100);
        
        spritePink = new Sprite(Resource.getInstance().dumpPink);
        spritePink.setSize(CoreData.CUBE_WIDTH, CoreData.CUBE_HEIGHT);
        spritePink.setPosition(-100, -100);
        
        reSetCurSprite(MathUtils.random(1, CoreData.TYPE_NUM));
    }
    
    public void reSetCurSprite(int type)
    {
        this.add_type = type;
        switch(type)
        {
            case CoreData.RED:
                curSprite = spriteRed;
                break;
            case CoreData.PINK:
                curSprite = spritePink;
                break;
            case CoreData.YELLOW:
                curSprite = spriteYellow;
                break;
            case CoreData.BLUE:
                curSprite = spriteBlue;
                break;
        }//end switch
    }

    public void draw(SpriteBatch batch, float delta)
    {
        if(curSprite==null) return;
        
        switch(status)
        {
            case STATUS_WAITSHOOT:
                curSprite.draw(batch);
                break;
            case STATUS_SHOOTING:
                curSprite.translateY(dy);
                curSprite.draw(batch);
                if (curSprite.getY() >= GameScreen.SC_HEIGHT)
                {
                    curSprite.setPosition(-100, -100);
                    status = STATUS_WAITSHOOT;
                    reSetCurSprite(MathUtils.random(1, CoreData.TYPE_NUM));
                }
                break;
        }//end switch
    }
}// end class
