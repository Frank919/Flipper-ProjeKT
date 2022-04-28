package Elements;

public class Obstacle extends ElementStatic{
    public Obstacle(int x, int y,double nx, double ny){
        this.positionX = x;
        this.positionY = y;
        this.nX=nx;
        this.nY=ny;
    }

    public Obstacle(int x, int y, double nx, double ny, double s, double e){
        this(x, y,nx,ny);
        this.smoothness = s;
        this.elasticity = e;
    }
}
