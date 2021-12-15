package com.ayman.entities;

public class tileMap {

    //2d tile array:
    private  int width, height;
    public tile[][] tiles;

    public tileMap(int width, int height) {

        this.width = width;
        this.height = height;
        tiles = new tile[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new tile(Terrain.WATER_1);
            }
        }


    }

    //getters:

    public tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
