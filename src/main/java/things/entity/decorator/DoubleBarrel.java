package things.entity.decorator;

import things.entity.Barrel;
import things.entity.Bullet;

import java.awt.*;

public class DoubleBarrel extends TankBarrelDecorator {

    private Barrel barrel1;
    private Barrel barrel2;

    public DoubleBarrel(AbstractBarrel abstractBarrel) {
        super(abstractBarrel.getTopLeftXPos(), abstractBarrel.getTopLeftYPos(),
                abstractBarrel.getWidth(), abstractBarrel.getHeight(),
                abstractBarrel.getColor(), abstractBarrel.getHorizontalSpeed(), abstractBarrel);
        barrel1 = new Barrel(topLeftXPos, topLeftYPos, width, height, color, horizontalSpeed);
        barrel2 = new Barrel(topLeftXPos, topLeftYPos, width, height, color, horizontalSpeed);
        barrel1.setTopLeftXPos(this.getTopLeftXPos() + 25);
        barrel2.setTopLeftXPos(this.getTopLeftXPos() - 25);
    }

    @Override
    public void draw(Graphics2D g) {
        barrel1.draw(g);
        barrel2.draw(g);
    }

    @Override
    public void update(){
        handleMovement();
        barrel1.setTopLeftXPos(this.getTopLeftXPos() + 25);
        barrel2.setTopLeftXPos(this.getTopLeftXPos() - 25);

        // checking to see if firing is true
        if (firing){
            Bullet bullet = new Bullet(barrel1.getTopLeftXPos() + (width / 4), 570, 5, 10, Color.WHITE, false);
            tankBullets.addBullet(bullet);
            bullet = new Bullet(barrel2.getTopLeftXPos() + (width / 4), 570, 5, 10, Color.WHITE, false);
            tankBullets.addBullet(bullet);
            // setting firing to false
            firing = false;
        }


    }


}
