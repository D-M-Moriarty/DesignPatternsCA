package things.entity;

import things.entity.template_method.DestroyableObject;

import java.awt.*;

/**
 * Created by Darren Moriarty on 17/11/2016.
 */
public class AlienEntity extends DestroyableObject {

    public AlienEntity(int topLeftXPos, int topLeftYPos, int width, int height, Color color, boolean destroyed){
        super(topLeftXPos, topLeftYPos, width, height, color);
        setDestroyed(destroyed);
    }


    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void update() {

    }

    @Override
    protected void performDestroyableObjectCollisionAction(DestroyableObject destroyableObject, Bullet tankBullet) {

    }

}
