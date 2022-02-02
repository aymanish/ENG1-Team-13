package com.ayman.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
/**
 * Class that acts as the barrier between boss college and other colleges.
 *
 * barrier: Sets the sprite to represent the barrier
 */
public class Barrier extends GameObject {
    public Sprite barrier;

    /**
     * Constructor for Barrier class.
     */

    public Barrier() {
        barrier = textureAtlas.createSprite("barrier");
    }
}
