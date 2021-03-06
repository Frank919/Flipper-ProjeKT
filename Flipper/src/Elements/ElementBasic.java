package Elements;

/**
 * @author Chenglai FANG
 * @Description: Les éléments dans ce jeu
 * @date 01/05/2022
 */
public class ElementBasic {
    /**
     * La position de l'élément dans un repère cartésien
     */
    protected int positionX, positionY;

    /**
     * La glissance est definie entre 0 et 1.
     * 
     * @see Elements.Ball#collidesWith()
     */
    protected double smoothness;

    /**
     * L'elasticité est definie entre 0 et 1.
     * 
     * @see Elements.Ball#collidesWith()
     */
    protected double elasticity;

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
    
    public ElementBasic(int positionX,int positionY,double nx,double ny){
        this(positionX, positionY);
        this.nX=nx;
        this.nY=ny;
    }
    public ElementBasic(int positionX,int positionY,double nx,double ny,double smoothness,double elasticity){
        this(positionX, positionY, nx, ny);
        this.smoothness=smoothness;
        this.elasticity=elasticity;
    }
    public int getPositionX(){
        return positionX;
    }
    public int getPositionY(){
        return positionY;
    }
    public void setPositonX(int x){
        this.positionX = x;
    }
    public void setPositonY(int y){
        this.positionY = y;
    }

}
