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
            
            //Le début et la fin d'un ségement
            ElementBasic start = new ElementBasic();
            ElementBasic end = new ElementBasic();
            start = elm[i];
            if(i == elm.length - 1){
                end = elm[0];
            }else{
                end = elm[i+1];
            }
            
            /**
             * Calculer le vecteur normal
             */
            int deltaY = end.positionY - start.positionY;
            int deltaX = end.positionX - start.positionX;
            float k = 0;
            float b = 0;
            if(deltaX!=0){
                //La pente
                k = deltaY / deltaX;
                //L'ordonée à l'origine
                b = start.positionY - k * start.positionX;
                //Le vecteur normal
                ny = (float)Math.sqrt(1/(1+k*k));
                nx = - k * ny;
            }else{
                nx = 1;
                ny = 0;
            }

            /**
             *  Si le produit vectoriel est positif, on change le signe de nx et ny,
             *  car on me veut que le vecteur vers l'extérieur
             */ 
            float produitVectoriel = (nx*deltaY)-(ny*deltaX);
            if(produitVectoriel>0){
                ny = -ny;
                nx = -nx;
            }
            
            /**
             * On remplit le GameTable par Obstacle pour construire un ségement
             * Si le ségement n'est pas vertical, on utilise l'équation du ségement et parcourt X
             * Si il l'est, on parcourt Y
             */
            int max = 0;
            int min = 0;
            if(deltaX != 0){
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
            }else{
                if(start.positionY<end.positionY){
                    max = end.positionY;
                    min = start.positionY;
                }else{
                    min = end.positionY;
                    max = start.positionY;
                }
                for(int y = min;y<=max;y++){
                    int x = (int)start.positionX;
                    GameTable.table[x][y]=new Obstacle(nx, ny, s, e);
                }
            }
        }
    }
}
