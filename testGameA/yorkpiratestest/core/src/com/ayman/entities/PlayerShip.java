package com.ayman.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class PlayerShip extends GameObject{

    private boolean left;
    private  boolean right;
    private  boolean up;

    private  float maxSpeed;
    private  float acceleration;
    private float deceleration;

    //ammo

    //constructor:
    public PlayerShip() {
        x = 800/2;
        y = 150;
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

    }


    public void setShape() {
        shapeX[0] = x + MathUtils.cos(radians) * 8;
        shapeY[0] = y + MathUtils.sin(radians) * 8;

        shapeX[1] = x + MathUtils.cos(radians - 4 * 3.1415f / 5) * 8;
        shapeY[1] = y + MathUtils.sin(radians - 4 * 3.1415f / 5) * 8;

        shapeX[2] = x + MathUtils.cos(radians + 3.1415f) * 5;
        shapeY[2] = y + MathUtils.sin(radians + 3.1415f) * 5;

        shapeX[3] = x + MathUtils.cos(radians + 4 * 3.1415f / 5) * 8;
        shapeY[3] = y + MathUtils.sin(radians + 4 * 3.1415f / 5) * 8;
    }

    public void setUp(boolean bool) {up = bool;}//set control booleans to true/false
    public void setLeft(boolean bool) {left = bool;}//set control booleans to true/false
    public void setRight(boolean bool) {right = bool;}//set control booleans to true/false

    //shoot method

    public void update(float dt) {

        //turn:
        if (left) {
            radians += rotationSpeed * dt;
        }
        else if (right) {
            radians -= rotationSpeed * dt;
        }

        //accelerating:
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

        //set shape:
        setShape();

        //boundaries:
        boundaries();
    }

    public void draw(ShapeRenderer sr) {

        sr.setColor(1,1,1,1);
        sr.begin(ShapeRenderer.ShapeType.Line);
        for (int i = 0, j = shapeX.length -1; i < shapeX.length; j = i++) {
            sr.line(shapeX[i], shapeY[i], shapeX[j], shapeY[j]);
        }
        sr.end();
    }
}
