package com.ayman.entities;

/**
 * Extends the college class with information specific to the Constantine College.
 */

public class Constantine extends College {

    /**
     * Constructor for Constantine college.
     */

    public Constantine() {
        //changed values for BOSS college
        x = 2260;
        y = 900;
        HP = 10;
        POINTS = 1000;
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
