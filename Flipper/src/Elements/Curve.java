package Elements;

public class Curve extends Obstacle{
    
    protected float radius;
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
    public Curve(ElementBasic startPoint, ElementBasic endPoint, ElementBasic centre, int radius, float s, float e){

        this.centreX=centre.positionX;
        this.centreY=centre.positionY;
        int innerRadius = radius - 1;

        //我们直接造有的obstacle

        //确定象限
        int startQuadrant = 0;
        int endQuadrant = 0;
        int deltaX = 0;
        int deltaY = 0;

        deltaX = startPoint.positionX-centreX;
        deltaY = startPoint.positionY-centreY;
        if(((deltaX)>=0)&&((deltaY)>=0)){
            startQuadrant=1;
        }

        if(((deltaX)<=0)&&((deltaY)>=0)){
            startQuadrant=2;
        }

        if(((deltaX)<=0)&&((deltaY)<=0)){
            startQuadrant=3;
        }

        if(((deltaX)>=0)&&((deltaY)<=0)){
            startQuadrant=4;
        }

        deltaX = endPoint.positionX-centreX;
        deltaY = endPoint.positionY-centreY;
        if(((deltaX)>=0)&&((deltaY)>=0)){
            endQuadrant=1;
        }

        if(((deltaX)<=0)&&((deltaY)>=0)){
            endQuadrant=2;
        }

        if(((deltaX)<=0)&&((deltaY)<=0)){
            endQuadrant=3;
        }

        if(((deltaX)>=0)&&((deltaY)<=0)){
            endQuadrant=4;
        }

        //求角度(0-pi)
        double startCeta = Math.acos((deltaX)/radius);
        double endCeta = Math.acos((deltaX)/radius);


        //用象限确定ceta
        if(startQuadrant>2){
            startCeta = 2*Math.PI-startCeta;
        }
        if(endQuadrant>2){
            endCeta =2 *Math.PI-endCeta;
        } 
        
        //造obstacles((x1,y1)是外面一层，（x2，y2）是里面一层)
        if(startCeta < endCeta){
            for(double ceta=startCeta;ceta<=endCeta;ceta+=0.001){
                int x1 = (int)(radius*Math.cos(ceta)+centreX);
                int y1 = (int)(radius*Math.sin(ceta)+centreY);
                int x2 = (int)(innerRadius*Math.cos(ceta)+centreX);
                int y2 = (int)(innerRadius*Math.sin(ceta)+centreY);

                float nx1 = centreX-x1/Math.abs(centreX-x1);
                float ny1 = centreY-y1/Math.abs(centreX-x1);
                float nx2 = -(centreX-x2)/Math.abs(centreX-x2);
                float ny2 = -(centreY-y2)/Math.abs(centreY-y2);

                GameTable.table[x1][y1]=new Obstacle(nx1, ny1, s, e);
                GameTable.table[x2][y2]=new Obstacle(nx2, ny2, s, e);
            } 
        }else if(startCeta >= endCeta){
            for(double ceta=startCeta;ceta<=2*Math.PI;ceta+=0.001){
                int x1= (int)(radius*Math.cos(ceta)+centreX);
                int y1= (int)(radius*Math.sin(ceta)+centreY);
                int x2= (int)(innerRadius*Math.cos(ceta)+centreX);
                int y2= (int)(innerRadius*Math.sin(ceta)+centreY);

                float nx1 = centreX-x1/Math.abs(centreX-x1);
                float ny1 = centreY-y1/Math.abs(centreX-x1);
                float nx2 = -(centreX-x2)/Math.abs(centreX-x2);
                float ny2 = -(centreY-y2)/Math.abs(centreY-y2);

                GameTable.table[x1][y1]=new Obstacle(nx1, ny1, s, e);
                GameTable.table[x2][y2]=new Obstacle(nx2, ny2, s, e); 
            }

            for(double ceta=0;ceta<=endCeta;ceta+=0.001){
                int x1= (int)(radius*Math.cos(ceta)+centreX);
                int y1= (int)(radius*Math.sin(ceta)+centreY);
                int x2= (int)(innerRadius*Math.cos(ceta)+centreX);
                int y2= (int)(innerRadius*Math.sin(ceta)+centreY);
                
                float nx1 = centreX-x1/Math.abs(centreX-x1);
                float ny1 = centreY-y1/Math.abs(centreX-x1);
                float nx2 = -(centreX-x2)/Math.abs(centreX-x2);
                float ny2 = -(centreY-y2)/Math.abs(centreY-y2);

                GameTable.table[x1][y1]=new Obstacle(nx1, ny1, s, e);
                GameTable.table[x2][y2]=new Obstacle(nx2, ny2, s, e); 
            }
        }
    }
}