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
        if(x <= 592) {dx = -3*dx/2;}
        if(x >= 2460) {dx = -3*dx/2;}

        //ship y-axis boundaries:
        if(y <= 510) {dy = -3*dy/2;}
        if(y >= 1380) {dy = -3*dy/2;}
    }


}
