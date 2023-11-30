package com.mygdx.test001.utils;

import com.badlogic.gdx.physics.box2d.*;

public class PlayerPhysics {
    private Body body;
    private BodyDef bodyDef;
    private PolygonShape bodyShape;
    private FixtureDef fixtureDef;
    public PlayerPhysics(World world) {
        bodyDef = new BodyDef();
        bodyShape = new PolygonShape();
        fixtureDef = new FixtureDef();

        //BodyDef
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(600, 780);
        bodyDef.fixedRotation = true;

        //Body Initialize
        body = world.createBody(bodyDef);

        //BodyShape
        bodyShape.setAsBox(20/2 - 3, 20/2 - 2);

        //FixtureDef
        fixtureDef.shape = bodyShape;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.5f;
        body.createFixture(fixtureDef);

        bodyShape.dispose();
    }

    public Body getBody() {
        return body;
    }
}
