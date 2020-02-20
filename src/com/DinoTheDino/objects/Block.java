package com.DinoTheDino.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.DinoTheDino.Game;
import com.DinoTheDino.framework.ObjectId;
import com.DinoTheDino.framework.Texture;

public class Block extends GameObject {
	Texture tex = Game.getInstance();
	private int type;

	public Block(float x, float y, int type, ObjectId id) {
		super(x, y, id);
		this.type = type;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {

	}

	@Override
	public void render(Graphics g) {

		// dirt
		if (type == 0) {
			g.drawImage(tex.block[0], (int) x, (int) y, null);
		}
		// grass
		if (type == 1) {
			g.drawImage(tex.block[1], (int) x, (int) y, null);
		}

	}

	@Override
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) x, (int) y, 32, 32);
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
