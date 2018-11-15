package things.entity.strategy.movement;

import things.entity.GameComponent;

public class StandardHorizontalMovement implements HorizontalMovement {
    private GameComponent gameComponent;
    private boolean left;
    private boolean right;
    private int deltaX;
    private int horizontalSpeed;

    public StandardHorizontalMovement(GameComponent gameComponent) {
       this.gameComponent = gameComponent;
    }

    @Override
    public void moveHorizontally() {
        // If the left attribute has the value of true
        if(left){
            // The next position of deltaX is the value of the horizontal speed ie. 5 pixels to the left
            deltaX = horizontalSpeed;
            // The original value of topLeftXPos is now the original value minus the next positions value deltaX
            gameComponent.setTopLeftXPos(gameComponent.getTopLeftXPos() - deltaX);
        }
        // If the right attribute has the value of true
        if(right){
            // The next position of x is the value of the horizontal speed ie. 5 pixels to the right
            deltaX  = horizontalSpeed;
            // The original value of x is now the original value plus the next positions value
            gameComponent.setTopLeftXPos(gameComponent.getTopLeftXPos() + deltaX);
        }

        deltaX = 0;
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
        // not applicable
    }

    @Override
    public void setDown(boolean down) {
        // not applicable
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
