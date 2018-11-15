package things.entity.factory_method;

import things.GameMain;
import things.entity.GameComponent;

import java.io.Serializable;

public abstract class GameComponentFactory implements Serializable {
    protected final GameMain gameMain;

    GameComponentFactory(GameMain gameMain) {
        this.gameMain = gameMain;
    }

    protected abstract GameComponent createGameComponent(Type component);

    public synchronized GameComponent getComponent(Type type) {
       return createGameComponent(type);
    }
}
