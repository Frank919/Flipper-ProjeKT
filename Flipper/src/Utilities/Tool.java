package Utilities;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Tool {
    /**
     * 获取图片
     *
     * @param imageName
     * @return
     */
    public static Image getImage(String imageName) {
        return new ImageIcon(Constants.RESOURCES_PATH + imageName).getImage();
    }

    /**
     * 开启一个 指定频率的定时器
     *
     * @param period
     * @param t
     */
    public static void task(long period, runnableTimer t) {
        java.util.Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                //当结束开关打开时，清除所有定时器
                if (Constants.TIMER_STOP_ON_OFF) {
                    timer.cancel();
                    return;
                }
                t.run();
            }
        };
        timer.schedule(timerTask, 0, period);
    }
}
