package com.ayman.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Barrier extends GameObject {
    public Sprite barrier;
    public Barrier() {
        barrier = textureAtlas.createSprite("barrier");
    }
}
