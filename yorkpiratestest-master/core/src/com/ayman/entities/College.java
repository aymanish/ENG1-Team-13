package com.ayman.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class College extends GameObject{

    public Circle AOE;
    public float radians = 0;
    public int HP, POINTS;
    public Sprite collegeSprite;
    public Rectangle boundRect;
    public ArrayList<Bullet> bullets;

    public College() {

        width = 174;
        height = 125;

        AOE = new Circle(x,y,400);
    }

    //college firing system, we will return a sprite bullet when called
    //public void shoot() {
        //if (!isCaptured() && playerInRange()) {
            //return bullet with correct direction etc
            //need to implement firing rate somehow possibly in game screen section or
            //as a condition in this if statement difference between time called
      //  }
    //}
    //checks if player is in range to be fired at
    //public boolean playerInRange() {
       // if (//distance check/overlap of hit box etc
       // ) {
       //     return true;
      //  }
       // else {
         //   return false;
       // }
  //  }
    //what happens to a college when said college is hit by a bullet from player
    public void collegeHit() {
        HP--;
        //players point gains in fireSuccess() method in PlayerShip class
    }
    //checks if college has been captured
    public boolean isCaptured() {
        if (HP == 0) {
            return true;
        }
        else {
            return false;
        }
    }
}
