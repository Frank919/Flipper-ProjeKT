package Elements;

public class Flipper extends ElementKinetic{
    protected ElementBasic centre;
    protected ElementBasic tip;
    protected boolean isOnRight;
    /**
     * Positif dans le sens horaire
     */
    protected double angle;
    /**
     * Positif dans le sens horaire
     */
    protected double velocityAng;
    /**
     * vecteur normal dans le sens horaire
     */
    protected float n1X,n1Y;
    /**
     * vecteur normal dans le sens inverse horaire
     */
    protected float n2X,n2Y;


    /**
     * 
     */
    public Flipper(ElementBasic centre, ElementBasic tip, float smoothness, float elasticity,Boolean isOnRight){
        this.centre = centre;
        this.tip = tip;
        this.smoothness = smoothness;
        this.elasticity = elasticity;
        this.isOnRight = isOnRight;
        int quadrant = 0;
        int deltaX = tip.positionX-centre.positionX;
        int deltaY = tip.positionY-centre.positionY;
        if((deltaX<=0)&&(deltaY<=0)){
            quadrant=3;
        }
        if((deltaX>=0)&&(deltaY<=0)){
            quadrant=4;
        } 
        this.angle = Math.acos((deltaX)/Math.sqrt(deltaX*deltaX + deltaY*deltaY));
        if(quadrant>2){
            this.angle = 2*Math.PI - angle;
        }
        /**
         * Calculer le vecteur normal
         */
        float k = 0;
        if(deltaX!=0){
            //La pente
            k = deltaY / deltaX;
            //Le vecteur normal
            n1Y = (float)Math.sqrt(1/(1+k*k));
            n1X = - k * n1Y;
        }else{
            n1X = 1;
            n1Y = 0;
        }
        // Si le produit vectoriel nxv est positif, on change le signe de nx et ny,
        //car on me veut que le vecteur n1 dans le sens horaire
        float produitVectoriel = (n1X*deltaY)-(n1Y*deltaX);
        if(produitVectoriel > 0){
            n1Y = -n1Y; 
            n1X = -n1X;
        }
        n2X = -n1X;
        n2Y = -n1Y;
        
        int r = 5;
        ElementBasic s = new ElementBasic(centre.positionX + r, centre.positionY);
        Curve base = new Curve(s, s, centre, r, this.smoothness, this.elasticity);

        
    }

    public void rotate(){
        if(isOnRight){
            angle += GameTable.frameTime*velocityAng;
        }else{
            angle -= GameTable.frameTime*velocityAng;
        }
        
        tip.positionX = (int)Math.cos(angle);
        tip.positionY = (int)Math.sin(angle);

    }
}
