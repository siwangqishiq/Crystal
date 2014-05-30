package com.xinlan.crystal.role;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.xinlan.crystal.Resource;
import com.xinlan.crystal.screen.GameScreen;

public final class CoreData
{
    public static int PAD = 15;// 边距值
    public static int CUBE = 75;// 色块大小

    public static final int TYPE_NUM = 4;// 类型数量
    public static final int BLUE = 1;// 兰
    public static final int RED = 2;// 红
    public static final int YELLOW = 3;// 黄
    public static final int PINK = 4;// 粉
    public static final int CUBE_WIDTH = 75;// 宽度
    public static final int CUBE_HEIGHT = 60;// 高度
    public static final int CUBE_BORN_Y=150;
    

    public static final int rowNum = 11;//行数
    public static final int colNum = 6;//列数

    private GameScreen context;
    public TextureRegion blueTexture;
    public TextureRegion redTexture;
    public TextureRegion yellowTexture;
    public TextureRegion pinkTexture;
    
    public static float Dump_Grow_Span = 1.5f;//产生方块毫秒间隔

    private float growDy = 6;
    private float growY = 0;
    private float growDx = 5;
    private float growX=0;

    public int[][] data = {//主运算矩阵
            { 0, 0, 0, 0, 0, 0 },//
            { 0, 0, 0, 0, 0, 0 },//
            { 0, 0, 0, 0, 0, 0 },//
            { 0, 0, 0, 0, 0, 0 },//
            { 0, 0, 0, 0, 0, 0 }, //
            { 0, 0, 0, 0, 0, 0 },//
            { 0, 0, 0, 0, 0, 0 },//
            { 0, 0, 0, 0, 0, 0 },//
            { 0, 0, 0, 0, 0, 0 },//
            { 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0 } };

    private int[] temp = new int[colNum];//临时存贮单行数组变量
    private float countTime = 0;//计数器
    
    private float xx;//debug

    public CoreData(GameScreen context)
    {
        this.context = context;
        
        blueTexture = Resource.getInstance().blueTextureRegion;
        redTexture = Resource.getInstance().redTextureRegion;
        yellowTexture = Resource.getInstance().yellowTextureRegion;
        pinkTexture = Resource.getInstance().pinkTextureRegion;

        genBottomOneRow();
    }

    public void genBottomOneRow()
    {
        for (int i = 0; i < colNum; i++)
        {
            temp[i] = MathUtils.random(1, TYPE_NUM);
        }// end for i
        for (int j = rowNum - 2; j >= 1; j--)
        {
            System.arraycopy(data[j - 1], 0, data[j], 0, colNum);
        }// end for
        System.arraycopy(temp, 0, data[0], 0, colNum);
    }

    private void showDataNormal(SpriteBatch batch)
    {
        int startX = PAD;
        int startY = GameScreen.SC_HEIGHT - CUBE_BORN_Y;
        for (int i = 0; i < rowNum; i++)
        {
            for (int j = 0; j < colNum; j++)
            {
                drawCube(batch, data[i][j], startX, startY, CUBE_WIDTH,
                        CUBE_HEIGHT);
                startX += CUBE_WIDTH;
            }// end for j
            startX = PAD;
            startY -= CUBE_HEIGHT;
        }// end for i
    }

    private void showDataGrowing(SpriteBatch batch)
    {
        int startX = PAD;
        int startY = GameScreen.SC_HEIGHT - CUBE_BORN_Y;
        for (int i = 0; i < rowNum; i++)
        {
            if (i == 0)
            {
                float drawY = startY + CUBE_HEIGHT - growY;
                for (int j = 0; j < colNum; j++)
                {
                    float drawX = startX+CUBE_WIDTH/2-growX;
                    drawCube(batch, data[i][j], drawX, drawY, 2*growX,
                            growY);
                    startX += CUBE_WIDTH;
                }// end for j
                startX = PAD;
                startY -= growY;
            }
            else
            {
                for (int j = 0; j < colNum; j++)
                {
                    drawCube(batch, data[i][j], startX, startY, CUBE_WIDTH,
                            CUBE_HEIGHT);
                    startX += CUBE_WIDTH;
                }// end for j
                startX = PAD;
                startY -= CUBE_HEIGHT;
            }
        }// end for i
    }

    /**
     * 绘制方块
     * @param batch
     * @param type
     * @param startX
     * @param startY
     * @param width
     * @param height
     */
    private void drawCube(SpriteBatch batch, int type, float startX,
            float startY, float width, float height)
    {
        switch (type)
        {
            case BLUE:
                batch.draw(blueTexture, startX, startY, width, height);
                break;
            case RED:
                batch.draw(redTexture, startX, startY, width, height);
                break;
            case YELLOW:
                batch.draw(yellowTexture, startX, startY, width,
                        height);
                break;
            case PINK:
                batch.draw(pinkTexture, startX, startY, width, height);
                break;
        }
    }

    public void draw(SpriteBatch batch, float delta)
    {
        switch (context.game_state)
        {
            case GameScreen.STATE_NORMAL:// 正常状态
                showDataNormal(batch);
                countTime += delta;
                if (countTime >= Dump_Grow_Span)
                {
                    countTime = 0;
                    genBottomOneRow();
                    context.game_state = GameScreen.STATE_GROWING;
                }
                break;
            case GameScreen.STATE_GROWING:// 增长状态
                showDataGrowing(batch);
                if (growY >= CUBE_HEIGHT)
                {
                    growY = 0;
                    context.game_state = GameScreen.STATE_NORMAL;
                    
                    growX=0;
                }
                else
                {
                    growY += growDy;
                    if(growX<CUBE_WIDTH>>1)
                    {
                        growX+=growDx;
                    }else{
                        growX = CUBE_WIDTH>>1;
                    }
                }
                break;
        }// end switch

    }
}// end class
