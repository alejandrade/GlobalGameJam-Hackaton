package com.me.GameJam.View;

import com.me.GameJam.Model.Person;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class InputHandler implements InputProcessor {

	World world;
	
	public boolean touchJ;
	public boolean touchA;
	public boolean touchS;
	public boolean touchD;
	public boolean touchK;
	public boolean touchW;
	
	public boolean touchOne;
	public boolean touchDown;
	public boolean touchLeft;
	public boolean touchRight;
	public boolean touchTwo;
	public boolean touchUp;
	
	public boolean test1;
	
	static public InputHandler inputHandler;

	public InputHandler(World world) {
		this.world = world;
		inputHandler = this;
	}
	
	
	//////////// GET THE MAIN INPUT HANDLER
	
	public static InputHandler getInput() {
		return inputHandler;
	}
	

	@Override
	public boolean keyDown(int keycode) {
		
		switch (keycode) {

		case Keys.J:
			touchJ = true;
			break;
		case Keys.K:
			touchK = true;
			break;
		case Keys.A:
			touchA = true;
			break;
		case Keys.D:
			touchD = true;
			break;
		case Keys.W:
			touchW = true;
			break;
		case Keys.LEFT:
			touchLeft = true;
			break;
		case Keys.NUMPAD_1:
			touchOne = true;
			break;
		case Keys.NUMPAD_3:
			touchTwo = true;
			break;
		case Keys.RIGHT:
			touchRight = true;
			break;
		case Keys.UP:
			touchUp = true;
			break;
			
			
		default:
			break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		switch (keycode) {
		case Keys.J:
			touchJ = false;
			break;
		case Keys.K:
			touchK = false;
			break;
		case Keys.A:
			touchA = false;
			break;
		case Keys.D:
			touchD = false;
			break;
		case Keys.W:
			touchW = false;
			break;
		case Keys.LEFT:
			touchLeft = false;
			break;
		case Keys.NUMPAD_1:
			touchOne = false;
			break;
		case Keys.NUMPAD_3:
			touchTwo = false;
			break;
		case Keys.RIGHT:
			touchRight = false;
			break;
		case Keys.UP:
			touchUp = false;
			break;
		
		default:
			break;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		test1 = false;
		System.out.println("x position is: " + screenX);
		System.out.println("y position is: " + screenY);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		test1= true;
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
