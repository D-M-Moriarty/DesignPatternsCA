package things;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import things.entity.Bullet;

import java.awt.*;


public class BulletTest {

    private Bullet alienBullet;

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void fireBullet() {
        alienBullet = new Bullet(0,
                0, 100, 50, Color.BLACK, true);
        int movement = 5;
        int initialTopLeftYPosition = alienBullet.getTopLeftYPos();
        int predictedYPosition = initialTopLeftYPosition + movement;
        alienBullet.updateEntity();
        int newYPosition = alienBullet.getTopLeftYPos();

        Assert.assertEquals(newYPosition, predictedYPosition);
    }

    @Test
    public void removeBullet() {
        alienBullet = new Bullet(100,
                100, 100, 50, Color.BLACK, true);

        GameMain gameMain = new GameMain();
    }

    @Test
    public void update() {
    }

    @Test
    public void draw() {
    }
}