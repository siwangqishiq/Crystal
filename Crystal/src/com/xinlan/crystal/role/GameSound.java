package com.xinlan.crystal.role;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.xinlan.crystal.screen.GameScreen;

public class GameSound
{
	public Music bgMusic;
	
	public Sound generateSound;
	public Sound fireSound;
	public Sound killSound;
	public Sound bombSound;
	
    public GameSound(GameScreen screen)
    {
    	bgMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/main_bg.mp3")); 
    	bgMusic.setLooping(true);
    	
    	generateSound = Gdx.audio.newSound(Gdx.files.internal("sound/generate.mp3"));
    	fireSound = Gdx.audio.newSound(Gdx.files.internal("sound/fire.ogg"));
    	killSound = Gdx.audio.newSound(Gdx.files.internal("sound/kill.wav"));
    	bombSound =  Gdx.audio.newSound(Gdx.files.internal("sound/bomb.ogg"));
    }
    
    
    public void dispose()
    {
    	bgMusic.dispose();
    	generateSound.dispose();
    	fireSound.dispose();
    	killSound.dispose();
    	bombSound.dispose();
    }
}//end class
