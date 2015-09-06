package com.me.GameJam.Load;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class GameAudio {
	
	public GameAudio() {}
	
	public static Music song = Gdx.audio.newMusic(Gdx.files.internal("data/489882_Worlds---LoveKavi.mp3"));
	public static Sound shoot = Gdx.audio.newSound(Gdx.files.internal("data/Laser.mp3"));
	
	public static void playMusic(boolean looping){
		song.setLooping(looping);
		song.play();
	}
	
	public static void stopMusic(){
		song.stop();
	}
	
	public static void shoot(){
		shoot.play();
	}
	

	public static void dispose(){
		shoot.dispose();
		song.dispose();
	}
	

}

