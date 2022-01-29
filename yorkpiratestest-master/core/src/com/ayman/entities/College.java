package com.ayman.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class College extends GameObject{

    public Sprite collegeSprite;
    public TextureAtlas textureAtlas;
    public Rectangle rectCollege, collegeAOE;
    public int HP, POINTS;
    public boolean isCaptured;
    public Texture AOE;
    public boolean isAttacked;

    public College() {

        x = 900; //800/2
        y = 900; //150
        width = 174;
        height = 125;
        HP = 1000;
        POINTS = 500;

        isCaptured = false;
        isAttacked = false;


        textureAtlas = new TextureAtlas("sprites.txt");
        collegeSprite = textureAtlas.createSprite("anneLister_island");

        rectCollege = this.collegeSprite.getBoundingRectangle();
        rectCollege.x = this.x;
        rectCollege.y = this.y;

        AOE = new Texture(Gdx.files.internal("collegeAOE.png"));

        collegeAOE = new Rectangle();
        collegeAOE.x = this.x - this.width/2;
        collegeAOE.y = this.y - this.height/2;
        collegeAOE.width = this.width*2;
        collegeAOE.height = this.height*2;


    }
}
