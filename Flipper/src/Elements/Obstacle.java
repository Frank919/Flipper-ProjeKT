package Elements;

public class Obstacle extends ElementStatic{
    public Obstacle(){
        
    }

    public Obstacle(double nx, double ny){
        this.nX=nx;
        this.nY=ny;
    }
    public Obstacle(double nx, double ny, double s, double e){
        this(nx,ny);
        this.smoothness = s;
        this.elasticity = e;
    }
}
