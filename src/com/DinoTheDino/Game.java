package com.DinoTheDino;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.DinoTheDino.Utils.BufferedImageLoader;
import com.DinoTheDino.framework.Camera;
import com.DinoTheDino.framework.Handler;
import com.DinoTheDino.framework.ObjectId;
import com.DinoTheDino.framework.Texture;
import com.DinoTheDino.input.KeyInput;

public class Game extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5633202898491539099L;

	private boolean running = false;
	private Thread thread;

	// Objects
	private Handler handler;
	private Camera camera;
	static Texture tex;

	public static int WIDTH, HEIGHT;

	public BufferedImage background, level01 = null;
	
	public static int LEVEL = 1;

	// game loop engine...
	double amountOfTicks = 60.0;
	double ns = 1000000000 / amountOfTicks;
	int updates = 0;
	int frames = 0;
	double delta = 0;
	// ...game loop engine

	private void init() {
		WIDTH = getWidth();
		HEIGHT = getHeight();

		tex = new Texture();

		BufferedImageLoader loader = new BufferedImageLoader();
		background = loader.loadImage("/background.png"); // loading the background
		
		level01 = loader.loadImage("/maps/Level00.png"); // loading the level


		camera = new Camera(0, 0);
		
		handler = new Handler(camera);

		

		handler.LoadImageLevel(level01);

		this.addKeyListener(new KeyInput(handler));

	}

	public synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	private void tick() {
		handler.tick();
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ObjectId.Player) {
				camera.tick(handler.object.get(i));
			}
		}

	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		//////////////////////////////
		g.setColor(new Color(25, 191, 224));
		g.fillRect(0, 0, getWidth(), getHeight());

		g2d.translate(camera.getX(), camera.getY()); // begin of camera

		for (int xx = -600; xx < background.getWidth() * 5; xx += background.getWidth()) {
			g.drawImage(background, xx, 0, this);
		}
		// draw all objects (includes entities)
		handler.render(g);

		g2d.translate(camera.getX(), -camera.getY()); // end of camera
		/////////////////////////////

		g.dispose();
		bs.show();

	}

	

	@Override
	public void run() {
		init();
		this.requestFocus();

		long lastTime = System.nanoTime();
//		double amountOfTicks = 60.0;
//		double ns = 1000000000 / amountOfTicks;
//		double delta = 0;
		long timer = System.currentTimeMillis();
//		int updates = 0;
//		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
//				fps = frames;
//				ticks = updates;
				System.out.println("FPS: " + frames + " - Ticks: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	public static Texture getInstance() {
		return tex;
	}

	public static void main(String args[]) {
		new Window(800, 600, "Tutorial 2D", new Game());
	}

}
