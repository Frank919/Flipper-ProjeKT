package Elements;

/**
 * @author MouZheng LI
 * @Description: Tous les courbes de forme circulaire ouvertes ou fermées
 * @date 01/05/2022
 */
public class Curve extends ElementStatic{
    
    protected double radius;
    protected int centreX, centreY;

   
    /**
     * Une constructeur qui permet construit une courbe circulaire partielle
     * @param startPoint
     *      Le point début de la courbe dans le sens horaire
     * @param endPoint
     *      Le point fin de la courbe dans le sens horaire
     * @param centre
     *      Le centre de la courbe circulaire
     * @param radius
     *      Le rayon de la courbe
     * @param s
     *      La glissance
     * @param e
     *      L'élasticité
     */
    public Curve(ElementBasic startPoint, ElementBasic endPoint, ElementBasic centre, int radius, double s, double e){

        this.centreX=centre.positionX;
        this.centreY=centre.positionY;
        int innerRadius = radius - 4;


        //Déterminer le quadrant
        int startQuadrant = 1;
        int endQuadrant = 1;
        
        double deltaXStrat = startPoint.positionX-centreX;
        double deltaYStrat= startPoint.positionY-centreY;
        if(((deltaXStrat)<0)&&((deltaYStrat)<0)){
            startQuadrant=3;
        }
        if(((deltaXStrat)>=0)&&((deltaYStrat)<=0)){
            startQuadrant=4;
        }

        double deltaXEnd = endPoint.positionX-centreX;
        double deltaYEnd = endPoint.positionY-centreY;
        if(((deltaXEnd)<=0)&&((deltaYEnd)<=0)){
            endQuadrant=3;
        }
        if(((deltaXEnd)>=0)&&((deltaYEnd)<=0)){
            endQuadrant=4;
        }

        

        //Déterminer l'angle (0-pi)
        double startCeta = Math.acos((deltaXStrat)/radius);
        double endCeta = Math.acos((deltaXEnd)/radius);
        if(startQuadrant>2){
            startCeta = 2*Math.PI-startCeta;
        }
        if(endQuadrant>2){
            endCeta = 2*Math.PI-endCeta;
        } 
        
        
        //ON aura deux couches des obstacles, (x1,y1) et (x2,y2), qui entournes la courbe
        if(startCeta < endCeta){
            for(double ceta=startCeta;ceta<=endCeta;ceta+=0.00001){
                int x1 = (int)(radius*Math.cos(ceta)+centreX);
                int y1 = (int)(radius*Math.sin(ceta)+centreY);
                int x2 = (int)(innerRadius*Math.cos(ceta)+centreX);
                int y2 = (int)(innerRadius*Math.sin(ceta)+centreY);

                

                double nx1 = -(centreX-x1)/Math.sqrt(Math.pow(centreX-x1,2)+Math.pow(centreY-y1,2));
                double ny1 = -(centreY-y1)/Math.sqrt(Math.pow(centreX-x1,2)+Math.pow(centreY-y1,2));
                double nx2 = (centreX-x2)/Math.sqrt(Math.pow(centreX-x2,2)+Math.pow(centreY-y2,2));
                double ny2 = (centreY-y2)/Math.sqrt(Math.pow(centreX-x2,2)+Math.pow(centreY-y2,2));

                

                if((x1<640)&&(y1<1000)&&(x1>=0)&&(y1>=0)
                &&(nx1!=Double.POSITIVE_INFINITY)&&(ny1!=Double.POSITIVE_INFINITY)&&(nx2!=Double.POSITIVE_INFINITY)&&(ny2!=Double.POSITIVE_INFINITY)
                &&(nx1!=Double.NEGATIVE_INFINITY)&&(ny1!=Double.NEGATIVE_INFINITY)&&(nx2!=Double.NEGATIVE_INFINITY)&&(ny2!=Double.NEGATIVE_INFINITY)){
                    //System.out.println(x1+","+y1+","+nx1+","+ ny1+","+ s+","+ e);
                    //System.out.println(x2+","+y2+","+nx2+","+ ny2+","+ s+","+ e);
                    GameTable.table[x2][y2]=new Obstacle(x2,y2,nx2, ny2, s, e);
                    GameTable.table[x1][y1]=new Obstacle(x1,y1,nx1, ny1, s, e);
                    
                }
            } 
        }else if(startCeta >= endCeta){
            for(double ceta=startCeta;ceta<=2*Math.PI;ceta+=0.00001){
                int x1= (int)(radius*Math.cos(ceta)+centreX);
                int y1= (int)(radius*Math.sin(ceta)+centreY);
                int x2= (int)(innerRadius*Math.cos(ceta)+centreX);
                int y2= (int)(innerRadius*Math.sin(ceta)+centreY);

                double nx1 = -(centreX-x1)/Math.sqrt(Math.pow(centreX-x1,2)+Math.pow(centreY-y1,2));
                double ny1 = -(centreY-y1)/Math.sqrt(Math.pow(centreX-x1,2)+Math.pow(centreY-y1,2));
                double nx2 = (centreX-x2)/Math.sqrt(Math.pow(centreX-x2,2)+Math.pow(centreY-y2,2));
                double ny2 = (centreY-y2)/Math.sqrt(Math.pow(centreX-x2,2)+Math.pow(centreY-y2,2));

                if((x1<640)&&(y1<1000)&&(x1>=0)&&(y1>=0)
                &&(nx1!=Double.POSITIVE_INFINITY)&&(ny1!=Double.POSITIVE_INFINITY)&&(nx2!=Double.POSITIVE_INFINITY)&&(ny2!=Double.POSITIVE_INFINITY)
                &&(nx1!=Double.NEGATIVE_INFINITY)&&(ny1!=Double.NEGATIVE_INFINITY)&&(nx2!=Double.NEGATIVE_INFINITY)&&(ny2!=Double.NEGATIVE_INFINITY)){
                    //System.out.println(x1+","+y1+","+nx1+","+ ny1+","+ s+","+ e);
                    //System.out.println(x2+","+y2+","+nx2+","+ ny2+","+ s+","+ e);
                    GameTable.table[x2][y2]=new Obstacle(x2,y2,nx2, ny2, s, e);
                    GameTable.table[x1][y1]=new Obstacle(x1,y1,nx1, ny1, s, e);
                    
                }
            }

            for(double ceta=0;ceta<=endCeta;ceta+=0.00001){
                int x1= (int)(radius*Math.cos(ceta)+centreX);
                int y1= (int)(radius*Math.sin(ceta)+centreY);
                int x2= (int)(innerRadius*Math.cos(ceta)+centreX);
                int y2= (int)(innerRadius*Math.sin(ceta)+centreY);

                
                
                double nx1 = -(centreX-x1)/Math.sqrt(Math.pow(centreX-x1,2)+Math.pow(centreY-y1,2));
                double ny1 = -(centreY-y1)/Math.sqrt(Math.pow(centreX-x1,2)+Math.pow(centreY-y1,2));
                double nx2 = (centreX-x2)/Math.sqrt(Math.pow(centreX-x2,2)+Math.pow(centreY-y2,2));
                double ny2 = (centreY-y2)/Math.sqrt(Math.pow(centreX-x2,2)+Math.pow(centreY-y2,2));

                if((x1<640)&&(y1<1000)&&(x1>=0)&&(y1>=0)
                &&(nx1!=Double.POSITIVE_INFINITY)&&(ny1!=Double.POSITIVE_INFINITY)&&(nx2!=Double.POSITIVE_INFINITY)&&(ny2!=Double.POSITIVE_INFINITY)
                &&(nx1!=Double.NEGATIVE_INFINITY)&&(ny1!=Double.NEGATIVE_INFINITY)&&(nx2!=Double.NEGATIVE_INFINITY)&&(ny2!=Double.NEGATIVE_INFINITY)){
                    //System.out.println(x1+","+y1+","+nx1+","+ ny1+","+ s+","+ e);
                    //System.out.println(x2+","+y2+","+nx2+","+ ny2+","+ s+","+ e);
                    GameTable.table[x2][y2]=new Obstacle(x2,y2,nx2, ny2, s, e);
                    GameTable.table[x1][y1]=new Obstacle(x1,y1,nx1, ny1, s, e);
                    
                    
                }
                  
            }
        }
    }
}