package com.ayman.screen;

import com.ayman.entities.Bullet;
import com.ayman.game.MyGame;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import sun.security.mscapi.CPublicKey;

public class GameScreen extends ScreenAdapter {
    MyGame game;

    //public Rectangle rectPlayer2;



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
        game.player2.update(delta);
        //game.player3.update(delta);
        game.playerWeapon.update(delta);


        //game.player3.x = game.player.x;
        //game.player3.y = game.player.y;
        game.playerWeapon.x = game.player.x;
        game.playerWeapon.y = game.player.y;

        //set rectangles for collision:

        //rectPlayer2 = game.player2.sprite.getBoundingRectangle();

        ///*
        //UPDATE BULLETS:
        for (int i = 0; i < game.player.bullets.size(); i++) {
            game.player.bullets.get(i).update(delta);
            //System.out.println("BULLET UPDATE");
            if (game.player.bullets.get(i).rectBullet.overlaps(game.AnneLister.rectCollege)) {
                //game.player.bullets.get(i).BulletCollide = true;
                game.AnneLister.isAttacked = true;
                System.out.println("BULLET COLLIDE");
            }
            //game.AnneLister.isAttacked = false;
            //System.out.println("BULLET NOT COLLIDE");
            if (game.player.bullets.get(i).shouldRemove()) {
                game.player.bullets.remove(i);
                i--;
                System.out.println("BULLET REMOVED");
            }
        }

        //*/
        //rectPlayer2.x = game.player2.x;
        //rectPlayer2.y = game.player2.y;

        //camera follows player after player sprite moves (player.update called)
        game.camera.position.set(game.player.x, game.player.y, 0);
        game.camera.update();

        //begin rendering batch
        game.batch.begin();

        //DRAW MAP
        game.player.map.draw(game.batch);

        //DRAW AIMER:
        game.playerWeapon.aimer.setPosition(game.player.x - 20, game.player.y - 5);
        game.playerWeapon.aimer.draw(game.batch);

        //DRAW PLAYER:
        game.player.sprite.setPosition(game.player.x, game.player.y);
        game.player.sprite.draw(game.batch);

        //player2:
        game.player2.sprite.setPosition(game.player2.x, game.player2.y);
        game.player2.sprite.draw(game.batch);

        //college:
        game.AnneLister.collegeSprite.setPosition(game.AnneLister.x, game.AnneLister.y);
        game.AnneLister.collegeSprite.draw(game.batch);
        game.batch.draw(game.AnneLister.AOE, game.AnneLister.collegeAOE.x, game.AnneLister.collegeAOE.y);

        //game.player.boundary.draw(game.batch);
        //player3:
        //game.player3.sprite.setPosition(game.player.x, game.player.y);
        //game.player3.sprite.draw(game.batch);

        game.font.draw(game.batch, "\nx: "+ game.player.x+"    y: "+game.player.y+" \n   shipAngle: "+game.player.angle+" \n shipR: "+game.player.radians +" \n DX: "+game.player.dx + " DY: "+game.player.dy , 700, 700); //Gdx.graphics.getWidth() * .15f
        game.font.draw(game.batch, "\nHP: " + game.player.HP + "\nP: " + game.player.POINTS+ "\nAMMO: " + game.player.bullets.size(), game.player.x+game.player.width, game.player.y+game.player.height);
        game.font.draw(game.batch, "\nHP: " + game.AnneLister.HP + "\nP: " + game.AnneLister.POINTS+ "\nC: " + game.AnneLister.isCaptured, game.AnneLister.x+game.AnneLister.width, game.AnneLister.y+game.AnneLister.height);

        ///*
        //DRAW BULLETS:
        for (int i = 0; i < game.player.bullets.size(); i++) {
            //game.bullets.get(i).bullet;
            //game.player.bullets.get(i).bulletSprite.setPosition(800+10*i, 800+10*i);
            game.player.bullets.get(i).bulletSprite.draw(game.batch);
            System.out.println("BULLET DRAWN");
        }

         //*/

        game.batch.end();

        //ship controls:
        game.player.setLeft(Gdx.input.isKeyPressed(Input.Keys.LEFT));
        game.player.setRight(Gdx.input.isKeyPressed(Input.Keys.RIGHT));
        game.player.setUp(Gdx.input.isKeyPressed(Input.Keys.UP));



        ///*
        //bullet fire:
        if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            game.player.shoot();
            System.out.println("BULLET ADDED");
        }

         //*/
        //player2 controls:
        game.player2.setLeft(Gdx.input.isKeyPressed(Input.Keys.A));
        game.player2.setRight(Gdx.input.isKeyPressed(Input.Keys.D));
        game.player2.setUp(Gdx.input.isKeyPressed(Input.Keys.W));

        //player3 controls:
        //game.player3.setLeft(Gdx.input.isKeyPressed(Input.Keys.A));
        //game.player3.setRight(Gdx.input.isKeyPressed(Input.Keys.D));
        //game.player3.setUp(Gdx.input.isKeyPressed(Input.Keys.UP));

        game.playerWeapon.setLeft(Gdx.input.isKeyPressed(Input.Keys.Z));
        game.playerWeapon.setRight(Gdx.input.isKeyPressed(Input.Keys.X));

        //object overlap/ exit sequence
        //rectPlayer = game.player.getBoundingRectangle();
        //rectPlayer2 = game.player2.getBoundingRectangle();

        //PLAYER-PLAYER COLLISION:
        boolean isPlayerOverlap = game.player.rectPlayer.overlaps(game.player2.rectPlayer);
        if ((isPlayerOverlap)) {
            System.out.println("Player Hit");
            game.player.dx = -3*game.player.dx/2;
            game.player.dy = -3*game.player.dx/2;

            game.player2.dx = -3*game.player2.dx/2;
            game.player2.dy = -3*game.player2.dy/2;
        }
        boolean isAOEOverlap = game.player.rectPlayer.overlaps(game.AnneLister.collegeAOE);
        if ((isAOEOverlap)) {
            System.out.println("AOE Hit");
        }

        /*
        //PLAYER-COLLEGE COLLISION
        boolean isOverlap = game.player.rectPlayer.overlaps(game.AnneLister.rectCollege);
        if ((isOverlap)) {
            //game.player.x = 800/2;
            //game.player.y = 150;
            System.out.println("College Hit");
            //game.player2.y += (20*delta);
            //game.player.x = 800/2;
            //game.player.y = 150;
            game.player.dx = -3*game.player.dx/2;
            game.player.dy = -3*game.player.dy/2;

            if ((game.AnneLister.isCaptured == false)) {
                game.player.HP -= 1;
                game.AnneLister.HP -= 1;
            }


            //game.setScreen(new EndScreen(game));
        }*/
        //BULLET-COLLEGE COLLISION
        //boolean isBulletOverlap = (game.player.bullets.get(i).rectBullet.overlaps(game.AnneLister.rectCollege));
        if (game.AnneLister.isAttacked) {
            if ((game.AnneLister.isCaptured == false)) {
                game.AnneLister.HP -= 1;
                System.out.println("COLLEGE ATTACKED");
                game.AnneLister.isAttacked = false;
            }
        }







        //PLAYER-COLLEGE COLLISION
        boolean isOverlap = game.player.rectPlayer.overlaps(game.AnneLister.rectCollege);
        if ((isOverlap)) {
            //game.player.x = 800/2;
            //game.player.y = 150;
            System.out.println("College Hit");
            //game.player2.y += (20*delta);
            //game.player.x = 800/2;
            //game.player.y = 150;
            game.player.dx = -3*game.player.dx/2;
            game.player.dy = -3*game.player.dy/2;

        }





        //PLAYER DEATH:
        if ((game.player.HP == 0)) {

            System.out.println("DEAD");
            game.player.x= 600;
            game.player.y = 600;
            game.player.HP = 3;
            //game.setScreen(new EndScreen(game));
            //game.setScreen(new EndScreen(game));
        }

        //PLAYER-COLLEGE CAPTURE:
        if (((game.AnneLister.isCaptured == false) && game.AnneLister.HP == 0)) {

            game.AnneLister.isCaptured = true;
            System.out.println("CAPTURED");
            game.AnneLister.collegeSprite = game.AnneLister.textureAtlas.createSprite("anneLister_island_captured");
            game.AnneLister.HP = 1000;
            game.player.POINTS += game.AnneLister.POINTS;
        }
    }
    @Override
    public void show(){
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.BACKSPACE) {
                    game.setScreen(new EndScreen(game));
                }
                return true;
            }
        });
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
