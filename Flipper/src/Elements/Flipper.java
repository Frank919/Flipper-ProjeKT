package Elements;

/**
 * @author Chenglai FANG
 * @Description: Les manàttes soit à gauche soit à droite dans ce jeu
 * @date 01/05/2022
 */
public class Flipper extends ElementKinetic{
    public ElementBasic centre;
    public ElementBasic tip;
    public boolean isOnRight;
    /**
     * Angle initial positif dans le sens horaire
     */
    protected double angle;
    /**
     * Angle courrant
     */
    protected double curAngle;

    protected double radius;
    /**
     * Positif dans le sens horaire
     */
    protected double velocityAng = Math.PI/2;
    /**
     * vecteur normal dans le sens horaire
     */
    protected double n1X,n1Y;
    /**
     * vecteur normal dans le sens inverse horaire
     */
    protected double n2X,n2Y;

    /**
     * 
     * @param centre centre autour duquel la manette tourne
     * @param tip l'extrémité 
     * @param smoothness glissance
     * @param elasticity élasticité
     * @param isOnRight {@code true} à droite, {@code false} à gauche 
     */
    public Flipper(ElementBasic centre, ElementBasic tip, double smoothness, double elasticity,Boolean isOnRight){
        this.centre = centre;
        this.tip = tip;
        this.smoothness = smoothness;
        this.elasticity = elasticity;
        this.isOnRight = isOnRight;
        int quadrant = 0;
        double deltaX = tip.positionX-centre.positionX;
        double deltaY = tip.positionY-centre.positionY;
        this.radius= Math.sqrt(deltaX*deltaX + deltaY*deltaY);
        if((deltaX<=0)&&(deltaY<=0)){
            quadrant=3;
        }
        if((deltaX>=0)&&(deltaY<=0)){
            quadrant=4;
        } 
        this.angle = Math.acos((deltaX)/radius);
        if(quadrant>2){
            this.angle = 2*Math.PI - angle;
        }
        //Initializtion de l'angle courrant
        curAngle = angle;
        /**
         * Calculer le vecteur normal
         */
        double k = 0;
        if(deltaX!=0){
            //La pente
            k = deltaY / deltaX;
            //Le vecteur normal
            n1Y = Math.sqrt(1/(1+k*k));
            n1X = - k * n1Y;
        }else{
            n1X = 1;
            n1Y = 0;
        }
        // Si le produit vectoriel nxv est positif, on change le signe de nx et ny,
        //car on me veut que le vecteur n1 dans le sens horaire
        double produitVectoriel = (n1X*deltaY)-(n1Y*deltaX);
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

    public void rotateUp(){
        if(isOnRight){
            if(curAngle <= Math.PI*5/4){
                curAngle += GameTable.refreshTime*velocityAng;
            }
        }else{
            if(curAngle >= -Math.PI/4){
                curAngle -= GameTable.refreshTime*velocityAng;
            }
        }
        tip.positionX = (int)(Math.cos(curAngle) * radius)+ centre.positionX;
        tip.positionY = (int)(Math.sin(curAngle) * radius) + centre.positionY;
    }

    public void rotateDown(){
        if(! isOnRight){
            while(curAngle <= angle){
                curAngle += GameTable.refreshTime*velocityAng;
            }
        }else{
            while(curAngle >= angle){
                curAngle -= GameTable.refreshTime*velocityAng;
            }
        }
        tip.positionX = (int)(Math.cos(curAngle) * radius) + centre.positionX ;
        tip.positionY = (int)(Math.sin(curAngle) * radius) + centre.positionY;

    }
    
    
	
}
