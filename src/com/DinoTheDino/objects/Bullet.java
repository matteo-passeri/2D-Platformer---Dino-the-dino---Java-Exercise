package com.DinoTheDino.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.DinoTheDino.framework.ObjectId;

public class Bullet extends GameObject {
	
	private int velBullet = 10;
	boolean facingRight;

	public Bullet(float x, float y, ObjectId id, boolean facingRight, int bulletDamage) {
		super(x, y, id);
		this.facingRight = facingRight;
		
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		
		if (!facingRight) { // left
			x -= velBullet;
		} else if (facingRight) { // right
			x += velBullet;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		if (!facingRight) { // left
			g.fillRect((int) x, (int) y +15, 16, 16);
		} else if (facingRight) { // right
			g.fillRect((int) x +35, (int) y +15, 16, 16);
		}
		
	}

	@Override
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) x, (int) y, 16, 16);
	}

	@Override
	public Rectangle getBoundsTop() {
		return null;
	}

	@Override
	public Rectangle getBoundsLeft() {
		return null;
	}

	@Override
	public Rectangle getBoundsRight() {
		return null;
	}

}
