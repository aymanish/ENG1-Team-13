package com.ayman.screen;

import com.ayman.entities.AnneLister;
import com.ayman.entities.Bullet;
import com.ayman.entities.College;
import com.ayman.entities.npcShip;
import com.ayman.game.MyGame;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen extends ScreenAdapter {
    MyGame game;

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

        /*
        //NPCs:
        for (int i = 0; i <game.AnneLister.npcCount; i++) {
            game.AnneLister.NPCs.get(i).npcSprite.setPosition(game.AnneLister.NPCs.get(i).x, game.AnneLister.NPCs.get(i).y);
            game.AnneLister.NPCs.get(i).npcSprite.draw(game.batch);
            game.Constantine.NPCs.get(i).npcSprite.setPosition(game.Constantine.NPCs.get(i).x, game.Constantine.NPCs.get(i).y);
            game.Constantine.NPCs.get(i).npcSprite.draw(game.batch);
            game.Goodricke.NPCs.get(i).npcSprite.setPosition(game.Goodricke.NPCs.get(i).x, game.Goodricke.NPCs.get(i).y);
            game.Goodricke.NPCs.get(i).npcSprite.draw(game.batch);
        }

         */


        //DRAW GAME STATS and UI:

        //needs to be defined under MyGame near the end after testing complete:
        game.font.draw(game.batch, "\nx: "+ game.player.x+"    y: "+game.player.y+" \n   shipAngle: "+game.player.angle+" \n shipR: "+game.player.radians +" \n DX: "+game.player.dx + " DY: "+game.player.dy , 700, 700);
        game.font.draw(game.batch, "\nHP: " + game.player.HP + "\nP: " + game.player.POINTS+ "\nAMMO: " + game.player.bullets.size() + "\nSCREENX: " + Gdx.graphics.getWidth(), game.player.x+game.player.width, game.player.y+game.player.height);
        //stat for each college
        game.drawCollegeStats();
        //objectives in top left:
        game.drawObjectives();
        //draw health-bar:
        game.drawPlayerHearts();


        game.batch.end();


        //GAME LOGIC:
        game.playerInRange();
        game.bulletCollegeHit();
        game.playerBulletHit();
        game.playerCollegeHit();

        //game.collegeCaptured();

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
