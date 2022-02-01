package com.ayman.screen;

import com.ayman.game.MyGame;
import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen extends ScreenAdapter {
    MyGame game;

    long startTime = TimeUtils.millis();

    public GameScreen(MyGame game_instance) {

        this.game = game_instance;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.6f, 1);

        //important camera stuff (not sure what this does tho)
        game.batch.setProjectionMatrix(game.camera.combined);

        //move player sprite based on input
        game.player.update(delta);
        game.updatePlayerBullets(delta);
        game.updateCollegeBullets(delta);


        //camera follows player after player sprite moves (player.update called)
        game.camera.position.set(game.player.x, game.player.y, 0);
        //ensures camera maintains aspect ratio of screen:
        game.camera.viewportWidth = Gdx.graphics.getWidth()/1.75f;
        game.camera.viewportHeight = Gdx.graphics.getHeight()/1.75f;
        game.camera.update();


        //begin rendering batch
        game.batch.begin();

        game.drawMap();

        game.drawColleges();
        game.drawCollegeNPC();

        game.drawPlayer();

        //if unlocked: draw barrier and remove player restriction:
        game.unlockBoss();

        //draw bullets
        game.drawPlayerBullets();
        game.drawCollegeBullets();


        //DRAW GAME STATS and UI:
        game.drawTutorial();
        //stat for each college
        game.drawCollegeStats();
        //objectives in top left:
        game.drawObjectives();
        //display tutorial:
        game.drawTutorial();
        //draw health-bar:
        game.drawPlayerHearts();

        game.batch.end();

        //GAME LOGIC:
        game.playerInRange();
        game.bulletCollegeHit();
        game.playerBulletHit();
        game.playerCollegeHit();

        //TASK CHECKLIST:
        if(game.isGameEnd()) {
            game.setScreen(new EndScreen(game));
        }

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
