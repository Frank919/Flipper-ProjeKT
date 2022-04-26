package Elements;


public class Ball extends ElementKinetic{
    protected int radius;
    
    /**
     * Construire une balle
     * @param positionX
     *          Coordonnée X initiale du centre de la balle dans le table vrai
     * @param positionY
     *          Coordonnée Y initiale du centre de la balle dans le table vrai
     * @param radius
     *          Radium de la balle
     * @param messe
     *          Masse de la balle
     * @param smoothness
     *          Glissement défini entre 0 et 1 
     * @param elasticity
     *          Élasticity défini entre 0 et 1 
     * 
     * @see #collidesWith(ElementBasic e)
     */
    public Ball(int positionX, int positionY, int radius,int messe,float smoothness,float elasticity){
        /**
         * Ici on met la balle dans le pseudoTable car tous les calculs s'y passent.
         * Donc on fait décaler la position.
         */
        super(positionX + GameTable.margin, positionY + GameTable.margin, messe, smoothness, elasticity);
        this.radius=radius; 
    }

    /**
     * Mettre à jour la position de la balle
     */
    public void moves(){
        this.falls();
        positionX += velocityX * GameTable.refreshTime;
        positionY += velocityY * GameTable.refreshTime;
    }
    

    /**
     * Cette méthode permet de calculer la variation de la vitesse en raison de la gravitation,
     * qui impacte la vitesse selon Y.
     */               
    public void falls(){
        int g = 150;
        velocityY+=g*GameTable.refreshTime;
    }

    /**
     * Cette méthode permet de calculer la variation de la vitesse en raison de la collision et de la frction,
     * qui impacte la vitesse normale et tangentielle.
     * 
     * Le {@code coeffRebound} est défini par le produit de {@code elasticity} des deux objets en contact,
     * donc entre 0 et 1.
     * Et si    {@code coeffRebound = 0}, la vitesse normale se sera anullée.
     *    Si    {@code coeffRebound = 1}, la vitesse normale ne varie pas.
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
        if(e instanceof Flipper){
            Flipper eF = (Flipper)e;
            //Vecteur1 centre--->balle
            int v1X = positionX - eF.centre.positionX;
            int v1Y = positionY - eF.centre.positionY;
            //Vecteur3 centre--->tip
            int v3X = eF.tip.positionX - eF.centre.positionX;
            int v3Y = eF.tip.positionY - eF.centre.positionY;
            //Vecteur1 X vecteur3
            float produitVectoriel = v1X * v3Y - v3X * v1Y;
            //Vecteur1 . vecteur3
            float produitScalaire = v1X * v3X + v1Y * v3Y;
            //Déterminer quelle surface à impacter
            if( produitVectoriel <= 0){
                eF.nX = eF.n1X;
                eF.nY = eF.n1Y;
            }else{
                eF.nX = eF.n2X;
                eF.nY = eF.n2Y;
            }
            //Calculer la vitesse normale au flipper
            float vNX = (float)( (velocityX * eF.nX + velocityX * eF.nY) * eF.nX);
            float vNY = (float)( (velocityX * eF.nX + velocityX * eF.nY) * eF.nY);
            //Calculer la vitesse tangentielle au flipper
            float vTX = velocityX - vNX;
            float vTY = velocityY - vNY;

            //Déterminer la position contact sur le flipper
            float rayon = (float)(produitScalaire / Math.pow((v3X*v3X + v3Y*v3Y),2));
            float velocityLineX = (float)(eF.velocityAng * rayon * nX);
            float velocityLineY = (float)(eF.velocityAng * rayon * nY);
            //Calculer la vitesse normale du flipper
            float vFNX = (float)( (velocityLineX * eF.nX + velocityLineY * eF.nY) * eF.nX);
            float vFNY = (float)( (velocityLineX * eF.nX + velocityLineY * eF.nY) * eF.nY);

            //Collision 
            vNX = - coeffRebound * vNX + vFNX - vNX;
            vNY = - coeffRebound * vNY + vFNY - vNY;
            //Friciton 
            vTX = (1 - coeffFriction) * vTX;
            vTY = (1 - coeffFriction) * vTY;
            //Conclusion 
            velocityX = vTX + vNX;
            velocityY = vTY + vNY;
        }else if(e instanceof Obstacle){
            //Calculer la vitesse normale à un obstacle
            float vNX = (float)( (velocityX * e.nX + velocityY * e.nY) * e.nX);
            float vNY = (float)( (velocityX * e.nX + velocityY * e.nY) * e.nY);
            //Calculer la vitesse tangentielle à un obstacle
            float vTX = velocityX - vNX;
            float vTY = velocityY - vNY;
            //Collision 
            vNX = - coeffRebound * vNX;
            vNY = - coeffRebound * vNY;
            //Friciton 
            vTX = (1 - coeffFriction) * vTX;
            vTY = (1 - coeffFriction) * vTY;
            //Conclusion 
            velocityX = vTX + vNX;
            velocityY = vTY + vNY;
        }  
    }
    
    /**
     * @param e
     *      une chose
     * @return 
     *      si la balle est en contact avec une chose
     */
    public boolean isOnContectWith(ElementBasic e){
        if(e instanceof Obstacle){
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
            //Vecteur1 centre--->balle
            int v1X = positionX - eF.centre.positionX;
            int v1Y = positionY - eF.centre.positionY;
            //Vecteur2 tip--->balle
            int v2X = positionX - eF.tip.positionX;
            int v2Y = positionY - eF.tip.positionY;
            //Vecteur3 centre--->tip
            int v3X = eF.tip.positionX - eF.centre.positionX;
            int v3Y = eF.tip.positionY - eF.centre.positionY;

            // float produitVectoriel = v1X * v2Y - v2X * v1Y;
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
     * Mettre en place la balle manuellement
     * @param x
     *      position X qu'on souhaite donner
     * @param y
     *      position Y qu'on souhaite donner
     */
    public void setPosition(int x, int y){
        this.positionX = x;
        this.positionY = y;
    }

    /**
     * 
     * @return la position X de la balle
     */
    public int getPositionX(){
        int x = positionX;
        return x;
    }

    /**
     * 
     * @return la position Y de la balle
     */
    public int getPositionY(){
        int y = positionY;
        return y;
    }

    /**
     * Donner manuellement la vitesse de la balle
     * @param vx
     *      vitesse X qu'on souhaite donner
     * @param vy
     *      vitesse Y qu'on souhaite donner
     */
    public void setVelocity(double vx, double vy){
        this.velocityX = (float)vx;
        this.velocityY = (float)vy;
    }
    /**
     * 
     * @return la vitesse Y de la balle
     */
    public float getVelocityX(){
        return velocityX;
    }
    /**
     * 
     * @return la vitesse Y de la balle
     */
    public float getVelocityY(){
        return velocityY;
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
     * 
     * @return la position de la balle dans le table vrai
     */
    public String toString(){
        return "( " 
            + (positionX - GameTable.margin) 
            + " , " 
            + (positionY - GameTable.margin) 
            + " )";
    }
}
