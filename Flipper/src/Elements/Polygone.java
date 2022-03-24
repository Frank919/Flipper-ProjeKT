package Elements;

public class Polygone extends Obstacle{
    /**
     * 三个顶点，两两求直线方程
     * 每两个顶点的x坐标之间，的所有x值，代入方程，求y
     * 这样就得到了边上所有点的坐标
     * 把这些点放到list里面，让java遍历，造Obstacle对象
     */
    /**
     * 
     * @param obs
     *      Une série de sommets bien rangée dans l'ordre consécutif
     * @param s
     *      Smoothness entre 0 et 1
     * @param e
     *      Elasticity entre 0 et 1
     */
    public Polygone(Obstacle[] obs, float s, float e){
        /**
         * Ranger les obs dans le sens croissant en X
         
        Obstacle temp = new Obstacle();
        for(int j=0;j<obs.length-1;j++){
            for(int i=0;i<obs.length-j-1;i++){
                if(obs[i+1].positionX < obs[i].positionX){
                    temp = obs[i];
                    obs[i] = obs[i+1];
                    obs[i+1] = temp;
                }
            }
        }
        */
        /**
         * Ranger les obs dans le sens croissant en Y
         
        for(int j=0;j<obs.length-1;j++){
            for(int i=0;i<obs.length-j-1;i++){
                if(obs[i+1].positionY < obs[i].positionY){
                    temp = obs[i];
                    obs[i] = obs[i+1];
                    obs[i+1] = temp;
                }
            }
        }
        */
        for(int i=0;i<obs.length;i++){
            float nx = 0;
            float ny = 0;
            int max = 0;
            int min = 0;
            if(i == obs.length - 1){//也可以不写特例，但是在定义顶点数组时，要多加一个位置存放第一个顶点
                float k = (obs[i].positionY - obs[0].positionY) /
                         (obs[i].positionX - obs[0].positionX);
                float b = obs[i].positionY - k * obs[i].positionX;
                if(obs[i].positionX<obs[0].positionX){
                    max = obs[0].positionX;
                    min = obs[i].positionX;
                }else{
                    min = obs[0].positionX;
                    max = obs[i].positionX;
                }
            }else{
                //La pente
                float k = (obs[i].positionY - obs[i+1].positionY) /
                         (obs[i].positionX - obs[i+1].positionX);
                //L'ordonée à l'origine
                float b = obs[i].positionY - k * obs[i].positionX;


                if(obs[i].positionX<obs[i+1].positionX){
                    max = obs[i+1].positionX;
                    min = obs[i].positionX;
                }else{
                    min = obs[i+1].positionX;
                    max = obs[i].positionX;
                }
                for(int x = min;x<=max;x++){
                    int y = (int)(k*x+b);
                    GameTable.table[x][y]=new Obstacle(nx, ny, s, e);
                }
                
            }
            


        }
    }
}
