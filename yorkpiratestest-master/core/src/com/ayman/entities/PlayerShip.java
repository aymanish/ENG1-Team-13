package com.ayman.entities;

import com.ayman.game.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class PlayerShip extends Ship{

    public int HP = 3;
    public int POINTS = 0;
    public Sprite playerSprite;
    public Rectangle rectPlayer;

    public PlayerShip() {

        sprite = textureAtlas.createSprite("ship_up");

        playerSprite = this.sprite;
        rectPlayer = playerSprite.getBoundingRectangle();

        x = 600; //800/2
        y = 600; //150
        dx = 0;
        dy = 0;
        width = 64;
        height = 64;

        maxSpeed = 300;
        max = false;
        acceleration = 200;
        deceleration = 100;

        radians = 3.1415f / 2;
        rotationSpeed = 3;
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
        angle = (MathUtils.radiansToDegrees * radians) - 90;
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
            max = true;
            dx -= (dx/vec)*deceleration*dt;
            dy -= (dy/vec)*deceleration*dt;
        }
        if (vec > maxSpeed) {
            dx = (dx/vec)*maxSpeed;
            dy = (dy/vec)*maxSpeed;
        }

        //set position:
        x += dx*dt;
        y += dy*dt;

        //set sprite position as well otherwise sprite wont move
        sprite.setX(x);
        sprite.setY(y);

        //set rectangle position:
        rectPlayer.x = this.x;
        rectPlayer.y = this.y;

        //boundaries:
        boundaries();
    }

    public boolean isDead() {
        if(HP == 0) {
            return true;
        }
        else {
            return false;
        }
    }
}
