package things.entity.strategy.update;

import things.SpaceInvadersGUI;
import things.entity.Bullet;
import things.entity.singleton.FiredBullets;

public class UpdateBullet implements UpdateSprite {
    private Bullet bullet;
     /* The difference between the initial y position and the new y position
        This is the amount of pixels the entity will move per second/update */
    private static final int DELTA_Y = 5;

    public UpdateBullet(Bullet gameComponent) {
        this.bullet = gameComponent;
    }

    public void update() {
        fireBullet();
        removeBullet();
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

    // This method removes a bullet from the ArrayList of bullets once its hit
    // an enemy or passed the top of the screen
    private void removeBullet(){
        if(bullet.getTopLeftYPos() + bullet.getHeight() < 0
                || bullet.getTopLeftYPos()  > SpaceInvadersGUI.HEIGHT){
            FiredBullets.getTankBullets().removeBullet(bullet);
        }
    }
}
