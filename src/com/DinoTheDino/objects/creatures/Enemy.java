package com.DinoTheDino.objects.creatures;

import java.awt.Graphics;
import java.util.LinkedList;

import com.DinoTheDino.framework.Handler;
import com.DinoTheDino.framework.ObjectId;
import com.DinoTheDino.objects.GameObject;

public abstract class Enemy extends Creatures {

	private Handler handler;

	public Enemy(float x, float y, Handler handler, ObjectId id) {
		super(x, y, id);
		this.handler = handler;

	}

	@Override
	public void Collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ObjectId.Bullet) {
				hurt(damage);
			}
			// collide with BLOCKS
			else if (tempObject.getId() == ObjectId.Block) {

				// top
				if (getBoundsTop().intersects(tempObject.getBoundsBottom())) {
					y = tempObject.getY() + height / 2;
					jumping = false;

					velY = 0;
				}
				// bottom
				if (getBoundsBottom().intersects(tempObject.getBoundsBottom())) {
					y = tempObject.getY() - height;

					velY = 0;
					falling = false;
					jumping = false;
				} 
				else {
					falling = true;
				}
				// right
				if (getBoundsRight().intersects(tempObject.getBoundsBottom())) {
					x = tempObject.getX() - width -25;
					jumping = true;
					velY = -10;
					velX = 5;
				}
				// left
				if (getBoundsLeft().intersects(tempObject.getBoundsBottom())) {
					x = tempObject.getX() + width +25;
					jumping = true;
					velY = -10;
					velX = -5;

				}
			}

//			 collide with PLAYER
			else if (tempObject.getId() == ObjectId.Player) {
				
				// view Box
				if (getRightViewBox().intersects(tempObject.getBoundsBottom())) {
					velX += SPEED;
				} else if (getLeftViewBox().intersects(tempObject.getBoundsBottom())) {
					velX -= SPEED;
				} else {
					velX = 0;
				}
			}

		}

	}

	@Override
	public void die() {
		active = false;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {

	}

	@Override
	public void render(Graphics g) {
//		// collision boxes
//		Graphics2D g2d = (Graphics2D) g;
//		g.setColor(Color.RED);
//
//		g2d.draw(getBounds());
	}

}
