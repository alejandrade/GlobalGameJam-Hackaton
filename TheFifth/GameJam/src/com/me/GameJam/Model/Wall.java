package com.me.GameJam.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Wall extends Entity {

	public static Array<Entity> _walls = new Array<Entity>();

	public float floorHeight = 0;
	public float leftWall = 0;
	public float rightWall = 0;

	public static Array<Entity> getWallArray() {
		return _walls;
	}

	public Wall(Texture tex, Vector2 position, int type, boolean flipped) {
		super(tex, type, flipped);
		
		setPosition(position.x, position.y);

		floorHeight = this.getY() + this.getHeight();
		leftWall = this.getX();
		rightWall = leftWall + this.getWidth();
		
		_walls.add(this);
	}

	public void update() {
		
		floorHeight = this.getY() + this.getHeight() / 2;
		leftWall = this.getX();
		rightWall = leftWall + this.getWidth();
		

	}

	public float getFloorHeight() {
		return floorHeight;
	}
	
	

}
