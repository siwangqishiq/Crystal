package com.xinlan.crystal.action;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.xinlan.crystal.role.CoreData;
import com.xinlan.crystal.screen.GameScreen;

/**
 * 
 * @author panyi
 * 
 */
public class TouchListener implements InputProcessor
{
    private GameScreen context;

    boolean isPressed = false;
    Vector3 touchPos = new Vector3();

    public TouchListener(GameScreen context)
    {
        this.context = context;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        this.isPressed = true;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        // System.out.println(screenX+","+screenY);

        touchPos.set(screenX, screenY, 0);
        context.cam.unproject(touchPos);

        Vector2 pos = context.addDump.pos;
        pos.x = touchPos.x - (CoreData.CUBE_WIDTH >> 1);

        if (pos.x < 0)
        {
            pos.x = 0;
        }
        else if (pos.x > GameScreen.SC_WIDTH - CoreData.CUBE_WIDTH)
        {
            pos.x = GameScreen.SC_WIDTH - CoreData.CUBE_WIDTH;
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        this.isPressed = false;

        touchPos.set(screenX, screenY, 0);
        context.cam.unproject(touchPos);
        Vector2 pos = context.addDump.pos;
        pos.x = touchPos.x;

        int LeftBound = CoreData.CUBE_WIDTH + CoreData.PAD;
        if (pos.x < LeftBound)
        {// 1
            pos.x = CoreData.PAD;
        }
        else if (pos.x >= LeftBound
                && pos.x < (LeftBound + CoreData.CUBE_WIDTH))
        {// 2
            pos.x = LeftBound;
        }
        else if (pos.x >= LeftBound + CoreData.CUBE_WIDTH
                && pos.x < LeftBound + (CoreData.CUBE_WIDTH << 1))
        {// 3
            pos.x = LeftBound + CoreData.CUBE_WIDTH;
        }
        else if (pos.x >= LeftBound + (CoreData.CUBE_WIDTH << 1)
                && pos.x <LeftBound + (CoreData.CUBE_WIDTH << 1)+CoreData.CUBE_WIDTH)
        {// 4
            pos.x = LeftBound + (CoreData.CUBE_WIDTH << 1);
        }
        else if (pos.x >= LeftBound + (CoreData.CUBE_WIDTH << 1)+CoreData.CUBE_WIDTH
                && pos.x < LeftBound + (CoreData.CUBE_WIDTH << 2))
        {// 5
            pos.x =  LeftBound + (CoreData.CUBE_WIDTH << 1)+CoreData.CUBE_WIDTH;
        }
        else if (pos.x >= LeftBound + (CoreData.CUBE_WIDTH << 2))
        {//6
            pos.x = LeftBound + (CoreData.CUBE_WIDTH << 2);
        }
        //System.out.println("---->"+pos.x);
        
        return false;
    }

    @Override
    public boolean keyDown(int arg0)
    {
        return false;
    }

    @Override
    public boolean keyTyped(char arg0)
    {
        return false;
    }

    @Override
    public boolean keyUp(int arg0)
    {
        return false;
    }

    @Override
    public boolean mouseMoved(int arg0, int arg1)
    {
        return false;
    }

    @Override
    public boolean scrolled(int arg0)
    {
        return false;
    }
}// end class
