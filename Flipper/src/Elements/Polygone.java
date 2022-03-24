package Elements;

public class Polygone extends Obstacle{
    protected Obstacle[] apex;
    /**
     * 
     * @param elm
     *      Une série de sommets bien rangés dans le sens horaire
     * @param s
     *      Smoothness entre 0 et 1
     * @param e
     *      Elasticity entre 0 et 1
     */
    public Polygone(ElementBasic[] elm, float s, float e){
        this.smoothness = s;
        this.elasticity = e;
        for(int i=0;i<elm.length;i++){
            apex[i] = (Obstacle)elm[i];
            //le vecteur normal
            float nx = 0;
            float ny = 0;
            //un interval de position
            int max = 0;
            int min = 0;
            //
            ElementBasic start = new ElementBasic();
            ElementBasic end = new ElementBasic();
            
            start = elm[i];
            if(i == elm.length - 1){
                end = elm[0];
            }else{
                end = elm[i+1];
            }
            //La pente
            int deltaY = end.positionY - start.positionY;
            int deltaX = end.positionX - start.positionX;
            float k = deltaY / deltaX;
            //L'ordonée à l'origine
            float b = start.positionY - k * start.positionX;

            //Le vecteur normal
            ny = (float)Math.sqrt(1/(1+k*k));
            nx = - k * ny;
            float produitVectoriel =(nx*deltaY)-(ny*deltaX);
            /**
             *  le produit vectoriel est positif, on change le signe de nx et ny,
             *  car on me veut que le vecteur vers l'extérieur
             */ 
            if(produitVectoriel>0){
                ny = -ny;
                nx = -nx;
            }
            if(start.positionX<end.positionX){
                max = end.positionX;
                min = start.positionX;
            }else{
                min = end.positionX;
                max = start.positionX;
            }
            for(int x = min;x<=max;x++){
                int y = (int)(k*x+b);
                GameTable.table[x][y]=new Obstacle(nx, ny, s, e);
            }
        }
    }
}
