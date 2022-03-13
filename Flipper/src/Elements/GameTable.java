package Elements;

public class GameTable{
    /**
     * 每一帧运算一次小球位置
     */
    public static int frameRate = 60;
    public static float frameTime = (float)1.0/frameRate;
    public static float dt = (float) 0.000001;
    public int width;
    public int height;
    public ElementBasic[][] element;
    

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
            if(containsBall()){
                
            }
            break;
        }
    }
    public boolean containsBall(){
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