package com.ayman.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Extends LibGDX's sprite class with information needed by every item on the screen.
 *
 * textureAtlas: The texture atlas used to access assets
 * x: x coordinate of game object
 * y: y coordinate of game object
 * dx: x coordinate of vector for game object
 * dy: y coordinate of vector for game object
 * radians: angle in radians of game object relative to positive y axis
 * width: Width of the sprite in pixels
 * height: Height of the sprite in pixels
 */

public class GameObject extends Sprite {

    public TextureAtlas textureAtlas = new TextureAtlas("sprites.txt");

    public float x;
    public float y;

    //vectors
    public float dx;
    public float dy;

    //angle
    public float radians;
    public int width;
    public int height;
}
