package things.entity.command;

import things.entity.GameComponent;
import things.entity.decorator.AbstractBarrel;
import things.entity.decorator.DoubleBarrel;

public class EquipDoubleBarrelCommand implements Command {
    private AbstractBarrel barrel;

    public EquipDoubleBarrelCommand(GameComponent barrel) {
        this.barrel = (AbstractBarrel) barrel;
    }

    @Override
    public void execute() {
        if (barrel instanceof DoubleBarrel)
            barrel = barrel.getBarrel();
        else
            barrel = new DoubleBarrel(barrel);
    }

    @Override
    public void unexecute() {

    }
}
