package com.ayman.game;
import com.ayman.entities.*;
import com.ayman.screen.TitleScreen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MyGame extends Game {
	//initialize shared resources: camera, font, spritebatch and game objects
	public OrthographicCamera camera;
	public BitmapFont font;
	public SpriteBatch batch;
	public Map map;
	public PlayerShip player;
	public College AnneLister, Constantine, Goodricke;
	private ArrayList<College> collegeList = new ArrayList<>();
	public ArrayList<Bullet> bullets;

	//COLLEGE AIM:
	//public Vector2 bulletDirection;
	//public float bulletRadians;

	//start/end screen textures:
	//public Texture titleScreen, endScreen;

	//ayman new barrier:
	public Barrier barrier;
	//ayman new code: screen images:
	public Screens screens;



	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();

		//camera setup: viewport 0.5 x smaller that game screen:
		//viewport ratio should be same as game screen ratio to maintain aspect ratio/avoid distortion:
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		//initialize map
		map = new Map();

		//ayman new barrier
		//initialize barrier:
		barrier = new Barrier();

		//ayman new code:
		screens = new Screens();


		//initialize players
		bullets = new ArrayList<Bullet>();
		player = new PlayerShip(bullets);

		//initialize colleges;
		AnneLister = new AnneLister();
		Constantine = new Constantine();
		Goodricke = new Goodricke();

		//initialize list of colleges
		collegeList = new ArrayList<>();
		collegeList.add(AnneLister);
		collegeList.add(Goodricke);
		collegeList.add(Constantine);

		//initialize NPC:
		for (College college : collegeList) {
			for (int i = 0; i < college.npcCount; i++) {
				college.NPCs.add(new npcShip(college.png_npc));
				college.NPCs.get(i).x = college.x + (i*50) + 50;
				college.NPCs.get(i).y = college.y - 100;
			}
		}


		//set screen to title screen always upon startup:
		setScreen(new TitleScreen(this));
	}

	//TODO
	public void updatePlayerBullets(float delta) {
		for (College college: collegeList) {
			for (int i = 0; i < player.bullets.size(); i++) {
				player.bullets.get(i).update(delta);
				if (player.bullets.get(i).bulletHit || player.bullets.get(i).timeCheck()) {
					player.bullets.remove(i);
					i--;
				}
			}
		}
	}
	//TODO
	public void updateCollegeBullets(float delta) {
		for (College college: collegeList) {
			for (int i = 0; i < college.bullets.size(); i++) {
				college.bullets.get(i).update(delta);
				if (college.bullets.get(i).bulletHit || college.bullets.get(i).timeCheck()) {
					college.bullets.remove(i);
					i--;
				}
			}
		}
	}

	//ayman's new code:
	//drawing title and end screens:
	public void drawTitleScreen() {
		screens.titleScreen.setPosition(player.x-200, player.y-300);
		screens.titleScreen.draw(batch);
	}

	public void drawEndScreen() {
		screens.endScreen.setPosition(player.x, player.y);
		screens.endScreen.draw(batch);
	}

	public void drawMap() {map.map.draw(batch);
	}

	public void drawPlayer() {player.sprite.draw(batch);
	}

	public void drawColleges() {
		for (College college: collegeList) {
			if (college.isCaptured()) {
				college.capturedSprite.draw(batch);
			} else {
				//college.collegeSprite.setPosition(college.x,college.y);
				college.collegeSprite.draw(batch);
			}
		}
	}

	public void drawCollegeStats() {
		for (College college: collegeList) {
			//stat for each college
			font.draw(batch, "\nHP: " + college.HP + "\nP: " + college.POINTS+ "\nAMMO: "
					+ college.bullets.size() + "\nC: " + college.isCaptured(),
					college.x+college.width, college.y+college.height);
		}
	}

	//aymans code:
	public void drawCollegeNPC() {
		for (College college: collegeList) {
			for (int i = 0; i <college.npcCount; i++) {
				college.NPCs.get(i).npcSprite.setPosition(college.NPCs.get(i).x, college.NPCs.get(i).y);
				college.NPCs.get(i).npcSprite.draw(batch);
			}
		}
	}

	public void drawPlayerBullets() {
		for (int i = 0; i < player.bullets.size(); i++) {
			player.bullets.get(i).bulletSprite.draw(batch);
		}
	}

	public void drawCollegeBullets() {
		for (College college: collegeList) {
			for (int i = 0; i < college.bullets.size(); i++) {
				college.bullets.get(i).bulletSprite.draw(batch);
			}
		}
	}

	//function to draw objectives:
	public void drawObjectives() {
		font.draw(batch, "\nOBJECTIVES: \nCapture 2 colleges: " + player.captures + "/2" , player.x-500, player.y+270);
		if (bossUnlocked()) {
			if (!playerWin()) {
				font.draw(batch, "\n\n\nCapture Boss college: 0/1" , player.x-500, player.y+270);
			} else {
				font.draw(batch, "\n\n\nCapture Boss college: 1/1" , player.x-500, player.y+270);
			}
		}
	}

	//ayman's new code: RENDER PLAYER HEALTH
	//EASIER TO DO THIS WAY THAN INITIALIZING A LIST OF SPRITES
	// FOR ALL 3 HEARTS IN PLAYERSHIP CLASS AS ITS A WASTE OF MEMORY???

	//updates the health bar sprite based on player HP:
	public void drawPlayerHearts() {
		if (player.HP == 3) {
			player.hearts = player.textureAtlas.createSprite("heart3");
			player.hearts.setPosition(player.x-10, player.y-20);
			player.hearts.draw(batch);
		}
		else if (player.HP == 2) {
			player.hearts = player.textureAtlas.createSprite("heart2");
			player.hearts.setPosition(player.x-10, player.y-20);
			player.hearts.draw(batch);
		}
		else if (player.HP == 1) {
			player.hearts = player.textureAtlas.createSprite("heart1");
			player.hearts.setPosition(player.x-10, player.y-20);
			player.hearts.draw(batch);
		}
	}

	//college check if player is in range and shoots if so
	public void playerInRange() {
		for (College college: collegeList) {
			//bullet radian was here initially:
			if (Intersector.overlaps(college.AOE, player.rectPlayer)) {
				if (!(college.isCaptured())) {
					college.shoot(college.shootDirection(player.x,player.y));
				}
			}
		}
	}

	//ayman:NOT SURE IF WE WANT IT THIS WAY BECAUSE WE WANT THE COLLEGE TO SHOOT THE PLAYER ONLY WHEN ITS CAPTURED RIGHT?
	//UNLESS YOU THINK THE COLLEGE STILL NEEDS TO BE ABLE TO SHOOT WHILE CAPTURED. THAT'S UNDERSTANDABLE

	//if player bullet hits college then college is attacked, if college captured then hit is registered but no damage dealt
	public void bulletCollegeHit() {
		for (College college: collegeList) {
			for (Bullet bullet : player.bullets) {
				if (college.boundRect.overlaps(bullet.rectBullet)) {
					if (!college.isCaptured()) {
						college.collegeHit();
						if (college.isCaptured()) {
							player.POINTS += college.POINTS;
							player.captures++;
						}
					} bullet.bulletHit = true;
				}
			}
		}
	}


	//checks if a bullet from a college hits a player, if so damage is dealt to player and hit is registered
	public void playerBulletHit() {
		for (College college: collegeList) {
			for (Bullet bullet : college.bullets) {
				if (player.rectPlayer.overlaps(bullet.rectBullet)) {
					player.playerHit();
					bullet.bulletHit = true;
				}
			}
		}
	}

	//checks if player collides with college, collision physics if so
	public void playerCollegeHit() {
		for (College college: collegeList) {
			if (player.rectPlayer.overlaps(college.boundRect)) {
				player.dx = -3*player.dx/2;
				player.dy = -3*player.dy/2;
			}
		}
	}
	/* method now redundant due to refactoring
	public void collegeCaptured() {
		for (College college: collegeList) {
			//COLLEGE CAPTURED:
			if (college.isCaptured() && !college.captured) {
				player.POINTS += college.POINTS;
				player.captures++;
				college.captured = true;
			}
		}
	}*/

	//ayman new code:
	//checks if boss is unlocked and restricts player based on that
	//similarly, draws barrier sprite if locked
	public void unlockBoss() {
		if (!bossUnlocked()) {
			//set barrier:
			player.unlocked = false;
			//draw barrier
			barrier.barrier.setPosition(1452+50, 510);
			barrier.barrier.draw(batch);
		} else {
			System.out.println("BOSS UNLOCKED");
			//set boundaries:
			player.unlocked = true;
			//don't draw barrier
		}
	}

	//boolean checks for ending game + unlocking boss:
	public boolean isGameEnd() {
		return (playerWin()) || (playerLose());
	}

	public boolean playerWin() {
		return player.captures == collegeList.size();
	}

	public boolean playerLose() {
		return player.isDead();
	}

	public boolean bossUnlocked() {
		return player.captures == collegeList.size()-1;
	}

	@Override
	public void dispose () {
		batch.dispose();
		//unsure what else to dispose
	}
}
