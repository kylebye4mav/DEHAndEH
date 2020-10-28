package edu.lewisu.cs.kylevbye;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class CameraShake {
	private OrthographicCamera cam;
    private int duration, progress;
    private ShapeRenderer renderer;
    private SpriteBatch batch;
    
    private int intensity;
    private int speed;
    private boolean isVertical;
	
	///
	///	Getters
	///
    public OrthographicCamera getCam() { return cam; }
	public ShapeRenderer getRenderer() { return renderer; }
	public int getProgress() { return progress; }
	public int getDuration() { return duration; }
	public SpriteBatch getBatch() { return batch; }
	public int getSpeed() { return speed; }
	public int getIntensity() { return intensity; }
	public boolean isVertical() { return isVertical; }
	
	///
	///	Setters
	///
	public void setCam(OrthographicCamera cam) { this.cam = cam; }
	public void setRenderer(ShapeRenderer renderer) { this.renderer = renderer; }
	public void setProgress(int progress) { this.progress = progress; }
	public void setDuration(int duration) { this.duration = duration; }
	public void setBatch(SpriteBatch batch) { this.batch = batch; }
	public void setSpeed(int speed) { 
		if (speed >= 0) this.speed = speed; 
		else this.speed = 0;
	}
	public void setIntensity(int intensity) { 
		if (intensity >= 0 )this.intensity = intensity;
		else this.intensity = 0;
	}
	public void setVertical(boolean verticalFlag) { this.isVertical = verticalFlag; }
	
	///
	///	Functions
	///
	
	public boolean isActive() {
		
		return (progress<duration) && speed > 0;
		
	}
	
	public void begin() {
		
		setProgress(0);
		cam.translate(intensity, 0);
		update();
		
	}
	
	public void perform() {
		
		if (isActive()) {
			
			if (progress % speed == 0) {
				intensity *= -1;
				if (!isVertical) cam.translate(2*intensity, 0f);
				else cam.translate(0f, 2*intensity);
				intensity -= intensity/7;
			}
			
			++progress;
			
			if (!isActive()) cam.translate(-intensity, 0f);
			
			update();
			
		}
		
	}
	
	public void update() {
		
		cam.update();
		
		//	Null Check, then update accordingly.
		if (renderer != null) renderer.setProjectionMatrix(cam.combined);
		if (batch != null) batch.setProjectionMatrix(cam.combined);
		
	}

	
	///
	///	Constructor
	///
	public CameraShake(OrthographicCamera cam, int duration, SpriteBatch batch,
		    ShapeRenderer renderer, int intensity, int speed, boolean verticalFlag) {
		
		setCam(cam);
		setDuration(duration);
		setBatch(batch);
		setRenderer(renderer);
		setIntensity(intensity);
		setSpeed(speed);
		setVertical(verticalFlag);
		
	}
	
}
