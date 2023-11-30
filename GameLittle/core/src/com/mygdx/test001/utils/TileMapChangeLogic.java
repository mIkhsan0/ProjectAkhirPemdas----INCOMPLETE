package com.mygdx.test001.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.physics.box2d.Body;

public class TileMapChangeLogic {
    private TiledMap map;
    private MapLayer tiledMapLayer;
    private TiledMapTileLayer tiledMapTileLayer;
    private OrthographicCamera camera;
    private TiledMapTileLayer.Cell cell;
    private TiledMapTileSets tileSets;
    private TiledMapTileSet tileSet;
    private TiledMapTile tile;
    public TileMapChangeLogic(TiledMap map, OrthographicCamera camera) {
        this.map = map;
        this.camera = camera;

        //ChangeTile
        tiledMapLayer = map.getLayers().get("Land");
        if (tiledMapLayer instanceof TiledMapTileLayer)
            tiledMapTileLayer = (TiledMapTileLayer) tiledMapLayer;


        //Get Tile from Tile Set
        tileSets = map.getTileSets(); //Get TileSets from map. All TileSet in a map
        tileSet = tileSets.getTileSet("Tilled Dirt"); //Get ONLY one TileSet from the map.
    }

    public void changeCurrentTile() {
        //Get Cell Location. this is where the individual tile is located. to change to another tile.
        cell = tiledMapTileLayer.getCell((int) camera.position.x, (int) camera.position.y);
        System.out.println("Cell: " + cell);

        System.out.println("Player position: (" + camera.position.x + ", " + camera.position.y + ")");

        //TiledMapTile. its a tile. required to change tile. YOU NEED TILE TO CHANGE TILE.
        tile = tileSet.getTile(34); //Get A Tile From TileSet.
        System.out.println("Tile: " + tile);

        //Change Tile to new Tile
        if (cell != null) {
            cell.setTile(tile);
        } else {
            System.out.println("Cell is null. Tile not changed.");
        }
    }
}
