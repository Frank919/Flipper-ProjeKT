package IHM;
import javax.swing.*;
import java.awt.*;
/**
 * @author Chenglai FANG
 * @Description: La classe pour stocker et manipuler les images des éléments
 * @date 01/05/2022
 */
public class Picture {
    private int x,y,w,h;
    private Image image;
    /**
     * 
     * @param name Le nom de l'image dans le dossier Resource
     */
    public Picture(String name){
        this.image = new ImageIcon("./Flipper/src/Resource/"+name+".png").getImage();
        this.w = image.getWidth(null);
        this.h = image.getHeight(null);
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
