package Elements;


public class Ball extends ElementKinetic{
    protected int radius;
    protected int num;
    
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
    public Ball(int num,int positionX, int positionY, int radius,int messe,double smoothness,double elasticity){
        /**
         * Ici on met la balle dans le pseudoTable car tous les calculs s'y passent.
         * Donc on fait décaler la position.
         */
        super(positionX + GameTable.margin, positionY + GameTable.margin,messe,smoothness,elasticity);
        this.radius=radius; 
        this.num = num;
    }

    /**
     * Mettre à jour la position de la balle
     */
    public void moves(){
        
        double deltaX = velocityX * GameTable.refreshTime;
        double deltaY = velocityY * GameTable.refreshTime;
        
        if(deltaX>0&&deltaX<1){
            deltaX = 1;
        }
        if(deltaY>0&&deltaY<1){
            deltaY = 1;
        }
        if(deltaX<0&&deltaX>-1){
            deltaX = -1;
        }
        if(deltaY<0&&deltaY>-1){
            deltaY = -1;
        }
        //System.out.println("deltaX = "  + deltaX+" deltaY = "+ deltaY);
        positionX += deltaX;
        positionY += deltaY;
        System.out.println(this.toString());
    }
    

    /**
     * Cette méthode permet de calculer la variation de la vitesse en raison de la gravitation,
     * qui impacte la vitesse selon Y.
     */               
    public void falls(){
        double g = 800;
        double deltaVY = g*GameTable.refreshTime;
        if(deltaVY<1){
            deltaVY = 1;
        }
        velocityY+=g*GameTable.refreshTime;
    }

    /**
     * Cette méthode permet de calculer la variation de la vitesse en raison de la collision et de la frction,
     * qui impacte la vitesse normale et tangentielle.
     * 
     * <p>Le {@code coeffRebound} est défini par le produit de {@code elasticity} des deux objets en contact,
     * donc entre 0 et 1.
     * Et si    {@code coeffRebound = 0}, la vitesse normale se sera anullée.
     *    Si    {@code coeffRebound = 1}, la vitesse normale ne varie pas.
     * 
     * <p>Le {@code coeffFriction} est défini par le produit de {@code smoothness} des deux objets frotté,
     * donc entre 0 et 1.
     * Et si    {@code coeffFriction = 1}, la vitesse tangentielle se sera anullée.
     *    Si    {@code coeffFriction = 0}, la vitesse tangentielle ne varie pas.
     * 
     * @param e 
     *          Un {@code elementBasic} qui sera heurté et frotté par la balle
     */
    public void collidesWith(ElementBasic e){
        //Calculer le coefficient répondissement et celui de fricion
        double coeffRebound = 1 - elasticity * e.elasticity;
        double coeffFriction = 1 - smoothness * e.smoothness;
        if(e instanceof Flipper){
            Flipper eF = (Flipper)e;
            //Vecteur1 centre--->balle
            int v1X = positionX - eF.centre.positionX;
            int v1Y = positionY - eF.centre.positionY;
            //Vecteur3 centre--->tip
            int v3X = eF.tip.positionX - eF.centre.positionX;
            int v3Y = eF.tip.positionY - eF.centre.positionY;
            //Vecteur1 X vecteur3
            double produitVectoriel = v1X * v3Y - v3X * v1Y;
            //Vecteur1 . vecteur3
            double produitScalaire = v1X * v3X + v1Y * v3Y;
            //Déterminer quelle surface à impacter
            if( produitVectoriel <= 0){
                eF.nX = eF.n1X;
                eF.nY = eF.n1Y;
            }else{
                eF.nX = eF.n2X;
                eF.nY = eF.n2Y;
            }
            //Calculer la vitesse normale au flipper
            double vNX = (double)( (velocityX * eF.nX + velocityY * eF.nY) * eF.nX);
            double vNY = (double)( (velocityX * eF.nX + velocityY * eF.nY) * eF.nY);
            //Calculer la vitesse tangentielle au flipper
            double vTX = velocityX - vNX;
            double vTY = velocityY - vNY;

            //Déterminer la position contact sur le flipper
            double rayon = (double)(produitScalaire / Math.pow((v3X*v3X + v3Y*v3Y),2));
            double velocityLineX = (double)(eF.velocityAng * rayon * nX);
            double velocityLineY = (double)(eF.velocityAng * rayon * nY);
            //Calculer la vitesse normale du flipper
            double vFNX = (double)( (velocityLineX * eF.nX + velocityLineY * eF.nY) * eF.nX);
            double vFNY = (double)( (velocityLineX * eF.nX + velocityLineY * eF.nY) * eF.nY);

            //Collision 
            vNX = - coeffRebound * vNX + vFNX ;
            vNY = - coeffRebound * vNY + vFNY ;
            //Friciton 
            vTX = coeffFriction * vTX;
            vTY = coeffFriction * vTY;
            //Conclusion 
            velocityX = vTX + vNX;
            velocityY = vTY + vNY;
        }else if(e instanceof Obstacle){
            //Calculer la vitesse normale à un obstacle
            double vNX = (double)( (velocityX * e.nX + velocityY * e.nY) * e.nX);
            double vNY = (double)( (velocityX * e.nX + velocityY * e.nY) * e.nY);
            //Calculer la vitesse tangentielle à un obstacle
            double vTX = velocityX - vNX;
            double vTY = velocityY - vNY;
            //Collision 
            vNX = - coeffRebound * vNX;
            vNY = - coeffRebound * vNY;
            //Friciton 
            vTX = coeffFriction * vTX;
            vTY = coeffFriction * vTY;
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
            double distance = Math.sqrt(
                (e.positionX - getPositionX())*(e.positionX - getPositionX()) + 
                (e.positionY - getPositionY())*(e.positionY - getPositionY())
            );
            if(distance <= GameTable.detectionRange){
                return true;
            }
            //return true;
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

            // double produitVectoriel = v1X * v2Y - v2X * v1Y;
            double produitScalaire = v1X * v2X + v1Y * v2Y;

            // Calculer la distance entre la balle et la droite représentant le flipper
            double distance = (double)Math.sqrt(
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
     *      position réel X qu'on souhaite donner
     * @param y
     *      position réel Y qu'on souhaite donner
     */
    public void setPosition(int x, int y){
        this.positionX = x + GameTable.margin;
        this.positionY = y + GameTable.margin;
    }

    /**
     * 
     * @return le numero de la balle
     */
    public int getNum(){
        int n = num;
        return n;
    }
    /**
     * 
     * @return la position réel X de la balle
     */
    public int getPositionX(){
        int x = positionX - GameTable.margin;
        return x;
    }

    /**
     * 
     * @return la position réel Y de la balle
     */
    public int getPositionY(){
        int y = positionY - GameTable.margin;
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
        this.velocityX = (double)vx;
        this.velocityY = (double)vy;
    }
    /**
     * 
     * @return la vitesse Y de la balle
     */
    public double getVelocityX(){
        return velocityX;
    }
    /**
     * 
     * @return la vitesse Y de la balle
     */
    public double getVelocityY(){
        return velocityY;
    }
    /**
     * Déterminer si on a la fin du jeu 
     * @return  {@code true} si la balle est en dehors du table
     *      <li>{@code false} sinon
     *      
     */
    public boolean isOut(){
        if(positionY - GameTable.margin>=GameTable.height){
            return true;
        }
        return false;
    }
    /**
     * 
     * @return la position de la balle dans le table vrai
     */
    public String toString(){
        return "Position = "
            +"( " 
            + (positionX - GameTable.margin) 
            + " , " 
            + (positionY - GameTable.margin)
            + " )" 
            + " Velocity = "
            + "("
            + velocityX
            + " , " 
            + velocityY 
            + " )";
    }
}
