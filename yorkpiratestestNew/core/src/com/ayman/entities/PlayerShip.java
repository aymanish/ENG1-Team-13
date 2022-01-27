package com.ayman.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class PlayerShip extends GameObject{
    //prolly dont need tile map as it required integer based coordinates
    // for game objects while ours uses floats for precise movement
    private boolean left;
    private  boolean right;
    private  boolean up;

    private  float maxSpeed;
    private  float acceleration;
    private float deceleration;

    public float angle;
    public Sprite sprite, map, boundary;
    public TextureAtlas textureAtlas;


    private ArrayList<Bullet> bullets;
    private final int MAX_BULLETS = 4;

    //ammo

    //constructor:
    public PlayerShip(ArrayList<Bullet> bullets) {

        this.bullets = bullets;

        x = 800/2;
        y = 430; //150
        width = 64;
        height = 64;

        maxSpeed = 300;
        max = false;
        acceleration = 200;
        deceleration = 100;

        shapeX = new float[4];
        shapeY = new float[4];

        radians = 3.1415f / 2;
        rotationSpeed = 3;

        textureAtlas = new TextureAtlas("sprites.txt");
        sprite = textureAtlas.createSprite("ship_up");
        map = textureAtlas.createSprite("map");
        boundary = textureAtlas.createSprite("boundaries");


    }

    //booleans for input checks in game screen.java
    public void setUp(boolean bool) {up = bool;}//set control booleans to true/false
    public void setLeft(boolean bool) {left = bool;}//set control booleans to true/false
    public void setRight(boolean bool) {right = bool;}//set control booleans to true/false



    //player ship control based on input bools:
    public void update(float dt) {

        //turn:
        if (left) {
            radians += rotationSpeed * dt;
        }
        else if (right) {
            radians -= rotationSpeed * dt;
        }

        //rotate sprite based on rads:
        angle = (MathUtils.radiansToDegrees * radians) - 90;
        if(angle != 0){
            angle = angle % 360;
        }
        sprite.setRotation(angle);

        //accelerating/movement:
        if (up) {
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

        //boundaries:
        boundaries();
    }

    //shoot method placeholder for now
    public void shoot() {
        if (bullets.size() == MAX_BULLETS) return;
        bullets.add(new Bullet(x, y, radians));
    }
}
