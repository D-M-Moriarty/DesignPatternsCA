package things.entity.decorator;

import things.entity.Barrel;
import things.entity.Bullet;

import java.awt.*;

public class MoltenBullet extends TankBarrelDecorator {
    private AbstractBarrel barrel;

    public MoltenBullet(AbstractBarrel abstractBarrel) {
        super(abstractBarrel.getTopLeftXPos(), abstractBarrel.getTopLeftYPos(),
                abstractBarrel.getWidth(), abstractBarrel.getHeight(),
                abstractBarrel.getColor(), abstractBarrel.getHorizontalSpeed(), abstractBarrel);
        barrel = abstractBarrel;
    }

    @Override
    public void update() {
        handleMovement();
    // checking to see if firing is true
         if (firing) {
            Bullet bullet = new Bullet(barrel.getTopLeftXPos() + (width / 4), 570, 5, 10, Color.ORANGE, false);
            tankBullets.addBullet(bullet);
            // setting firing to false
            firing = false;
        }
    }
}
