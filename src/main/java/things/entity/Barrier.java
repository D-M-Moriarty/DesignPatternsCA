package things.entity;

import things.entity.singleton.FiredBullets;
import things.entity.template_method.DestroyableObject;

import java.awt.*;

/**
 * This is an instantiable class called Barrier for creating a barrier entity.
 * It is a sub-class of GameComponent therefore it inherits all of its
 * attributes and abstract methods
 *
 * @author Darren Moriarty
 * created on 11/11/2016.
 *
 * @version 2.0
 */
public class Barrier extends DestroyableObject {
    private final int BARRIER_ROWS;
    private final int BARRIER_COLUMNS;

    //Class Attributes

    // Two dimensional array of BarrierBlock objects
    private DestroyableObject[][] barrierBlocks;
    private FiredBullets alienBulls = FiredBullets.getAlienBullets();

    /**
     * 5 argument constructor
     * @param topLeftXPos
     * @param topLeftYPos
     * @param width
     * @param height
     * @param color
     */
    public Barrier(int topLeftXPos, int topLeftYPos, int width, int height, Color color) {
        super(topLeftXPos, topLeftYPos, width, height, color);
        BARRIER_ROWS = 7;
        BARRIER_COLUMNS = 12;
        // Instantiating the array of barrierBlocks
        barrierBlocks = new BarrierBlock[BARRIER_ROWS][BARRIER_COLUMNS];
        // Setting the dimensions of each barrier block in the array with a nested for loops to
        // create a grid of block (5 x 6)

        for (int i = 0; i < BARRIER_ROWS; i++) {
            for (int j = 0; j < BARRIER_COLUMNS; j++) {
                // Setting the dimensions of each block
                // The position of each block is the width of the block wider relative to the block beside it
                barrierBlocks[i][j] = new BarrierBlock(getTopLeftXPos() + j * (getWidth() / BARRIER_COLUMNS), getTopLeftYPos()
                        + i * (getHeight() / BARRIER_ROWS), (getWidth() / BARRIER_COLUMNS), (getHeight() / BARRIER_ROWS), getColor(), false);
            }
        }


    }

    // This method decides what to do every time the screen refreshes
    @Override
    public void update() {
        // checking for collisions with the barriers
        collisionDecisions(barrierBlocks);
        collisionsWithAliens();
    }

    private void collisionsWithAliens() {
        // checking for collisions with alien bullets
        for (int k = 0; k < alienBulls.size(); k++) {
            Bullet alienBullet = alienBulls.getBullet(k);


            for (int i = 0; i < BARRIER_ROWS; i++) {
                for (int j = 0; j < BARRIER_COLUMNS; j++) {

                    if (alienBullet.collidesWith(barrierBlocks[i][j])) {
                        alienBulls.removeBullet(alienBullet);
                        barrierBlocks[i][j].setHeight(-1);
                        barrierBlocks[i][j].setWidth(-1);
                        barrierBlocks[i][j].setTopLeftXPos(-1);
                        barrierBlocks[i][j].setTopLeftYPos(-1);
                        barrierBlocks[i][j].setDestroyed(true);

                        try{
                            playSound("sounds/fastinvader2.wav");
                        }
                        catch (Exception e) { e.printStackTrace(); }
                    }
                }
            }
        }
    }

    @Override
    protected void performDestroyableObjectCollisionAction(DestroyableObject destroyableObject, Bullet tankBullet) {
        System.out.println("There was a collision");
        tankBullets.removeBullet(tankBullet);
        destroyableObject.setHeight(-1);
        destroyableObject.setWidth(-1);
        destroyableObject.setTopLeftXPos(-1);
        destroyableObject.setTopLeftYPos(-1);
        destroyableObject.setDestroyed(true);

        try{
            playSound("sounds/fastinvader1.wav");
        }
        catch (Exception e) { e.printStackTrace(); }
    }


    @Override
    public void draw(Graphics2D g) {
        // Setting the dimensions of each barrier block in the array with a nested for loops to
        // create a grid of block (7 x 12)
        for (int i = 0; i < BARRIER_ROWS; i++) {
            for (int j = 0; j < BARRIER_COLUMNS; j++) {
                //  Drawing the array of blocks to the screen
                if(!barrierBlocks[i][j].isDestroyed()){
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g.drawRect(barrierBlocks[i][j].getTopLeftXPos(),
                            barrierBlocks[i][j].getTopLeftYPos(),
                            barrierBlocks[i][j].getWidth(),
                            barrierBlocks[i][j].getHeight());
                    g.fillRect(barrierBlocks[i][j].getTopLeftXPos(),
                            barrierBlocks[i][j].getTopLeftYPos(),
                            barrierBlocks[i][j].getWidth(),
                            barrierBlocks[i][j].getHeight());
                }
            }
        }
    }

    @Override
    public String toString(){
        return "Barrier class is working";
    }

}
