package things;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.awt.*;


public class AlienBulletTest {

    private AlienBullet alienBullet;

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void fireBullet() {
        alienBullet = new AlienBullet(0,
                0, 100, 50, Color.BLACK);
        int movement = 5;
        int initialTopLeftYPosition = alienBullet.getTopLeftYPos();
        int predictedYPosition = initialTopLeftYPosition + movement;
        alienBullet.fireBullet();
        int newYPosition = alienBullet.getTopLeftYPos();

        Assert.assertEquals(newYPosition, predictedYPosition);
    }

    @Test
    public void removeBullet() {
        alienBullet = new AlienBullet(100,
                100, 100, 50, Color.BLACK);

        GameMain gameMain = new GameMain();
    }

    @Test
    public void update() {
    }

    @Test
    public void draw() {
    }
}