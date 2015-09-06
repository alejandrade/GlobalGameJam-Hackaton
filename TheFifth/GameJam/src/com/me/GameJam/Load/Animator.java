package com.me.GameJam.Load;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.GameJam.Model.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Animator {

	// ... omitted ... //

	public  float RUNNING_FRAME_DURATION = 0.09f;

	/** Textures **/
	private TextureRegion playerLeft;
	private TextureRegion playerRight;
	private TextureRegion playerFrame;
	
	private int type;

	/** Animations **/
	private Animation walkLeftAnimation;
	private Animation walkRightAnimation;

	// ... omitted ... //

	public Animator(String atlasPath, String Region, int start, int end, float frame) {
		loadTextures(atlasPath, Region, start, end + 1, frame);
		
		
	}


	public void loadTextures(String atlasPath, String Region, int start, int end, float frame) {

		int size = end - start;

		System.out.println(start + ", " + end);
		System.out.println("size of array :" + size);

		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(atlasPath));
		playerLeft = atlas.findRegion(Region + "-" + start);
		playerRight = new TextureRegion(playerLeft);
		playerRight.flip(true, false);
		TextureRegion[] walkLeftFrames = new TextureRegion[size];
		for (int i = 0; i < size; i++) {
			walkLeftFrames[i] = atlas.findRegion(Region + "-" + (start + i));
		}
		walkLeftAnimation = new Animation(frame,
				walkLeftFrames);

		TextureRegion[] walkRightFrames = new TextureRegion[size];

		for (int i = 0; i < size; i++) {
			System.out.println(i);
			walkRightFrames[i] = new TextureRegion(walkLeftFrames[i]);
			walkRightFrames[i].flip(true, false);
		}
		walkRightAnimation = new Animation(frame,
				walkRightFrames);
	}

	public void render(Entity entity, SpriteBatch batch, Boolean facin, int type) {
		
		this.type = type;
		
		Entity moving = entity;
		
		playerFrame = entity.getFlipped() ? playerLeft : playerRight;
		

		entity.setCurrentFrame(playerFrame);
		
		
		//if(entity.isMoving()) {
		playerFrame = entity.getFlipped() ? walkLeftAnimation.getKeyFrame(entity.getStateTime(), true) : walkRightAnimation.getKeyFrame(entity.getStateTime(), true);
		//}
		
		//batch.draw(playerFrame, entity.getX(), entity.getY(), playerFrame.getRegionWidth(), playerFrame.getRegionHeight());
		Color c = batch.getColor();

		if (type == 2) {
			batch.setColor(Color.RED);
		}
		
		batch.draw(playerFrame, entity.getX(), entity.getY(), playerFrame.getRegionWidth(), playerFrame.getRegionHeight());
		
		batch.setColor(new Color(c));
	}
}
