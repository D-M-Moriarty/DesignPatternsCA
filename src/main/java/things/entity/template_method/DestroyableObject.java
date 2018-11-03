package things.entity.template_method;

import things.entity.Bullet;
import things.entity.GameComponent;
import things.entity.singleton.FiredBullets;

import java.awt.*;

public abstract class DestroyableObject extends GameComponent {

    // Boolean attribute to signify whether this entity is destroyed or not
    private boolean destroyed;
    /**
     * 5 argument constructor method
     *
     * @param topLeftXPos value passed into the method
     * @param topLeftYPos value passed into the method
     * @param width       value passed into the method
     * @param height      value passed into the method
     * @param color       value passed into the method
     */
    public DestroyableObject(int topLeftXPos, int topLeftYPos, int width, int height, Color color) {
        super(topLeftXPos, topLeftYPos, width, height, color);
    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void update() {

    }


    protected final void collisionDecisions(DestroyableObject[][] destroyableObjects) {
        for (int i = 0; i < tankBullets.size(); i++) {
            Bullet tankBullet = tankBullets.getBullet(i);

            removeBulletsThatCollided(tankBullet, alienBullets);

            checkForCollisionWithBulletAndDestroyableObject(destroyableObjects, tankBullet);

        }


    }

    private void checkForCollisionWithBulletAndDestroyableObject(DestroyableObject[][] destroyableObjects, Bullet tankBullet) {
        for (int i = 0; i < destroyableObjects.length; i++) {
            for (int j = 0; j < destroyableObjects[0].length; j++) {
                if (tankBullet.collidesWith(destroyableObjects[i][j])) {
                    performDestroyableObjectCollisionAction(destroyableObjects[i][j], tankBullet);
                }
            }
        }
    }

    protected abstract void performDestroyableObjectCollisionAction(DestroyableObject destroyableObject, Bullet tankBullet);

    private void removeBulletsThatCollided(Bullet tankBullet, FiredBullets alienBullets) {
        // Checking for collision between alien and tank bullets
        for (int i = 0; i < alienBullets.size(); i++) {
            Bullet aBullet = alienBullets.getBullet(i);
            if (tankBullet.collidesWith(aBullet)) {
                alienBullets.removeBullet(aBullet);
                tankBullets.removeBullet(tankBullet);
            }
        }
    }

    /**
     * This sets the value of destroyed
     * @param destroyed
     */
    public void setDestroyed(boolean destroyed){
        this.destroyed = destroyed;
    }

    /**
     * This returns the value of destroyed
     * @return
     */
    public boolean isDestroyed() {
        return destroyed;
    }
}
