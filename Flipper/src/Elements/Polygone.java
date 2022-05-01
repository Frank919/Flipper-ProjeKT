package Elements;

/**
 * @author Chenglai FANG
 * @Description: Les éléments dans ce jeu
 * @date 01/05/2022
 */
public class Polygone extends ElementStatic{
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
    public Polygone(ElementBasic[] elm, double s, double e){
        this.smoothness = s;
        this.elasticity = e;
        for(int i=0;i<elm.length;i++){
            //le vecteur normal
            double nx = 0;
            double ny = 0;
            
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
            double deltaY = end.positionY - start.positionY;
            double deltaX = end.positionX - start.positionX;
            double k = 0;
            double b = 0;
            if(deltaX!=0){
                //La pente
                k = deltaY / deltaX;
                //L'ordonée à l'origine
                b = start.positionY - k * start.positionX;
                //Le vecteur normal
                ny = Math.sqrt(1/(1+k*k));
                nx = - k * ny;
            }else{
                
                nx = 1;
                ny = 0;
            }

            /**
             *  Si le produit vectoriel est pas positif, on change le signe de nx et ny,
             *  car on me veut que le vecteur vers l'extérieur
             */ 
            double produitVectoriel = (nx*deltaY)-(ny*deltaX);
            if(produitVectoriel < 0){
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
                    //System.out.println("y="+y);
                    GameTable.table[x-1][y-1]=new Obstacle(x,y,nx, ny, s, e);
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
                    GameTable.table[x-1][y-1]=new Obstacle(x,y,nx, ny, s, e);
                }
            }
            System.out.println(nx+","+ny);
        }
    }
}
