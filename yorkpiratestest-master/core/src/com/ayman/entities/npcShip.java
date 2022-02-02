package com.ayman.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 * Used to create non-player ships. Currently, for decoration but with facilities for functionality in future
 *
 *  npcSprite: The image used as the ship sprite
 *  rectNPC: The bounding rectangle for the ship
 */

public class npcShip extends Ship{

    public Sprite npcSprite;
    public Rectangle rectNPC;

    /**
     * Constructor for npcShip class
     * @param college_png
     *      Identifier for npcShip for a particular college.
     */

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

    /**
     * Updates parameters for npcShip and acts as a boundary check.
     * @param dt
     *      Delta. Time between each frame.
     */

    public void update(float dt) {

        //insert any positional updates
        //can use the college bullet aim to update radians
        boundaries();
    }

}
