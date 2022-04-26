package Elements;

public class Boundary extends Obstacle{
    
    /**
     * Construire les bords de GameTable, qui sont 3 ségements situés à gauche, en haut et à droit.
     * @param elm
     *      Une série de sommets bien rangés dans le sens horaire， commençant par en bas à gauche
     * @param s
     *      Smoothness entre 0 et 1
     * @param e
     *      Elasticity entre 0 et 1
     */
    public Boundary(float s, float e){
        ElementBasic[] elm = new ElementBasic[4];
        elm[0] = new ElementBasic(1,GameTable.height);
        elm[1] = new ElementBasic(1,1);
        elm[2] = new ElementBasic(GameTable.width,1);
        elm[3] = new ElementBasic(GameTable.width,GameTable.height);
        this.smoothness = s;
        this.elasticity = e;
        for(int i=0;i<elm.length-1;i++){
            

            //Le début et la fin d'un ségement
            ElementBasic start = new ElementBasic();
            ElementBasic end = new ElementBasic();
            start = elm[i];
            end = elm[i+1];

            //La pente
            int deltaY = end.positionY - start.positionY;
            int deltaX = end.positionX - start.positionX;

            //Définir le vecteur normal pour un ségement soit verticale soit horizontal
            float nx = 0;
            float ny = 0;
            if(deltaX == 0){
                nx = 1;
                ny = 0;
            }
            if(deltaY == 0){
                nx = 0;
                ny = 1;
            }

            /**
             *  le produit vectoriel est négatif, on change le signe de nx et ny,
             *  car on me veut que le vecteur vers l'intérieur
             */ 
            float produitVectoriel =(nx*deltaY)-(ny*deltaX);
            if(produitVectoriel>0){
                ny = -ny;
                nx = -nx;
            }

            /**
             * On remplit le GameTable par Obstacle pour construire un ségement
             * Si le ségement n'est pas vertical, on parcourt X
             * Si il l'est, on parcourt Y
             */
            int max = 0;
            int min = 0;
            if(deltaX == 0){
                if(start.positionY<end.positionY){
                    max = end.positionY;
                    min = start.positionY;
                }else{
                    min = end.positionY;
                    max = start.positionY;
                }
                for(int y = min;y<=max;y++){
                    int x = (int)start.positionX;
                    GameTable.table[x-1][y-1]=new Obstacle(nx, ny, s, e);
                }
            }
            if(deltaY == 0){
                if(start.positionX<end.positionX){
                    max = end.positionX;
                    min = start.positionX;
                }else{
                    min = end.positionX;
                    max = start.positionX;
                }
                for(int x = min;x<=max;x++){
                    int y = (int)start.positionY;
                    GameTable.table[x-1][y-1]=new Obstacle(nx, ny, s, e);
                }
            }
        }
    }
}
