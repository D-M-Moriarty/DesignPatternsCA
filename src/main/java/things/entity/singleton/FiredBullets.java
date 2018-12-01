package things.entity.singleton;

import things.entity.Bullet;
import things.entity.GameComponent;
import things.entity.observer.Observer;
import things.entity.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class FiredBullets implements Subject, Observer {
    private static FiredBullets alienBullets = new FiredBullets();
    private static FiredBullets tankBullets = new FiredBullets();
    private List<Observer> observers;
    private List<Bullet> bullets;
    private GameComponent bulletToObserve;

    private FiredBullets() {
        this.observers = new ArrayList<>();
        bullets = new ArrayList<>();
    }

    public static FiredBullets getAlienBullets() {
        return alienBullets;
    }

    public static FiredBullets getTankBullets() {
        return tankBullets;
    }

    public void addBullet(Bullet bullet) {
        bullet.registerObserver(this);
        bullets.add(bullet);
    }

    public Bullet getBullet(int i) {
        return bullets.get(i);
    }

    public void removeBullet(Bullet bullet) {
        bullets.remove(bullet);
    }

    public void removeBullet(int bulletIndex) {
        bullets.remove(bulletIndex);
    }

    public int size() {
        return bullets.size();
    }
    @Override
    public void notifyObservers() {
        for (Observer observer: observers)
            observer.updateObserver(bulletToObserve);
        bulletToObserve = null;
    }

    @Override
    public void updateObserver(GameComponent gameComponent) {
        this.bulletToObserve = gameComponent;
        notifyObservers();
    }
    @Override
    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }
    @Override
    public void removeObserver(Observer observer) {
        this.observers.add(observer);
    }
}
