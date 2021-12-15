package com.ayman.entities;

public class tile {

    public Terrain terrain;

    public GameObject object;

    //constructors:
    public tile (Terrain terrain) {
        this.terrain = terrain;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    //getters + setters:


    public GameObject getObject() {
        return object;
    }

    public void setObject(GameObject object) {
        this.object = object;
    }
}
