package edu.lewisu.cs.kylevbye;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * 
 * 
 * @author	Kyle V Bye
 */
public class InputHandler extends InputAdapter {
	
	private PlayerInput playerInput;
	private boolean shiftHeld = false;
	private Image playerImg;
	private SpriteBatch batch;
	private OrthographicCamera cam;
	
	private Vector3 startCam, startMouse;
	
	///
	///	Getters
	///
	public PlayerInput getPlayerInput() { return playerInput; }
	public Image getPlayerImg() { return playerImg; }
	public SpriteBatch getBatch() { return batch; }
	public OrthographicCamera getCam() { return cam; }
	
	///
	///	Setters
	///
	public void setPlayerImg(Image playerImg) { this.playerImg = playerImg; }
	public void setBatch(SpriteBatch batch) { this.batch = batch; }
	public void setCam(OrthographicCamera cam) { this.cam = cam; }
	
	///
	///	Functions
	///
	@Override
	public boolean keyDown(int keyCode) {
		
		if (keyCode == Keys.SHIFT_LEFT || keyCode == Keys.SHIFT_RIGHT) shiftHeld = true;
		
		//	Movement WASD (with Image) (On held)
		if (keyCode == Keys.W) playerInput.playerChangeY = 3;
		if (keyCode == Keys.A) playerInput.playerChangeX = -3;
		if (keyCode == Keys.S) playerInput.playerChangeY = -3;
		if (keyCode == Keys.D) playerInput.playerChangeX = 3;
		
		
		 
		//	Camera Pan - ARROW KEYS (On Held)
		if (keyCode == Keys.UP) playerInput.camChangeY = 3;
		if (keyCode == Keys.LEFT) playerInput.camChangeX = -3;
		if (keyCode == Keys.DOWN) playerInput.camChangeY = -3;
		if (keyCode == Keys.RIGHT) playerInput.camChangeX = 3;
		
		//	Camera Shake (On Held)
		if (keyCode == Keys.SPACE) playerInput.increaseIntensity = true;
		
		//	Camera Speed (On Held)
		if (keyCode == Keys.Q) playerInput.decreaseSpeed = true;
		if (keyCode == Keys.E) playerInput.increaseSpeed = true;
		
		return true;
		
	}
	
	@Override 
	public boolean keyUp(int keyCode) {
		
		if (keyCode == Keys.SHIFT_LEFT || keyCode == Keys.SHIFT_RIGHT) shiftHeld = false;
		
		//	Movement
		if (keyCode == Keys.W) playerInput.playerChangeY = 0;
		if (keyCode == Keys.A) playerInput.playerChangeX = 0;
		if (keyCode == Keys.S) playerInput.playerChangeY = 0;
		if (keyCode == Keys.D) playerInput.playerChangeX = 0;
		
		//	Pan
		if (keyCode == Keys.UP) playerInput.camChangeY = 0;
		if (keyCode == Keys.LEFT) playerInput.camChangeX = 0;
		if (keyCode == Keys.DOWN) playerInput.camChangeY = 0;
		if (keyCode == Keys.RIGHT) playerInput.camChangeX = 0;
		
		//	Camera Shake (On Release)
		if (keyCode == Keys.SPACE) {
			playerInput.increaseIntensity = false;
			playerInput.shake = 1;
		}
		
		//	Camera Speed 
		if (keyCode == Keys.Q) playerInput.decreaseSpeed = false;
		if (keyCode == Keys.E) playerInput.increaseSpeed = false;
		
		//	Vertical Flag (On Press Only)
		if (keyCode == Keys.R) playerInput.hitVerticalFlag = true;
		
		return true;
		
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		startCam = new Vector3(cam.position.x, cam.position.y, 0f);
		startMouse = new Vector3(screenX, screenY, 0f);
		
		return true;
		
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		float dx, dy;
		dx = screenX;
		dy = screenY;
		
				
		dx -= startMouse.x; dy -= startMouse.y;
		
		//	Only change the camera is the mouse is within the game window.
		if ((screenX >= 0 && screenY >= 0) && (screenX <= Gdx.graphics.getWidth() && screenY <= Gdx.graphics.getHeight())) {
			
			//	Pan
			if (!shiftHeld) {
				cam.position.x = startCam.x + dx;
				cam.position.y = startCam.y - dy;
			}
			//	Rotate
			else {
				float angle = (float)Math.tan(dy/dx);
				//	Tangent fails at certain values, so don't rotate when it does.
				if (!Float.isNaN(angle)) cam.rotate(angle);
			}
			
		}
		
		update();
		
		return true;
		
	}
	
	public void update() {
		
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		
	}
	
	///
	///	Constructors
	///
	
	public InputHandler(Image playerImg, OrthographicCamera cam, SpriteBatch batch) {
		
		playerInput = new PlayerInput();
		setPlayerImg(playerImg); setCam(cam);
		setBatch(batch);
		
	}


	


	
}
