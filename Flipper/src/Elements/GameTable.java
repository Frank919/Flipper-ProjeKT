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
    //将游戏桌想象成一堆格子，将球的横纵坐标对应到表格的行列，位置更新时，同时更新其表格中的位置
    public static ElementBasic[][] table;
    public Ball xiangpiqiu = new Ball(2, 3, 4, 5, (float)0.3, (float)0.1);
    public Ball gangqiu = new Ball(2, 3, 4, 5, (float)0.3, (float)0.1);
    

    public GameTable(int width, int height){
        this.width = width;
        this.height = height;
        table = new ElementBasic[width][height];
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                table[i][j]=new ElementBasic();
            }
        }
        while(true){
            for(int i=xiangpiqiu.positionX - 100;i<xiangpiqiu.positionX + 100;i++){
                for(int j=xiangpiqiu.positionY - 100;j<xiangpiqiu.positionY + 100;j++){
                    if (xiangpiqiu.isOnContectWith(table[i][j])){
                        xiangpiqiu.collidesWith(table[i][j]);
                    }
                }
            }
            break;
        }
    }


}