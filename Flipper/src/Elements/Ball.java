package Elements;


public class Ball extends ElementKinetic{
    protected int radius;
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
    public float moveX(){
        //TO DO with vitesseX
        float displacementXPerFrame = velocityX * GameTable.frameTime;
        positionX += displacementXPerFrame;
        return displacementXPerFrame;
    }
    public float moveY(){
        //TO DO with vitesseY
        float displacementYPerFrame = velocityY * GameTable.frameTime;
        positionY += displacementYPerFrame;
        return displacementYPerFrame;
    }

    /**
     * Cette méthode permet de calculer la variation de la vitesse en raison de la gravitation,
     * qui impacte la vitesse selon Y.
     */
    public final void fall(){

    }




    /**
     * Cette méthode permet de calculer la variation de la vitesse en raison de la friction,
     * qui impacte la vitesse tangentielle.
     * 
     * Le {@code coeffFriction} est défini par le produit de {@code smoothness} des deux objets frotté,
     * donc entre 0 et 1.
     * Et si    {@code coeffFriction = 1}, la vitesse tangentielle se sera anullée.
     *    Si    {@code coeffFriction = 0}, la vitesse tangentielle ne varie pas.
     * 
     * @param E
     *          Un {@code elementBasic} qui sera frotté par la balle
     * 
     * @return 
     */
    public final void rubAgainst(ElementBasic E){
        float coeffFriction = smoothness * E.smoothness;

    }

    /**
     * Cette méthode permet de calculer la variation de la vitesse en raison de la collision,
     * qui impacte la vitesse normale.
     * 
     * Le {@code coeffRebound} est défini par le produit de {@code elasticity} des deux objets frotté,
     * donc entre 0 et 1.
     * Et si    {@code coeffRebound = 0}, la vitesse normale se sera anullée.
     *    Si    {@code coeffRebound = 1}, la vitesse normale ne varie pas.
     * 
     * @param E 
     *          Un {@code elementBasic} qui sera heurté par la balle
     * 
     * @return
     */
    public final void collideWith(ElementBasic E){
        float coeffRebound = elasticity * E.elasticity;

    }
}
