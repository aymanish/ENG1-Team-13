package com.ayman.entities;

public class Goodricke extends College {
    public Goodricke() {
        //ayman: changed college position for game
        x = 1200;
        y = 700;
        HP = 5;
        POINTS = 500;
        //set college sprite
        png_npc = "ship_GR";
        png_captured = "goodricke_island_captured";
        capturedSprite = textureAtlas.createSprite(png_captured);
        collegeSprite = textureAtlas.createSprite("goodricke_island");
        collegeSprite.setPosition(x, y);
        capturedSprite.setPosition(x, y);
        //set bounding rectangle based on college sprite
        boundRect = collegeSprite.getBoundingRectangle();
        boundRect.x = x;
        boundRect.y = y;
        //set AOE
        AOE.x = x+(width/2);
        AOE.y = y+(height/2);
    }
}
