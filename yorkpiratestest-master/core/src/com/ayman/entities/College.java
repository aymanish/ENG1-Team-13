package com.ayman.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class College extends GameObject{

    public Circle AOE;
    public float bulletradians, bulletX, bulletY; //= 3.1415f/2
    public int HP, POINTS;
    public Sprite collegeSprite;
    public Rectangle boundRect;
    public ArrayList<Bullet> bullets;

    //aymans addition:
    public boolean isAttacked;
    public boolean isAOE;
    private final int MAX_COLLEGE_BULLETS = 4;
    public boolean isCaptured;


    //double diffX, diffY = 600;





    public College() {

        this.bullets = new ArrayList<Bullet>();

        //bulletradians = (float) Math.atan2(diffY,diffX);





        width = 174;
        height = 125;

        //what value are these x and y?
        AOE = new Circle(x,y,250);

        isAttacked = false;
        isCaptured = false;
        isAOE = false;

    }

    //college firing system, we will return a sprite bullet when called
    public void shoot(float radians) {
        if (!isCaptured) {
            if (bullets.size() == MAX_COLLEGE_BULLETS) {return;}
            bullets.add(new Bullet(x + width/2f, y + height/2f, radians));
        }
    }
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
        if (!isCaptured) {
            HP--;
            isAttacked = false;
        }

        //players point gains in fireSuccess() method in PlayerShip class
    }
    /*
    //checks if college has been captured
    public boolean isCaptured() {
        if (HP == 0) {
            return true;
        }
        else {
            return false;
        }
    }

     */


}
