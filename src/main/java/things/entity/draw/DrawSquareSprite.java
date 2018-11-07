package things.entity.draw;

import things.GameComponent;

import java.awt.*;

public class DrawSquareSprite implements DrawSprite {

    private GameComponent gameComponent;

    public DrawSquareSprite(GameComponent gameComponent) {
        this.gameComponent = gameComponent;
    }

    public void draw(Graphics2D graphics2D) {
        int x = gameComponent.getTopLeftXPos();
        int y = gameComponent.getTopLeftYPos();
        int width = gameComponent.getWidth();
        int height = gameComponent.getHeight();

        graphics2D.setColor(gameComponent.getColor());
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawRect(x, y, width, height);
        graphics2D.fillRect(x, y, width, height);
    }
}
