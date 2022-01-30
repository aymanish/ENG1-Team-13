package com.ayman.entities;

import com.ayman.game.MyGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class AnneLister extends College {
    public AnneLister() {
        x = 900; //800/2
        y = 900; //150
        HP = 5;
        POINTS = 500;
        //set college sprite
        collegeSprite = textureAtlas.createSprite("anneLister_island");
        collegeSprite.setPosition(x, y);
        //set bounding rectangle based on college sprite
        boundRect = collegeSprite.getBoundingRectangle();
        boundRect.x = x;
        boundRect.y = y;
        //set AOE
        AOE.x = x+(width/2);
        AOE.y = y+(height/2);
    }
    //changing of sprite to captured will be made after isCaptured() call in gamescreen class
}
