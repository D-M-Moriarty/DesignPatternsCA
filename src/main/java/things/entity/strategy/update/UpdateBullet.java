package things.entity.strategy.update;

import things.Bullet;
import things.GameComponent;

public class UpdateBullet implements UpdateSprite {
    private GameComponent gameComponent;

    public UpdateBullet(GameComponent gameComponent) {
        this.gameComponent = gameComponent;
    }

    public void update() {
        // TODO use Factory Method here?
        Bullet bullet = (Bullet) gameComponent;
        bullet.fireBullet();
        bullet.notifyObservers();
    }
}
