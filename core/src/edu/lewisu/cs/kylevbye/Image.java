package edu.lewisu.cs.kylevbye;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public class Image implements Disposable {
		
	///
	///	Fields
	///
	private TextureRegion texture;
	private int x, y;
	private int width, height;
	private int orgX, orgY;
	private int angle;
	private float minZoom;
	private float scaleX, scaleY;
		
	///
	///	Getters
	///
	public TextureRegion getTexture() { return texture; }
	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getOrgX() { return orgX; }
	public int getOrgY() { return orgY; }
	public int getAngle() { return angle; }
	public float getMinZoom() { return minZoom; }
	public float getScaleX() { return scaleX; }
	public float getScaleY() { return scaleY; }
		
	///
	///	Setters
	///
	public void setTexture(TextureRegion texture) { this.texture = texture; }
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y;}
	public void setWidth(int width) { this.width = width; }
	public void setHeight(int height) { this.height = height; }
	public void setOrgX(int orgX) { this.orgX = orgX; }
	public void setOrgY(int orgY) { this.orgY = orgY; }
	public void setAngle(int angle) { this.angle = angle;}
	public void setMinZoom(float minZoom) { this.minZoom = minZoom; }
	public void setScaleX(float scaleX) { this.scaleX = scaleX; }
	public void setScaleY(float scaleY) { this.scaleY = scaleY; }
		
	///
	///	Constructor
	///
	public Image() { this(null,0,0,0,0,0,0,0,0,1,1); }
		
	public Image(
			TextureRegion texture, int x, int y, int width, int height, 
			int orgX, int orgY, int angle, float minZoom, float scaleX, float scaleY
			) {
		setTexture(texture);
		setX(x); setY(y); setOrgX(orgX); setOrgY(orgY);
		setWidth(width); setHeight(height);
		setAngle(angle);
		setMinZoom(minZoom);
		setScaleX(scaleX); setScaleY(scaleY);
	}
	
	///
	///	Deconstructors
	///
	
	public void dispose() {
		texture = null;
	}
		
}
