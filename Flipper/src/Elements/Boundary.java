package Elements;

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
            GameTable.table[GameTable.width-1][y]=new Obstacle(GameTable.width-1,y,-1, 0, s, e);
        }
        for(int x=1; x<GameTable.width;x++){
            GameTable.table[x][0]=new Obstacle(x,1,0, 1, s, e);
        }
        for(int x=415; x<511;x++){
            GameTable.table[x][798]=new Obstacle(x,1,0, 1, s, e);
        }

    }
}
