package com.me.GameJam.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


public class Entity extends Sprite {
	
	protected int type = 1;
	protected Vector2 velocity;
	protected boolean flipped = false;
	protected boolean alive = true;
	protected boolean moving = false;
	
	protected TextureRegion currentFrame = null;
	ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	@Override
	public float getWidth() {
		if (currentFrame != null) return currentFrame.getRegionWidth();
		return super.getWidth();
	}
	@Override
	public float getHeight() {
		if (currentFrame != null) return currentFrame.getRegionHeight();
		return super.getHeight();
	}
	
	public void setCurrentFrame(TextureRegion frame) {
		currentFrame = frame;
	}
	
	
	public boolean isMoving() {
		return moving;
	}


	public void setMoving(boolean moving) {
		this.moving = moving;
	}



	protected float stateTime = 0.0f;

	private static Array<Entity> entityArray = new Array<Entity>();
	
	public static Array<Entity> getEntityArray() {
		return entityArray;
	}
	
	
	public Entity(Texture tex, int type, boolean flipped) {
		super(tex);
		this.type = type;
		this.flipped = flipped;
		
		entityArray.add(this);
	}
	
	
	
	
	public float getStateTime() {
		return stateTime;
	}
	
	public boolean getFlipped() {
		return flipped;
	}
	public void setFlipped(boolean flip) {
		flipped = flip;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}


	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}
	
	
	public void die() {
		entityArray.removeValue(this, true);
	}
	
	
	public void update() {
		
		

		
		
		this.setBounds(getX(), getY(), getWidth(), getHeight());
		
		
		
	}
	@Override
	public void draw (Batch batch) {
		super.draw(batch);
		
		

			
		
	}
	
	public Rectangle getBoundingRectangle() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}
	
	public void updateEntity() {
		stateTime += Gdx.graphics.getDeltaTime();
		if (alive) {
			
			
			for (Entity e: entityArray) {
				if (! (e.equals(this))) {
					
					boolean isPlayer = (getClass() == Player1.class || getClass() == Player2.class);
					boolean otherIsEnemy = (e.getClass() == EnemyOne.class || e.getClass() == EnemyTwo.class);
					boolean typesMatch = e.getType() == getType();
					
					boolean canCollide = typesMatch || (isPlayer && otherIsEnemy);
					
					if (canCollide) {
					
						if (this.getBoundingRectangle().overlaps(e.getBoundingRectangle())) {
							
							
							
							didCollideWith(e);
						
							
						}
						
					}
				}
				
			}
		
		}

		
	}
	
	/////////// COLLISION STUFF
	
	public void didCollideWith(Entity e) {
		
		Rectangle collisionRect = rectIntersection(this.getBoundingRectangle(), e.getBoundingRectangle());
		
		if (collisionRect.getWidth() > collisionRect.getHeight()) { /////WIDTH IS GREATER COLLIDED WITH TOP OR BOTTOM
			
			if (this.getY() < e.getY()) { ////position less, collided with bottom.
				collidedWithBottomOf(e);
			}
			
			else { ////position is greater, collided with top.
				collidedWithTopOf(e);
			}
			
		}
		
		else {  /////////////////////// HEIGHT IS GREATER, COLLIDED WITH LEFT OR RIGHT
		
			if (this.getX() < e.getX()) {  ////you're position is less, so you collide with the left of it.
				collidedWithLeftOf(e);
			}
			
			else { ///your position is greater you collide with the right.
				collidedWithRightOf(e);
			}
			
		
		}
		
	}
	
	public void collidedWithTopOf(Entity e) {
		
	}
	public void collidedWithBottomOf(Entity e) {
		
	}
	public void collidedWithLeftOf(Entity e) {
		
	}
	public void collidedWithRightOf(Entity e) {
		
	}
	
	
	
	private Rectangle rectIntersection(Rectangle r1, Rectangle r2) {
		/* If both of them are empty we can return r2 as an empty rect,
	     so this covers all cases: */


		Rectangle rect = new Rectangle();
		  
		
		if (r1.getHeight() == 0 && r1.getWidth() == 0)
	    return r2;
		else if (r2.getHeight() == 0 && r2.getWidth() == 0)
	    return r1;

		if (r1.getPosition(new Vector2()).x + r1.getSize(new Vector2()).x <= r2.getPosition(new Vector2()).x ||
			r2.getPosition(new Vector2()).x + r2.getSize(new Vector2()).x <= r1.getPosition(new Vector2()).x ||
			r1.getPosition(new Vector2()).y + r1.getSize(new Vector2()).y <= r2.getPosition(new Vector2()).y ||
	        r2.getPosition(new Vector2()).y + r2.getSize(new Vector2()).y <= r1.getPosition(new Vector2()).y) 
	    return null;
	  
	  
		rect.setX( (r1.getPosition(new Vector2()).x > r2.getPosition(new Vector2()).x ? r1.getPosition(new Vector2()).x : r2.getPosition(new Vector2()).x)  );
		rect.setY( (r1.getPosition(new Vector2()).y > r2.getPosition(new Vector2()).y ? r1.getPosition(new Vector2()).y : r2.getPosition(new Vector2()).y)  );

		if (r1.getPosition(new Vector2()).x + r1.getSize(new Vector2()).x < r2.getPosition(new Vector2()).x + r2.getSize(new Vector2()).x)
			rect.setWidth( r1.getPosition(new Vector2()).x + r1.getSize(new Vector2()).x - rect.getPosition(new Vector2()).x  );
		else
			rect.setWidth( r2.getPosition(new Vector2()).x + r2.getSize(new Vector2()).x - rect.getPosition(new Vector2()).x  );

		if (r1.getPosition(new Vector2()).y + r1.getSize(new Vector2()).y < r2.getPosition(new Vector2()).y + r2.getSize(new Vector2()).y)
			rect.setHeight( r1.getPosition(new Vector2()).y + r1.getSize(new Vector2()).y - rect.getPosition(new Vector2()).y  );
		else
			rect.setHeight( r2.getPosition(new Vector2()).y + r2.getSize(new Vector2()).y - rect.getPosition(new Vector2()).y  );

		return rect;
}
	
	
}
