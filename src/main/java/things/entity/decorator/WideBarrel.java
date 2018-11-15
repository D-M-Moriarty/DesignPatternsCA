package things.entity.decorator;

public class WideBarrel extends TankBarrelDecorator {

    private static final int NEW_WIDTH = 10;
    private static final int NEW_POSITION = 5;

    public WideBarrel(AbstractBarrel abstractBarrel) {
        super(abstractBarrel.getTopLeftXPos(), abstractBarrel.getTopLeftYPos(),
                abstractBarrel.getWidth(), abstractBarrel.getHeight(),
                abstractBarrel.getColor(), abstractBarrel.getMovement().getHorizontalSpeed(), abstractBarrel);
        this.width += NEW_WIDTH;
        this.topLeftXPos -= NEW_POSITION;
    }


}
