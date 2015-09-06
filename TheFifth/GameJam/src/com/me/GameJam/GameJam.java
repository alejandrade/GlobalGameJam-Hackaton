package com.me.GameJam;


import com.me.GameJam.Screens.GameScreen;
import com.me.GameJam.Screens.SplashScreen;
import com.badlogic.gdx.Game;

public class GameJam extends Game {

	public static final String VERSION = "0.0.0.2 Pre Alpha";
	public static final String LOG = "Game Jam";
	@Override
	public void create() {
		setScreen(new GameScreen(this));
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

}
