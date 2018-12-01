package things.entity.factory_method;

import things.entity.GameComponent;

import java.io.Serializable;

public abstract class GameComponentFactory implements Serializable {

    protected abstract GameComponent createGameComponent(ComponentType component);

    public synchronized GameComponent getComponent(ComponentType componentType) {
       return createGameComponent(componentType);
    }
}
