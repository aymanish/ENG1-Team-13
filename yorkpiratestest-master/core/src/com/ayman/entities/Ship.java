package com.ayman.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Ship extends GameObject{

    public Sprite sprite;
    protected float rotationSpeed;
    protected  float maxSpeed;
    protected  float acceleration;
    protected float deceleration;
    public float angle;
    public ArrayList<Bullet> bullets;
    public boolean isAttacked = false;

    public void boundaries() {
        //ship x-axis boundaries:
        if(x <= 592) {dx = 0;} //dx = -3*dx/2
        if(x >= 2460) {dx = 0;}

        //ship y-axis boundaries:
        if(y <= 510) {dy = 0;}
        if(y >= 1380) {dy = 0;}
    }


}
