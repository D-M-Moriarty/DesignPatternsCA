package things.entity.factory_method;

import things.entity.*;

import java.awt.*;

import static things.SpaceInvadersGUI.WIDTH;

public class StandardGameComponentFactory extends GameComponentFactory {
    private final int tankTopLeftXPos = WIDTH / 2 - 60;
    private final int tankTopLeftYPos = 580;
    private final int tankWidth = 120;
    private final int tankHeight = 50;
    private final Color green = Color.GREEN;
    private final int tankLivesLeft = 3;
    private final int horizontalSpeed = 5;
    private final int barrelTopLeftXPos = WIDTH / 2 - 5;
    private final int barrelTopLeftYPos = 570;
    private final int barrelWidth = 10;
    private final int barrelHeight = 10;

    @Override
    protected GameComponent createGameComponent(ComponentType component) {
        switch (component) {
            case TANK:
                return new Tank(tankTopLeftXPos, tankTopLeftYPos, tankWidth,
                        tankHeight, green, tankLivesLeft, horizontalSpeed);
            case LIFE1:
                return new Tank(920, 10, 60, 25, Color.GREEN, 3, 5);
            case LIFE2:
                return new Tank(850, 10, 60, 25, Color.GREEN, 3, 5);
            case LIFE3:
                return new Tank(780, 10, 60, 25, Color.GREEN, 3, 5);
            case BARREL:
                return new Barrel(barrelTopLeftXPos, barrelTopLeftYPos, barrelWidth,
                        barrelHeight, green, horizontalSpeed);
            case BARRIER1:
                return new Barrier(90, 470, 120, 70, Color.GREEN);
            case BARRIER2:
                return new Barrier(WIDTH/2-60, 470, 120, 70, Color.GREEN);
            case BARRIER3:
                return new Barrier(790, 470, 120, 70, Color.GREEN);
            case ALIENS:
                return new AlienInvaders(50, 50, 50, 50, Color.WHITE);
                default: return null;
        }
    }
}
