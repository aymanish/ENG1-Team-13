package com.ayman.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

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

    public boolean max;
}
