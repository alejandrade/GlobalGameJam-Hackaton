package com.me.GameJam.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class WallMoving extends Wall {

	int startingX;
	
	boolean movingLeft = false;
	
	public WallMoving (Texture tex, Vector2 position, int type, boolean flipped) {
		super(tex, position, type, flipped);
		
		startingX = (int)position.x;
		setVelocity(new Vector2(5,0));
	}
	
	
	
	
	public void update() {
		
		
		float winWidth = Gdx.graphics.getWidth();
		
		if (!movingLeft) {
			
			if (getX() >= winWidth - getWidth()) {
				movingLeft = true;
			}
			
			else {
				setPosition(getX() + velocity.x, getY());
			}
			
		}
		
		
		else {
			
			if (getX() <= 0) {
				movingLeft = false;
			}
			
			else {
				setPosition(getX() - velocity.x, getY());
			}
			
		}
		
	}
	
}
