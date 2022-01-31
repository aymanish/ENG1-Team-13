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
	public PlayerShip player, player2;
	public College AnneLister, Constantine, Goodricke;
	private ArrayList<College> collegeList = new ArrayList<>();
	public ArrayList<Bullet> bullets;
	public npcShip shipAL, shipGR, shipCT;

	//COLLEGE AIM:
	public Vector2 bulletDirection;
	public float bulletRadians;


	//shared resources
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

		//initialize players
		bullets = new ArrayList<Bullet>();
		player = new PlayerShip(bullets);

		//initialize npc ships

		//initialize colleges;
		AnneLister = new AnneLister();
		Constantine = new Constantine();
		Goodricke = new Goodricke();

		//AnneLister.spreadNPC();
		//Constantine.spreadNPC();
		//Goodricke.spreadNPC();

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

	public void drawMap() {map.map.draw(batch);
	}

	public void drawPlayer() {player.sprite.draw(batch);
	}

	public void drawColleges() {
		for (College college: collegeList) {
			college.collegeSprite.draw(batch);
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

	public void playerInRange() {
		for (College college: collegeList) {
			//bulletDirection = new Vector2(college.x-player.x, college.y-player.y).nor();
			//bulletRadians = bulletDirection.angleRad() + 10213.2f;
			if (Intersector.overlaps(college.AOE, player.rectPlayer)) {
				college.isAOE = true;
				System.out.println("AOE Hit");
				if (!(college.isCaptured)) {
					bulletDirection = new Vector2(college.x-player.x, college.y-player.y).nor();
					bulletRadians = bulletDirection.angleRad() + 10213.2f;
					college.shoot(bulletRadians);
					System.out.println("AOE Hit");
				}
			} else {
				college.isAOE = false;
			}
		}
	}

	public void bulletCollegeHit() {
		for (College college: collegeList) {
			if (college.isAttacked) {
				if (!(college.isCaptured)) {
					college.collegeHit();
				}
				college.isAttacked = false;
			}
		}
	}

	public void playerBulletHit() {
		if (player.isAttacked) {
			player.playerHit();
		}
	}

	public void playerCollegeHit() {
		for (College college: collegeList) {
			//PLAYER-COLLEGE COLLISION
			if (player.rectPlayer.overlaps(college.boundRect)) {
				System.out.println("College Hit");
				//collision:
				player.dx = -3*player.dx/2;
				player.dy = -3*player.dy/2;
			}
		}
	}

	public void collegeCaptured() {
		for (College college: collegeList) {
			//COLLEGE CAPTURED:
			if ((!college.isCaptured) && college.HP == 0) {
				System.out.println("CAPTURED");
				college.isCaptured = true;
				//IF STATEMENT FOR EACH COLLEGE:
				if (college.x == 900) {
					college.collegeSprite = college.textureAtlas.createSprite("anneLister_island_captured");
				} else if (college.x == 2000) {
					college.collegeSprite = college.textureAtlas.createSprite("goodricke_island_captured");
				} else {
					college.collegeSprite = college.textureAtlas.createSprite("constantine_island_captured");
				}
				player.POINTS += college.POINTS;
			}
		}
	}


	@Override
	public void dispose () {
		batch.dispose();
		//unsure what else to dispose
	}
}
