package things.entity.singleton;

import things.Bullet;
import things.entity.observer.Observer;
import things.entity.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class FiredBullets implements Subject {
    private static FiredBullets alienBullets;
    private static FiredBullets tankBullets;
    private List<Observer> observers;
    private List<Bullet> bullets;

    private FiredBullets() {
        this.observers = new ArrayList<Observer>();
        bullets = new ArrayList<Bullet>();
    }

    public static synchronized FiredBullets getAlienBullets() {
        if (alienBullets == null) {
            alienBullets = new FiredBullets();
        }
        return alienBullets;
    }

    public static synchronized FiredBullets getTankBullets() {
        if (tankBullets == null) {
            tankBullets = new FiredBullets();
        }
        return tankBullets;
    }

    public void addBullet(Bullet bullet) {
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

    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void notifyObserver() {
        for (Observer observer: observers)
            observer.updateObserver();
    }

    public int size() {
        return bullets.size();
    }
}
