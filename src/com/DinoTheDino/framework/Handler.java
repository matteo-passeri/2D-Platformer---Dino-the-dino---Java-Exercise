package com.DinoTheDino.framework;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.DinoTheDino.Game;
import com.DinoTheDino.Utils.BufferedImageLoader;
import com.DinoTheDino.objects.Block;
import com.DinoTheDino.objects.FinishPoint;
import com.DinoTheDino.objects.GameObject;
import com.DinoTheDino.objects.creatures.Player;
import com.DinoTheDino.objects.creatures.Skeleton;

public class Handler {

	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	private GameObject tempObject;
	private Camera camera;
	public BufferedImage level01 = null, level02 = null, level03 = null, level04 = null;
	
	public Handler(Camera camera) {
		this.camera = camera;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level01 = loader.loadImage("/maps/Level00.png"); // loading the level
		level02 = loader.loadImage("/maps/Level01.png"); // loading the level
	}

	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);

			tempObject.tick(object);

		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);

			tempObject.render(g);

		}
	}
	
	public void LoadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		for (int xx = 0; xx < h; xx++) {
			for (int yy = 0; yy < w; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				// if pixel is WHITE is dirt
				if (red == 255 && green == 255 && blue == 255) {
					addObject(new Block(xx * 32, yy * 32, 0, ObjectId.Block));
				}
				// if pixel is GREY is grass
				if (red == 128 && green == 128 && blue == 128) {
					addObject(new Block(xx * 32, yy * 32, 1, ObjectId.Block));
				}
				// if pixel is BLUE is player
				if (red == 0 && green == 0 && blue == 255) {
					addObject(new Player(xx * 32, yy * 32, this, camera, ObjectId.Player));
				}
				// if pixel is YELLOW is finishPoint
				if (red == 255 && green == 255 && blue == 0) {
					addObject(new FinishPoint(xx * 32, yy * 32, ObjectId.FinishPoint));
				}
				// if pixel is RED is skeleton
				if (red == 255 && green == 0 && blue == 0) {
					addObject(new Skeleton(xx * 32, yy * 32, this, ObjectId.Skeleton));
				}
			}
		}
	}
	
	public void changeLevel() {
		clearLevel();
		camera.setX(0);
		
		switch(Game.LEVEL) {
		case 1:
			LoadImageLevel(level02);
			break;
		case 2:
			LoadImageLevel(level03);
			break;
		case 3:
			LoadImageLevel(level04);
			break;
		}
		Game.LEVEL++;
		
	}
	
	private void clearLevel() {
		object.clear();
	}

	public void addObject(GameObject object) {
		this.object.add(object);

	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}


}
