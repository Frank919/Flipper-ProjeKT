package Elements;

/**
 * @author Chenglai FANG
 * @Description: Tous les obstacles en bord ou spéciale
 * @date 01/05/2022
 */
public class Boundary extends ElementStatic{
    
    /**
     * Construire les bords de GameTable, qui sont 3 ségements situés à gauche, en haut et à droit.
     * @param elm
     *      Une série de sommets bien rangés dans le sens horaire， commençant par en bas à gauche
     * @param s
     *      Smoothness entre 0 et 1
     * @param e
     *      Elasticity entre 0 et 1
     */
    public Boundary(double s, double e){
        this.smoothness = s;
        this.elasticity = e;

        for(int y=1; y<GameTable.height;y++){
            GameTable.table[0][y]=new Obstacle(0,y,1, 0, s, e);
            GameTable.table[1][y]=new Obstacle(1,y,1, 0, s, e);
            GameTable.table[GameTable.width-1][y]=new Obstacle(GameTable.width-1,y,-1, 0, s, e);
            GameTable.table[GameTable.width-2][y]=new Obstacle(GameTable.width-2,y,-1, 0, s, e);
        }
        for(int y=80; y<GameTable.height;y++){
            GameTable.table[560][y]=new Obstacle(580,y,-1, 0, s, e);
            GameTable.table[562][y]=new Obstacle(562,y,1, 0, s, e);
        }
        for(int y=375; y<646;y++){
            GameTable.table[186][y]=new Obstacle(186,y,-1, 0, s, e);
            GameTable.table[188][y]=new Obstacle(188,y,1, 0, s, e);
        }

        for(int x=1; x<GameTable.width;x++){
            GameTable.table[x][0]=new Obstacle(x,1,0, 1, s, e);
            GameTable.table[x][1]=new Obstacle(x,2,0, 1, s, e);
        }
        for(int x=415; x<511;x++){
            GameTable.table[x][798]=new Obstacle(x,799,0, 1, s, e);
        }
        for(int x=188; x<253;x++){
            GameTable.table[x][375]=new Obstacle(x,375,0, 1, s, e);
        }

        ElementBasic start = new ElementBasic(188,640);
        ElementBasic end = new ElementBasic(117,757);
        /**
         * Calculer le vecteur normal
         */
        double ny = 0;
        double nx = 0;
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
                GameTable.table[x-1][y-1]=new Obstacle(x,y,nx, ny, s, e);
                GameTable.table[x-1][y-2]=new Obstacle(x,y-1,nx, ny, s, e);
            }
        }                        

    }
}
