package things.entity.factory_method;

import things.GameMain;
import things.entity.GameComponent;

public class ClassicGameComponentFactory extends GameComponentFactory {

    @Override
    protected GameComponent createGameComponent(Type component) {
        return null;
    }
}
