package com.ayman.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class GameObject extends Sprite {
    //prolly dont need tile map as it required integer based coordinates
    // for game objects while ours uses floats for precise movement

    public float x;
    public float y;

    public float dx; //vectors
    public float dy;

    public float radians; //angle
    //public float speed;
    public float rotationSpeed;

    public int width;
    public int height;

    public float[] shapeX;
    public float[] shapeY;

    public boolean max;




    public void boundaries() {

        //ship x-axis boundaries:
        if(x <= 0) {dx = 0;}
        if(x >= 1999) {dx = 0;}

        //ship y-axis boundaries:
        if(y <= 0) {dy = 0;}
        if(y >= 999) {dy = 0;}
    }


}
