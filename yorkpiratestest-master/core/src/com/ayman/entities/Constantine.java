package com.ayman.entities;

public class Constantine extends College {
    public Constantine() {
        x = 2000;
        y = 900;
        HP = 5;
        POINTS = 500;
        //set college sprite
        png_npc = "ship_CT";
        png_captured = "constantine_island_captured";
        capturedSprite = textureAtlas.createSprite(png_captured);
        collegeSprite = textureAtlas.createSprite("constantine_island");
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
