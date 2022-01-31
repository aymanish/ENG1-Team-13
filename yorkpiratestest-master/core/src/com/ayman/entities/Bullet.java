package com.ayman.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Bullet extends GameObject {

    private float timeLimit;
    private float timer;

    public Sprite bulletSprite;
    public Rectangle rectBullet;

    //aymans code:
    private boolean remove;
    public boolean BulletCollide;

    public Bullet(float x, float y, float radians) {

        bulletSprite = textureAtlas.createSprite("bullet");

        rectBullet = bulletSprite.getBoundingRectangle();

        width = height = 18;
        this.x = x;
        this.y = y;
        this.radians = radians;

        //set bullet speed
        float speed = 350;
        dx = MathUtils.cos(radians)*speed;
        dy = MathUtils.sin(radians)*speed;

        timer = 0;
        timeLimit = 2;

        BulletCollide = false;
    }

    public boolean shouldRemove() {return (remove);}

    /*
    //won't work since bullet needs to be 'removed' in the update() call:
    public boolean isRemove() {
        if ((timer > timeLimit)) { //add or logical check for bullet overlap with college/other players
            return true;
        }
        else {
            return false;
        }
    }

     */

    public void update(float dt){
        x += dx * dt;
        y += dy * dt;

        bulletSprite.setX(x);
        bulletSprite.setY(y);

        rectBullet.x = x;
        rectBullet.y = y;

        timer += dt;
        if (timer > timeLimit) {
            remove = true;
        }
    }
}
