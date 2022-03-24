package Elements;

public class ElementBasic {
    /**
     * Les deux définient la position de l'élément dans un repère cartésien
     */
    protected int positionX, positionY;

    /**
     * La glissance est definie entre 0 et 1.
     * 
     * @see Elements.Ball#collidesWith()
     */
    protected float smoothness;

    /**
     * L'elasticité est definie entre 0 et 1.
     * 
     * @see Elements.Ball#collidesWith()
     */
    protected float elasticity;

    /**
     * Les vecteurs normales selon X et Y
     */
    protected double nX,nY;

    public ElementBasic(){
        
    }
    public ElementBasic(int positionX,int positionY){
        this.positionX=positionX;
        this.positionY=positionY;
    }
    public ElementBasic(int positionX,int positionY,float smoothness,float elasticity){
        this(positionX, positionY);
        this.smoothness=smoothness;
        this.elasticity=elasticity;
    }
    public ElementBasic(int positionX,int positionY,double nx,double ny){
        this(positionX, positionY);
        this.nX=nx;
        this.nY=ny;
    }
    public ElementBasic(int positionX,int positionY,double nx,double ny,float smoothness,float elasticity){
        this(positionX, positionY, nx, ny);
        this.smoothness=smoothness;
        this.elasticity=elasticity;
    }


}
