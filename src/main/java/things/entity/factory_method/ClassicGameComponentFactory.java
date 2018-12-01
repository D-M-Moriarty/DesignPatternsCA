package things.entity.factory_method;

import things.entity.GameComponent;

public class ClassicGameComponentFactory extends GameComponentFactory {

    @Override
    protected GameComponent createGameComponent(ComponentType component) {
        return null;
    }
}
