package Elements;

public class ElementKinetic extends ElementBasic{

    /**
     * La masse est définie en kg.
     */
    protected int mass;

    /**
     * La vitesse est définie selon l'axe X et l"axe Y, en pixel/s.
     */
    protected long vitesseX, vitesseY;

    /**
     * L'accélération est définie selon l'axe X et l"axe Y, en pixel/s^2.
     */
    protected long accelerationX, accelerationY;

    public ElementKinetic(){
        System.out.println("Element created");
    }
    public ElementKinetic(int PositionX,int PositionY){
        super(PositionX, PositionY);
    }
    public ElementKinetic(int PositionX,int PositionY, int mass){
        super(PositionX, PositionY);
        this.mass = mass;
    }

}
