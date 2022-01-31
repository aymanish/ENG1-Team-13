package com.ayman.entities;

public class Constantine extends College {
    public Constantine() {
        x = 2000;
        y = 900;
        HP = 5;
        POINTS = 500;
        collegeSprite = textureAtlas.createSprite("constantine_island");
        boundRect = collegeSprite.getBoundingRectangle();
        boundRect.x = x;
        boundRect.y = y;
        //set AOE
        AOE.x = x+(width/2);
        AOE.y = y+(height/2);
    }
}
