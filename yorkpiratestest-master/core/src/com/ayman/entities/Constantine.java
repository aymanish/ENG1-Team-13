package com.ayman.entities;

public class Constantine extends College {
    public Constantine() {
        x = 0;
        y = 0;
        HP = 5;
        POINTS = 500;
        collegeSprite = textureAtlas.createSprite("constantine_island");
        boundRect = collegeSprite.getBoundingRectangle();
    }
}
