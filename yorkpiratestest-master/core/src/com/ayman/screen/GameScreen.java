package com.ayman.screen;

import com.ayman.entities.AnneLister;
import com.ayman.entities.Bullet;
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
    //Bullet bul = new Bullet(600, 600, 0);
    public Vector2 bulletDirection;
    public float bulletRadians;


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




        //ayman's code:
        //UPDATE PLAYER BULLETS:
        for (int i = 0; i < game.player.bullets.size(); i++) {

            game.player.bullets.get(i).update(delta);
            if (game.player.bullets.get(i).rectBullet.overlaps(game.AnneLister.boundRect)) {
                //game.player.bullets.get(i).BulletCollide = true;
                game.AnneLister.isAttacked = true;
                System.out.println("BULLET COLLIDE");
            }

            if ((game.player.bullets.get(i).shouldRemove())||(game.AnneLister.isAttacked)) {
                game.player.bullets.remove(i);
                i--;
                System.out.println("BULLET REMOVED");
            }
        }

        //ayman's code:
        //UPDATE COLLEGE BULLETS:
        for (int i = 0; i < game.AnneLister.bullets.size(); i++) {

            game.AnneLister.bullets.get(i).update(delta);

            if (game.AnneLister.bullets.get(i).rectBullet.overlaps(game.player.rectPlayer)) {
                //game.player.bullets.get(i).BulletCollide = true;
                game.player.isAttacked = true;
                System.out.println("PLAYER BULLET COLLIDE");
            }

            if ((game.AnneLister.bullets.get(i).shouldRemove())||(game.player.isAttacked)) {
                game.AnneLister.bullets.remove(i);
                i--;
                System.out.println("PLAYER BULLET REMOVED");
            }
        }

        //camera follows player after player sprite moves (player.update called)
        game.camera.position.set(game.player.x, game.player.y, 0);
        //ensures camera maintains aspect ratio of screen:
        game.camera.viewportWidth = Gdx.graphics.getWidth();
        game.camera.viewportHeight = Gdx.graphics.getHeight();
        game.camera.update();

        //begin rendering batch
        game.batch.begin();

        //DRAW MAP
        game.map.map.draw(game.batch);

        //DRAW PLAYER:
        game.player.sprite.draw(game.batch);

        //player2:
        //game.player2.sprite.setPosition(game.player2.x, game.player2.y);
        //game.player2.sprite.draw(game.batch);

        //college:
        //Line below is important to render any change in college sprite:
        game.AnneLister.collegeSprite.setPosition(game.AnneLister.x, game.AnneLister.y);
        game.AnneLister.collegeSprite.draw(game.batch);
        //game.batch.draw(game.AnneLister.AOE, game.AnneLister.collegeAOE.x, game.AnneLister.collegeAOE.y);


        //GAME STATS:
        game.font.draw(game.batch, "\nx: "+ game.player.x+"    y: "+game.player.y+" \n   shipAngle: "+game.player.angle+" \n shipR: "+game.player.radians +" \n DX: "+game.player.dx + " DY: "+game.player.dy , 700, 700);
        game.font.draw(game.batch, "\nHP: " + game.player.HP + "\nP: " + game.player.POINTS+ "\nAMMO: " + game.player.bullets.size() + "\nSCREENX: " + Gdx.graphics.getWidth(), game.player.x+game.player.width, game.player.y+game.player.height);
        game.font.draw(game.batch, "\nHP: " + game.AnneLister.HP + "\nP: " + game.AnneLister.POINTS+ "\nC: " + game.AnneLister.isCaptured+ "\nAOE: " + game.AnneLister.isAOE+ "\nAMMO: " + game.AnneLister.bullets.size()+"\nAOEXY: " + game.AnneLister.AOE.x+ " " + game.AnneLister.AOE.y+"\nXY: " + game.AnneLister.x+ " " + game.AnneLister.y+"\nangle: " + game.AnneLister.bulletradians, game.AnneLister.x+game.AnneLister.width, game.AnneLister.y+game.AnneLister.height);

        //draw bullets
        for (int i = 0; i < game.player.bullets.size(); i++) {
            game.player.bullets.get(i).bulletSprite.draw(game.batch);
            System.out.println("BULLET DRAWN");
        }

        //DRAW COLLEGE BULLETS:
        for (int i = 0; i < game.AnneLister.bullets.size(); i++) {
            game.AnneLister.bullets.get(i).bulletSprite.draw(game.batch);
            System.out.println("COLLEGE BULLET DRAWN");
        }

        //remove bullets
        //game.player.removeBullets();

        game.batch.end();

        //ship controls:

        /*
        //PLAYER-PLAYER COLLISION (player ship class method):
        if (game.player.rectPlayer.overlaps(game.player2.rectPlayer)) {
            System.out.println("Player Hit");
            game.player.dx = -3*game.player.dx/2;
            game.player.dy = -3*game.player.dx/2;

            game.player2.dx = -3*game.player2.dx/2;
            game.player2.dy = -3*game.player2.dy/2;
        }
        */

        //update college bullet angle before PLAYER IN RANGE below:
        bulletDirection = new Vector2(game.AnneLister.x-game.player.x, game.AnneLister.y-game.player.y).nor();
        bulletRadians = bulletDirection.angleRad() + 10213.2f;

        //PLAYER IN RANGE:
        if (Intersector.overlaps(game.AnneLister.AOE, game.player.rectPlayer)) {
            game.AnneLister.isAOE = true;
            System.out.println("AOE Hit");
            if (!(game.AnneLister.isCaptured)) {
                game.AnneLister.shoot(bulletRadians);
            }
        } else {
            game.AnneLister.isAOE = false;
        }

        //BULLET COLLEGE COLLISION:
        if (game.AnneLister.isAttacked) {
            if (!(game.AnneLister.isCaptured)) {
                game.AnneLister.collegeHit();
            }
            game.AnneLister.isAttacked = false;
        }

        //PLAYER BULLET COLLISION:
        if (game.player.isAttacked) {
            game.player.playerHit();
        }

        //PLAYER-COLLEGE COLLISION
        if (game.player.rectPlayer.overlaps(game.AnneLister.boundRect)) {
            System.out.println("College Hit");
            //collision:
            game.player.dx = -3*game.player.dx/2;
            game.player.dy = -3*game.player.dy/2;
        }

        //PLAYER DEAD:
        if (game.player.isDead()) {
            System.out.println("DEAD");
            //called game function to restart game at end screen)
            //game.player.x= 600;
            //game.player.y = 600;
            //game.player.HP = 10;
            //game.player.dx = 0;
            //game.player.dy = 0;
            game.setScreen(new EndScreen(game));
        }

        //COLLEGE CAPTURED:
        //AnneLister captured:
        if ((!game.AnneLister.isCaptured) && game.AnneLister.HP == 0) {
            System.out.println("CAPTURED");
            game.AnneLister.isCaptured = true;
            //this doesnt render in game screen for some reason???
            game.AnneLister.collegeSprite = game.AnneLister.textureAtlas.createSprite("anneLister_island_captured");
            game.AnneLister.HP = 10;
            game.player.POINTS += game.AnneLister.POINTS;
        }


    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
