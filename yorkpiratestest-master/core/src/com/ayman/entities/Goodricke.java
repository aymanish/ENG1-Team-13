package com.ayman.entities;

public class Goodricke extends College {
    public Goodricke() {
        x = 1600;
        y = 900;
        HP = 5;
        POINTS = 500;
        collegeSprite = textureAtlas.createSprite("goodricke_island");
        boundRect = collegeSprite.getBoundingRectangle();
        boundRect.x = x;
        boundRect.y = y;
        //set AOE
        AOE.x = x+(width/2);
        AOE.y = y+(height/2);
    }
}
