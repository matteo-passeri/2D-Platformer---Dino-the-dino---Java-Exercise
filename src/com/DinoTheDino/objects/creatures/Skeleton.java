package com.DinoTheDino.objects.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.DinoTheDino.Game;
import com.DinoTheDino.framework.Animation;
import com.DinoTheDino.framework.Handler;
import com.DinoTheDino.framework.ObjectId;
import com.DinoTheDino.framework.Texture;
import com.DinoTheDino.objects.GameObject;

public class Skeleton extends Enemy {

	Texture tex = Game.getInstance();

	private Animation skeletonWalking;
	private Animation skeletonStanding;
	private Animation skeletonJumping;

	public Skeleton(float x, float y, Handler handler, ObjectId id) {
		super(x, y, handler, id);

		health = health * 1;
		damage = damage * 1;
		MAX_SPEED = 1;

		skeletonStanding = new Animation(15, tex.skeleton_standing[0], tex.skeleton_standing[1], tex.skeleton_standing[2], tex.skeleton_standing[3]);
		skeletonWalking = new Animation(3, tex.skeleton_walking[0], tex.skeleton_walking[1], tex.skeleton_walking[2], tex.skeleton_walking[3], tex.skeleton_walking[4], tex.skeleton_walking[5], tex.skeleton_walking[6], tex.skeleton_walking[7], tex.skeleton_walking[8], tex.skeleton_walking[9], tex.skeleton_walking[10]);
		skeletonJumping = new Animation(15, tex.skeleton_jumping[0]);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;

		// stop player going faster than max_speed
		if (velX > MAX_SPEED) {
			velX = MAX_SPEED;
		}
		if (velX < -MAX_SPEED) {
			velX = -MAX_SPEED;
		}
		
		if (falling || jumping) {
			velY += gravity;

			// stop player falling/jumping faster than max_speed
			if (velY > MAX_SPEED) {
				velY = MAX_SPEED;
			}
		}

		Collision(object);

		skeletonWalking.runAnimation();
		skeletonStanding.runAnimation();
		skeletonJumping.runAnimation();

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		if (velY != 0) {
			// jumping
			if (facingRight) {
				skeletonJumping.drawAnimation(g, (int) x, (int) y -10, 56, 60);
			} else if (!facingRight) {
				skeletonJumping.drawAnimation(g, (int) x + 56, (int) y -10, -56, 60);
			}

		} else {
			if (velX > 0) {
				// right
				skeletonWalking.drawAnimation(g, (int) x, (int) y -10, 56, 56);
				facingRight = true;
			} else if (velX < 0) {
				// left
				skeletonWalking.drawAnimation(g, (int) x + 56, (int) y -10, -56, 56);
				facingRight = false;
			} else if (velX == 0) {
				// standing
				if (facingRight == true) {
					skeletonStanding.drawAnimation(g, (int) x, (int) y -10, 56, 56);
				} else if (facingRight == false) {
					skeletonStanding.drawAnimation(g, (int) x + 56, (int) y -10, -56, 56);
				}
			} else
				g.drawImage(tex.skeleton_standing[0], (int) x, (int) y -10, null);
		}

		// collision boxes
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.RED);
		g2d.draw(getRightAttackBox());
		g2d.draw(getLeftAttackBox());
		g.setColor(Color.BLUE);
		g2d.draw(getRightViewBox());
		g2d.draw(getLeftViewBox());
		g.setColor(Color.ORANGE);
		g2d.draw(getBoundsBottom());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsTop());

	}
	
	@Override
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) (x + (width / 2) - ((width / 2) / 2) + 2), (int) (y + (height / 2) +1),
				(int) ((width / 2)), (int) (height / 2));
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int) (x + (width / 2) - ((width / 2) / 2) + 2), (int) (y +1), (int) ((width / 2)),
				(int) ((height / 2)));
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) (x + width - 8), (int) (y +2), 3, (int) (height -2));
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) (x + 9), (int) (y +2), 3, (int) (height -2));
	}
}
