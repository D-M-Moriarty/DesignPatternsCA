package things.entity.strategy.movement;

public interface HorizontalMovement {
    void moveHorizontally();
    void setLeft(boolean left);
    void setRight(boolean right);
    void setUp(boolean up);
    void setDown(boolean down);
    int getHorizontalSpeed();
    void setHorizontalSpeed(int horizontalSpeed);
}
