package com.ayman.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Bullet extends GameObject {

    private float lifeTime;
    private float lifeTimer;

    private boolean remove;
    public boolean BulletCollide;


    public Sprite bulletSprite;
    public TextureAtlas textureAtlas;
    public Rectangle rectBullet;

    public Bullet(float x, float y, float radians) {
        this.x = x;
        this.y = y;
        this.radians = radians;

        textureAtlas = new TextureAtlas("sprites.txt");
        bulletSprite = textureAtlas.createSprite("bullet");
        rectBullet = this.bulletSprite.getBoundingRectangle();

        float speed = 350;
        dx = MathUtils.cos(radians)*speed;
        dy = MathUtils.sin(radians)*speed;

        width = height = 18;
        lifeTimer = 0;
        lifeTime = 1;

        BulletCollide = false;
    }

    public boolean shouldRemove() {return remove;}

    public void update(float dt){
        x += dx * dt;
        y += dy * dt;

        bulletSprite.setX(x);
        bulletSprite.setY(y);

        rectBullet.x = this.x;
        rectBullet.y = this.y;

        lifeTimer += dt;
        if (lifeTimer > lifeTime) {
            remove = true;
        }
    }
}
