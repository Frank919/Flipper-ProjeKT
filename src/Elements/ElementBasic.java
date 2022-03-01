package Elements;

public class ElementBasic {
    /**
     * Les deux définient la position de l'élément dans un repère cartésien
     */
    protected int PositionX, PositionY;

    /**
     * La glissance est definie entre 0 et 1.
     * 
     * @see Elements.Ball#rubAgainst()
     */
    protected float smoothness;

    /**
     * L'elasticité est definie entre 0 et 1.
     * 
     * @see Elements.Ball#collideWith()
     */
    protected float elasticity;

    public ElementBasic(){
        System.out.println("Element created");
    }
    public ElementBasic(int PositionX,int PositionY){
        this.PositionY=PositionX;
        this.PositionY=PositionY;
    }


}
