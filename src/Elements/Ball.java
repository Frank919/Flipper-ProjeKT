package Elements;

public class Ball extends ElementKinetic{
    protected int radius;

    public Ball(int PositionX, int PositionY, int radius,int messe){
        super(PositionX, PositionY, messe);
        this.radius=radius;
        
        
    }
    public void move(){

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
     * Et si    {@code coeffFrction = 1}, la vitesse tangentielle se sera anullée.
     *          {@code coeffFrction = 0}, la vitesse tangentielle ne varie pas.
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
     *          {@code coeffRebound = 1}, la vitesse normale ne varie pas.
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
