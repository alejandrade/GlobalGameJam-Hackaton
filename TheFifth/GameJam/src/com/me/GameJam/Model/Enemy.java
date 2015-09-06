package com.me.GameJam.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Enemy extends Entity{

	
	public static Array<Entity> enemyArray = new Array<Entity>();;
	
	float health;
	
	
	public static Array<Entity> getEnemyArray() {
		return enemyArray;
	}

	public Enemy(Texture tex, int t, boolean flipped, Vector2 pos, float h){
			super(tex, t, flipped);
		
			setOrigin(pos.x, pos.y);
			
			health = h;
			
	}
	
	
	
	
	public void hitPlayer() {
		
	}
	
	
	public void hitByBullet(float p) {
		
		health -= p;
		
	}
	
	
	@Override
	public void update() {
		if (health <= 0) {
			
			enemyArray.removeValue(this, true);
			
			die();
			
		}
		
		
	}
	
	
	

}
