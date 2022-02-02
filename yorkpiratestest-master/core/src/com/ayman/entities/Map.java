package com.ayman.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Loads the image used as the play-space for the game
 *
 * Map: sets the image to be used as texture for the playable area
 */

public class Map extends GameObject {
    public Sprite map;

    /**
     * Constructor for Map class.
     */

    public Map() {
        map = textureAtlas.createSprite("boundary");
    }
}
