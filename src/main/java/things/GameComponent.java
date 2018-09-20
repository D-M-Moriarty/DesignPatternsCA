package things;

//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;

import things.entity.draw.DrawSprite;
import things.entity.update.UpdateSprite;

import java.awt.*;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * This is an abstract class called GameComponent for creating different game entities
 *
 * @author Darren Moriarty
 * created on 11/11/2016.
 *
 * @version 2.0
 */

// This class is not instantiable
public abstract class GameComponent {

    //Class attributes are protected as they are only to be accessed subclasses of this class

    // The position of the x coordinate of this entity
    protected int topLeftXPos;
    // The position of the y coordinate of the is entity
    protected int topLeftYPos;
    // The width of this entity
    protected int width;
    // The height of this entity
    protected int height;
    // The colour of this entity
    protected Color color;
    protected UpdateSprite updateSprite;
    protected DrawSprite drawSprite;

    /** 5 argument constructor method
     *
     * @param topLeftXPos value passed into the method
     * @param topLeftYPos value passed into the method
     * @param width value passed into the method
     * @param height value passed into the method
     * @param color value passed into the method
     *
     * this.width = width assigns the value of the argument to the class attribute
     */
    public GameComponent(int topLeftXPos, int topLeftYPos, int width, int height, Color color){
        setTopLeftXPos(topLeftXPos);
        setTopLeftYPos(topLeftYPos);
        setWidth(width);
        setHeight(height);
        setColor(color);
    }

    // List of abstract methods

    /** mutator method to set the top left X position
     * of the entity being created
     * THis is from the left of the screen towards th right
     @param topLeftXPos the top left corner*/
    public void setTopLeftXPos(int topLeftXPos) {
        this.topLeftXPos = topLeftXPos;
    }

    /**
     * mutator method to set the top left Y position
     * of the entity being created
     * This is from the top of the screen down
     *
     * @param topLeftYPos the top left corner
     */
    public void setTopLeftYPos(int topLeftYPos) {
        this.topLeftYPos = topLeftYPos;
    }

    /**
     * mutator method to set the width of the game component
     * @param width is the width of the game entity
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * mutator method to set the height of the game component
     * @param height is the height of the game entity
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * mutator method to set the colour of the game component
     * @param color is the colour of the game entity
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Accessor method to get the value of the top left x position
     * @return the top left x position of the entity
     */
    public int getTopLeftXPos() {
        return this.topLeftXPos;
    }

    /**
     *Accessor method to get the value of the top left y position
     * @return the top left y position of the entity
     */
    public int getTopLeftYPos() {
        return this.topLeftYPos;
    }

    /**
     * Accessor method to get the value of the width
     * @return the width of the entity
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Accessor method to get the value of the height
     * @return the height of the entity
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Accessor method to get the value of the colour
     * @return the colour of the entity
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * This method draws graphics to the screen
     * @param g is a reference to the graphics object
     */
    public abstract void draw(Graphics2D g);

    /**
     *
     */
    public abstract void update();

    public void updateEntity() {
        updateSprite.update();
    }

    public void drawEntity(Graphics2D graphics2D) {
        drawSprite.draw(graphics2D);
    }

    public UpdateSprite getUpdateSprite() {
        return updateSprite;
    }

    public void setUpdateSprite(UpdateSprite updateSprite) {
        this.updateSprite = updateSprite;
    }

    public DrawSprite getDrawSprite() {
        return drawSprite;
    }

    public void setDrawSprite(DrawSprite drawSprite) {
        this.drawSprite = drawSprite;
    }

    /**
     *
     * @param g a reference to another GameComponent object
     * @return the boolean value of true or false, to tell if their has been an intersection
     */
    public boolean collidesWith(GameComponent g) {

        Rectangle r1 = new Rectangle(g.getTopLeftXPos(), g.getTopLeftYPos(),
                g.getWidth(), g.getHeight());
        Rectangle r2 = new Rectangle(this.getTopLeftXPos(), this.getTopLeftYPos(),
                this.getWidth(), this.getHeight());

        if (r1.intersects(r2)){
            return true;
        }

        return false;
    }



    /**
     *
     * @return a string to show that the class is working
     */
    public String toString(){
        return "Game Component is working";
    }

    /*****************************************************
     *    Title: Java sound example - how to play a sound file in Java
     *    Author: Alvin Alexander
     *    Site owner/sponsor: Alvin Alexander.com
     *    Date: June 3 2016
     *    Availability: http://alvinalexander.com/java/java-audio-example-java-au-play-sound (Accessed 29 November 2016)
     *****************************************************/

    public static void playSound(String path) throws Exception{
        // open the sound file as a Java input stream
        String gongFile = path;
        InputStream in = new FileInputStream(gongFile);

        // create an audiostream from the inputstream
//        AudioStream audioStream = new AudioStream(in);
//
//        // play the audio clip with the audioplayer class
//        AudioPlayer.player.start(audioStream);
    }

} // End of class
