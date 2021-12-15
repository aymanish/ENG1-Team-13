package com.ayman.game;
import com.ayman.entities.PlayerShip;
import com.ayman.screen.TitleScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MyGame extends Game {
	public OrthographicCamera camera;
	public BitmapFont font;
	public   Texture dropImage, playerImage;
	public Rectangle drop;

	public TextureAtlas textureAtlas;
	public Sprite ship;

	public SpriteBatch batch;
	//public Texture playerSprite;

	public ShapeRenderer sr;
	public PlayerShip player, player1, player2, player3, player4, player5;

	//shared resources
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();

		textureAtlas = new TextureAtlas("sprites.txt");
		ship = textureAtlas.createSprite("ship_up");
		//camera setup:
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		playerImage = new Texture(Gdx.files.internal("ship_up.png"));

		player = new PlayerShip();
		sr = new ShapeRenderer();
/*
		player1 = new PlayerShip();
		player2 = new PlayerShip();
		player3 = new PlayerShip();
		player4 = new PlayerShip();
		player5 = new PlayerShip();

		player1.x = 410;
		player2.x = 420;
		player3.x = 405;
		player3.y = 160;
		player4.x = 415;
		player4.y = 160;
		player5.x = 410;
		player5.y = 170;

 */
		//player6.x = 460;
		//player1.rotationSpeed = (float) 0.0174533;



		setScreen(new TitleScreen(this));
	}

	@Override
	public void dispose () {
		batch.dispose();
		textureAtlas.dispose();
		//img.dispose();
	}
}
