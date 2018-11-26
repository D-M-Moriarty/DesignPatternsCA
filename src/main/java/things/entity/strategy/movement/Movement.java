package things.entity.strategy.movement;

public interface Movement {
    void moveSprite();
    void setLeft(boolean left);
    void setRight(boolean right);
    void setUp(boolean up);
    void setDown(boolean down);
    int getSpeed();
    void setSpeed(int horizontalSpeed);
}
