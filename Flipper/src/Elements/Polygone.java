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
     *      Une série de sommets bien rangée dans le sens horaire
     * @param s
     *      Smoothness entre 0 et 1
     * @param e
     *      Elasticity entre 0 et 1
     */
    public Polygone(Obstacle[] obs, float s, float e){
        for(int i=0;i<obs.length;i++){
            float nx = 0;
            float ny = 0;
            int max = 0;
            int min = 0;
            Obstacle end = new Obstacle();
            Obstacle start = new Obstacle();
            if(i == obs.length - 1){//也可以不写特例，但是在定义顶点数组时，要多加一个位置存放第一个顶点
                end = obs[0];
                start = obs[i];
            }else{
                end = obs[i+1];
                start = obs[i];
            }
            //La pente
            int deltaY = end.positionY - start.positionY;
            int deltaX = end.positionX - start.positionX;
            float k = deltaY / deltaX;
            //L'ordonée à l'origine
            float b = start.positionY - k * start.positionX;

            //Le vecteur normal
            ny = (float)Math.sqrt(1/(1+k*k));
            nx = - k * ny;
            float produitVectoriel =(nx*deltaY)-(ny*deltaX);
            
            //Si le produit vectoriel est négatif, on change le signe de nx et ny
            if(produitVectoriel>0){
                ny = -ny;
                nx = -nx;
            }
            if(start.positionX<end.positionX){
                max = end.positionX;
                min = start.positionX;
            }else{
                min = end.positionX;
                max = start.positionX;
            }
            for(int x = min;x<=max;x++){
                int y = (int)(k*x+b);
                GameTable.table[x][y]=new Obstacle(nx, ny, s, e);
            }
        }
    }
}
