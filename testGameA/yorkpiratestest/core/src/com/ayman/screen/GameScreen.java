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

public class GameScreen extends ScreenAdapter {
    MyGame game;
    public float radians = 3.1415f;
    public float vec1 =0;
    public float rotationSpeed = 2;
    public float maxSpeed = 250;
    public float acceleration = 200;
    public float deceleration = 160;
    public float dx;
    public float dy;
    public float x = 400-10;
    public float y = 150-10;


    public GameScreen(MyGame game_instance) {
        this.game = game_instance;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.6f, 1);
        //camera.position.set(bucket.x, bucket.y, 0);
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);

        //UNCOMMENT TO USE THE PLAYER_SHIP INSTANCE UNDER GAMEOBJECT
        //game.player.update(delta);



        //UNCOMMENT TO USE THE PLAYER_SHIP INSTANCE UNDER GAMEOBJECT
        //game.player.draw(game.sr);




        game.batch.begin();

        game.ship.setPosition(x, y);
        game.ship.draw(game.batch);
        //float xInput = Gdx.input.getX();
        //float yInput = (Gdx.graphics.getHeight() - Gdx.input.getY());


        boolean left = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean right = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean up = Gdx.input.isKeyPressed(Input.Keys.UP);

        if (left) {
            radians += rotationSpeed * delta;
        }
        else if (right) {
            radians -= rotationSpeed * delta;
        }
        if (up) {
            dx += MathUtils.cos(radians)*acceleration*delta;
            dy += MathUtils.sin(radians)*acceleration*delta;
        }
        else {
            //deceleration:
            float vec = (float) Math.sqrt(dx*dx+dy*dy);
            vec1 = vec;
            if (vec > 0) {
                dx -= (dx/vec)*deceleration*delta;
                dy -= (dy/vec)*deceleration*delta;
            }
            if (vec > maxSpeed) {
                dx = (dx/vec)*maxSpeed;
                dy = (dy/vec)*maxSpeed;
            }

        }
        //set position:
        x += dx*delta;
        y += dy*delta;

        //ship x-axis boundaries:
        if(x <= 0) {dx = 0;}
        if(x >= 800) {dx = 0;}
        //ship y-axis boundaries:
        if(y <= 0) {dy = 0;}
        if(y >= 400) {dy = 0;}

        game.ship.setX(x);
        game.ship.setY(y);



    //convert rad to angles and update rotation:
        float angle = (MathUtils.radiansToDegrees * radians) - 90;

        if(angle < 0){
            angle += 360;
        }

        game.ship.setRotation(angle);
        //game.ship.setPosition(x, y);



        //game.batch.draw(game.playerImage, game.player.x - 10, game.player.y - 10);
        //game.batch.draw(game.playerImage, 390, 140);
        //game.ship.draw(game.batch);
        //game.batch.draw(game.dropImage, game.drop.x, game.drop.y);
        game.font.draw(game.batch, "speed: "+vec1+"\nx: "+x+"    y: "+y+" \n   shipAngle: "+angle+" \n shipR: "+radians+ "  R:"+game.player.radians , Gdx.graphics.getWidth() * .15f, Gdx.graphics.getHeight() * .25f);
        game.batch.end();

        //ship controls:

        game.player.setLeft(Gdx.input.isKeyPressed(Input.Keys.LEFT));
        game.player.setRight(Gdx.input.isKeyPressed(Input.Keys.RIGHT));
        game.player.setUp(Gdx.input.isKeyPressed(Input.Keys.UP));
/*
        game.player1.setLeft(Gdx.input.isKeyPressed(Input.Keys.LEFT));
        game.player1.setRight(Gdx.input.isKeyPressed(Input.Keys.RIGHT));
        game.player1.setUp(Gdx.input.isKeyPressed(Input.Keys.UP));

        game.player2.setLeft(Gdx.input.isKeyPressed(Input.Keys.LEFT));
        game.player2.setRight(Gdx.input.isKeyPressed(Input.Keys.RIGHT));
        game.player2.setUp(Gdx.input.isKeyPressed(Input.Keys.UP));

        game.player3.setLeft(Gdx.input.isKeyPressed(Input.Keys.LEFT));
        game.player3.setRight(Gdx.input.isKeyPressed(Input.Keys.RIGHT));
        game.player3.setUp(Gdx.input.isKeyPressed(Input.Keys.UP));

        game.player4.setLeft(Gdx.input.isKeyPressed(Input.Keys.LEFT));
        game.player4.setRight(Gdx.input.isKeyPressed(Input.Keys.RIGHT));
        game.player4.setUp(Gdx.input.isKeyPressed(Input.Keys.UP));

        game.player5.setLeft(Gdx.input.isKeyPressed(Input.Keys.LEFT));
        game.player5.setRight(Gdx.input.isKeyPressed(Input.Keys.RIGHT));
        game.player5.setUp(Gdx.input.isKeyPressed(Input.Keys.UP));
        //rotate left:
        //if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            //game.player.rotate(10*Gdx.graphics.getDeltaTime());

            //game.ship.update(Gdx.graphics.getDeltaTime());
        //}



        //bucket controls:


        //ship x-axis boundaries:
        if(game.bucket.x < 0) game.bucket.x = 0;
        if(game.bucket.x > 800 - 64) game.bucket.x = 800 - 64;

        //ship left movement:
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            game.bucketImage = new Texture(Gdx.files.internal("ship_left.png"));
            game.bucket.x -= 200 * Gdx.graphics.getDeltaTime();
        }

        //ship right movement:
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            game.bucketImage = new Texture(Gdx.files.internal("ship_right.png"));
            game.bucket.x += 200 * Gdx.graphics.getDeltaTime();
        }

        //ship y-axis boundaries:
        if(game.bucket.y < 0) game.bucket.y = 0;
        if(game.bucket.y > 480 - 64) game.bucket.y = 480 - 64;

        //ship up movement:
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            game.bucketImage = new Texture(Gdx.files.internal("ship_up.png"));
            game.bucket.y += 200 * Gdx.graphics.getDeltaTime();
        }
        //ship up-left movement:
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            game.bucketImage = new Texture(Gdx.files.internal("ship_up_left.png"));
            //bucket.y += 200 * Gdx.graphics.getDeltaTime();
            //bucket.x -= 200 * Gdx.graphics.getDeltaTime();
        }
        //ship up-right movement:
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            game.bucketImage = new Texture(Gdx.files.internal("ship_up_right.png"));
            //bucket.y += 200 * Gdx.graphics.getDeltaTime();
            //bucket.x += 200 * Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            game.bucketImage = new Texture(Gdx.files.internal("ship_down.png"));
            game.bucket.y -= 200 * Gdx.graphics.getDeltaTime();
        }
        //ship down-left movement:
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            game.bucketImage = new Texture(Gdx.files.internal("ship_down_left.png"));
            //bucket.y -= 200 * Gdx.graphics.getDeltaTime();
            //bucket.x -= 200 * Gdx.graphics.getDeltaTime();
        }
        //ship down-right movement:
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            game.bucketImage = new Texture(Gdx.files.internal("ship_down_right.png"));
            //bucket.y -= 200 * Gdx.graphics.getDeltaTime();
            //bucket.x += 200 * Gdx.graphics.getDeltaTime();
        }

        if (game.player.overlaps(game.drop)) {
            game.ship.x = 800/2;
            game.ship.y = 152;
            game.setScreen(new EndScreen(game));
        }
*/
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
