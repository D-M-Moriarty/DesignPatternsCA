package things.entity;

import things.GameMain;
import things.Player;
import things.SpaceInvadersGUI;
import things.entity.observer.Observer;
import things.entity.singleton.FiredBullets;
import things.entity.template_method.DestroyableObject;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by Darren Moriarty on 17/11/2016.
 */
public class AlienInvaders extends DestroyableObject {
    private final int ALIEN_ROWS = 5;
    private final int ALIEN_COLUMNS = 11;
    // 2d array of alien entities
    private AlienEntity[][] alienEntities;
    //Setting the firing delay
    private int bulletTimingDelay = 2000;
    //Time spent waiting
    private int timeWaiting = 0;
    // Counts the amount of aliens killed
    private int deathCount;
    ClassLoader classLoader = getClass().getClassLoader();
    String gerPath = classLoader.getResource("images/ger.png").getFile();
    // Image of the beautiful Geraldine
    private ImageIcon gerImage = new ImageIcon(gerPath);
    // Delta variables for controlling speed
    private int delta = 1;
    private int delta2 = 1;
    // The x position
    private static int gerX = -2000;
    // boolean to tell if firing
    private boolean firing = true;
    // Imageicons for aliens
    String aP = classLoader.getResource("images/Space-large-invader.png").getFile();
    private ImageIcon alienImage = new ImageIcon(aP);
    String aP2 = classLoader.getResource("images/mS0hGaS.png").getFile();
    private ImageIcon alienImage2 =  new ImageIcon(aP2);
    String aP3 = classLoader.getResource("images/Space-medium-invader.png").getFile();
    private ImageIcon alienImage3 =  new ImageIcon(aP3);
    // The original positions to reset the aliens
    private int originalTopLeftXPos;
    private int originalTopLeftYPos;
    private int originalHeight;
    private int originalWidth;
    private Color originalColor;
    // checks if the aliens have reached a certain height on the screen
    private boolean heightReached = false;
    private FiredBullets alienBulls = FiredBullets.getAlienBullets();
    private FiredBullets tankBulls = FiredBullets.getTankBullets();

    public AlienInvaders(int topLeftXPos, int topLeftYPos, int width, int height, Color color) {
        super(topLeftXPos, topLeftYPos, width, height, color);
        // Initialising the 2d array of aliens
        alienEntities = new AlienEntity[ALIEN_ROWS][ALIEN_COLUMNS];
        populateAliens();
        // Setting the original positions
        setOriginalPositions();
    }

    private void populateAliens() {
        for (int i = 0; i < ALIEN_ROWS; i++) {
            for (int j = 0; j < ALIEN_COLUMNS; j++) {
                // Populating the array
                alienEntities[i][j] = new AlienEntity((getTopLeftXPos() + j * getWidth()),
                        (getTopLeftYPos() + i * getHeight()), getWidth(), getHeight(), getColor(),false);
            }
        }
    }

    private void setOriginalPositions() {
        originalTopLeftXPos = getTopLeftXPos();
        originalTopLeftYPos = getTopLeftYPos();
        originalHeight = getHeight();
        originalWidth = getWidth();
        originalColor = getColor();
    }

    @Override
    public void draw(Graphics2D g) {
        for (int i = 0; i < ALIEN_ROWS; i++) {
            for (int j = 0; j < ALIEN_COLUMNS; j++) {
                // Drawing the aliens
                drawingTheAliens(g, i, j);
            }
            // when geraldine is drawn
            drawGer(g);
        }
    }

    private void drawGer(Graphics2D g) {
        if (gerX > 0)
            g.drawImage(gerImage.getImage(), gerX, 10, null);

        g.setColor(Color.RED);
        if (gerX > -20 && gerX < 300) {
            g.drawString("OH no, Its Ger!!!!!", 400, 20);

        }
    }

    private void drawingTheAliens(Graphics2D g, int i, int j) {
        if (!alienEntities[i][j].isDestroyed()) {
            g.setColor(Color.BLACK);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            drawRects(g, alienEntities[i][j]);
            fillRects(g, alienEntities[i][j]);
            // choosing the correct image to draw
            chooseCorrectImages(g, i, j);
        }
    }

    private void drawRects(Graphics2D g, AlienEntity alienEntity) {
        g.drawRect(alienEntity.getTopLeftXPos(),
                alienEntity.getTopLeftYPos(),
                alienEntity.getWidth(),
                alienEntity.getHeight());
    }

    private void fillRects(Graphics2D g, AlienEntity alienEntity) {
        g.fillRect(alienEntity.getTopLeftXPos(),
                alienEntity.getTopLeftYPos(),
                alienEntity.getWidth(),
                alienEntity.getHeight());
    }

    private void chooseCorrectImages(Graphics2D g, int i, int j) {
        if (i == 0 || i == 3){
            g.drawImage(alienImage.getImage(), alienEntities[i][j].getTopLeftXPos(), alienEntities[i][j].getTopLeftYPos(), null);
        }else if (i == 1 || i == 4){
            g.drawImage(alienImage2.getImage(), alienEntities[i][j].getTopLeftXPos(), alienEntities[i][j].getTopLeftYPos(), null);
        }else{
            g.drawImage(alienImage3.getImage(), alienEntities[i][j].getTopLeftXPos(), alienEntities[i][j].getTopLeftYPos(), null);
        }
    }

    @Override
    public void update() {
        // moving ger
        movingGer();
        // resetting ger
        resettingGer();
        makeGerSound();
        //Firing the enemy Bullets
        fireEnemyBullets();
        //Checking the y axis f the aliens to stop the game
        aliensStopGameCheck();
        collisionDecisions(alienEntities);
        // checking for collisions with geraldine
        checkForGerCollisions();
        //Setting the directions of the aliens
        alienDirections();
    }

    private void alienDirections() {
        for (int i = 0; i < ALIEN_ROWS; i++) {
            for (int j = 0; j < ALIEN_COLUMNS; j++) {
                //Move right
                moveRight(alienEntities[i][j]);
                //Move left
                moveLeft(alienEntities[i][j]);
                //move back right again
                moveBackRightAgain(alienEntities[i][j]);
            }
        }
    }

    private void moveBackRightAgain(AlienEntity alienEntity) {
        if(alienEntity.topLeftXPos < 0 && !alienEntity.isDestroyed()){
            for (int k = 0; k < ALIEN_ROWS; k++) {
                for (int l = 0; l < ALIEN_COLUMNS; l++) {
                    delta = delta2;
                    alienEntities[k][l].setTopLeftYPos(alienEntities[k][l].getTopLeftYPos() + 15);
                }
            }
        }
    }

    private void moveLeft(AlienEntity alienEntity) {
        if(alienEntity.topLeftXPos >= 1000 - alienEntity.width   && !alienEntity.isDestroyed()){
            for (int k = 0; k < ALIEN_ROWS; k++) {
                for (int l = 0; l < ALIEN_COLUMNS; l++) {
                    delta = -delta2;
                    alienEntities[k][l].setTopLeftYPos(alienEntities[k][l].getTopLeftYPos() + 15);
                }
            }
        }
    }

    private void moveRight(AlienEntity alienEntity) {
        if(alienEntity.getTopLeftXPos() < 1000 && !alienEntity.isDestroyed())
            alienEntity.setTopLeftXPos(alienEntity.getTopLeftXPos() + delta);
    }

    private void checkForGerCollisions() {
        for (int i = 0; i < tankBulls.size(); i++) {
            Rectangle r2 = new Rectangle(tankBulls.getBullet(i).getTopLeftXPos(),
                    tankBulls.getBullet(i).getTopLeftYPos(),
                    tankBulls.getBullet(i).getWidth(),
                    tankBulls.getBullet(i).getHeight());
            // Calculating the random value for hitting geraldine
            if(r2.intersects(getGerX(), 10, 50 ,50)){
                System.out.println("It hit ger");
                tankBulls.removeBullet(i);
                setGerX(-2000);
                SpaceInvadersGUI.setPlayerScore(SpaceInvadersGUI.getPlayerScore() +
                        ((int)(Math.random() * 150) + 51));
                System.out.println(SpaceInvadersGUI.getPlayerScore());
            }
        }
    }

    private void aliensStopGameCheck() {
        for (int i = 0; i < ALIEN_ROWS; i++) {
            for (int j = 0; j < ALIEN_COLUMNS; j++) {
                if(alienEntities[i][j].getTopLeftYPos() + alienEntities[i][j].getHeight() >= 430){

                    GameMain.getGameMain().manageHighScores();
                    // breaks the game loop if there are more than one alien crossing the permitted y axis
                    heightReached = true;
                    break;
                }
            }
        }
    }

    private void fireEnemyBullets() {
        for (int i = 0; i < ALIEN_ROWS; i++) {
            for (int j = 0; j < ALIEN_COLUMNS; j++) {
                // creating the random firing order of the aliens
                Random rand = new Random();
                int  randomFire = rand.nextInt(ALIEN_COLUMNS);
                int  randomFireRow = rand.nextInt(ALIEN_ROWS);
                // reducing the firing delay so you can fire
                timeWaiting -= 1;
                if(timeWaiting <= 0 && firing && !alienEntities[randomFireRow][randomFire].isDestroyed()){
                    Bullet bullet = new Bullet(alienEntities[randomFireRow][randomFire].getTopLeftXPos() + 20,
                            alienEntities[randomFireRow][randomFire].getTopLeftYPos() + 40, ALIEN_ROWS, 10,
                            Color.RED, true);
                    alienBulls.addBullet(bullet);
                    Bullet gerBullet = new Bullet(getGerX() + 20,
                            10 + 40, ALIEN_ROWS, 10, Color.YELLOW, true);
                    alienBulls.addBullet(gerBullet);
                    // resetting the time waiting
                    timeWaiting = bulletTimingDelay;
                }
            }
        }
    }

    private void movingGer() {
        gerX += 2;
    }

    private void resettingGer() {
        if(gerX > 1000){
            gerX = -2000;
        }
    }

    private void makeGerSound() {
        // sounds for ger every 40 pixels
        if (gerX % 40 == 0 && gerX > 0 && gerX < SpaceInvadersGUI.WIDTH) {
            try{
                playSound("sounds/ufo_highpitch.wav");
            }
            catch (Exception e) {e.printStackTrace();}
        }
    }

    public void setGerX(int x){
        gerX = x;
    }

    public int getGerX(){
        return gerX;
    }

    @Override
    public boolean isHeightReached(){
        return heightReached;
    }

    @Override
    protected void performDestroyableObjectCollisionAction(DestroyableObject destroyableObject, Bullet tankBullet) {

        try{
            playSound("sounds/invaderkilled.wav");
        }
        catch (Exception e) { e.printStackTrace(); }

        drawAliensOffScreen(destroyableObject, tankBullet);

        // all the aliens are dead
        allAliensDead();

    }

    private void drawAliensOffScreen(DestroyableObject destroyableObject, Bullet tankBullet) {
        // drawing the aliens off the screen and reducing their dimensions
        tankBulls.removeBullet(tankBullet);
        destroyableObject.setHeight(-1000);
        destroyableObject.setWidth(-1000);
        destroyableObject.setTopLeftXPos(-1000);
        destroyableObject.setTopLeftYPos(-1000);
        destroyableObject.setDestroyed(true);

        // keeping track of deaths
        deathCount++;
        System.out.println(deathCount);

        if (deathCount == 54) {
            delta *= ALIEN_ROWS;
            delta2 *= ALIEN_ROWS;

        }
    }

    private void allAliensDead() {
        if (deathCount == 55){

            delta /= ALIEN_ROWS;
            delta2 /= ALIEN_ROWS;

            for (int l = 0; l < ALIEN_ROWS; l++) {
                for (int m = 0; m < ALIEN_COLUMNS; m++) {

                    // redraw them at their original positions
                    alienEntities[l][m].setTopLeftXPos(originalTopLeftXPos + m * originalWidth);
                    alienEntities[l][m].setTopLeftYPos(originalTopLeftYPos + l * originalHeight);
                    alienEntities[l][m].setWidth(originalWidth);
                    alienEntities[l][m].setHeight(originalHeight);
                    alienEntities[l][m].setColor(originalColor);
                    alienEntities[l][m].setDestroyed(false);

                }
            }

            // doubling the traversal speed
            delta *= 2;
            delta2 *= 2;
            // resetting the death count
            deathCount = 0;

        }
    }

}
