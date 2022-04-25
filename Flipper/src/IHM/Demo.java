package IHM;

import javax.swing.*;
import java.awt.*;

public class Demo {
    private int x,y,w,h;
    private Image image;
    public Demo(){
        this.image = new ImageIcon("Flipper/src/Resource/balle3.png").getImage();
        this.w = 100;
        this.h = 100;
        this.x = 50;
        this.y = 50;
    }
    public Image getImage(){
        return this.image;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getW(){
        return w;
    }
    public int getH(){
        return h;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
}
