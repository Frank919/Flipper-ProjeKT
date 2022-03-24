package Elements;

public class Circle extends Obstacle{
    
    protected float radius;
    protected int centerX, centerY;

    public Circle(ElementBasic elm, int r, float e, float s){
        radius = r;
        centerX = elm.positionX;
        centerX = elm.positionY;
    } 


}
