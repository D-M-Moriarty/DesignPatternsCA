package things.entity.factory_method;

import things.GameMain;
import things.entity.GameComponent;

public abstract class GameComponentFactory {
    protected final GameMain gameMain;

    GameComponentFactory(GameMain gameMain) {
        this.gameMain = gameMain;
    }

    protected abstract GameComponent createGameComponent(Type component);

    public GameComponent getComponent(Type type) {
       return createGameComponent(type);
    }
}
