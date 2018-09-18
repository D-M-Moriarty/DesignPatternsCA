package things;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by Darren Moriarty on 17/11/2016.
 */
public class AlienInvaders2 extends GameComponent {


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

    // player
    private Player player;
    private GameMain gameMain;

    // checks if the aliens have reached a certain height on the screen
    private boolean heightReached = false;


    public AlienInvaders2(int topLeftXPos, int topLeftYPos, int width, int height, Color color, GameMain gameMain) {
        super(topLeftXPos, topLeftYPos, width, height, color);
        this.gameMain = gameMain;

        // Initialising the 2d array of aliens
        alienEntities = new AlienEntity[5][11];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 11; j++) {

                // Populating the array
                alienEntities[i][j] = new AlienEntity((getTopLeftXPos() + j * getWidth()),
                        (getTopLeftYPos() + i * getHeight()), getWidth(), getHeight(), getColor(),false);

            }

        }

        // Setting the original positions
        originalTopLeftXPos = getTopLeftXPos();
        originalTopLeftYPos = getTopLeftYPos();
        originalHeight = getHeight();
        originalWidth = getWidth();
        originalColor = getColor();

    }

    @Override
    public void draw(Graphics2D g) {

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 11; j++) {

                // Drawing the aliens
                if (!alienEntities[i][j].isDestroyed()) {

                    g.setColor(Color.BLACK);


                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    g.drawRect(alienEntities[i][j].getTopLeftXPos(),
                            alienEntities[i][j].getTopLeftYPos(),
                            alienEntities[i][j].getWidth(),
                            alienEntities[i][j].getHeight());

                    g.fillRect(alienEntities[i][j].getTopLeftXPos(),
                            alienEntities[i][j].getTopLeftYPos(),
                            alienEntities[i][j].getWidth(),
                            alienEntities[i][j].getHeight());

                    // choosing the correct image to draw
                    if (i == 0 || i == 3){
                        g.drawImage(alienImage.getImage(), alienEntities[i][j].getTopLeftXPos(), alienEntities[i][j].getTopLeftYPos(), null);
                    }else if (i == 1 || i == 4){
                        g.drawImage(alienImage2.getImage(), alienEntities[i][j].getTopLeftXPos(), alienEntities[i][j].getTopLeftYPos(), null);

                    }else{
                        g.drawImage(alienImage3.getImage(), alienEntities[i][j].getTopLeftXPos(), alienEntities[i][j].getTopLeftYPos(), null);
                    }

                }

            }

            // when geraldine is drawn
            if (gerX > 0)
                g.drawImage(gerImage.getImage(), gerX, 10, null);

            g.setColor(Color.RED);
            if (gerX > -20 && gerX < 300) {
                g.drawString("OH no, Its Ger!!!!!", 400, 20);

            }

        }
    }

    @Override
    public void update() {

        // moving ger
        gerX += 2;

        // resetting ger
        if(gerX > 1000){
            gerX = -2000;
        }

        // sounds for ger every 40 pixels
        if (gerX % 40 == 0 && gerX > 0 && gerX < SpaceInvadersGUI.WIDTH) {
            try{
                playSound("sounds/ufo_highpitch.wav");
            }
            catch (Exception e) {e.printStackTrace();}
        }


        //Firing the enemy Bullets
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 11; j++) {

                // creating the random firing order of the aliens
                Random rand = new Random();
                int  randomFire = rand.nextInt(11);
                int  randomFireRow = rand.nextInt(5);

                // reducing the firing delay so you can fire
                timeWaiting -= 1;

                if(timeWaiting <= 0 && firing && !alienEntities[randomFireRow][randomFire].isDestroyed()){
                    SpaceInvadersGUI.alienBullets.add(new AlienBullet(alienEntities[randomFireRow][randomFire].getTopLeftXPos() + 20,
                            alienEntities[randomFireRow][randomFire].getTopLeftYPos() + 40, 5, 10, Color.RED));

                    SpaceInvadersGUI.alienBullets.add(new AlienBullet(getGerX() + 20,
                            10 + 40, 5, 10, Color.YELLOW));

                    // resetting the time waiting
                    timeWaiting = bulletTimingDelay;

                }


            }

        }

        //Checking the y axis f the aliens to stop the game
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 11; j++) {
                if(alienEntities[i][j].getTopLeftYPos() + alienEntities[i][j].getHeight() >= 430){
                    //Create the player class
                    player = new Player(SpaceInvadersGUI.getPlayerScore(), JOptionPane.showInputDialog(null, "The aliens have reached you\nPlease enter your name: "));
                    // Resetting the static score
                    SpaceInvadersGUI.setPlayerScore(0);

                    //Keeping track of the highScores
                    if(gameMain.getHighScorersSize() < 11){
                        gameMain.addToHighScorers(player);

                        gameMain.sortHighScorers();

                        if(gameMain.getHighScorersSize() == 11){
                            gameMain.removeFirstLink();
                        }

                    }

                    gameMain.changeContentPane2();
                    // breaks the game loop if there are more than one alien crossing the permitted y axis
                    heightReached = true;
                    break;
                }
            }
        }



        // Checking for collisions between tank bullets and aliens
        for (int k = 0; k < SpaceInvadersGUI.bullets.size(); k++) {
            Bullet bullet = SpaceInvadersGUI.bullets.get(k);

            // Checking for collision between alien and tank bullets
            for (int i = 0; i < SpaceInvadersGUI.alienBullets.size(); i++) {
                AlienBullet alienBullet = SpaceInvadersGUI.alienBullets.get(i);

                // removing the bullets
                if (alienBullet.collidesWith(bullet)){
                    SpaceInvadersGUI.bullets.remove(bullet);
                    SpaceInvadersGUI.alienBullets.remove(alienBullet);
                }
            }


            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 11; j++) {

                    if (bullet.collidesWith(alienEntities[i][j])) {

                        try{
                            playSound("sounds/invaderkilled.wav");
                        }
                        catch (Exception e) { e.printStackTrace(); }

                        // checking the row that an alien was on the determine the enemy value / rank
                        if (i == 2 || i == 4){
                            SpaceInvadersGUI.setPlayerScore(SpaceInvadersGUI.getPlayerScore() + 10);
                            System.out.println("1 and 3");
                        }else if (i == 3 || i == 5){
                            SpaceInvadersGUI.setPlayerScore(SpaceInvadersGUI.getPlayerScore() + 25);
                            System.out.println("2 and 4");
                        }else{
                            SpaceInvadersGUI.setPlayerScore(SpaceInvadersGUI.getPlayerScore() + 50);
                            System.out.println("ekse");
                        }

                        // drawing the aliens off the screen and reducing their dimensions
                        SpaceInvadersGUI.bullets.remove(bullet);
                        alienEntities[i][j].setHeight(-1000);
                        alienEntities[i][j].setWidth(-1000);
                        alienEntities[i][j].setTopLeftXPos(-1000);
                        alienEntities[i][j].setTopLeftYPos(-1000);
                        alienEntities[i][j].setDestroyed(true);

                        // keeping track of deaths
                        deathCount++;
                        System.out.println(deathCount);

                        if(deathCount == 54){
                            delta *= 5;
                            delta2 *= 5;

//                            if(alienEntities[i][j].getTopLeftXPos() > 100){
//
//                                try{
//                                    playSound("sounds/fastinvader1.wav");
//                                }
//                                catch (Exception e) { e.printStackTrace(); }
//
//                            }
//                            if(alienEntities[i][j].getTopLeftXPos() > 200){
//
//                                try{
//                                    playSound("sounds/fastinvader2.wav");
//                                }
//                                catch (Exception e) { e.printStackTrace(); }
//
//                            }
//                            if(alienEntities[i][j].getTopLeftXPos() > 300){
//
//                                try{
//                                    playSound("sounds/fastinvader3.wav");
//                                }
//                                catch (Exception e) { e.printStackTrace(); }
//
//                            }
//                            if(alienEntities[i][j].getTopLeftXPos() > 400){
//
//                                try{
//                                    playSound("sounds/fastinvader4.wav");
//                                }
//                                catch (Exception e) { e.printStackTrace(); }
//                            }

                        }

                        // all the aliens are dead
                        if (deathCount == 55){

                            delta /= 5;
                            delta2 /= 5;

                            for (int l = 0; l < 5; l++) {
                                for (int m = 0; m < 11; m++) {

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
            }



        }

        // checking for collisions with geraldine
        for (int i = 0; i < SpaceInvadersGUI.bullets.size(); i++) {
            Rectangle r2 = new Rectangle(SpaceInvadersGUI.bullets.get(i).getTopLeftXPos(),
                    SpaceInvadersGUI.bullets.get(i).getTopLeftYPos(),
                    SpaceInvadersGUI.bullets.get(i).getWidth(),
                    SpaceInvadersGUI.bullets.get(i).getHeight());

            // Calculating the random value for hitting geraldine
            if(r2.intersects(getGerX(), 10, 50 ,50)){
                System.out.println("It hit ger");
                SpaceInvadersGUI.bullets.remove(i);
                setGerX(-2000);
                SpaceInvadersGUI.setPlayerScore(SpaceInvadersGUI.getPlayerScore() +
                        ((int)(Math.random() * 150) + 51));
                System.out.println(SpaceInvadersGUI.getPlayerScore());
            }
        }

        //Setting the directions of the aliens
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 11; j++) {

                //Move right
                if(alienEntities[i][j].getTopLeftXPos() < 1000 && !alienEntities[i][j].isDestroyed())
                    alienEntities[i][j].setTopLeftXPos(alienEntities[i][j].getTopLeftXPos() + delta);

                //Move left
                if(alienEntities[i][j].topLeftXPos >= 1000 - alienEntities[i][j].width   && !alienEntities[i][j].isDestroyed()){
                    for (int k = 0; k < 5; k++) {
                        for (int l = 0; l < 11; l++) {
                            delta = -delta2;
                            alienEntities[k][l].setTopLeftYPos(alienEntities[k][l].getTopLeftYPos() + 15);

                        }

                    }

                }

                //move back right again
                if(alienEntities[i][j].topLeftXPos < 0 && !alienEntities[i][j].isDestroyed()){
                    for (int k = 0; k < 5; k++) {
                        for (int l = 0; l < 11; l++) {
                            delta = delta2;
                            alienEntities[k][l].setTopLeftYPos(alienEntities[k][l].getTopLeftYPos() + 15);

                        }
                    }
                }
            }

        }


    }

    public void setGerX(int x){
        gerX = x;
    }

    public int getGerX(){
        return gerX;
    }

    public boolean isHeightReached(){
        return heightReached;
    }

    public void setFiring(boolean firing) {
        this.firing = firing;
    }


}
