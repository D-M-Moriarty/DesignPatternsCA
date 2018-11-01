package things.entity.command;

import things.entity.decorator.AbstractBarrel;
import things.entity.decorator.MoltenBullet;

public class MakeBulletMoltenCommand implements Command {
    private AbstractBarrel barrel;

    public MakeBulletMoltenCommand(AbstractBarrel barrel) {
        this.barrel = barrel;
    }

    @Override
    public void execute() {
        if (barrel instanceof MoltenBullet)
            barrel = barrel.getBarrel();
        else
            barrel = new MoltenBullet(barrel);
    }

    @Override
    public void unexecute() {

    }
}
