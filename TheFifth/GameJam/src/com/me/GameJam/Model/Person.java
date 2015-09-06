package com.me.GameJam.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.me.GameJam.Load.GameAudio;
import com.me.GameJam.Load.Load;
import com.me.GameJam.View.InputHandler;

public class Person extends Entity {

	InputHandler pad = InputHandler.getInput();
	
	Load loader = Load.getLoader();

	// //jumping variables
	boolean isJumping = false;
	boolean isFalling = false;
	boolean isHoldingJump = false;
	boolean isHoldingJumpButton = false;
	float maxHoldTime = 0.3f;
	float jumpTime = 0.0f;

	float jumpVelocity = 7;

	float jumpGravity = 0.6f;

	// //walking variables

	boolean isWalking = true;
	Wall currentFloor = null;
	float floorHeight = 50;

	// //shooting variables.
	boolean isHoldingShoot = false;
	boolean isMovingStraight = false;
	boolean isLookingUp = false;
	boolean isShootingNormal = false;
	boolean isShootingDiagonal = false;
	
	
	public Vector2 bulletPosition = new Vector2(getX(),getY());



	float bulletY = 0f, bulletX = 20f;
	
	public boolean isMovingStraight() {
		return isMovingStraight;
	}
	public boolean isLookingUp() {
		return isLookingUp;
	}
	public boolean isShootingNormal() {
		return isShootingNormal;
	}
	public boolean isShootingDiagonal() {
		return isShootingDiagonal;
	}
	public boolean isJumping() {
		return isJumping;
	}


	public Person(Texture tex, int type, boolean flipped) {
		super(tex, type, flipped);
		this.setPosition(0, 0);
		setVelocity(new Vector2(5,0));
		
	}

	public void checkJump() { // /// put jumping conditions for each subclass of
								// person

		// /this is default for player 1.

		if (pad.touchK && !isFalling) {
			isHoldingJumpButton = true;
			if (!isJumping) {
				jump();
			}
		} else {
			isHoldingJump = false;
			isHoldingJumpButton = false;
		}
	}

	public void jump() {
		isWalking = false;
		isJumping = true;
		isHoldingJump = true;
		setVelocity(new Vector2(getVelocity().x, jumpVelocity));
	}

	public void endJump() {
		isWalking = true;
		isJumping = false;
		isHoldingJump = false;
		isFalling = false;
		jumpTime = 0.0f;
		if (currentFloor != null)
			setPosition(getX(), currentFloor.floorHeight);
		else
			setPosition(getX(), floorHeight);
		
		setVelocity(new Vector2(getVelocity().x, 0));
	}

	public void landOnFloor(Wall w) {
		currentFloor = w;
		endJump();
	}

	public void fallOffFloor() {
		currentFloor = null;
		floorHeight = 50;
		isJumping = true;
		isFalling = true;
	}

	// /////////// SHOOTING
	public void shoot(boolean directionNegative, Vector2 velocity) {
		isHoldingShoot = true;
		GameAudio.shoot();
		
		int scaleX = 1;
		if (getFlipped()) scaleX = -1;
		int bWidth = (int)getWidth();
		if (getFlipped()) bWidth = 0;
		Bullet.getBulletArray().add( new Bullet(loader.getDefaultBullet(), getType(), false, new Vector2(getX()+ (bWidth),getY()+getHeight()-20), directionNegative,
						velocity, 1) );
	}

	public void checkShoot() { // //override it

		// default player1
		if (pad.touchW) {
			
			bulletY = 20;
			if (!pad.touchA && !pad.touchD) { ////// aiming up and not moving left or right.
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
			
			if (pad.touchA || pad.touchD)
				isMovingStraight = true;
			else isMovingStraight = false;
			
			bulletY = 0;
			bulletX = 20;
		}

		if (pad.touchJ) {
			if (!isHoldingShoot)
				shoot(flipped, new Vector2(bulletX, bulletY));
				if (! (isShootingDiagonal || isLookingUp) ) isShootingNormal = true;
				else isShootingNormal = false;
		} else {
			isHoldingShoot = false;
			isShootingNormal = false;
		}

	}

	public void hitEnemy() {

	}

	@Override
	public void update() {
		
		if (currentFloor != null) {
			if (currentFloor.getClass() == WallMoving.class) {
				
				setPosition(getX() + currentFloor.getVelocity().x, getY());
				
			}
		}

		checkJump();
		checkShoot();

		if (isJumping) {
			jumpTime = jumpTime + Gdx.graphics.getDeltaTime();
			if (isHoldingJump) {
				if (jumpTime >= maxHoldTime) {
					isHoldingJump = false;
				}
			} else { // /not holding jump, slow down jump velocity with gravity.
				setVelocity(new Vector2(getVelocity().x, getVelocity().y
						- jumpGravity));
			}

			// //set position

			setPosition(getX(), getY() + velocity.y);
		}

		if (isWalking) {
			if (currentFloor != null) {
				float playerLeft = getX();
				float playerRight = playerLeft + this.getWidth();

				if (playerRight < currentFloor.leftWall
						|| playerLeft > currentFloor.rightWall) {
					fallOffFloor();
				}
			}

		}

		movement();

		if (this.getY() < floorHeight) {

			currentFloor = null;
			endJump();

		}

	}

	public void movement() {
		// Default movement player1

		setMoving(pad.touchA || pad.touchD);
		
		if (pad.touchA) {
			flipped = true;
			setPosition(getX() - getVelocity().x, getY());
		} else if (pad.touchD) {
			flipped = false;
			setPosition(getX() + getVelocity().x, getY());
		}
		
		
		if (getX() <= 0)
			setPosition(0, getY());
		if (getX() >= Gdx.graphics.getWidth() - getWidth())
			setPosition(Gdx.graphics.getWidth() - getWidth(), getY());
		
		
		
	}
	
	
	
	
	
	

	@Override
	public void collidedWithTopOf(Entity e) {
		if (e.getClass() == Wall.class) {
			if (velocity.y <= 0) {
				Wall w = (Wall) e;
				landOnFloor(w);
			
			}
		}
		
		else if (e.getClass() == WallMoving.class) {
			if (velocity.y <= 0) {
				WallMoving w = (WallMoving) e;
				landOnFloor(w);
			}
		}

	}

	public void didCollideWith(Entity e) {
		super.didCollideWith(e);

		if (e.getClass() == Enemy.class) {
			hitEnemy();
			Enemy en = (Enemy) e;
			en.hitPlayer();
		}
	}
	
	
	public Vector2 getBulletPosition() {
		return bulletPosition;
	}
	public void setBulletPosition(Vector2 bulletPosition) {
		this.bulletPosition = bulletPosition;
	}

}
