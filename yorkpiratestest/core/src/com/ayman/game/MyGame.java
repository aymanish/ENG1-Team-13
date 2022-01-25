package com.ayman.game;
import com.ayman.entities.PlayerShip;
import com.ayman.screen.TitleScreen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MyGame extends Game {
	//initialize shared resources: camera, font, spritebatch and game objects
	public OrthographicCamera camera;
	public BitmapFont font;
	public SpriteBatch batch;
	public PlayerShip player, player2;

	//shared resources
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		//camera setup: viewport 0.5 x smaller that game screen:
		//viewport ratio should be same as game screen ratio to maintain aspect ratio/avoid distortion:
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		//initialize players
		player = new PlayerShip();
		player2 = new PlayerShip();
		player2.x = 600;
		player2.y = 300;

		//set screen to title screen always upon startup:
		setScreen(new TitleScreen(this));
	}

	@Override
	public void dispose () {
		batch.dispose();
		//unsure what else to dispose
	}
}
