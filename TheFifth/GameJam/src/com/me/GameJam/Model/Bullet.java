package com.me.GameJam.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Bullet extends Entity {
	
	public static Array<Entity> _bullets = new Array<Entity>();
	
	public float power;
	
	public static Array<Entity> getBulletArray() {
		return _bullets;
	}
	
	public Bullet(Texture tex, int type, boolean flipped, Vector2 pos, boolean directionNegative, Vector2 vel, float p) {

		super(tex, type, flipped);
		
		float xVelocity = vel.x;
		if (directionNegative) xVelocity *= -1;
		
		velocity = new Vector2 (xVelocity,vel.y);
		setPosition(pos.x, pos.y);
		
		power = p;
		
	}
	
	public void bulletDie() {
		_bullets.removeValue(this, true);
		this.die();
	}
	

	@Override
	public void update() {
		
		this.setPosition(getX()+velocity.x, getY()+velocity.y);
		
		if (getX() <= 0 || getX() + getWidth() >= Gdx.graphics.getWidth()) {
			
			bulletDie();
		}
		
	}
	
	
	
	@Override
	public void didCollideWith(Entity e) {
		
			if (e.getClass() == EnemyTwo.class) {
				
				EnemyTwo en = (EnemyTwo)e;
				
				if (en.getType() == getType()) { /////enemy type designated for player 1.
					System.out.println("hitby bullet");
					en.hitByBullet(power);
					
					bulletDie();
					
				}
				
			}
			else if (e.getClass() == EnemyOne.class) {
				
				EnemyOne en = (EnemyOne)e;
				
				if (en.getType() == getType()) { /////enemy type designated for player 1.
					System.out.println("hitby bullet");
					en.hitByBullet(power);
					
					bulletDie();
					
				}
				
			}
		
	}

}
