package com.me.GameJam.Load;

import com.badlogic.gdx.graphics.Texture;

public class Load {
	
	public static Load loader = new Load();
	

	Texture Player1;
	Texture world;
	Texture platform1;
	Texture movingPlatform;
	Texture Player2;
	Texture defaultBullet;
	Texture defaultEnemy;
	Texture EnemyOne;
	Texture EnemyTwo;
	
	Texture concreteBottom;
	Texture concreteTop;
	
	public static Load getLoader() {
		return loader;
	}
	
	
	
	public Load() {
		
		world = new Texture("data/backgroundOtherWorld.png");
		platform1 = new Texture("data/platformMiddle.png");
		movingPlatform = new Texture("data/movingPlatform.png");
		Player1 = new Texture("data/Player/player.png");
		defaultBullet = new Texture("data/defaultBullet.png");
		defaultEnemy = new Texture("data/enemy.png");
		EnemyOne = new Texture("data/otherEnemy2.png");
		EnemyTwo = new Texture("data/enemyFloat1.png");
		
		concreteTop = new Texture("data/concretePlatTop.png");
		concreteBottom = new Texture("data/concretePlatBottom.png");
		
		//Animator(6,5,Player2);

	}
	
	
	public Texture getWorld() {
		return world;
	}

	public void setWorld(Texture world) {
		this.world = world;
	}

	public Texture getPlatBottom() {
		return concreteBottom;

	}
	public Texture getPlatTop() {
		return concreteTop;
	}
	
	public Texture getPlatform1() {
		return platform1;
	}
	public Texture getPlatformMoving() {
		return movingPlatform;
	}

	public Texture getEnemyOne() {
		return EnemyOne;
	}

	public Texture getEnemyTwo() {
		return EnemyTwo;
	}

	public void setPlatform1(Texture platform1) {
		this.platform1 = platform1;
	}

	public void dispose(){
		Player1.dispose();
		Player2.dispose();
		world.dispose();
		platform1.dispose();
		defaultBullet.dispose();
		defaultEnemy.dispose();
		EnemyOne.dispose();
		EnemyTwo.dispose();
	}
	

	public Texture getPlayer1() {
		return Player1;
	}

	public Texture getPlayer2() {
		return Player2;
	}

	public void setPlayer2(Texture player2) {
		Player2 = player2;
	}

	public void setPlayer1(Texture person) {
		this.Player1 = person;
	}

	public Texture getDefaultBullet() {
		
		return defaultBullet;
	}
	
	public Texture getDefaultEnemy() {
		return defaultEnemy;
	}
	

	
	
	

	

}
