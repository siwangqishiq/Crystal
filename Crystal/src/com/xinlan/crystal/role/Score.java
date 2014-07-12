package com.xinlan.crystal.role;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.xinlan.crystal.screen.GameScreen;

/**
 * 分数展示 逻辑 控件
 * 
 * @author panyi 
 * 
 */
public final class Score
{
    public static final int LFET = 460;
    public static final int TOP = 760;
    public static final int SCORE_VALUE=7;
    public static final int CHAR_WIDTH=25;
    public static final int dScore = 1;// 分数增长量

    public int value = 0;// 分数
    private int cur_value;// 当前展示分数
    private GameScreen context;
    private BitmapFont bitmapFont;

    public Score(GameScreen context)
    {
        this.context = context;
        bitmapFont = new BitmapFont(Gdx.files.internal("font/score2.fnt"),
                Gdx.files.internal("font/score2.png"), false);
        bitmapFont.setColor(0.5f, 0.5f, 0.5f, 1); // 设置颜色
        bitmapFont.setScale(1.0f); // 设置字体比例大小
    }

    /**
     * 消去的团子数量
     * 
     * @param addScore
     */
    public void addScore(int dismissNum)
    {
        int addScore = dismissNum*SCORE_VALUE;
        value += addScore;
    }

    public void draw(SpriteBatch batch, float delta)
    {
        int weight = (cur_value+"").length();
        bitmapFont.draw(batch, cur_value + "", LFET-weight*CHAR_WIDTH, TOP);
        
        if (cur_value < value)
        {
            cur_value += dScore;
        }
    }
}// end class
