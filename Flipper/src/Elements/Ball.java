package Elements;


public class Ball extends ElementKinetic{
    protected int radius;
    protected float force[] = new float[2];
    
    /**
     * Construire une balle
     * @param positionX
     *          Coordonnée X initiale du centre de cette balle dans le table vrai
     * @param positionY
     *          Coordonnée Y initiale du centre de cette balle dans le table vrai
     * @param radius
     *          Radium de cette balle
     * @param messe
     *          Masse de cette balle
     * @param smoothness
     *          Glissement défini entre 0 et 1 
     * 
     * @param elasticity
     *          Élasticity défini entre 0 et 1 
     * 
     * @see #collidesWith(ElementBasic e)
     */
    public Ball(int positionX, int positionY, int radius,int messe,float smoothness,float elasticity){
        /**
         * Ici on met la balle dans le pseudoTable car tous les calculs et simulations s'y passent.
         * Donc on fait décaler la position.
         */
        super(positionX + GameTable.detectionRange, positionY + GameTable.detectionRange, messe, smoothness, elasticity);
        this.radius=radius; 
    }

    /**
     * Mettre à jour la position de la balle
     */
    public void moves(){
        positionX += velocityX * GameTable.frameTime;
        positionY += velocityY * GameTable.frameTime;
    }
    

    /**
     * Cette méthode permet de calculer la variation de la vitesse en raison de la gravitation,
     * qui impacte la vitesse selon Y.
     */               
    public final void falls(){
        int g = 10;
        velocityY+=g*GameTable.frameTime;
    }

    /**
     * Cette méthode permet de calculer la variation de la vitesse en raison de la collision et de la frction,
     * qui impacte la vitesse normale et tangentielle.
     * 
     * Le {@code coeffRebound} est défini par le produit de {@code elasticity} des deux objets frotté,
     * donc entre 0 et 1.
     * Et si    {@code coeffRebound = 0}, la vitesse normale se sera anullée.
     * Si    {@code coeffRebound = 1}, la vitesse normale ne varie pas.
     * 
     * Le {@code coeffFriction} est défini par le produit de {@code smoothness} des deux objets frotté,
     * donc entre 0 et 1.
     * Et si    {@code coeffFriction = 1}, la vitesse tangentielle se sera anullée.
     *    Si    {@code coeffFriction = 0}, la vitesse tangentielle ne varie pas.
     * 
     * @param e 
     *          Un {@code elementBasic} qui sera heurté et frotté par la balle
     */
    public void collidesWith(ElementBasic e){
        //Calculer le coefficient répondissement et celui de fricion
        float coeffRebound = elasticity * e.elasticity;
        float coeffFriction = smoothness * e.smoothness;
        //Calculer la vitesse normale à un obstacle
        float vNX = (float)( (velocityX * e.nX + velocityX * e.nY) * e.nX);
        float vNY = (float)( (velocityX * e.nX + velocityX * e.nY) * e.nY);
        //Calculer la vitesse tangentielle à un obstacle
        float vTX = velocityX - vNX;
        float vTY = velocityY - vNY;
        if(e instanceof ElementKinetic){
            if(e instanceof Flipper){
                Flipper eF = (Flipper)e;
                //Collision 碰撞将法向速度反转，并考虑弹力系数
                vNX = - coeffRebound * vNX;
                vNY = - coeffRebound * vNY;
                //Friciton 摩擦将减小切向速度
                vTX = (1 - coeffFriction) * vTX;
                vTY = (1 - coeffFriction) * vTY;
                //Conclusion 将法向速度和切向速度相加，还原得到正交的速度
                velocityX = vTX + vNX;
                velocityY = vTY + vNY;

            }else if(e instanceof Launcher){
                Flipper eL = (Flipper)e;
 
             }
        }else if(e instanceof ElementStatic){
            //Collision 碰撞将法向速度反转，并考虑弹力系数
            vNX = - coeffRebound * vNX;
            vNY = - coeffRebound * vNY;
            //Friciton 摩擦将减小切向速度
            vTX = (1 - coeffFriction) * vTX;
            vTY = (1 - coeffFriction) * vTY;
            //Conclusion 将法向速度和切向速度相加，还原得到正交的速度
            velocityX = vTX + vNX;
            velocityY = vTY + vNY;
        }  
    }
    /**
     * Déterminer si on a la fin du jeu
     * @return  {@code true} si la balle est en dehors du table
     *      <li>{@code false} sinon
     *      
     */
    public boolean isOut(){
        if(positionY>GameTable.height){
            return true;
        }
        return false;
    }
    
    /**
     * @param e
     *      une chose
     * @return 
     *      si la balle est en contact avec une chose
     */
    public boolean isOnContectWith(ElementBasic e){
        if(e instanceof ElementStatic){
            float distance = (float)Math.sqrt(
                        (e.positionX - positionX)*(e.positionX - positionX) 
                        + (e.positionY - positionY)*(e.positionY - positionY)
                        
            );
            if(distance <= radius){
                return true;
            }
        }
        if(e instanceof Flipper){
            Flipper eF = (Flipper)e;
            
            int v1X = positionX - eF.centre.positionX;
            int v1Y = positionY - eF.centre.positionY;

            int v2X = positionX - eF.tip.positionX;
            int v2Y = positionY - eF.tip.positionY;

            int v3X = eF.tip.positionX - eF.centre.positionX;
            int v3Y = eF.tip.positionY - eF.centre.positionY;

            // float produitVectoriel = deltaX1 * deltaY2 - deltaX2 * deltaY1;
            float produitScalaire = v1X * v2X + v1Y * v2Y;

            // Calculer la distance entre la balle et la droite représentant le flipper
            float distance = (float)Math.sqrt(
                        v1X*v1X + v1Y*v1Y
                        - (v1X*v3X+v1Y*v3Y)*(v1X*v3X+v1Y*v3Y)
                        / (v3X*v3X + v3Y*v3Y)
            );
            // Si la distance est inférieur au rayon 
            // et si la balle se trouve entre le centre et le tip du flipper
            // on considère qu'il y a une collision
            if(distance <= radius && produitScalaire<=0){
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @return la position de la balle dans le table vrai
     */
    public String toString(){
        return "( " 
            + (positionX - GameTable.detectionRange) 
            + " , " 
            + (positionY - GameTable.detectionRange) 
            + " )";
    }
}
