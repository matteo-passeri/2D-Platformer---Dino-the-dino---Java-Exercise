package com.DinoTheDino.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.DinoTheDino.framework.ObjectId;

public abstract class GameObject 
{
	protected float x, y;
	protected float futureX, futureY;
	protected ObjectId id;
	protected float velX = 0, velY = 0;
	protected boolean falling = true;
	protected boolean jumping = false;
	protected boolean facingRight = true;
	protected int damage = 1;
	protected boolean active = true;
	protected int health = 1;

	
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
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

	public GameObject(float x, float y, ObjectId id)
	{
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	public abstract Rectangle getBoundsBottom();
	public abstract Rectangle getBoundsTop();
	public abstract Rectangle getBoundsLeft();
	public abstract Rectangle getBoundsRight();
	


	public boolean isFacingRight() {
		return facingRight;
	}

	public void setFacingRight(boolean facingRight) {
		this.facingRight = facingRight;
	}
	
	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	
	public float getX()
	{
		return x;
	}
	public float getY()
	{
		return y;
	}
	public void setX(float x)
	{
		this.x = x;
	}
	public void setY(float y)
	{
		this.y = y;
	}
	
	public float getVelX()
	{
		return velX;
	}
	public float getVelY()
	{
		return velY;
	}
	public void setVelX(float velX)
	{
		this.velX = velX;
	}
	public void setVelY(float velY)
	{
		this.velY = velY;
	}
	
	public ObjectId getId()
	{
		return id;
	}
	 
	
	
}
