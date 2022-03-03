package Elements;

public class GameTable{
    public static int frameRate = 60;
    public static float frameTime = (float)1.0/frameRate;
    public int width;
    public int height;
    public ElementBasic[][] element;
    protected float dt = (float) 0.000001;

    public GameTable(int width, int height){
        this.width = width;
        this.height = height;
        element = new ElementBasic[width][height];
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                element[i][j]=new ElementBasic();
            }
        }
        while(true){
            if(containBall()){
                
            }
            break;
        }
    }
    public boolean containBall(){
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                if(element[i][j] instanceof Ball){
                    return true;
                }
            }
        }
        return false;
    }
}