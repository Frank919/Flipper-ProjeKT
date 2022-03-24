package Elements;

public class Obstacle extends ElementStatic{
    public Obstacle(){
        
    }

    public Obstacle(float nx, float ny){
        this.nX=nx;
        this.nY=ny;
    }
    public Obstacle(float nx, float ny, float s, float e){
        this(nx,ny);
        this.smoothness = s;
        this.elasticity = e;
    }
}
