package Elements;


public class Ball extends ElementKinetic{
    protected int radius;
    protected float force[] = new float[2];
    /**
     * 
     * @param positionX
     *          Coordonnée X du centre de cette balle
     * @param positionY
     *          Coordonnée Y du centre de cette balle
     * @param radius
     *          Radium de cette balle
     * @param messe
     *          Masse de cette balle
     * @param smoothness
     *          
     * @param elasticity
     */
    public Ball(int positionX, int positionY, int radius,int messe,float smoothness,float elasticity){
        super(positionX, positionY, messe, smoothness, elasticity);
        this.radius=radius; 
    }
    public float movesX(){
        //TO DO with vitesseX
        float displacementXPerFrame = velocityX * GameTable.frameTime;
        positionX += displacementXPerFrame;
        return displacementXPerFrame;
    }
    public float movesY(){
        //TO DO with vitesseY
        float displacementYPerFrame = velocityY * GameTable.frameTime;
        positionY += displacementYPerFrame;
        return displacementYPerFrame;
    }

    /**
     * Cette méthode permet de calculer la variation de la vitesse en raison de la gravitation,
     * qui impacte la vitesse selon Y.
     */
    public final void falls(){
        int g = 10;
        velocityY-=g*GameTable.frameTime;
    }

    /**
     * Cette méthode permet de calculer la variation de la vitesse en raison de la collision et de la frction,
     * qui impacte la vitesse normale et tangentielle.
     * 
     * Le {@code coeffRebound} est défini par le produit de {@code elasticity} des deux objets frotté,
     * donc entre 0 et 1.
     * Et si    {@code coeffRebound = 0}, la vitesse normale se sera anullée.
     *    Si    {@code coeffRebound = 1}, la vitesse normale ne varie pas.
     * 
     * Le {@code coeffFriction} est défini par le produit de {@code smoothness} des deux objets frotté,
     * donc entre 0 et 1.
     * Et si    {@code coeffFriction = 1}, la vitesse tangentielle se sera anullée.
     *    Si    {@code coeffFriction = 0}, la vitesse tangentielle ne varie pas.
     * 
     * @param E 
     *          Un {@code elementBasic} qui sera heurté et frotté par la balle
     * 
     * @return
     */
    public final void collidesWith(ElementBasic E){
        float coeffRebound = elasticity * E.elasticity;
        float coeffFriction = smoothness * E.smoothness;
        float vNX = (velocityX * E.nX + velocityX * E.nY) * E.nX;
        float vNY = (velocityX * E.nX + velocityX * E.nY) * E.nY;
        float vTX = velocityX - vNX;
        float vTY = velocityY - vNY;
        //Collision
        vNX = -coeffRebound * vNX;
        vNY = -coeffRebound * vNY;
        //Friciton
        vTX = (1 - coeffFriction) * vTX;
        vTY = (1 - coeffFriction) * vTY;
        //Conclusion
        velocityX = vTX + vNX;
        velocityY = vTY + vNY;
    }
}
