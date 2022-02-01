package com.ayman.entities;

import com.ayman.game.MyGame;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Map extends GameObject {
    public Sprite map;

    public Map() {
        map = textureAtlas.createSprite("boundary");
    }
}
