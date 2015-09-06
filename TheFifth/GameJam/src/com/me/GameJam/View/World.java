package com.me.GameJam.View;

import com.me.GameJam.GameJam;
import com.me.GameJam.Model.*;
import com.me.GameJam.Load.Load;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.Iterator;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Array.ArrayIterator;

public class World {

	GameJam game;
	Load texLoad = new Load();
	//float player1LocX=1, player1LocY=1, player1SocWidth = texLoad.getPlayer1().getWidth(), player1SocHeight = texLoad.getPlayer1().getHeight(), player1Speed = 1.0f, player1Rotation = 0;
	//float player2LocX=1280-player1SocWidth, player2LocY=1, player2SocWidth = texLoad.getPlayer2().getWidth(), player2SocHeight = texLoad.getPlayer2().getHeight(), player2Speed = 1.0f, player2Rotation = 0;

	InputHandler mainInput;
	
	Sprite background;
	
	Player1 player1;
	Player2 player2;
	
	Wall platform1;
	Wall platform2;
	Wall platform3;
	Wall platform4;
	Wall platform5;
	WallMoving platform1Moving;
	
	Wall platform21;
	Wall platform22;
	Wall platform23;
	Wall platform24;
	Wall platform25;
	Wall platform2Moving;
	
	Wall concreteBottom;
	Wall concreteTop;
	
	
	SpawnPoint spawn1;
	SpawnPoint spawn2;
	SpawnPoint spawn3;
	SpawnPoint spawn4;

	SpawnPoint spawn21;
	SpawnPoint spawn22;
	SpawnPoint spawn23;
	SpawnPoint spawn24;
	
	ArrayIterator<Entity> i;
	
	

	double enemyTimer = 0.0f;
	double nextEnemyTime = 0.0f;
	int nextEnemyType = 1; ///1 is player 1 type.
	int nextEnemyClass = 0; /// 0 is default enemy
	private WallMoving platfom1Moving;
	
	
	
	
	
	
	public World(GameJam game){
		this.game=game;
		Gdx.input.setInputProcessor(new InputHandler(this));
		
		//mainInput = new InputHandler(this);
		
		background = new Sprite(texLoad.getWorld());
		
		player1 = new Player1(texLoad.getPlayer1(), 1, false);
		player2 = new Player2(texLoad.getPlayer1(), 2, false);
		
		concreteBottom = new Wall(texLoad.getPlatBottom(), new Vector2(280, 50), 1, false);
		concreteTop = new Wall(texLoad.getPlatTop(), new Vector2(352, 80), 1, false);
		
		platform1 = new Wall(texLoad.getPlatform1(), new Vector2(0, 558), 1, false);
		platform2 = new Wall(texLoad.getPlatform1(), new Vector2(0, 211), 1, false);
		platform3 = new Wall(texLoad.getPlatform1(), new Vector2(983, 211), 1, false);
		platform4 = new Wall(texLoad.getPlatform1(), new Vector2(983, 558), 1, false);
		platform5 = new Wall(texLoad.getPlatform1(), new Vector2(472, 558), 1, false);
		platfom1Moving = new WallMoving(texLoad.getPlatformMoving(), new Vector2(960 - texLoad.getPlatformMoving().getWidth(), 350), 1, false);
		
		
		platform21 = new Wall(texLoad.getPlatform1(), new Vector2(0, 365), 2, false);
		platform22 = new Wall(texLoad.getPlatform1(), new Vector2(217, 543), 2, false);
		platform23 = new Wall(texLoad.getPlatform1(), new Vector2(724, 543), 2, false);
		platform24 = new Wall(texLoad.getPlatform1(), new Vector2(983, 365), 2, false);
		//platform2Moving = 
		
		int enemy1Width = 20;
		int platFormWidth = texLoad.getPlatform1().getWidth();

		spawn1 = new SpawnPoint(new Vector2(platform1.getX() - enemy1Width + 1, platform1.getY() + 20), 1 );
		spawn2 = new SpawnPoint(new Vector2(platform2.getX() - enemy1Width + 1, platform2.getY() + 20), 1 );
		spawn3 = new SpawnPoint(new Vector2(platform3.getX() + platFormWidth + 1, platform3.getY() + 20), 1 );
		spawn4 = new SpawnPoint(new Vector2(platform4.getX() + platFormWidth + 1, platform1.getY() + 20), 1 );
		
		
		spawn21 = new SpawnPoint(new Vector2(platform21.getX() - enemy1Width + 1, platform21.getHeight() + 20), 2 );
		spawn22 = new SpawnPoint(new Vector2(platform24.getX() + platFormWidth + 1, platform24.getHeight() + 20), 2 );
		spawn23 = new SpawnPoint(new Vector2(-enemy1Width + 1, 50), 2 );
		spawn24 = new SpawnPoint(new Vector2(Gdx.graphics.getWidth() + 1, 50), 2 );

		spawnEnemy();
		
		
	}
	
	public Sprite getBackground() {
		return background;
	}
	
	public Player1 getPlayer1(){
		return player1;
	}
	public Player2 getPlayer2(){
		return player2;
	}
	public Wall getWall(){
		return platform1;
	}
	
	
	public Wall getConBottom() {
		return concreteBottom;
	}
	
	public Wall getConTop() {
		return concreteTop;
	}
	public Wall getMoving() {
		return platform1Moving;
	}
	
	
	
	
	public void update(){
		
	
		/////////////
		//////////////// ----------- CALL ALL UPDATES
		
		i = new ArrayIterator<Entity>(Entity.getEntityArray());
		Entity e;
		while (i.hasNext()) {
			e = i.next();
			e.updateEntity();
			if(e.getClass()==Bullet.class){
				Bullet b = (Bullet)e;
				b.update();
			}
			else if (e.getClass()==EnemyOne.class){
				EnemyOne en = (EnemyOne)e;
				en.update();
			}
			else if (e.getClass()==EnemyTwo.class){
				EnemyTwo en = (EnemyTwo)e;
				en.update();
			}
			
			
			else if (e.getClass() == WallMoving.class){
				WallMoving w = (WallMoving)e;
				w.update();
			}
			else if (e.getClass() == Wall.class){
				Wall w = (Wall)e;
				w.update();
			}
		}
		
		
		
		
		
		player1.update();
		player2.update();
		
		
		///////////////////////////////////
		///////////////////////////////////
		////////////////////////////////////
		
		
		
		///////////// spawning the enemies.
		
	
		
		if (enemyTimer >= nextEnemyTime) {
			enemyTimer = 0.0f;
			nextEnemyTime = Math.random() + 0.3;
			if (Math.random() >= 0.5) 
					nextEnemyTime = nextEnemyTime + Math.random();
			
			spawnEnemy();
				
		}
		
		else {
			enemyTimer += Gdx.graphics.getDeltaTime();
		}
		
	
		
		
	}
	
	
	
	
	
	
	public void spawnEnemy() {
		
		
		double worldChoice = Math.random();
		

		////affect type
		int enemyT = 1;
		boolean enemyFlip = false;
		SpawnPoint chosenPoint;
		
		if (worldChoice >= 0.5f) {
			///chose world 1.
			
			double pointChoice = Math.random();
			
			if (pointChoice <= 0.25) {
				chosenPoint = spawn1;
			}
			else if (pointChoice <= 0.5) {
				chosenPoint = spawn2;
			}
			else if (pointChoice <= 0.75) {
				chosenPoint = spawn3;
				enemyFlip = true;
			}
			else {
				chosenPoint = spawn4;
				enemyFlip = true;
			}
			
		}
		
		
		
		else {
			////chose world 2.
			
			enemyT = 2;
			
			double pointChoice = Math.random();
			
			if (pointChoice <= 0.25) {
				chosenPoint = spawn21;
			}
			else if (pointChoice <= 0.5) {
				chosenPoint = spawn22;
				enemyFlip = true;
			}
			else if (pointChoice <= 0.75) {
				chosenPoint = spawn23;
			}
			else {
				chosenPoint = spawn24;
				enemyFlip = true;
			}
			
		}
		
		
		////affect position
		Vector2 enemyPos = chosenPoint.getPosition();
		
		
		
	
		double randomClass = Math.random();
		
		if (randomClass >= 0.5f) {
			double randomY = Math.random() * Gdx.graphics.getHeight() - texLoad.getDefaultEnemy().getHeight();
			enemyPos = new Vector2(0, (float)randomY);
			if (enemyFlip) enemyPos = new Vector2(Gdx.graphics.getWidth() + 30, (float)randomY);
			
			Enemy.getEnemyArray().add( new EnemyTwo(texLoad.getEnemyTwo(), enemyT, enemyFlip, enemyPos, 2) );
		}
		
		else {
			Enemy.getEnemyArray().add( new EnemyOne(texLoad.getEnemyOne(), enemyT, enemyFlip, enemyPos, 1) );
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void dispose(){
		
	}


}
