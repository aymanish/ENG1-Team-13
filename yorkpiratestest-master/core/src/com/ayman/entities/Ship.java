package com.ayman.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import java.util.ArrayList;

/**
 * Sets the basic information about any ship in the game, NPC or player controlled
 *
 * sprite: The sprite used for the boats visuals
 * rotationSpeed: How quickly the boat is capable of turning
 * maxSpeed: The fastest the boat can move in a given direction in pixels per frame
 * acceleration: speed of the movement when moving in a given direction
 * deceleration: speed of the movement when not moving in a given direction and slowing down
 * angle: the current direction the front of the boat is pointing
 * bullets: list of projectiles belonging to the boat currently on the screen
 *
 */

public class Ship extends GameObject{

    public Sprite sprite;
    protected float rotationSpeed;
    protected  float maxSpeed;
    protected  float acceleration;
    protected float deceleration;
    public float angle;
    public ArrayList<Bullet> bullets;

    /**
     * Sets the boundaries for the Ship.
     */

    public void boundaries() {
        //ship x-axis boundaries:
        if(x <= 592) {dx = 0;}
        if(x >= 2460) {dx = 0;}

        //ship y-axis boundaries:
        if(y <= 510) {dy = 0;}
        if(y >= 1380) {dy = 0;}
    }


}
