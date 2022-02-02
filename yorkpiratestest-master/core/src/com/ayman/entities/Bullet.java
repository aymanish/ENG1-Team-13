package com.ayman.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

/**
 * Class to represent a bullet object.
 *
 * timeLimit: the maximum time a bullet can be alive for
 * timer: the current length of time a bullet has been alive for
 * bulletSprite: Sprite representing a bullet
 * rectBullet: the bounding rectangle of a bullet
 * bulletHit: boolean value for whether a bullet has collied with anything
 */

public class Bullet extends GameObject {

    private float timeLimit;
    private float timer;
    public Sprite bulletSprite;
    public Rectangle rectBullet;
    public boolean bulletHit;

    /**
     * Constructor for bullet class.
     * @param x
     *      X coordinate of starting position.
     * @param y
     *      Y coordinate of starting position.
     * @param radians
     *      Degree in radians of trajectory of bullet
     */

    public Bullet(float x, float y, float radians) {

        bulletSprite = textureAtlas.createSprite("bullet");
        rectBullet = bulletSprite.getBoundingRectangle();
        width = height = 18;
        this.x = x;
        this.y = y;
        this.radians = radians;
        float speed = 350;
        dx = MathUtils.cos(radians)*speed;
        dy = MathUtils.sin(radians)*speed;

        timer = 0;
        timeLimit = 2;

        bulletHit = false;
    }

    /**
     * Checks if a bullet has been alive for too long
     * @return
     *      True or false.
     */
    public boolean timeCheck() {
        if (timer > timeLimit) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Updates variables for the bullet for each frame.
     * @param dt
     *      Delta, time between each frame.
     */
    public void update(float dt){
        x += dx * dt;
        y += dy * dt;

        bulletSprite.setX(x);
        bulletSprite.setY(y);

        rectBullet.x = x;
        rectBullet.y = y;

        timer += dt;
    }
}
