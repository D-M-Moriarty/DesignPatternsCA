package things.entity.command;

import things.entity.GameComponent;
import things.entity.decorator.AbstractBarrel;
import things.entity.decorator.WideBarrel;

public class WidenBarrelCommand implements Command {
    private AbstractBarrel barrel;

    public WidenBarrelCommand(GameComponent barrel) {
        this.barrel = (AbstractBarrel) barrel;
    }

    @Override
    public void execute() {
        if (barrel.hasDecorator("WideBarrel")) {
            barrel.withoutDecorator("WideBarrel");
            barrel = barrel.getBarrel();
            System.out.println();
        } else
            barrel = new WideBarrel(barrel);
        // TODO fix this horrible issue
//            if (barrel instanceof WideBarrel)
//                barrel = barrel.getBarrel();
//            else
//                barrel = new WideBarrel(barrel);
    }

    @Override
    public void unexecute() {

    }
}
