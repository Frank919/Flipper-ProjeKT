package Elements;

/**
 * @author Chenglai FANG
 * @Description: Les obstacles dans ce jeu
 * @date 01/05/2022
 */
public class Obstacle extends ElementStatic{
    /**
     * 
     * @param x positionX
     * @param y positionY
     * @param nx composant vecteur normal selon X 
     * @param ny composant vecteur normal selon Y 
     * @param s glissance entre 0 et 1
     * @param e élasticité entre 0 et 1
     */
    public Obstacle(int x, int y, double nx, double ny, double s, double e){
        this.positionX = x;
        this.positionY = y;
        this.nX=nx;
        this.nY=ny;
        this.smoothness = s;
        this.elasticity = e;
    }
}
