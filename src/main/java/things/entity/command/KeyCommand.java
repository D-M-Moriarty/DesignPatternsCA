package things.entity.command;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

public class KeyCommand extends KeyAdapter {
    private Command up;
    private Command down;
    private Command left;
    private Command right;
    private Command space;
    private Command w;
    private Command m;
    private Command d;

    public KeyCommand(Command up, Command down, Command left, Command right,
                      Command space, Command w, Command m, Command d) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.space = space;
        this.w = w;
        this.m = m;
        this.d = d;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case VK_UP: up.execute();
                break;
            case VK_DOWN: down.execute();
                break;
            case VK_LEFT: left.execute();
                break;
            case VK_RIGHT: right.execute();
                break;
            case VK_SPACE: space.execute();
                break;
            case VK_W: w.execute();
                break;
            case VK_M: m.execute();
                break;
            case VK_D: d.execute();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case VK_UP: up.unexecute();
                break;
            case VK_DOWN: down.unexecute();
                break;
            case VK_LEFT: left.unexecute();
                break;
            case VK_RIGHT: right.unexecute();
                break;
            case VK_SPACE: space.unexecute();
                break;
            case VK_W: w.unexecute();
                break;
            case VK_M: m.unexecute();
                break;
            case VK_D: d.unexecute();
                break;
            default:
                break;
        }
    }

}
