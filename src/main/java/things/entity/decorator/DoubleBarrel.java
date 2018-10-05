package things.entity.decorator;

import things.entity.Barrel;
import things.entity.Bullet;

import java.awt.*;

public class DoubleBarrel extends TankBarrelDecorator {

    public DoubleBarrel(AbstractBarrel abstractBarrel) {
        super(abstractBarrel.getTopLeftXPos(), abstractBarrel.getTopLeftYPos(),
                abstractBarrel.getWidth(), abstractBarrel.getHeight(),
                abstractBarrel.getColor(), abstractBarrel.getHorizontalSpeed());
    }

    public void draw(Graphics2D g) {
        g.setColor(getColor());
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawRect(topLeftXPos + 25, topLeftYPos, width, height);
        g.fillRect(topLeftXPos + 25, topLeftYPos, width, height);
        g.drawRect(topLeftXPos - 25, topLeftYPos, width, height);
        g.fillRect(topLeftXPos - 25, topLeftYPos, width, height);
    }

    public AbstractBarrel getBarrel() {
        return new Barrel(topLeftXPos, topLeftYPos, width, height, color, horizontalSpeed);
    }

    @Override
    protected void fireBulletFromBarrel() {
        // Adding a new instance of a bullet to the arrayList of bullets
        Bullet bullet = new Bullet(topLeftXPos + 25 + (width / 4), 570, 5, 10, Color.WHITE, false);
        tankBullets.addBullet(bullet);
        bullet = new Bullet(topLeftXPos - 25 + (width / 4), 570, 5, 10, Color.WHITE, false);
        tankBullets.addBullet(bullet);
        try{
            playSound("sounds/shoot.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // setting firing to false
        firing = false;
    }

}
