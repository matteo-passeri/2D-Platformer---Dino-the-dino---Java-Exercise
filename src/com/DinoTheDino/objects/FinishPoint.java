package com.DinoTheDino.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.DinoTheDino.framework.ObjectId;

public class FinishPoint extends GameObject {

	public FinishPoint(float x, float y, ObjectId id) {
		super(x, y, id);

	}

	@Override
	public void tick(LinkedList<GameObject> object) {

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect((int) x, (int) y, 16, 16);

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