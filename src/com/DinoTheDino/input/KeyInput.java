package com.DinoTheDino.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.DinoTheDino.framework.Handler;
import com.DinoTheDino.framework.ObjectId;
import com.DinoTheDino.objects.Bullet;
import com.DinoTheDino.objects.GameObject;

public class KeyInput extends KeyAdapter 
{
	Handler handler;
	
	private boolean shooting = false;
	
	public KeyInput(Handler handler)
	{
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i=0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ObjectId.Player)
			{
				if (key == KeyEvent.VK_D) tempObject.setVelX(5);
				if (key == KeyEvent.VK_A) tempObject.setVelX(-5);
				if (key == KeyEvent.VK_W && !tempObject.isJumping()) {
					tempObject.setJumping(true);
					tempObject.setVelY(-13);
				}
				
				if(key == KeyEvent.VK_SPACE && !shooting) {
					shooting = true;
					handler.addObject(new Bullet(tempObject.getX(), tempObject.getY(), ObjectId.Bullet, tempObject.isFacingRight(), tempObject.getDamage()));
				}
				
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE)
		{
			System.exit(0);
		}
		
	}
	
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i=0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ObjectId.Player)
			{
				if (key == KeyEvent.VK_D) tempObject.setVelX(0);
				if (key == KeyEvent.VK_A) tempObject.setVelX(0);
				if (key == KeyEvent.VK_W && !tempObject.isJumping()) {
					tempObject.setJumping(true);
					tempObject.setVelY(-10);
				}
				
				if(key == KeyEvent.VK_SPACE && shooting) {
					shooting = false;
				}


			}
		}
	}
	
}
