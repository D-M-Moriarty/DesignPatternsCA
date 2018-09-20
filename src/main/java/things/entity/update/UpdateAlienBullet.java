package things.entity.update;

import things.AlienBullet;
import things.GameComponent;

public class UpdateAlienBullet implements UpdateSprite {

    private AlienBullet alienBullet;

    public UpdateAlienBullet(AlienBullet alienBullet) {
        this.alienBullet = alienBullet;
    }

    public void update() {
        alienBullet.fireBullet();
        alienBullet.removeBullet();
    }
}
