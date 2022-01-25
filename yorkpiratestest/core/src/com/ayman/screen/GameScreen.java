package com.ayman.screen;

import com.ayman.game.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import sun.security.mscapi.CPublicKey;

public class GameScreen extends ScreenAdapter {
    MyGame game;
    public Rectangle rectPlayer;
    public Rectangle rectPlayer2;



    public GameScreen(MyGame game_instance) {
        this.game = game_instance;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.6f, 1);

        rectPlayer = game.player.getBoundingRectangle();
        rectPlayer2 = game.player2.getBoundingRectangle();

        //important camera stuff (not sure what this does tho)
        game.batch.setProjectionMatrix(game.camera.combined);

        //move player sprite based on input
        game.player.update(delta);
        game.player2.update(delta);
        //camera follows player after player sprite moves (player.update called)
        game.camera.position.set(game.player.x, game.player.y, 0);
        game.camera.update();

        //begin rendering batch
        game.batch.begin();

        //player:
        game.player.sprite.setPosition(game.player.x, game.player.y);
        game.player.map.draw(game.batch);
        game.player.sprite.draw(game.batch);
        //player2:
        game.player2.sprite.setPosition(game.player2.x, game.player2.y);
        game.player2.sprite.draw(game.batch);

        game.font.draw(game.batch, "\nx: "+ game.player.x+"    y: "+game.player.y+" \n   shipAngle: "+game.player.angle+" \n shipR: "+game.player.radians +" \n DX: "+game.player.dx + " DY: "+game.player.dy , Gdx.graphics.getWidth() * .15f, Gdx.graphics.getHeight() * .25f);
        game.batch.end();

        //ship controls:
        game.player.setLeft(Gdx.input.isKeyPressed(Input.Keys.LEFT));
        game.player.setRight(Gdx.input.isKeyPressed(Input.Keys.RIGHT));
        game.player.setUp(Gdx.input.isKeyPressed(Input.Keys.UP));

        //player2 controls:
        game.player2.setLeft(Gdx.input.isKeyPressed(Input.Keys.A));
        game.player2.setRight(Gdx.input.isKeyPressed(Input.Keys.D));
        game.player2.setUp(Gdx.input.isKeyPressed(Input.Keys.W));


        //object overlap/ exit sequence
        //rectPlayer = game.player.getBoundingRectangle();
        //rectPlayer2 = game.player2.getBoundingRectangle();

        boolean isOverlap = rectPlayer.overlaps(rectPlayer2);

        if ((isOverlap)) {
            game.player.x = 800/2;
            game.player.y = 150;
            game.setScreen(new EndScreen(game));
        }
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
