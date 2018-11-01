package things.entity.factory_method;

import things.GameMain;
import things.entity.*;

import java.awt.*;

import static things.SpaceInvadersGUI.WIDTH;

public class StandardGameComponentFactory extends GameComponentFactory {
    public StandardGameComponentFactory(GameMain gameMain) {
        super(gameMain);
    }

    @Override
    protected GameComponent createGameComponent(Type component) {
        switch (component) {
            case TANK:
                return new Tank(WIDTH/2-60, 580, 120, 50, Color.GREEN, 3, 5, gameMain);
            case LIFE1:
                return new Tank(920, 10, 60, 25, Color.GREEN, 3, 5, gameMain);
            case LIFE2:
                return new Tank(850, 10, 60, 25, Color.GREEN, 3, 5, gameMain);
            case LIFE3:
                return new Tank(780, 10, 60, 25, Color.GREEN, 3, 5, gameMain);
            case BARREL:
                return new Barrel(WIDTH/2-5, 570, 10, 10, Color.GREEN, 5);
            case BARRIER1:
                return new Barrier(90, 470, 120, 70, Color.GREEN);
            case BARRIER2:
                return new Barrier(WIDTH/2-60, 470, 120, 70, Color.GREEN);
            case BARRIER3:
                return new Barrier(790, 470, 120, 70, Color.GREEN);
            case ALIENS:
                return new AlienInvaders(50, 50, 50, 50, Color.WHITE, gameMain);
                default: return null;
        }
    }
}
