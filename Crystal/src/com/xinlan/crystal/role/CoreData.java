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

    public static final int STATE_NORMAL = 13;
    public static final int STATE_GROWING = 14;

    public static final int rowNum = 10;
    public static final int colNum = 6;

    public TextureRegion blueTexture;
    public TextureRegion redTexture;
    public TextureRegion yellowTexture;
    public TextureRegion pinkTexture;
    public static float Dump_Grow_Span = 2;

    public int game_state = STATE_NORMAL;

    private float growDy = 5;
    private float growY = 0;

    public int[][] data = {//
    { 0, 0, 0, 0, 0, 0 },//
            { 0, 0, 0, 0, 0, 0 },//
            { 0, 0, 0, 0, 0, 0 },//
            { 0, 0, 0, 0, 0, 0 },//
            { 0, 0, 0, 0, 0, 0 }, //
            { 0, 0, 0, 0, 0, 0 },//
            { 0, 0, 0, 0, 0, 0 },//
            { 0, 0, 0, 0, 0, 0 },//
            { 0, 0, 0, 0, 0, 0 },//
            { 0, 0, 0, 0, 0, 0 } };

    private int[] temp = new int[colNum];
    private float countTime = 0;

    public CoreData()
    {
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
        int startY = GameScreen.SC_HEIGHT - 100;
        for (int i = 0; i < rowNum; i++)
        {
            for (int j = 0; j < colNum; j++)
            {
                switch (data[i][j])
                {
                    case BLUE:
                        batch.draw(blueTexture, startX, startY, CUBE_WIDTH,
                                CUBE_HEIGHT);
                        break;
                    case RED:
                        batch.draw(redTexture, startX, startY, CUBE_WIDTH,
                                CUBE_HEIGHT);
                        break;
                    case YELLOW:
                        batch.draw(yellowTexture, startX, startY, CUBE_WIDTH,
                                CUBE_HEIGHT);
                        break;
                    case PINK:
                        batch.draw(pinkTexture, startX, startY, CUBE_WIDTH,
                                CUBE_HEIGHT);
                        break;
                }
                startX += CUBE_WIDTH;
            }// end for j
            startX = PAD;
            startY -= CUBE_HEIGHT;
        }// end for i
    }

    private void showDataGrowing(SpriteBatch batch)
    {
        int startX = PAD;
        int startY = GameScreen.SC_HEIGHT - 100;
        for (int i = 0; i < rowNum; i++)
        {
            if (i == 0)
            {
                for (int j = 0; j < colNum; j++)
                {
                    switch (data[i][j])
                    {
                        case BLUE:
                            batch.draw(blueTexture, startX, startY, CUBE_WIDTH,
                                    growY);
                            break;
                        case RED:
                            batch.draw(redTexture, startX, startY, CUBE_WIDTH,
                                    growY);
                            break;
                        case YELLOW:
                            batch.draw(yellowTexture, startX, startY,
                                    CUBE_WIDTH, growY);
                            break;
                        case PINK:
                            batch.draw(pinkTexture, startX, startY, CUBE_WIDTH,
                                    growY);
                            break;
                    }
                    startX += CUBE_WIDTH;
                }// end for j
                startX = PAD;
                startY -= growY;
            }
            else
            {
                for (int j = 0; j < colNum; j++)
                {
                    switch (data[i][j])
                    {
                        case BLUE:
                            batch.draw(blueTexture, startX, startY, CUBE_WIDTH,
                                    CUBE_HEIGHT);
                            break;
                        case RED:
                            batch.draw(redTexture, startX, startY, CUBE_WIDTH,
                                    CUBE_HEIGHT);
                            break;
                        case YELLOW:
                            batch.draw(yellowTexture, startX, startY,
                                    CUBE_WIDTH, CUBE_HEIGHT);
                            break;
                        case PINK:
                            batch.draw(pinkTexture, startX, startY, CUBE_WIDTH,
                                    CUBE_HEIGHT);
                            break;
                    }
                    startX += CUBE_WIDTH;
                }// end for j
                startX = PAD;
                startY -= CUBE_HEIGHT;
            }
        }// end for i
    }

    public void draw(SpriteBatch batch, float delta)
    {
        switch (game_state)
        {
            case STATE_NORMAL:// 正常状态
                showDataNormal(batch);
                countTime += delta;
                if (countTime >= Dump_Grow_Span)
                {
                    countTime = 0;
                    genBottomOneRow();
                    game_state = STATE_GROWING;
                }
                break;
            case STATE_GROWING:// 增长状态
                showDataGrowing(batch);
                if (growY >= CUBE_HEIGHT)
                {
                    growY = 0;
                    game_state = STATE_NORMAL;
                }
                else
                {
                    growY += growDy;
                }
                break;
        }// end switch

    }
}// end class
