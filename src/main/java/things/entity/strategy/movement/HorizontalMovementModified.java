package things.entity.strategy.movement;

import things.entity.GameComponent;

public class HorizontalMovementModified implements HorizontalMovement {
    private GameComponent gameComponent;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    private int deltaX;
    private int deltaY;
    private int horizontalSpeed;

    public HorizontalMovementModified(GameComponent gameComponent) {
        this.gameComponent = gameComponent;
    }

    @Override
    public void moveHorizontally() {
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
    public int getHorizontalSpeed() {
        return horizontalSpeed;
    }

    @Override
    public void setHorizontalSpeed(int horizontalSpeed) {
        this.horizontalSpeed = horizontalSpeed;
    }
}
