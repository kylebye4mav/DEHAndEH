package edu.lewisu.cs.kylevbye;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DEHAndEH extends ApplicationAdapter {
	SpriteBatch batch;
	OrthographicCamera cam;
	Image playerImg;
	InputHandler inputHandler;
	CameraShake shake;
	BitmapFont intensityText, speedText, directionText;
	int intensity, speed;
	boolean isVertical;
	int WIDTH, HEIGHT;
	
	@Override
	public void create () {
		
		//	480p
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		
		batch = new SpriteBatch();
		cam = new OrthographicCamera(WIDTH, HEIGHT);
		Texture playerTex = new Texture("badlogic.jpg");
		playerImg = new Image(
				new TextureRegion(playerTex), 
				0, 0, playerTex.getWidth(), playerTex.getHeight(), 0, 0, 0, 0f, 1f, 1f
				);
		
		cam.translate(WIDTH/2, HEIGHT/2);
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		
		//	Init Input
		inputHandler = new InputHandler(playerImg, cam, batch);
		Gdx.input.setInputProcessor(inputHandler);
		
		//	Init Shake
		
		intensity = 0;
		speed = 2;
		isVertical = false;
		
		
		shake = new CameraShake(cam, 100, batch, null, intensity, speed, isVertical);
		
		//	Init intensityText
		intensityText = new BitmapFont();
		speedText = new BitmapFont();
		directionText = new BitmapFont();
		
	}

	@Override
	public void render () {
		
		handleEvents();
		shake.perform();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		intensityText.draw(batch, "Intensity: " + intensity, WIDTH/2, HEIGHT/2);
		speedText.draw(batch, "Speed: " + speed, WIDTH/2, HEIGHT/2 + 20);
		directionText.draw(batch, "IsVerticalShake? --> : " + shake.isVertical(), WIDTH/2, HEIGHT/2 + 40);
		batch.draw(
				playerImg.getTexture(), playerImg.getX(), playerImg.getY(), playerImg.getOrgX(), playerImg.getOrgY(), 
				playerImg.getWidth(), playerImg.getHeight(), playerImg.getScaleX(), playerImg.getScaleY(), playerImg.getAngle()
				);
		batch.end();
		inputHandler.update();
		
	}
	
	private void handleEvents() {
		
		PlayerInput playerInput = inputHandler.getPlayerInput();
		playerImg.setX(playerImg.getX() + playerInput.playerChangeX);
		playerImg.setY(playerImg.getY() + playerInput.playerChangeY);
		cam.translate(playerInput.camChangeX,playerInput.camChangeY);
		
		if (playerInput.increaseIntensity) intensity += 1;
		if (!(playerInput.increaseSpeed && playerInput.decreaseSpeed)) {
			if (playerInput.increaseSpeed) if (speed < 100) speed += 1;
			if (playerInput.decreaseSpeed) if (speed > 0) speed -= 1;
			
			shake.setSpeed(speed);
		}
		
		
		if (playerInput.shake != 0) {
			playerInput.shake = 0;
			shake.setIntensity(intensity);
			intensity = 0;
			shake.begin();
		}
		
		if (playerInput.hitVerticalFlag) {
			playerInput.hitVerticalFlag = false;
			if (shake.isVertical()) shake.setVertical(false);
			else shake.setVertical(true);
		}
		
	}
	
	@Override
	public void dispose () {
		
		batch.dispose();
		playerImg.dispose();
		
	}
	
}
