package com.ayman.entities;

public class Goodricke extends College {
    public Goodricke() {
        x = 1200;
        y = 700;
        HP = 5;
        POINTS = 500;
        //set college sprite
        png_npc = "ship_GR";
        collegeSprite = textureAtlas.createSprite("goodricke_island");
        collegeSprite.setPosition(x, y);
        //set bounding rectangle based on college sprite
        boundRect = collegeSprite.getBoundingRectangle();
        boundRect.x = x;
        boundRect.y = y;
        //set AOE
        AOE.x = x+(width/2);
        AOE.y = y+(height/2);
    }
}
