package com.ayman.entities;
/**
 * Extends the college class with information specific to the Anne lister College.
 */
public class AnneLister extends College {

    /**
     * Constructor for AnneLister class.
     */

    public AnneLister() {
        x = 700; //800/2
        y = 1100; //150
        HP = 5;
        POINTS = 500;
        //set college sprite
        png_npc = "ship_AL";
        png_captured = "anneLister_island_captured";
        capturedSprite = textureAtlas.createSprite(png_captured);
        collegeSprite = textureAtlas.createSprite("anneLister_island");
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
