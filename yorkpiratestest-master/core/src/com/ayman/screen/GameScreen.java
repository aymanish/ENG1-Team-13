package com.ayman.screen;

import com.ayman.entities.AnneLister;
import com.ayman.game.MyGame;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import sun.security.mscapi.CPublicKey;

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

        //camera follows player after player sprite moves (player.update called)
        game.camera.position.set(game.player.x, game.player.y, 0);
        game.camera.update();

        //begin rendering batch
        game.batch.begin();

        //DRAW MAP
        game.map.map.draw(game.batch);

        //DRAW PLAYER:
        game.player.sprite.setPosition(game.player.x, game.player.y);
        game.player.sprite.draw(game.batch);

        //player2:
        //game.player2.sprite.setPosition(game.player2.x, game.player2.y);
        //game.player2.sprite.draw(game.batch);

        //college:
        game.AnneLister.collegeSprite.setPosition(game.AnneLister.x, game.AnneLister.y);
        game.AnneLister.collegeSprite.draw(game.batch);
        //game.batch.draw(game.AnneLister.AOE, game.AnneLister.AOE.x, game.AnneLister.AOE.y);

        //game.player.boundary.draw(game.batch);
        //player3:
        //game.player3.sprite.setPosition(game.player.x, game.player.y);
        //game.player3.sprite.draw(game.batch);

        game.font.draw(game.batch, "\nx: "+ game.player.x+"    y: "+game.player.y+" \n   shipAngle: "+game.player.angle+" \n shipR: "+game.player.radians +" \n DX: "+game.player.dx + " DY: "+game.player.dy , 700, 700); //Gdx.graphics.getWidth() * .15f
        game.font.draw(game.batch, "\nHP: " + game.player.HP + "\nP: " + game.player.POINTS, game.player.x+game.player.width, game.player.y+game.player.height);
        game.font.draw(game.batch, "\nHP: " + game.AnneLister.HP + "\nP: " + game.AnneLister.POINTS+ "\nC: " + game.AnneLister.isCaptured(), game.AnneLister.x+game.AnneLister.width, game.AnneLister.y+game.AnneLister.height);

        //draw bullets
        for (int i = 0; i < game.player.bullets.size(); i++) {
            game.player.bullets.get(i).bulletSprite.draw(game.batch);
        }
        //remove bullets
        //game.player.removeBullets();

        game.batch.end();

        //ship controls:

        //player2 controls:
        //game.player2.setLeft(Gdx.input.isKeyPressed(Input.Keys.A));
        //game.player2.setRight(Gdx.input.isKeyPressed(Input.Keys.D));
        //game.player2.setUp(Gdx.input.isKeyPressed(Input.Keys.W));

        //object overlap/ exit sequence
        //rectPlayer = game.player.getBoundingRectangle();
        //rectPlayer2 = game.player2.getBoundingRectangle();

        //PLAYER-PLAYER COLLISION (player ship class method):
        //boolean isPlayerOverlap = game.player.rectPlayer.overlaps(game.player2.rectPlayer);
        //if ((isPlayerOverlap)) {
        //    System.out.println("Player Hit");
        //    game.player.dx = -3*game.player.dx/2;
        //    game.player.dy = -3*game.player.dx/2;

        //    game.player2.dx = -3*game.player2.dx/2;
        //    game.player2.dy = -3*game.player2.dy/2;
        //}

        if (Intersector.overlaps(game.AnneLister.AOE, game.player.rectPlayer)) {
            System.out.println("AOE Hit");
        }
        //PLAYER-COLLEGE COLLISION
        if (game.player.rectPlayer.overlaps(game.AnneLister.boundRect)) {
            //game.player.x = 800/2;
            //game.player.y = 150;
            System.out.println("College Hit");
            //game.player2.y += (20*delta);
            //game.player.x = 800/2;
            //game.player.y = 150;
            game.player.dx = -3*game.player.dx/2;
            game.player.dy = -3*game.player.dy/2;

            if (game.AnneLister.isCaptured()) {
                game.player.HP -= 1;
                game.AnneLister.HP -= 1;
            }

            //game.setScreen(new EndScreen(game));
        }

        if (game.player.isDead()) {
            System.out.println("DEAD");
            game.setScreen(new EndScreen(game));
        }

        //AnneLister captured:
        if ((game.AnneLister.isCaptured())) {
            System.out.println("CAPTURED");
            game.AnneLister.collegeSprite = game.AnneLister.textureAtlas.createSprite("anneLister_island_captured");
        }


    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
