package com.ayman.screen;

import com.ayman.game.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.ScreenUtils;

public class TitleScreen extends ScreenAdapter{

    MyGame game;

    public  TitleScreen(MyGame game_instance) {
        this.game = game_instance;
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {
                    game.setScreen(new GameScreen(game));
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        game.camera.position.set(game.player.x, game.player.y, 0);
        //ensures camera maintains aspect ratio of screen:
        game.camera.viewportWidth = Gdx.graphics.getWidth()/1.75f;
        game.camera.viewportHeight = Gdx.graphics.getHeight()/1.75f;
        game.camera.update();

        game.batch.begin();
        //draw title screen:
        game.drawTitleScreen();
        //game.font.draw(game.batch, "WELCOME TO YORK PIRATES! \n\nCAPTURE ALL COLLEGES TO WIN! \n\nPRESS SPACE TO PLAY", game.player.x, game.player.y);
        game.batch.end();
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }

}
