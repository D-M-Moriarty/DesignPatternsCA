package things.entity;

import things.entity.observer.Observer;
import things.entity.observer.Subject;
import things.entity.strategy.draw.DrawSquareSprite;
import things.entity.strategy.update.UpdateBullet;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This is an instantiable class called Bullet for creating a bullet entity.
 * It is a sub-class of GameComponent therefore it inherits all of its
 * attributes and abstract methods
 *
 * @author Darren Moriarty
 * created on 11/11/2016.
 *
 * @version 2.0
 */
public class Bullet extends GameComponent implements Subject {

    private boolean isAlienBullet;
    private List<Observer> observers;

    public Bullet(int topLeftXPos, int topLeftYPos, int width, int height, Color color, boolean isAlienBullet) {
        super(topLeftXPos, topLeftYPos, width, height, color);
        this.isAlienBullet = isAlienBullet;
        setUpdateSprite(new UpdateBullet(this));
        setDrawSprite(new DrawSquareSprite(this));
        this.observers = new ArrayList<>();
    }


    @Override
    public void update(){
    }

    @Override
    public void draw(Graphics2D g) {
    }

    public boolean isAlienBullet() {
        return isAlienBullet;
    }

    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer: observers)
            observer.updateObserver(this);
    }
}
