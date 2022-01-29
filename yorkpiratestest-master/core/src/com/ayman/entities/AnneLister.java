package com.ayman.entities;

import com.ayman.game.MyGame;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;

public class AnneLister extends College {
    public AnneLister() {
        x = 900; //800/2
        y = 900; //150
        HP = 5;
        POINTS = 500;
        collegeSprite = textureAtlas.createSprite("anneLister_island");
        boundRect = collegeSprite.getBoundingRectangle();
    }
    //changing of sprite to captured will be made after isCaptured() call in gamescreen class
}
