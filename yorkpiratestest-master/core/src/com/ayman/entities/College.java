package com.ayman.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class College extends GameObject{

    public Circle AOE;
    public int HP, POINTS;
    public Sprite collegeSprite;
    public Sprite capturedSprite;
    public Rectangle boundRect;
    public ArrayList<Bullet> bullets;
    public ArrayList<npcShip> NPCs;
    public String png_npc;
    public String png_captured = "";
    private final int MAX_COLLEGE_BULLETS = 1;

    public int npcCount = 3;
    //call generateNPC() function:


    public College() {

        this.bullets = new ArrayList<Bullet>();
        this.NPCs = new ArrayList<npcShip>();

        width = 174;
        height = 125;

        AOE = new Circle(x,y,300);

    }

    //college firing system, we will return a sprite bullet when called
    public void shoot(float radians) {
        if (!isCaptured()) {
            if (bullets.size() == MAX_COLLEGE_BULLETS) {return;}
            bullets.add(new Bullet(x + width/2f, y + height/2f, radians));
        }
    }

    public float shootDirection(float x_coord, float y_coord) {
        Vector2 bulletDirection = new Vector2(x-x_coord, y-y_coord).nor();
        float bulletRadians = bulletDirection.angleRad() + 10213.2f;
        return bulletRadians;
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
        if (!isCaptured()) {
            HP--;
        }

        //players point gains in fireSuccess() method in PlayerShip class
    }

    public boolean isCaptured() {
        return HP == 0;
    }
}
