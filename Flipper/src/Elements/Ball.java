package Elements;


public class Ball extends ElementKinetic{
    protected int radius;
    protected float force[] = new float[2];
    /**
     * 
     * @param positionX
     *          Coordonnée X initiale du centre de cette balle dans le table vrai
     * @param positionY
     *          Coordonnée Y initiale du centre de cette balle dans le table vrai
     * @param radius
     *          Radium de cette balle
     * @param messe
     *          Masse de cette balle
     * @param smoothness
     *          @see #collidesWith()
     * @param elasticity
     *          @see #collidesWith()
     */
    public Ball(int positionX, int positionY, int radius,int messe,float smoothness,float elasticity){
        /**
         * Ici on met la balle dans le pseudoTable car tous les calculs et simulations s'y passent.
         * Donc on fait décaler la position.
         */
        super(positionX + GameTable.margin, positionY + GameTable.margin, messe, smoothness, elasticity);
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
     * Si    {@code coeffRebound = 1}, la vitesse normale ne varie pas.
     * 
     * Le {@code coeffFriction} est défini par le produit de {@code smoothness} des deux objets frotté,
     * donc entre 0 et 1.
     * Et si    {@code coeffFriction = 1}, la vitesse tangentielle se sera anullée.
     *    Si    {@code coeffFriction = 0}, la vitesse tangentielle ne varie pas.
     * 
     * @param e 
     *          Un {@code elementBasic} qui sera heurté et frotté par la balle
     * 
     * @return
     */
    public void collidesWith(ElementBasic e){
        /**
         * 只考虑完全弹性碰撞
         */
        if(e instanceof ElementKinetic){
            velocityX -= ((ElementKinetic)e).velocityX;
            velocityY -= ((ElementKinetic)e).velocityY;
        }   
        float coeffRebound = elasticity * e.elasticity;
        float coeffFriction = smoothness * e.smoothness;
        //Calculer la vitesse normale à un obstacle 计算法向速度
        float vNX = (velocityX * e.nX + velocityX * e.nY) * e.nX;
        float vNY = (velocityX * e.nX + velocityX * e.nY) * e.nY;
        //Calculer la vitesse tangentielle à un obstacle 计算切向速度
        float vTX = velocityX - vNX;
        float vTY = velocityY - vNY;
        //Collision 碰撞将法向速度反转，并考虑弹力系数
        vNX = - coeffRebound * vNX;
        vNY = - coeffRebound * vNY;
        //Friciton 摩擦将减小切向速度
        vTX = (1 - coeffFriction) * vTX;
        vTY = (1 - coeffFriction) * vTY;
        //Conclusion 将法向速度和切向速度相加，还原得到正交的速度
        velocityX = vTX + vNX;
        velocityY = vTY + vNY;
        //Update positions
        positionX += velocityX * GameTable.frameTime;
        positionY += velocityY * GameTable.frameTime;
        
    }

    public boolean isOut(){
        if(positionY>GameTable.height){
            return true;
        }
        return false;
    }

    public boolean isOnContectWith(ElementBasic e){
        float distance = (float)Math.sqrt(
                        Math.pow((e.positionX - positionX),2) +   
                        Math.pow((e.positionY - positionY),2)
                    );
        if(e instanceof Circle){
            if(distance <= radius + ((Circle)e).radius){
                return true;
            }
        }else if(e instanceof InnerCircle){//这里很有问题
                if(distance <= radius - ((InnerCircle)e).radius){
                    return true;            
                }
        }else if(e instanceof StraightObstacle){
                if(distance <= radius ){
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
        return "( " + (positionX - GameTable.margin) + " , " + (positionY - GameTable.margin) + " )";
    }
}
