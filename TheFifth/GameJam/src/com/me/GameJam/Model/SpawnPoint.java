package com.me.GameJam.Model;

import com.badlogic.gdx.math.Vector2;

public class SpawnPoint {

	Vector2 pos;
	
	int type;
	
	public SpawnPoint(Vector2 position, int type) {
		
		pos = position;
		this.type = type;
	}
	
	public Vector2 getPosition() {
		return pos;
	}
	
	public int getType() {
		return type;
	}
	
}
