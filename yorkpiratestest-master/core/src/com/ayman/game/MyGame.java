package com.ayman.game;
import com.ayman.entities.*;
import com.ayman.screen.TitleScreen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

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
	public College AnneLister;
	private ArrayList<College> collegeList = new ArrayList<>();
	public ArrayList<Bullet> bullets;


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

		//initialize list of colleges
		collegeList = new ArrayList<>();
		collegeList.add(AnneLister);

		//set screen to title screen always upon startup:
		setScreen(new TitleScreen(this));
	}

	@Override
	public void dispose () {
		batch.dispose();
		//unsure what else to dispose
	}
}
