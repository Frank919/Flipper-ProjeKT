package Elements;

public class ElementKinetic extends ElementBasic{

    /**
     * La masse est définie en kg.
     */
    protected int mass;

    /**
     * La composition X de vitesse en pixel/s, positive vers la droite.
     */
    protected float velocityX;
    /**
     * La composition Y de vitesse en pixel/s, positive vers le bas.
     */
    protected float velocityY;

    /**
     * L'accélération est définie selon l'axe X et l"axe Y, en pixel/s^2.
     */
    protected long accelerationX, accelerationY;

    public ElementKinetic(){

    }
    public ElementKinetic(int PositionX,int PositionY){
        super(PositionX, PositionY);
    }
    public ElementKinetic(int positionX,int positionY, int mass,float smoothness,float elasticity){
        super(positionX, positionY,smoothness,elasticity);
        this.mass = mass;
    }

}
