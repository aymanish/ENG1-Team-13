package com.ayman.screen;

import com.ayman.game.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

public class EndScreen extends ScreenAdapter {

    MyGame game;

    public EndScreen(MyGame game_instance) {
        this.game = game_instance;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean keyDown(int keyCode) {

                if (keyCode == Input.Keys.ENTER) {
                    game.setScreen(new TitleScreen(game));
                    //RESET GAME:
                    game.create();
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.position.set(game.player.x, game.player.y, 0);
        //ensures camera maintains aspect ratio of screen:
        game.camera.viewportWidth = Gdx.graphics.getWidth()/1.75f;
        game.camera.viewportHeight = Gdx.graphics.getHeight()/1.75f;
        game.camera.update();

        game.batch.begin();
        //draw end screen:
        game.drawEndScreen();
        if (game.playerWin()) {
            game.font.draw(game.batch, "YOU WON! \n\nPOINTS: "+game.player.POINTS, game.player.x-100, game.player.y);//Gdx.graphics.getHeight() * .25f
        } else {
            game.font.draw(game.batch, "YOU LOST! \n\nPOINTS: "+game.player.POINTS, game.player.x-100, game.player.y);//Gdx.graphics.getHeight() * .25f
        }
        game.batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
