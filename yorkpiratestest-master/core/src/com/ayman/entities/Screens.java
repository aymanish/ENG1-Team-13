package com.ayman.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Screens extends GameObject {
    public Sprite titleScreen, endScreen;

    public Screens() {
        titleScreen = textureAtlas.createSprite("startScreen");
        endScreen = textureAtlas.createSprite("endScreen");
    }
}