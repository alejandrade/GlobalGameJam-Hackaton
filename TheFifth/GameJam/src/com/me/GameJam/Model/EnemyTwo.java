package com.me.GameJam.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class EnemyTwo extends Enemy {

	float originalY;
	
	public EnemyTwo(Texture tex, int type, boolean flipped, Vector2 pos, int h) {
		super(tex, type, !flipped, pos, h);
		// TODO Auto-generated constructor stub
		
		setPosition(pos.x, pos.y);
		originalY = this.getY();
		
		setVelocity(new Vector2(3, 0));
		if (flipped) setVelocity(new Vector2(-3, 0));
		
	}
	
	
	
	@Override
	public void update () {
		super.update();
		
		float newX = getX() + velocity.x;
		double newY = ( Math.sin(newX / 50) * 50 ) + originalY;
		
		
		setPosition(newX, (float)newY);
		
		//System.out.println(newX);
		
	}

}
