package com.mygdx.test001.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.physics.box2d.*;

public class ObjectsMapRenderer {
    private SpriteBatch batch;
    private TiledMapTileMapObject tiledMapTileMapObject;
    private TextureMapObject textureMapObject;
    private BodyDef bodyDef;
    private Body body;
    private World world;
    private FixtureDef fixtureDef;
    private PolygonShape polygonShape;
    public ObjectsMapRenderer(World world, SpriteBatch batch) {
        this.batch = batch;
        this.world = world;
    }

    public void renderObjects(MapObjects mapObjects) {
        //MapObjectsRenderer
        for (MapObject object : mapObjects) {
            if (object instanceof TextureMapObject) {
                textureMapObject = ((TextureMapObject) object);
                batch.draw(textureMapObject.getTextureRegion(), textureMapObject.getX(), textureMapObject.getY());
            }
        }
    }

    public void renderObjectsPhyscis(MapObjects mapObjects) {
        for (MapObject object : mapObjects) {
            if (object instanceof TiledMapTileMapObject) {
                tiledMapTileMapObject = ((TiledMapTileMapObject) object);

                bodyDef = new BodyDef();
                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set(tiledMapTileMapObject.getX(), tiledMapTileMapObject.getY());
                //Maybe Set Position

                body = world.createBody(bodyDef);

                polygonShape = new PolygonShape();
                //Adjust or Maybe Dont Use
                polygonShape.setAsBox(16 / 2, 16/2);

                fixtureDef = new FixtureDef();
                fixtureDef.shape = polygonShape;

                body.createFixture(fixtureDef);

            }
        }
    }
}
