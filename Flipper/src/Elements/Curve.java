package Elements;

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
        int innerRadius = radius - 1;

        //我们直接造有的obstacle

        //确定象限
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

        

        //求角度(0-pi)
        double startCeta = Math.acos((deltaXStrat)/radius);
        double endCeta = Math.acos((deltaXEnd)/radius);
        


        //用象限确定ceta
        if(startQuadrant>2){
            startCeta = 2*Math.PI-startCeta;
        }
        if(endQuadrant>2){
            endCeta = 2*Math.PI-endCeta;
        } 
        
        System.out.println(startCeta+","+endCeta);
        
        //造obstacles((x1,y1)是外面一层，（x2，y2）是里面一层)
        if(startCeta < endCeta){
            for(double ceta=startCeta;ceta<=endCeta;ceta+=0.001){
                int x1 = (int)(radius*Math.cos(ceta)+centreX);
                int y1 = (int)(radius*Math.sin(ceta)+centreY);
                int x2 = (int)(innerRadius*Math.cos(ceta)+centreX);
                int y2 = (int)(innerRadius*Math.sin(ceta)+centreY);

                double nx1 = -(centreX-x1)/radius;
                double ny1 = -(centreY-y1)/radius;
                double nx2 = (centreX-x2)/innerRadius;
                double ny2 = (centreY-y2)/innerRadius;

                

                GameTable.table[x1][y1]=new Obstacle(x1,y1,nx1, ny1, s, e);
                GameTable.table[x2][y2]=new Obstacle(x2,y2,nx2, ny2, s, e); 
            } 
        }else if(startCeta >= endCeta){
            for(double ceta=startCeta;ceta<=2*Math.PI;ceta+=0.001){
                int x1= (int)(radius*Math.cos(ceta)+centreX);
                int y1= (int)(radius*Math.sin(ceta)+centreY);
                int x2= (int)(innerRadius*Math.cos(ceta)+centreX);
                int y2= (int)(innerRadius*Math.sin(ceta)+centreY);

                double nx1 = -(centreX-x1)/radius;
                double ny1 = -(centreY-y1)/radius;
                double nx2 = (centreX-x2)/innerRadius;
                double ny2 = (centreY-y2)/innerRadius;

                GameTable.table[x1][y1]=new Obstacle(x1,y1,nx1, ny1, s, e);
                GameTable.table[x2][y2]=new Obstacle(x2,y2,nx2, ny2, s, e); 
            }

            for(double ceta=0;ceta<=endCeta;ceta+=0.001){
                int x1= (int)(radius*Math.cos(ceta)+centreX);
                int y1= (int)(radius*Math.sin(ceta)+centreY);
                int x2= (int)(innerRadius*Math.cos(ceta)+centreX);
                int y2= (int)(innerRadius*Math.sin(ceta)+centreY);
                
                double nx1 = -(centreX-x1)/radius;
                double ny1 = -(centreY-y1)/radius;
                double nx2 = (centreX-x2)/innerRadius;
                double ny2 = (centreY-y2)/innerRadius;


                GameTable.table[x1][y1]=new Obstacle(x1,y1,nx1, ny1, s, e);
                GameTable.table[x2][y2]=new Obstacle(x2,y2,nx2, ny2, s, e);  
            }
        }
    }
}