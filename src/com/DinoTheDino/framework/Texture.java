package com.DinoTheDino.framework;

import java.awt.image.BufferedImage;

import com.DinoTheDino.Utils.BufferedImageLoader;
import com.DinoTheDino.Utils.SpriteSheet;

public class Texture {
	
	SpriteSheet bs, ps, sss, sws, sjs;
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	private BufferedImage skeleton_standing_sheet = null, skeleton_walking_sheet = null, skeleton_jumping_sheet = null;

	
	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] player = new BufferedImage[13];
	public BufferedImage[] skeleton_standing = new BufferedImage[11];
	public BufferedImage[] skeleton_walking = new BufferedImage[12];
	public BufferedImage[] skeleton_jumping = new BufferedImage[2];


	
	BufferedImageLoader loader = new BufferedImageLoader();

	
	public Texture() {
		
		try {
			player_sheet = loader.loadImage("/player/player_sheet.png");
			skeleton_standing_sheet = loader.loadImage("/enemies/skeleton/Skeleton_Idle.png");
			skeleton_walking_sheet = loader.loadImage("/enemies/skeleton/Skeleton_Walking.png");
			skeleton_jumping_sheet = loader.loadImage("/enemies/skeleton/Skeleton_Attack.png");


		} catch(Exception e) {
			e.printStackTrace();
		}
		
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		sss = new SpriteSheet(skeleton_standing_sheet);
		sws = new SpriteSheet(skeleton_walking_sheet);
		sjs = new SpriteSheet(skeleton_jumping_sheet);

		
		getTextures();
	}
	
	private void getTextures() {
		// BLOCKS
		block[0] = loader.loadImage("/tiles/dirt.png"); // dirt block
		block[1] = loader.loadImage("/tiles/grass.png"); // grass block
		
		// PLAYER
		player[0] = ps.grabImage(1, 1, 24, 24); // idle frame/animation for player
		player[1] = ps.grabImage(2, 1, 24, 24); // idle animation for player
		player[2] = ps.grabImage(3, 1, 24, 24); // idle animation for player
		player[3] = ps.grabImage(4, 1, 24, 24); // idle animation for player
		
		player[4] = ps.grabImage(5, 1, 24, 24); // walking animation for player
		player[5] = ps.grabImage(6, 1, 24, 24); // walking animation for player
		player[6] = ps.grabImage(7, 1, 24, 24); // walking animation for player
		player[7] = ps.grabImage(8, 1, 24, 24); // walking animation for player
		player[8] = ps.grabImage(9, 1, 24, 24); // walking animation for player
		player[9] = ps.grabImage(10, 1, 24, 24); // walking animation for player
		
		player[10] = ps.grabImage(18, 1, 24, 24); // jumping animation for player
		player[11] = ps.grabImage(19, 1, 24, 24); // jumping animation for player
		player[12] = ps.grabImage(20, 1, 24, 24); // jumping animation for player

		// SKELETON
		skeleton_standing[0] = sss.grabImage(1, 1, 24, 32); // idle animation for skeleton
		skeleton_standing[1] = sss.grabImage(2, 1, 24, 32); // idle animation for skeleton
		skeleton_standing[2] = sss.grabImage(3, 1, 24, 32); // idle animation for skeleton
		skeleton_standing[3] = sss.grabImage(4, 1, 24, 32); // idle animation for skeleton
		skeleton_standing[4] = sss.grabImage(5, 1, 24, 32); // idle animation for skeleton
		skeleton_standing[5] = sss.grabImage(6, 1, 24, 32); // idle animation for skeleton
		skeleton_standing[6] = sss.grabImage(7, 1, 24, 32); // idle animation for skeleton
		skeleton_standing[7] = sss.grabImage(8, 1, 24, 32); // idle animation for skeleton
		skeleton_standing[8] = sss.grabImage(9, 1, 24, 32); // idle animation for skeleton
		skeleton_standing[9] = sss.grabImage(10, 1, 24, 32); // idle animation for skeleton
		skeleton_standing[10] = sss.grabImage(11, 1, 24, 32); // idle animation for skeleton

		skeleton_walking[0] = sws.grabImage(1, 1, 22, 32); // walking animation for skeleton
		skeleton_walking[1] = sws.grabImage(2, 1, 22, 32); // walking animation for skeleton
		skeleton_walking[2] = sws.grabImage(3, 1, 22, 32); // walking animation for skeleton
		skeleton_walking[3] = sws.grabImage(4, 1, 22, 32); // walking animation for skeleton
		skeleton_walking[4] = sws.grabImage(5, 1, 22, 32); // walking animation for skeleton
		skeleton_walking[5] = sws.grabImage(6, 1, 22, 32); // walking animation for skeleton
		skeleton_walking[6] = sws.grabImage(7, 1, 22, 32); // walking animation for skeleton
		skeleton_walking[7] = sws.grabImage(8, 1, 22, 32); // walking animation for skeleton
		skeleton_walking[8] = sws.grabImage(9, 1, 22, 32); // walking animation for skeleton
		skeleton_walking[9] = sws.grabImage(10, 1, 22, 32); // walking animation for skeleton
		skeleton_walking[10] = sws.grabImage(11, 1, 22, 32); // walking animation for skeleton
		skeleton_walking[11] = sws.grabImage(12, 1, 22, 32); // walking animation for skeleton
		
		skeleton_jumping[0] = sjs.grabImage(6, 1, 35, 37); // jumping animation for skeleton
		skeleton_jumping[1] = sjs.grabImage(7, 1, 35, 37); // jumping animation for skeleton






		

	}

}
