package things.entity.update;

import things.AlienBullet;
import things.GameComponent;

public class UpdateAlienBullet implements UpdateSprite {
    public void update(GameComponent gameComponent) {
        AlienBullet alienBullet = (AlienBullet) gameComponent;
        alienBullet.fireBullet();
        alienBullet.removeBullet();
    }
}
