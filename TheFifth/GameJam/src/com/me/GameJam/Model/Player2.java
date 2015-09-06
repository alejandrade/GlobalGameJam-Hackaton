package com.me.GameJam.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.me.GameJam.Load.GameAudio;

public class Player2 extends Person{
	
	
	public Player2(Texture tex, int type, boolean flipped){
		
		super(tex, type, flipped);
	}
	
	@Override
	public void checkShoot(){
		
		
		// default player1
		// default player1
				if (pad.touchUp) {
					
					bulletY = 20;
					if (!pad.touchLeft && !pad.touchRight) { ////// aiming up and not moving left or right.
						isLookingUp = true;
						isShootingDiagonal = false;
						isMovingStraight = false;
						bulletX = 0;
					} else { ////aiming up and moving left or right.
						isShootingDiagonal = true;
						isMovingStraight = false;
						isLookingUp = false;
						bulletX = 20;
					}
				} else { 
					
					isLookingUp = false;
					isShootingDiagonal = false;
					
					if (pad.touchLeft || pad.touchRight)
						isMovingStraight = true;
					else isMovingStraight = false;
					
					bulletY = 0;
					bulletX = 20;
				}

		if (pad.touchOne) {
			if (!isHoldingShoot)
				shoot(flipped, new Vector2(bulletX, bulletY));
				if (! (isShootingDiagonal || isLookingUp) ) isShootingNormal = true;
				else isShootingNormal = false;
		} else {
			isHoldingShoot = false;
			isShootingNormal = false;
		}
	}
	
	
	@Override
	
	public void checkJump() { ///// put jumping conditions for each subclass of person
		if(pad.test1){
			alive=false;
		}
		
		
		if (pad.touchTwo && !isFalling) {
			isHoldingJumpButton = true;
			if (!isJumping) {
				jump();
			}
		}
		else {
			isHoldingJump = false;
			isHoldingJumpButton = false;
		}
	}
	
	@Override
	public void movement(){
		
		setMoving(pad.touchLeft || pad.touchRight);
		
		
		if (pad.touchLeft) {
			
			flipped = true;
			setPosition(this.getX() - getVelocity().x, this.getY());
		} else if (pad.touchRight) {
			flipped = false;
			setPosition(this.getX() + getVelocity().x, this.getY());
		}
		

		if (getX() <= 0)
			setPosition(0, getY());
		if (getX() >= Gdx.graphics.getWidth() - getWidth())
			setPosition(Gdx.graphics.getWidth() - getWidth(), getY());
		
	}
}
