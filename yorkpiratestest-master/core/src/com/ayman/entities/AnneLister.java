package com.ayman.entities;

public class AnneLister extends College {
    public AnneLister() {
        x = 700; //800/2
        y = 1100; //150
        HP = 5;
        POINTS = 500;
        //set college sprite
        png_npc = "ship_AL";
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
}
