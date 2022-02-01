package com.ayman.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class npcShip extends Ship{

    public Sprite npcSprite;
    public Rectangle rectNPC;

    public npcShip(String college_png) {

        sprite = textureAtlas.createSprite(college_png);

        npcSprite = this.sprite;
        rectNPC = npcSprite.getBoundingRectangle();

        //need to manually set x,y
        dx = 0;
        dy = 0;
        width = 64;
        height = 64;

        //change if necessary for collisions against player ship
        maxSpeed = 0;
        acceleration = 0;
        deceleration = 0;

        //manually set radians
        radians = 0;
        rotationSpeed = 0;

    }

    public void update(float dt) {

        //insert any positional updates
        //can use the college bullet aim to update radians

        boundaries();
    }

}
