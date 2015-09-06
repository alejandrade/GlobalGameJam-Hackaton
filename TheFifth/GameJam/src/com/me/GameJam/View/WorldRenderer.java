package com.me.GameJam.View;

import com.me.GameJam.Load.Animator;
import com.me.GameJam.Load.GameAudio;
import com.me.GameJam.Load.Load;
import com.me.GameJam.Model.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Iterator;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Array.ArrayIterator;

public class WorldRenderer {

	World world;
	SpriteBatch batch;

	Load textureLoad;

	Player1 player1;
	Player2 player2;
	Person person;

	// //// animations go here DECLARATINONS !!!!!!!!!!!!!!!!!!!
	
	///////// Players
	
	////////////// walk
	Animator player2Animation;
	
	Animator player1Running;
	Animator player1ShootUp;
	Animator player1ShootForward;
	Animator player1ShootAngle;
	Animator player1Jumping;
	Animator player1Idle;
	Animator player1IdleShoot;
	
	Animator player2Running;
	Animator player2ShootUp;
	Animator player2ShootForward;
	Animator player2ShootAngle;
	Animator player2Jumping;
	Animator player2Idle;
	Animator player2IdleShoot;
	
	Animator flyingEnemy;
	Animator dogEnemie;
	

	// ////////////////////////////////////////////

	OrthographicCamera cam;

	Iterator<Entity> _entityIT;

	float width, height;
	float zoomH = 1, zoomW = 1;

	public WorldRenderer(World world) {
		this.world = world;

		width = (Gdx.graphics.getWidth()) / zoomW;
		height = (Gdx.graphics.getHeight()) / zoomH;

		cam = new OrthographicCamera();
		cam.setToOrtho(false, width, height);
		cam.update();

		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		textureLoad = new Load();

		//GameAudio.playMusic(true);

		// //define animations.
		player1Running = new Animator("data/Player/PlayerAnimation.atlas", "player", 0, 9, 0.09f);
		player1ShootUp= new Animator("data/Player/PlayerAnimation.atlas", "player", 10,10, 0.09f);
		player1ShootForward = new Animator("data/Player/PlayerAnimation.atlas", "player", 20,30, 0.09f);
		player1ShootAngle = new Animator("data/Player/PlayerAnimation.atlas", "player",37,45, 0.09f);
		player1Idle = new Animator("data/Player/PlayerAnimation.atlas", "player",47,47, 0.09f);
		player1IdleShoot = new Animator("data/Player/PlayerAnimation.atlas", "player",20,20, 0.09f);
		player1Jumping = new Animator("data/Player/PlayerAnimation.atlas", "player",31,31, 0.09f);
		
		player2Running = new Animator("data/Player/PlayerAnimation.atlas", "player", 0, 9, 0.09f);
		player2ShootUp= new Animator("data/Player/PlayerAnimation.atlas", "player", 10,10, 0.09f);
		player2ShootForward = new Animator("data/Player/PlayerAnimation.atlas", "player", 20,30, 0.09f);
		player2ShootAngle = new Animator("data/Player/PlayerAnimation.atlas", "player",37,45, 0.09f);
		player2Idle = new Animator("data/Player/PlayerAnimation.atlas", "player",47,47, 0.09f);
		player2IdleShoot = new Animator("data/Player/PlayerAnimation.atlas", "player",20,20, 0.09f);
		player2Jumping = new Animator("data/Player/PlayerAnimation.atlas", "player",31,31, 0.09f);
		
		flyingEnemy = new Animator("data/enemies/enemy.atlas", "flying",0,4, 0.0f);
		dogEnemie = new Animator("data/enemies/enemy.atlas", "otherEnemy",1,6, 0.2f);
		
		

	}

	public void render() {

		draw();

	}

	public void dispose() {
		batch.dispose();
		textureLoad.dispose();
	}

	private void draw(){
		
		player1 = world.getPlayer1();
		player2 = world.getPlayer2();
		
		
		batch.begin();
					
		///draw background.
		
		world.getBackground().draw(batch);
		
		
		
		//////////////// Player 1 animations
		
		if (! player1.isJumping()) {
			if (player1.isMovingStraight()) player1Running.render(player1, batch, player1.getFlipped(), player1.getType());
			else if (player1.isShootingDiagonal()) player1ShootAngle.render(player1, batch, player1.getFlipped(), player1.getType());
			else if (player1.isLookingUp())player1ShootUp.render(player1, batch, player1.getFlipped(), player1.getType());
			else if(player1.isShootingNormal()) {
				if (player1.isMoving()) {
					player1ShootForward.render(player1, batch, player1.getFlipped(), player1.getType());
					
					
					
				}
				else {
					player1IdleShoot.render(player1, batch, player1.getFlipped(), player1.getType());
				}
			}
			else	player1Idle.render(player1, batch, player1.getFlipped(), player1.getType());
		}else {
			if (player1.isMoving())player1Jumping.render(player1, batch, player1.getFlipped(), player1.getType());
			else if (player1.isShootingDiagonal()) player1Jumping.render(player1, batch, player1.getFlipped(), player1.getType());
			else if (player1.isLookingUp())player1Jumping.render(player1, batch, player1.getFlipped(), player1.getType());
			else player1Jumping.render(player1, batch, player1.getFlipped(), player1.getType());
				
			
		}
		
		//////////// Player 2 animations
		if (! player2.isJumping()) {
			if (player2.isMovingStraight()) player2Running.render(player2, batch, player2.getFlipped(), player2.getType());
			else if (player2.isShootingDiagonal()) player2ShootAngle.render(player2, batch, player2.getFlipped(), player2.getType());
			else if (player2.isLookingUp())player2ShootUp.render(player2, batch, player2.getFlipped(), player2.getType());
			else if(player2.isShootingNormal()) {
				if (player2.isMoving()) {
					player2ShootForward.render(player2, batch, player2.getFlipped(), player2.getType());
				}
				else {
					player2IdleShoot.render(player2, batch, player2.getFlipped(), player2.getType());
				}
			}
			else	player2Idle.render(player2, batch, player2.getFlipped(), player2.getType());
		}else {
			if (player2.isMoving())player2Jumping.render(player2, batch, player2.getFlipped(), player2.getType());
			else if (player2.isShootingDiagonal()) player2Jumping.render(player2, batch, player2.getFlipped(), player2.getType());
			else if (player2.isLookingUp())player2Jumping.render(player2, batch, player2.getFlipped(), player2.getType());
			else player2Jumping.render(player2, batch, player2.getFlipped(), player2.getType());
				
			
		}
		
		
		
		drawEntity(Wall.getWallArray(), textureLoad.getPlatform1(), false);
		drawEntity(Bullet.getBulletArray(), textureLoad.getDefaultBullet(), true);
		
		ArrayIterator<Entity> i = new ArrayIterator<Entity>(Enemy.getEnemyArray());
		while (i.hasNext()) {
			Enemy e = (Enemy)i.next();
			if (e.getClass() == Enemy.class) {
				e.draw(batch);
				
			}
			else if (e.getClass() == EnemyOne.class) {
				EnemyOne e1 = (EnemyOne)e;
				
					dogEnemie.render(e1, batch, e1.getFlipped(), e1.getType());		

			}
			else if (e.getClass() == EnemyTwo.class) {
				EnemyTwo e2 = (EnemyTwo)e;
					
					flyingEnemy.render(e2, batch, e2.getFlipped(), e2.getType());
					
				}
		
		
		
		}
		
		batch.end();
	
		////////////// ANIMATION BATCH DRAWING
		
		
		
		
	}

	public void drawEntity(Array<Entity> a, Texture tex, boolean movable) {
		_entityIT = a.iterator();
		Entity e;
		while (_entityIT.hasNext()) {
			e = _entityIT.next();
			if (!movable)
				e.draw(batch);
			else {
				Entity me = (Entity) e;
				me.draw(batch);

			}
		}
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

}
