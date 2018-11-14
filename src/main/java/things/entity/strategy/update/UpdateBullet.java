package things.entity.strategy.update;

import things.entity.Bullet;

public class UpdateBullet implements UpdateSprite {
    private Bullet bullet;
     /* The difference between the initial y position and the new y position
        This is the amount of pixels the entity will move per second/update */
    private static final int DELTA_Y = 5;

    public UpdateBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    @Override
    public void update() {
        fireBullet();
        bullet.notifyObservers();
    }

    // This method fires the bullet by reducing the the y position by Delta_y each update
    private void fireBullet(){
        int newTopLeftYPos;
        if (bullet.isAlienBullet()) {
            newTopLeftYPos = bullet.getTopLeftYPos() + DELTA_Y;
        } else
            newTopLeftYPos = bullet.getTopLeftYPos() - DELTA_Y;
        bullet.setTopLeftYPos(newTopLeftYPos);
    }

}
