package com.me.GameJam.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class EnemyOne extends Enemy {

	public Wall currentFloor;
	public float floorHeight;
	
	public EnemyOne(Texture tex, int type, boolean flipped, Vector2 pos, int h) {
		super(tex, type, !flipped, pos, h);
		// TODO Auto-generated constructor stub
		
		setPosition(pos.x, pos.y);
		
		health = h;
		
		setVelocity(new Vector2(2, -5));
		if (flipped) setVelocity(new Vector2(-2, -5));
	
	}
	
	
	public void landOnGround(Wall ground) {
		currentFloor = ground;
		floorHeight = ground.getFloorHeight();
	}
	
	public void update() {
		super.update();
		setPosition(getX() + velocity.x, getY() + velocity.y);
		
		if (getY() <= floorHeight) {
			setPosition(getX(), floorHeight);
		}
		
		if (currentFloor != null) {
			
			float leftWall = currentFloor.getX();
			float rightWall = leftWall + currentFloor.getWidth();
			
			if ((getX() < leftWall + getWidth()) || ( getX() > rightWall )) {
				floorHeight = 0;
			}
		}
		
	}
	
	
	public void didCollideWith(Entity e) {
		
		super.didCollideWith(e);
		
		if (e.getClass() == Wall.class) {
			
			Wall w = (Wall)e;
			
			if (!w.equals(currentFloor)) {
				
				landOnGround(w);
				
			}
			
		}
		
	}
	
	

}
