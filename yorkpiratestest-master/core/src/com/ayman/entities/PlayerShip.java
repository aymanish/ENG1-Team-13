package com.ayman.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;

public class PlayerShip extends Ship{

    public int HP = 3;
    public int POINTS = 0;
    public Sprite playerSprite, heart3, heart2, heart1;
    public Rectangle rectPlayer;
    private final int MAX_BULLETS = 5;

    public int captures = 0;

    public boolean unlocked;
    public Sprite heartsSprite;

    public PlayerShip(ArrayList<Bullet> bullets) {

        this.bullets = bullets;

        sprite = textureAtlas.createSprite("ship_up");
        heart3 = textureAtlas.createSprite("heart3");
        heart2 = textureAtlas.createSprite("heart2");
        heart1 = textureAtlas.createSprite("heart1");

        playerSprite = this.sprite;
        rectPlayer = playerSprite.getBoundingRectangle();

        x = 600; //800/2
        y = 600; //150
        dx = 0;
        dy = 0;
        width = 64;
        height = 64;

        maxSpeed = 300;
        acceleration = 200;
        deceleration = 100;

        radians = (3.1415f / 2); // - 1.5708f
        rotationSpeed = 3;

        heartsSprite = heart3;

        heartsSprite.setPosition(x-10, y-20);

        unlocked = false;

    }

    //player ship control based on input bools:
    public void update(float dt) {

        //turn:
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            radians += rotationSpeed * dt;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            radians -= rotationSpeed * dt;
        }

        //rotate sprite based on rads:
        angle = (MathUtils.radiansToDegrees * radians) -90; //-90
        if(angle != 0){
            angle = angle % 360;
        }
        sprite.setRotation(angle);

        //accelerating/movement:
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            dx += MathUtils.cos(radians)*acceleration*dt;
            dy += MathUtils.sin(radians)*acceleration*dt;
        }

        //deceleration:
        float vec = (float) Math.sqrt(dx*dx+dy*dy);
        if (vec > 0) {
            dx -= (dx/vec)*deceleration*dt;
            dy -= (dy/vec)*deceleration*dt;
        }

        //speed limiter
        if (vec > maxSpeed) {
            dx = (dx/vec)*maxSpeed;
            dy = (dy/vec)*maxSpeed;
        }

        //update position
        x += dx*dt;
        y += dy*dt;

        //set ship position as well otherwise sprite won't move
        sprite.setX(x);
        sprite.setY(y);

        //set hearts position
        heartsSprite.setX(x-10);
        heartsSprite.setY(y-20);

        //set bounding rectangle position:
        rectPlayer.x = x;
        rectPlayer.y = y;

        //bullet fire
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            shoot();
        }

        updateHearts();

        //ayman's new code: change player movement restrictions based
        // on whether boss is unlocked:
        if (!unlocked) {
            barrier();
        } else {
            boundaries();
        }
    }

    //try changing the shoot method():
    public void shoot() {
        if (bullets.size() < MAX_BULLETS) {
            bullets.add(new Bullet(x, y, this.radians));
        }
    }

    public void playerHit() {
        HP--;
    }

    public boolean isDead() {
        return HP == 0;
    }

    public void updateHearts() {
        if (HP == 2) {
            heartsSprite = heart2;
        } else if (HP == 1) {
            heartsSprite = heart1;
        }
    }

    public void barrier() {
        //ship x-axis boundaries + barrier collision:
        if(x <= 592) {dx = 0;}
        if(x >= 1452) {dx = -3*dx/2;}
        //ship y-axis boundaries:
        if(y <= 510) {dy = 0;}
        if(y >= 1380) {dy = 0;}
    }
}
