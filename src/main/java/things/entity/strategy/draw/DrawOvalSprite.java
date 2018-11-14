package things.entity.strategy.draw;

import things.entity.GameComponent;

import java.awt.*;

public class DrawOvalSprite implements DrawSprite {
    private GameComponent gameComponent;

    public DrawOvalSprite(GameComponent gameComponent) {
        this.gameComponent = gameComponent;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        int x = gameComponent.getTopLeftXPos();
        int y = gameComponent.getTopLeftYPos();
        int width = gameComponent.getWidth();
        int height = gameComponent.getHeight();

        graphics2D.setColor(gameComponent.getColor());
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawOval(x, y, width*5, height*5);
        graphics2D.fillOval(x, y, width*5, height*5);
    }
}
