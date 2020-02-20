package com.DinoTheDino.objects.creatures;

import java.awt.Rectangle;
import java.util.LinkedList;

import com.DinoTheDino.framework.ObjectId;
import com.DinoTheDino.objects.GameObject;

public abstract class Creatures extends GameObject {
	
	protected float width = 48f, height = 48f;
	protected float gravity = 0.5f;
	protected float MAX_SPEED = 10f;
	public float SPEED = 1f;

	public float getSPEED() {
		return SPEED;
	}

	public void setSPEED(float sPEED) {
		SPEED = sPEED;
	}

	public Creatures(float x, float y, ObjectId id) {
		super(x, y, id);
	}
	
	public Rectangle getRightViewBox() {
		return new Rectangle((int) (x + (width*2)), (int) (y),
				(int) ((width * 4)), (int) (height));
	}
	public Rectangle getLeftViewBox() {
		return new Rectangle((int) (x - (width * 5)), (int) (y),
				(int) ((width * 4)), (int) (height));
	}
	
	public Rectangle getRightAttackBox() {
		return new Rectangle((int) (x + (width)), (int) (y),
				(int) ((width * 1)), (int) (height));
	}
	public Rectangle getLeftAttackBox() {
		return new Rectangle((int) (x - (width * 1)), (int) (y),
				(int) ((width * 1)), (int) (height));
	}
		
	public abstract void Collision(LinkedList<GameObject> object) ;
			
	public abstract void die();
	
	protected void hurt(int damage) {
		
		health -= damage;
		if (health <= 0) {
			active = false;
			die();
			}
	}

	public void setMAX_SPEED(float mAX_SPEED) {
		MAX_SPEED = mAX_SPEED;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public int getBulletDamage() {
		return damage;
	}

	public void setBulletDamage(int bulletDamage) {
		this.damage = bulletDamage;
	}

	public float getMAX_SPEED() {
		return MAX_SPEED;
	}

}
