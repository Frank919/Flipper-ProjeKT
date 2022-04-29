package Utilities;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public enum Keys {
    LEFT(KeyEvent.VK_A),
    RIGHT(KeyEvent.VK_D);

    private final static Set<Integer> keySet = new HashSet<>();

    Keys(int keyValue) {
        this.keyValue = keyValue;
    }

    private int keyValue;//键值

    /**
     * 是否使用了某键
     *
     * @return
     */
    public boolean use() {
        return keySet.contains(keyValue);
    }

    /**
     * 添加按键
     *
     * @param keyCode
     */
    public static void add(int keyCode) {
        keySet.add(keyCode);
    }

    /**
     * 移除按键
     *
     * @param keyCode
     */
    public static void remove(int keyCode) {
        keySet.remove(keyCode);
    }
}
