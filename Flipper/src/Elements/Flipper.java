package Elements;

public class Flipper extends ElementKinetic{
    protected float angle;
    protected boolean isOnRight;
    
    /**
     * 
     */
    public Flipper(ElementBasic centre, float smoothness, float elasticity,Boolean isOnRight){
        this.smoothness = smoothness;
        this.elasticity = elasticity;
        this.isOnRight = isOnRight;
        int r = 5;
        ElementBasic s = new ElementBasic(centre.positionX + r, centre.positionY);
        Curve base = new Curve(s, s, centre, r, this.smoothness, this.elasticity);
    }
}
