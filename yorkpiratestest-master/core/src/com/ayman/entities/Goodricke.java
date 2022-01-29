package com.ayman.entities;

public class Goodricke extends College {
    public Goodricke() {
        x = 0;
        y = 0;
        HP = 5;
        POINTS = 500;
        collegeSprite = textureAtlas.createSprite("goodricke_island");
        boundRect = collegeSprite.getBoundingRectangle();
    }
}
