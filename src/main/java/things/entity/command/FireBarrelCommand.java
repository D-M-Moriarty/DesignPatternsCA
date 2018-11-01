package things.entity.command;

import things.entity.Barrel;
import things.entity.Tank;
import things.entity.decorator.AbstractBarrel;

public class FireBarrelCommand implements Command {
    private AbstractBarrel barrel;

    public FireBarrelCommand(AbstractBarrel barrel) {
        this.barrel = barrel;
    }

    @Override
    public void execute() {
        barrel.setFiring(false);
    }

    @Override
    public void unexecute() {
        barrel.setFiring(true);
    }
}
