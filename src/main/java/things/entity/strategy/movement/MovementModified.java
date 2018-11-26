package things.entity.strategy.movement;

import things.entity.GameComponent;

public class MovementModified implements Movement {
    private GameComponent gameComponent;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private int deltaX;
    private int deltaY;
    private int horizontalSpeed;

    public MovementModified(GameComponent gameComponent) {
        this.gameComponent = gameComponent;
    }

    @Override
    public void moveSprite() {
        if(left){
            deltaX = horizontalSpeed;
            gameComponent.setTopLeftXPos(gameComponent.getTopLeftXPos() - deltaX);
        }
        if(right){
            deltaX  = horizontalSpeed;
            gameComponent.setTopLeftXPos(gameComponent.getTopLeftXPos() + deltaX);
        }
        if(up){
            deltaY  = horizontalSpeed;
            gameComponent.setTopLeftYPos(gameComponent.getTopLeftYPos() - deltaY);
        }
        if(down){
            deltaY  = horizontalSpeed;
            gameComponent.setTopLeftYPos(gameComponent.getTopLeftYPos() + deltaY);
        }

        deltaX = 0;
        deltaY = 0;
    }
    @Override
    public void setLeft(boolean left) {
        this.left = left;
    }
    @Override
    public void setRight(boolean right) {
        this.right = right;
    }

    @Override
    public void setUp(boolean up) {
        this.up = up;
    }

    @Override
    public void setDown(boolean down) {
        this.down = down;
    }

    @Override
    public int getSpeed() {
        return horizontalSpeed;
    }

    @Override
    public void setSpeed(int horizontalSpeed) {
        this.horizontalSpeed = horizontalSpeed;
    }
}
