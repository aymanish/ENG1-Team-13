package com.ayman.game;

import com.ayman.entities.*;
import com.ayman.screen.TitleScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Intersector;
import java.util.ArrayList;

/**
 * Class to represent the main gameplay of the game.
 *
 * camera: camera viewpoint for the game
 * font: renders bitmap fonts
 * batch: draws 2d rectangles that reference a texture
 * map: instance of map game object
 * player: instance of user player game object
 * annelister: instance of Anne Lister college
 * Constantine: instance of Constantine college
 * Goodricke: instance of Goodricke college
 * collegeList: ArrayList of colleges in the game
 * bullets: Arraylist of bullets for player
 * barrier: barrier between boss college and other colleges
 * screens: screens used in the game
 * time: time elapsed in a game once started
 */

public class MyGame extends Game {
	//initialize shared resources: camera, font, spritebatch and game objects
	public OrthographicCamera camera;
	public BitmapFont font;
	public SpriteBatch batch;
	public Map map;
	public PlayerShip player;
	public College anneLister, Constantine, Goodricke;
	private ArrayList<College> collegeList = new ArrayList<>();
	public ArrayList<Bullet> bullets;
	public Barrier barrier;
	public Screens screens;
	float time;

	/**
	 * {@inheritDoc}
	 */

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
		anneLister = new AnneLister();
		Constantine = new Constantine();
		Goodricke = new Goodricke();

		//initialize list of colleges
		collegeList = new ArrayList<>();
		collegeList.add(anneLister);
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

		time = 0;

		//set screen to title screen always upon startup:
		setScreen(new TitleScreen(this));
	}

	/**
	 * Updates the list of player bullets and parameters for each bullet
	 * @param delta
	 * 		Time between frames.
	 */

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

	/**
	 * Updates the list of college bullets and parameters for each bullet
	 * @param delta
	 * 		Time between frames.
	 */

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

	/**
	 * Draws the title screen
	 */

	public void drawTitleScreen() {
		screens.titleScreen.setPosition(player.x-300, player.y-400);
		screens.titleScreen.draw(batch);
	}

	/**
	 * Draws the end screen.
	 */

	public void drawEndScreen() {
		screens.endScreen.setPosition(player.x-350, player.y-200);
		screens.endScreen.draw(batch);
	}

	/**
	 * Draws the game map.
	 */

	public void drawMap() {map.map.draw(batch);
	}

	/**
	 * Draws the player.
	 */

	public void drawPlayer() {player.sprite.draw(batch);
	}

	/**
	 * Draws the colleges
	 */

	public void drawColleges() {
		for (College college: collegeList) {
			if (college.isCaptured()) {
				college.capturedSprite.draw(batch);
			} else {
				college.collegeSprite.draw(batch);
			}
		}
	}

	/**
	 * Displays the stats for each college
	 */

	public void drawCollegeStats() {
		for (College college: collegeList) {
			//stat for each college
			font.draw(batch, "\nHP: " + college.HP + "\nP: " + college.POINTS+ "\nAMMO: "
					+ college.bullets.size() + "\nC: " + college.isCaptured(),
					college.x+college.width, college.y+college.height);
		}
	}

	/**
	 * Draws the NPC ships
	 */

	public void drawCollegeNPC() {
		for (College college: collegeList) {
			for (int i = 0; i <college.npcCount; i++) {
				college.NPCs.get(i).npcSprite.setPosition(college.NPCs.get(i).x, college.NPCs.get(i).y);
				college.NPCs.get(i).npcSprite.draw(batch);
			}
		}
	}

	/**
	 * Draws the bullets shot by the player
	 */

	public void drawPlayerBullets() {
		for (int i = 0; i < player.bullets.size(); i++) {
			player.bullets.get(i).bulletSprite.draw(batch);
		}
	}

	/**
	 * Draws the colleges bullets
	 */

	public void drawCollegeBullets() {
		for (College college: collegeList) {
			for (int i = 0; i < college.bullets.size(); i++) {
				college.bullets.get(i).bulletSprite.draw(batch);
			}
		}
	}

	/**
	 * Displays the game objectives
	 */

	public void drawObjectives() {
		font.draw(batch, "POINTS: "+ player.POINTS +"\nOBJECTIVES: \nCapture 2 colleges: "
				+ player.captures + "/2" , player.x-500, player.y+270);
		if (bossUnlocked()) {
			if (!playerWin()) {
				font.draw(batch, "\n\n\nCapture Boss college: 0/1" , player.x-500, player.y+270);
			} else {
				font.draw(batch, "\n\n\nCapture Boss college: 1/1" , player.x-500, player.y+270);
			}
		}
	}

	/**
	 * Displays the game tutorial
	 */

	public void drawTutorial() {
		time += Gdx.graphics.getDeltaTime();
		if (time < 10) {
			font.draw(batch, "Press arrow keys to move", player.x - 80, player.y + 100);
		} if (time > 5 && time < 10) {
			font.draw(batch, "Press SPACE to shoot", player.x - 80, player.y + 120);
		}
	}

	/**
	 * Displays the players lives remaining
	 */

	public void drawPlayerHearts() {
		player.heartsSprite.draw(batch);
	}

	/**
	 * Detects if a player is in range of a college. College shoots if so.
	 */

	public void playerInRange() {
		for (College college: collegeList) {
			if (Intersector.overlaps(college.AOE, player.rectPlayer)) {
				if (!(college.isCaptured())) {
					college.shoot(college.shootDirection(player.x,player.y));
				}
			}
		}
	}

	/**
	 * Checks if a bullet from the player hits a college and subsequent actions if so.
	 */

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

	/**
	 * Checks if a bullet from a college hits a player, if so damage is dealt to player and hit is registered.
	 */
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

	/**
	 * Checks if player collides with college, collision physics if so
	 */

	public void playerCollegeHit() {
		for (College college: collegeList) {
			if (player.rectPlayer.overlaps(college.boundRect)) {
				player.dx = -3*player.dx/2;
				player.dy = -3*player.dy/2;
			}
		}
	}

	/**
	 * Unlocks the boss college
	 */

	public void unlockBoss() {
		if (!bossUnlocked()) {
			//set barrier:
			player.unlocked = false;
			barrier.barrier.setPosition(1452+50, 510);
			barrier.barrier.draw(batch);
		} else {
			//set boundaries:
			player.unlocked = true;
			//don't draw barrier
		}
	}

	/**
	 * Checks if the game has reached its end.
	 * @return
	 * 		True or false.
	 */

	public boolean isGameEnd() {
		return (playerWin()) || (playerLose());
	}

	/**
	 * Checks if the player has won.
	 * @return
	 * 		True or false.
	 */

	public boolean playerWin() {
		return player.captures == collegeList.size();
	}

	/**
	 * Checks if the player has lost.
	 * @return
	 * 		True or false.
	 */
	public boolean playerLose() {
		return player.isDead();
	}

	/**
	 * Checks if the boss can be unlocked
	 * @return
	 * 		True or false.
	 */
	public boolean bossUnlocked() {
		return player.captures == collegeList.size()-1;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public void dispose () {
		batch.dispose();
		//unsure what else to dispose
	}
}
