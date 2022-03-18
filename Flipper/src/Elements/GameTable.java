package Elements;

public class GameTable{
    /**
     * 每一帧运算一次小球位置
     */
    public static int frameRate = 60;
    public static float frameTime = (float)1.0/frameRate;
    public static float dt = (float) 0.000001;
    //小球探测范围，以及台球桌边缘留白，小球和障碍物都不能出现在这一区域。
    public static int margin = 100;
    public int width;
    public int height;
    //将游戏桌想象成一堆格子，将球的横纵坐标对应到表格的行列，位置更新时，同时更新其表格中的位置
    public static ElementBasic[][] table;
    public Ball xiangpiqiu= new Ball(2, 3, 4, 5, (float)0.3, (float)0.1);
    public Ball gangqiu = new Ball(2, 3, 4, 5, (float)0.3, (float)0.1);
    

    public GameTable(int width, int height){
        this.width = width + 2 * margin;
        this.height = height+ 2 * margin;
        table = new ElementBasic[this.width][this.height];

        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                table[i][j]=new ElementBasic();
            }
            if(i%(width/100)==0){
                System.out.print("-");
            }
        }
        System.out.println();

        while(true){
            for(int i = xiangpiqiu.positionX - margin; i < xiangpiqiu.positionX + margin; i++){
                for(int j = xiangpiqiu.positionY - margin; j < xiangpiqiu.positionY + margin; j++){
                    if (xiangpiqiu.isOnContectWith(table[i][j])){
                        xiangpiqiu.collidesWith(table[i][j]);
                        
                    }
                }
                //System.out.println(xiangpiqiu.toString());
            }
            break;
        }
    }


}