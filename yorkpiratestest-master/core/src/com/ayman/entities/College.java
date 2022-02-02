package com.ayman.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;

/**
 * Creates a basis for college objects to be made.
 *
 * HP: Health for the college building
 * POINTS: Points gained from the capture of the college
 * png_npc: Sets the sprite used by affiliated NPC boats
 * png_captured: Set the sprite used by the captured college
 * collegeSprite: Sets the sprite used by the college itself
 * boundRect: The collision box for the college building
 * AOE: collision box used to denote area where the college will open fire on an enemy boat
 * boundRect: The area used to detect impacts from projectiles, boats etc
 * bullets: Arraylist that holds all currently active projectiles belonging to the college
 * NPCs: ArrayList that holds all associated Ship NPC objects
 * MAX_COLLEGE_BULLETS: The amount of bullets fired in a single burst by the college
 * npcCount: amount of associated NPC boats
 *
 */

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

    /**
     * Constructor for College class.
     */

    public College() {

        this.bullets = new ArrayList<Bullet>();
        this.NPCs = new ArrayList<npcShip>();
        width = 174;
        height = 125;
        AOE = new Circle(x,y,300);

    }

    /**
     *
     * @param radians
     *      Direction in radians of the player position relative to the college.
     *
     */

    //college firing system, we will return a sprite bullet when called
    public void shoot(float radians) {
        if (!isCaptured()) {
            if (bullets.size() == MAX_COLLEGE_BULLETS) {return;}
            bullets.add(new Bullet(x + width/2f, y + height/2f, radians));
        }
    }

    /**
     * Calculates the direction of player from the college.
     * @param x_coord
     *      X coordinate of the player.
     * @param y_coord
     *      Y coordinate of the player.
     * @return
     *      Direction in radians of the player position relative to the college.
     */

    public float shootDirection(float x_coord, float y_coord) {
        Vector2 bulletDirection = new Vector2(x-x_coord, y-y_coord).nor();
        float bulletRadians = bulletDirection.angleRad() + 10213.2f;
        return bulletRadians;
    }

    /**
     * Reduces college health point by one when called.
     */

    public void collegeHit() {
            HP--;
    }

    /**
     * Checks if a college has zero health points.
     * @return
     *      True or false.
     */

    public boolean isCaptured() {
        return HP == 0;
    }
}
