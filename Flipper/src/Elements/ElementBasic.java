package Elements;

public class ElementBasic {

    protected int position[] = new int[2];
    /**
     * Les deux définient la position de l'élément dans un repère cartésien
     */
    protected int positionX, positionY;

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

    /**
     * Les vecteurs normales selon X et Y
     */
    protected float nX,nY;

    public ElementBasic(){
        System.out.println("Element created");
    }
    public ElementBasic(int positionX,int positionY){
        this.positionY=positionX;
        this.positionY=positionY;
    }
    public ElementBasic(int positionX,int positionY,float smoothness,float elasticity){
        this.positionY=positionX;
        this.positionY=positionY;
        this.smoothness=smoothness;
        this.elasticity=elasticity;
    }


}
