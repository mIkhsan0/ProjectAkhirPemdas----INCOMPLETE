package com.mygdx.test001.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;

public class WorldPhysics {
    private World world;
    private Rectangle rectangle;
    private Circle circle;
    private CircleShape circleShape;
    private Polygon polygon;
    private PolygonShape polygonShape;
    private Polyline polyline;
    private ChainShape polylineShape;
    private Body body;
    private BodyDef bodyDef;
    private FixtureDef fixtureDef;
    private TiledMapTileMapObject tiledMapTileMapObject;

    public WorldPhysics(World world) {
        this.world = world;
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();
        //Body
        bodyDef.type = BodyDef.BodyType.StaticBody;
    }
    public void bodyAndFixture(MapObjects mapObjects) {

        //Fixture
        for (MapObject object : mapObjects) {
            polygonShape = new PolygonShape();
            circleShape = new CircleShape();
            polylineShape = new ChainShape();
            //Check InstanceOf
            if (object instanceof RectangleMapObject) {
                rectangle = ((RectangleMapObject) object).getRectangle();
                polygonShape.setAsBox(rectangle.getX()/2, rectangle.getY()/2, new Vector2(rectangle.getX() + rectangle.getWidth() /2, rectangle.getY() + rectangle.getHeight() /2), 0.0f);
                fixtureDef.shape = polygonShape;
            }
            else if (object instanceof CircleMapObject) {
                circle = ((CircleMapObject) object).getCircle();
                circleShape.setRadius(circle.radius);
                fixtureDef.shape = circleShape;
            }
            else if (object instanceof PolygonMapObject) {
                polygon = ((PolygonMapObject) object).getPolygon();
                polygonShape.set(polygon.getTransformedVertices());
                fixtureDef.shape = polygonShape;
            }
            else if (object instanceof PolylineMapObject) {
                polyline = ((PolylineMapObject) object).getPolyline();
                polylineShape.createChain(polyline.getTransformedVertices());
                fixtureDef.shape = polylineShape;
            } else continue;

            //Fixture
            body = world.createBody(bodyDef);
            body.createFixture(fixtureDef);

            polygonShape.dispose();
            circleShape.dispose();
            polylineShape.dispose();
        }
    }
}
