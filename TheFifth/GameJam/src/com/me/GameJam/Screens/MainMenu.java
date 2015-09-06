package com.me.GameJam.Screens;

import com.me.GameJam.GameJam;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class MainMenu implements Screen{
	
	GameJam game;
	Stage stage;
	BitmapFont font;
	BitmapFont titleFont;
	TextureAtlas atlas;
	Skin skin;
	SpriteBatch batch;
	TextButton newGame;
	TextButton savedGame;
	Label titleLable;
	
	public MainMenu(GameJam game){
		this.game = game;
		
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0,0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		batch.begin();
		stage.draw();
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		if(stage==null){
			stage = new Stage(width, height, true);
			stage.clear();
			Gdx.input.setInputProcessor(stage);
			
			TextButtonStyle style = new TextButtonStyle();
			style.up = skin.getDrawable("button1");
			style.down = skin.getDrawable("button2");
			style.font = font;
			
				
				newGame = new TextButton("New Game", style);
				savedGame = new TextButton("Saved Game", style);
				newGame.setSize(400,50);
				savedGame.setSize(newGame.getWidth(), newGame.getHeight());
				
				newGame.setX(Gdx.graphics.getWidth()/2 - newGame.getWidth()/2);
				newGame.setY(Gdx.graphics.getHeight()/2 - newGame.getHeight()/2);
				savedGame.setX(newGame.getX());
				savedGame.setY(newGame.getY()-savedGame.getHeight()-1);

				
				newGame.addListener(new InputListener(){
					
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
						return true;
					}
					
					public void touchUp(InputEvent event, float x, float y, int pointer, int button){
						game.setScreen(new GameScreen(game));
						
					}
					
					
		
				});
				
				LabelStyle ls = new LabelStyle(titleFont, Color.WHITE);

				titleLable = new Label("Something Else", ls);
				
				titleLable.setX(0);
				titleLable.setY(Gdx.graphics.getHeight()/2+100);
				titleLable.setWidth(width);
				titleLable.setAlignment(Align.center);
				stage.addActor(newGame);
				stage.addActor(savedGame);
				stage.addActor(titleLable);
				

		
	}
}

	@Override
	public void show() {
		batch = new SpriteBatch();
		skin = new Skin();
		atlas = new TextureAtlas("data/ui/button2.pack");
		skin.addRegions(atlas);
		font = new BitmapFont(Gdx.files.internal("data/font/font.fnt"), false);
		titleFont = new BitmapFont(Gdx.files.internal("data/font/TitleFont.fnt"), false);
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		skin.dispose();
		atlas.dispose();
		font.dispose();
		stage.dispose();
		titleFont.dispose();
	}

	
	
}
