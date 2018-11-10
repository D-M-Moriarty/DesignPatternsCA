package things.entity.singleton;

import things.entity.Bullet;
import things.entity.observer.Observer;
import things.entity.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class FiredBullets implements Subject, Observer {
    private static FiredBullets alienBullets;
    private static FiredBullets tankBullets;
    private List<Observer> observers;
    private List<Bullet> bullets;
    private Bullet bulletToObserve;

    private FiredBullets() {
        this.observers = new ArrayList<>();
        bullets = new ArrayList<>();
    }

    public static FiredBullets getAlienBullets() {
        if (alienBullets == null)
            alienBullets = new FiredBullets();
        return alienBullets;
    }

    public static FiredBullets getTankBullets() {
        if (tankBullets == null)
            tankBullets = new FiredBullets();
        return tankBullets;
    }

    public void addBullet(Bullet bullet) {
        bullet.registerObserver(this);
        bullets.add(bullet);
    }

    public void notifyObservers() {
        for (Observer observer: observers)
            observer.updateObserver(bulletToObserve);
        bulletToObserve = null;
    }

    @Override
    public void updateObserver(Bullet bullet) {
        this.bulletToObserve = bullet;
        notifyObservers();
    }

    public void registerObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observers.add(observer);
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

}
