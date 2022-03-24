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
    public static int width;
    public static int height;
    public int pseudoWidth;
    public int pseudoHeight;
    /**
     * Le table vrai qu'on peut voir. Tous les obstacles et élément sont mis ici.
     */
    public static ElementBasic[][] table;

    /**
     * Le table qu'on ne peut voir. Tous les calculs et la balle sont mis ici.
     * Il a des margins donc plus grand.
     * Ceux-ci existent pour le bon fonctionnement de la detection de collision.
     * Chaque élément dans ce table a donc un décalage d'un margin en X et en Y
     * par rapport à celui dans le table vrai.
     */
    public static ElementBasic[][] pseudoTable;

    /**
     * Créer des balles différentes
     */
    public Ball xiangpiqiu= new Ball(2, 3, 4, 5, (float)0.3, (float)0.1);
    public Ball gangqiu = new Ball(2, 3, 4, 5, (float)0.3, (float)0.1);
    
    /**
     * 
     * @param w
     *      La largeur du table
     * @param h
     *      La hauteur du table
     */
    public GameTable(int w, int h){

        width = w;
        height = h;
        this.pseudoWidth = w + 2 * margin;
        this.pseudoHeight = h + 2 * margin;

        table = new ElementBasic[width][height];
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
                table[i][j]=new ElementBasic();
            }
            if(i%(w/100)==0){
                System.out.print("-");
            }
        }
        System.out.println("\nThe game table successfully generated");

        pseudoTable = new ElementBasic[pseudoWidth][pseudoHeight];
        for(int i=0;i<pseudoWidth;i++){
            for(int j=0;j<pseudoHeight;j++){
                pseudoTable[i][j]=new ElementBasic();
            }
            if(i%(w/100)==0){
                System.out.print("-");
            }
        }
        System.out.println("\nThe pseudo game table successfully generated");

        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                pseudoTable[i+margin][j+margin]=table[i][j];
            }
            if(i%(w/100)==0){
                System.out.print("-");
            }
        }
        System.out.println("\nAll Elements successfully copied to pseudoTable");

        while(true){
            for(int i = xiangpiqiu.positionX - margin; i < xiangpiqiu.positionX + margin; i++){
                for(int j = xiangpiqiu.positionY - margin; j < xiangpiqiu.positionY + margin; j++){
                    if (xiangpiqiu.isOnContectWith(pseudoTable[i][j])){
                        xiangpiqiu.collidesWith(pseudoTable[i][j]);
                        
                    }
                }
                System.out.println(xiangpiqiu.toString());
            }
            break;
        }
    }


}