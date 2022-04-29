package Elements;

public class Obstacle extends ElementStatic{

    public Obstacle(int x, int y, double nx, double ny, double s, double e){
        this.positionX = x;
        this.positionY = y;
        this.nX=nx;
        this.nY=ny;
        this.smoothness = s;
        this.elasticity = e;
    }
}
