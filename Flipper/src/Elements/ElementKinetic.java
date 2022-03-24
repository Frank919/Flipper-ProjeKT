package Elements;

public class ElementKinetic extends ElementBasic{

    /**
     * La masse est définie en kg.
     */
    protected int mass;

    //protected float[] velocity =  new float[2];

    /**
     * La composition X de vitesse en pixel/s.
     */
    protected float velocityX;
    /**
     * La composition Y de vitesse en pixel/s.
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
