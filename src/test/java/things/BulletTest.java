package things;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import things.entity.Barrier;
import things.entity.Bullet;
import things.entity.GameComponent;
import things.entity.command.TankMoveLeftCommand;
import things.entity.decorator.AbstractBarrel;
import things.entity.decorator.DoubleBarrel;
import things.entity.decorator.MoltenBullet;
import things.entity.decorator.WideBarrel;
import things.entity.factory_method.GameComponentFactory;
import things.entity.factory_method.StandardGameComponentFactory;
import things.entity.factory_method.Type;
import things.entity.singleton.FiredBullets;
import things.entity.strategy.update.UpdateBullet;
import things.entity.template_method.DestroyableObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class BulletTest {

    private Bullet alienBullet;

    @BeforeEach
    public void setUp() {
        for (int i = 0; i < FiredBullets.getAlienBullets().size(); i++) {
            FiredBullets.getAlienBullets().removeBullet(i);
        }
    }

    @AfterEach
    public void tearDown() {
        for (int i = 0; i < FiredBullets.getAlienBullets().size(); i++) {
            FiredBullets.getAlienBullets().removeBullet(i);
        }
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
        for (int i = 0; i < FiredBullets.getAlienBullets().size(); i++) {
            FiredBullets.getAlienBullets().removeBullet(i);
        }
    }

    @Test
    public void countFiredBulletsInGameMain() {
        alienBullet = new Bullet(100,
                100, 100, 50, Color.BLACK, true);

        FiredBullets bullets = FiredBullets.getAlienBullets();
        bullets.addBullet(alienBullet);
        UpdateBullet updateBullet = new UpdateBullet(alienBullet);

        GameMain gameMain = GameMain.getGameMain();
        Assert.assertEquals(0, gameMain.getAlienBulletsFired().size());

        while (alienBullet.getTopLeftYPos() < SpaceInvadersGUI.HEIGHT) {
            updateBullet.update();
            bullets.getBullet(0).notifyObservers();
        }
        Assert.assertEquals(1, gameMain.getAlienBulletsFired().size());

        for (int i = 0; i < FiredBullets.getAlienBullets().size(); i++) {
            FiredBullets.getAlienBullets().removeBullet(i);
        }
    }

    @Test
    public void countFiredBulletsSpaceGUI() {
        alienBullet = new Bullet(100,
                100, 100, 50, Color.BLACK, true);

        FiredBullets bullets = FiredBullets.getAlienBullets();
        bullets.addBullet(alienBullet);
        UpdateBullet updateBullet = new UpdateBullet(alienBullet);

        SpaceInvadersGUI spaceInvadersGUI = new SpaceInvadersGUI();

        Assert.assertEquals(alienBullet, spaceInvadersGUI.getAlienBulls().getBullet(0));

        while (alienBullet.getTopLeftYPos() <= SpaceInvadersGUI.HEIGHT) {
            updateBullet.update();
        }
        Assert.assertEquals(0, spaceInvadersGUI.getAlienBulls().size());
        for (int i = 0; i < FiredBullets.getAlienBullets().size(); i++) {
            FiredBullets.getAlienBullets().removeBullet(i);
        }
    }

    @Test
    public void createSingleton() {
        FiredBullets firedBullets1 = FiredBullets.getTankBullets();
        firedBullets1.addBullet(alienBullet = new Bullet(100,
                100, 100, 50, Color.BLACK, true));

        FiredBullets firedBullets2 = FiredBullets.getTankBullets();

        Assert.assertEquals(firedBullets1, firedBullets2);
    }

    @Test
    public void testFactoryMethod() {
        GameComponent barrier = new Barrier(90, 470, 120, 70, Color.GREEN);

        GameComponentFactory factory = new StandardGameComponentFactory();
        GameComponent factoryBarrier = factory.getComponent(Type.BARRIER1);

        Assert.assertEquals(barrier.getTopLeftXPos(), factoryBarrier.getTopLeftXPos());
        Assert.assertEquals(barrier.getTopLeftYPos(), factoryBarrier.getTopLeftYPos());
        Assert.assertEquals(barrier.getHeight(), factoryBarrier.getHeight());
        Assert.assertEquals(barrier.getWidth(), factoryBarrier.getWidth());
        Assert.assertEquals(barrier.getColor(), factoryBarrier.getColor());
    }

    @Test
    public void testTemplate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Bullet alienBullet = new Bullet(100,
                100, 100, 50, Color.RED, true);
        FiredBullets alienBullets = FiredBullets.getAlienBullets();

        alienBullets.addBullet(alienBullet);

        Bullet tankBullet = new Bullet(100,
                500, 100, 50, Color.BLACK, false);
        FiredBullets tankBullets = FiredBullets.getTankBullets();
        tankBullets.addBullet(tankBullet);

        Assert.assertEquals(1, alienBullets.size());

        Barrier barrier = (Barrier) new StandardGameComponentFactory().getComponent(Type.BARRIER1);

        Method method = DestroyableObject.class.getDeclaredMethod("removeBulletsThatCollided",
                Bullet.class, FiredBullets.class);
        method.setAccessible(true);

        while (alienBullets.getBullet(0).getTopLeftYPos() != tankBullet.getTopLeftYPos()){
            alienBullets.getBullet(0).updateEntity();
            tankBullet.updateEntity();
            method.invoke(barrier, alienBullet, tankBullets);
        }
        method.invoke(barrier, tankBullet, alienBullets);

        Assert.assertEquals(0, alienBullets.size());

    }

    @Test
    public void testDecorator() {
        AbstractBarrel barrel = (AbstractBarrel) new StandardGameComponentFactory().getComponent(Type.BARREL);
        barrel = new MoltenBullet(barrel);
        barrel = new DoubleBarrel(barrel);
        barrel = new WideBarrel(barrel);

        Assert.assertTrue(barrel.hasDecorator("MoltenBullet"));
        Assert.assertTrue(barrel.hasDecorator("DoubleBarrel"));

        barrel.withoutDecorator("MoltenBullet");

        Assert.assertFalse(barrel.hasDecorator("MoltenBullet"));
        Assert.assertTrue(barrel.hasDecorator("DoubleBarrel"));
    }

    @Test
    public void testCommand() {
        GameComponent tank = new StandardGameComponentFactory().getComponent(Type.TANK);
        AbstractBarrel barrel = (AbstractBarrel) new StandardGameComponentFactory().getComponent(Type.BARREL);

        TankMoveLeftCommand moveLeftCommand = new TankMoveLeftCommand(tank, barrel);

        int originalX = tank.getTopLeftXPos();
        int newX = tank.getTopLeftXPos();

        Assert.assertTrue(newX == originalX);
        Assert.assertEquals(originalX, newX);

        moveLeftCommand.execute();
        tank.update();

        newX = tank.getTopLeftXPos();

        Assert.assertNotEquals(newX, originalX);
        Assert.assertEquals((originalX - 5), newX);
    }

}