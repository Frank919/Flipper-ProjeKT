package Elements;

public class ElementKinetic extends ElementBasic{

    /**
     * La masse est définie en kg.
     */
    protected int mass;

    /**
     * La composition X de vitesse en pixel/s, positive vers la droite.
     */
    protected double velocityX;
    /**
     * La composition Y de vitesse en pixel/s, positive vers le bas.
     */
    protected double velocityY;

    /**
     * L'accélération est définie selon l'axe X et l"axe Y, en pixel/s^2.
     */
    protected long accelerationX, accelerationY;

    public ElementKinetic(){

    }
    public ElementKinetic(int PositionX,int PositionY){
        super(PositionX, PositionY);
    }
    public ElementKinetic(int positionX,int positionY, int mass,double smoothness,double elasticity){
        super(positionX, positionY);
        this.smoothness = smoothness;
        this.elasticity = elasticity;
        this.mass = mass;
    }

}
