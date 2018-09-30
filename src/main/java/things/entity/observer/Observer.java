package things.entity.observer;

import things.Bullet;

public interface Observer {
    // TODO maybe user GameComponent instead of bullet
    void updateObserver(Bullet bullet);
}
