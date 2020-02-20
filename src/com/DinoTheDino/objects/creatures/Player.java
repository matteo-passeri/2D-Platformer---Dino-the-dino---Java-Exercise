package com.DinoTheDino.objects.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.DinoTheDino.Game;
import com.DinoTheDino.framework.Animation;
import com.DinoTheDino.framework.Camera;
import com.DinoTheDino.framework.Handler;
import com.DinoTheDino.framework.ObjectId;
import com.DinoTheDino.framework.Texture;
import com.DinoTheDino.objects.GameObject;

public class Player extends Creatures {

//	private float width = 48f, height = 48f;
//
//	private float gravity = 0.5f;
//	private final float MAX_SPEED = 10f;

	private Handler handler;
	private Camera camera;

	Texture tex = Game.getInstance();

	private Animation playerWalk;
	private Animation playerStanding;
	private Animation playerJumping;

	public Player(float x, float y, Handler handler, Camera camera, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.camera = camera;

		health = health * 10;
		damage = damage * 10;
		SPEED = SPEED * 10;

		playerWalk = new Animation(3, tex.player[4], tex.player[5], tex.player[6], tex.player[7], tex.player[8],
				tex.player[9]);
		playerStanding = new Animation(15, tex.player[0], tex.player[1], tex.player[2], tex.player[3]);
		playerJumping = new Animation(15, tex.player[10], tex.player[11], tex.player[12]);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;

		if (falling || jumping) {
			velY += gravity;

			// stop player going faster than max_speed
			if (velY > MAX_SPEED) {
				velY = MAX_SPEED;
			}
		}

		Collision(object);

		playerWalk.runAnimation();
		playerStanding.runAnimation();
		playerJumping.runAnimation();

	}

	public void Collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ObjectId.Bullet) {
				return;
			}
			// collide with BLOCKS ////////////////////////////////
			else if (tempObject.getId() == ObjectId.Block) {
				// bottom
				if (getBoundsBottom().intersects(tempObject.getBoundsBottom())) {
//					futureY = tempObject.getY() - height;
					y = tempObject.getY() - height;

					velY = 0;
					falling = false;
					jumping = false;
				} else {
					falling = true;
				}
				// top
				if (getBoundsTop().intersects(tempObject.getBoundsBottom())) {
					y = tempObject.getY() + height / 2 +10;
					
					velY = 0;
				} 
				// right
				if (getBoundsRight().intersects(tempObject.getBoundsBottom())) {
					// NOT LESS THAN 3 OR MORE THAN 4 or will glitch through the wall
					x = tempObject.getX() - width + 6;
				} 
				// left
				if (getBoundsLeft().intersects(tempObject.getBoundsBottom())) {
					// NOT LESS THAN 34 or will glitch through the wall
					x = tempObject.getX() + width - 34;
				} 				
				
			}

			// collide with SKELETON //////////////////////////////////////////
			else if (tempObject.getId() == ObjectId.Skeleton) {
				// right
				if (getBoundsRight().intersects(tempObject.getBoundsBottom())) {
					// NOT LESS THAN 3 OR MORE THAN 4 or will glitch through the wall
					x = tempObject.getX() - width;

					hurt(1);
				}
				// left
				if (getBoundsLeft().intersects(tempObject.getBoundsBottom())) {
					// NOT LESS THAN 21 OR MORE THAN 22 or will glitch through the wall
					x = tempObject.getX() + width;

					hurt(1);
				}
				// top
				if (getBoundsTop().intersects(tempObject.getBoundsBottom())) {
					y = tempObject.getY() + height / 2;


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
				
			} else if (tempObject.getId() == ObjectId.FinishPoint) {
				// change level
				if (tempObject.getBoundsBottom().intersects(getBoundsBottom())) {
					handler.changeLevel();
				}

			}

		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		if (jumping) {
			// jumping
			if (facingRight) {
				playerJumping.drawAnimation(g, (int) x, (int) y, 56, 56);
			} else if (!facingRight) {
				playerJumping.drawAnimation(g, (int) x + 56, (int) y, -56, 56);
			}

		} else {
			if (velX > 0) {
				// right
				playerWalk.drawAnimation(g, (int) x, (int) y, 56, 56);
				facingRight = true;
			} else if (velX < 0) {
				// left
				playerWalk.drawAnimation(g, (int) x + 56, (int) y, -56, 56);
				facingRight = false;
			} else if (velX == 0) {
				// standing
				if (facingRight == true) {
					playerStanding.drawAnimation(g, (int) x, (int) y, 56, 56);
				} else if (facingRight == false) {
					playerStanding.drawAnimation(g, (int) x + 56, (int) y, -56, 56);
				}
			} else
				g.drawImage(tex.player[0], (int) x, (int) y, null);
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
		return new Rectangle((int) (x + (width / 2) - ((width / 2) / 2) + 11),
				(int) (y + (height / 2) + 5), (int) ((width / 2) - 10), (int) (height / 2) - 5);
	}

	public Rectangle getBoundsTop() {
		return new Rectangle((int) (x + (width / 2) - ((width / 2) / 2) + 11), (int) (y + 8),
				(int) ((width / 2) - 10), (int) ((height / 2) - 3));
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) (x + width - 11), (int) (y + 10), 3, (int) (height) - 13);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) (x + 18), (int) (y + 10), 3, (int) (height) - 13);
	}

	@Override
	public void die() {
		System.out.println("GAME OVER");
		active = false;
	}
}
