package Elements;


public class GameTable implements Runnable{
    public Ball ball; 
    /**
     * Fois d'actualisation en 1 seconde
     */
    public static int refreshRate = 120;
    /**
     * Interval temporel entre 2 actualisations 
     */
    public static double refreshTime = (double)1.0/refreshRate;
    /**
     * Interval en milliseconde
     */
    public static long refreshTimeMS = (long)(1000*refreshTime);


    /**
     * C'est aussi le margin du pseudoTable
     */
    public static int detectionRange = 4;
    public static int margin = 20;

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
     * Il a des margins donc est plus grand que celui vrai.
     * <p>Ceux-là existent pour le bon fonctionnement de la detection de collision.
     * Chaque élément dans ce table a donc un décalage d'un margin en X et en Y
     * par rapport aux éléments dans le table vrai.
     */
    public static ElementBasic[][] pseudoTable;

    /**
     * Créer des balles différentes
     */
    

    /**
     * Zone des éléments
     */
    public Flipper flipperRight;
    public Flipper flipperLeft;
    /**
     * État du jeu
     */
    public boolean isRunning;
    /**
     * 
     * @param w
     *      La largeur du table
     * @param h
     *      La hauteur du table
     */
    public GameTable(int w, int h,Ball ball){
        this.ball = ball;
        width = w;
        height = h;
        this.pseudoWidth = w + 2 * margin;
        this.pseudoHeight = h + 2 * margin;

        this.buildAll();
        this.initialize();

    }
    public boolean buildAll(){
        table = new ElementBasic[width][height];
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                table[i][j]=new Space(i,j);
            }
            if(i%(width/100)==0){
                System.out.print("-");
            }
        }
        System.out.println("\nThe game table generated with success");

        pseudoTable = new ElementBasic[pseudoWidth][pseudoHeight];
        for(int i=0;i<pseudoWidth;i++){
            for(int j=0;j<pseudoHeight;j++){
                pseudoTable[i][j]=new Space(i,j);
            }
            if(i%(width/100)==0){
                System.out.print("-");
            }
        }
        System.out.println("\nThe pseudo game table generated with success");

        /**
         * 在此处构造所有障碍物，障碍物构造器会将其填入table[][]当中
         * ...
         */
        
        new Boundary(1.0, 0.5);
        double smooth = 0.5;
        double elasticity = 1;
        flipperLeft = new Flipper(new ElementBasic(132, 919), new ElementBasic(296, 948), 0.5,0.1, false);
        flipperRight = new Flipper(new ElementBasic(517, 919), new ElementBasic(353, 948), 0.5,0.1, true);
        /*Curve curve1 = new Curve(new ElementBasic(160,12), new ElementBasic(160,12), new ElementBasic(224,12), 64,smooth,elasticity);
        Curve curve2 = new Curve(new ElementBasic(523,148), new ElementBasic(523,148), new ElementBasic(396,148), 126,smooth,elasticity);
        Curve curve3 = new Curve(new ElementBasic(176,229), new ElementBasic(1,113), new ElementBasic(210,-3), 239,smooth,elasticity); 
        Curve curve4 = new Curve(new ElementBasic(3,393), new ElementBasic(342,372), new ElementBasic(174,396), 169,smooth,elasticity);
        Curve curve5 = new Curve(new ElementBasic(192,436), new ElementBasic(264,391), new ElementBasic(196,326), 74,smooth,elasticity);
        Curve curve6 = new Curve(new ElementBasic(528,536), new ElementBasic(528,536), new ElementBasic(425,536), 102,smooth,elasticity);
        Curve curve7 = new Curve(new ElementBasic(54,58), new ElementBasic(54,58), new ElementBasic(54,558), 500,smooth,elasticity);
        Curve curve8 = new Curve(new ElementBasic(169,588), new ElementBasic(169,588), new ElementBasic(146,588), 23,smooth,elasticity);
        Curve curve9 = new Curve(new ElementBasic(102,661), new ElementBasic(102,661), new ElementBasic(79,661), 23,smooth,elasticity);
        Curve curve10 = new Curve(new ElementBasic(435,840), new ElementBasic(581,840), new ElementBasic(507,836), 73,smooth,elasticity);
        */
        ElementBasic[] elm1={new ElementBasic(503,38),new ElementBasic(614,193),new ElementBasic(631,345)};
        Polygone polygone1= new Polygone (elm1,smooth,elasticity);
        ElementBasic[] elm2={new ElementBasic(65,473),new ElementBasic(79,437),new ElementBasic(79,493),new ElementBasic(65,493)};
        Polygone polygone2= new Polygone (elm2,smooth,elasticity);
        ElementBasic[] elm3={new ElementBasic(114,436),new ElementBasic(129,436),new ElementBasic(129,493),new ElementBasic(114,490)};
        Polygone polygone3= new Polygone (elm3,smooth,elasticity);
        ElementBasic[] elm4={new ElementBasic(52,821),new ElementBasic(116,925),new ElementBasic(29,925)};
        Polygone polygone4= new Polygone (elm4,smooth,elasticity);
        ElementBasic[] elm5={new ElementBasic(160,677),new ElementBasic(253,679),new ElementBasic(228,866)};
        Polygone polygone5= new Polygone (elm5,smooth,elasticity);
        ElementBasic[] elm6={new ElementBasic(294,751),new ElementBasic(354,751),new ElementBasic(321,901)};
        Polygone polygone6= new Polygone (elm6,smooth,elasticity);
        
        
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                pseudoTable[i+margin][j+margin]=table[i][j];
                if(pseudoTable[i][j] instanceof Obstacle){
                    System.out.print("o");
                }
            }
            if(i%(width/100)==0){
                System.out.println("-");
            }
        }
        System.out.println("\nAll Elements copied to pseudoTable with success");
        return true;
    }
    public boolean initialize(){
        this.ball.setPosition(450, 200);
        this.ball.setVelocity(-500, 0);
        return true;
    }

    public boolean refresh() { 
        
        this.isRunning = true;
        //Supprimer la balle de pseudoTable 
        pseudoTable[ball.positionX][ball.positionY] = new Space(ball.positionX - GameTable.margin,ball.positionY - GameTable.margin);
        ball.falls();
        if(ball.isOnContectWith(flipperLeft)){
            ball.collidesWith(flipperLeft);
            System.out.println("ccc1");
        }
        if(ball.isOnContectWith(flipperRight)){
            ball.collidesWith(flipperRight);
            System.out.println("ccc2");
        }
        
        outloop:
        for(int i = ball.positionX - detectionRange; i < ball.positionX + detectionRange - 1; i++){
            for(int j = ball.positionY - detectionRange; j < ball.positionY + detectionRange - 1; j++){
                if (ball.isOnContectWith(pseudoTable[i][j])){
                    ball.collidesWith(pseudoTable[i][j]);
                    System.out.println(
                        "contact on ("+(i-margin+1)+" , "+(j-margin+1)+") "
                        +"normal="+"("+pseudoTable[i][j].nX+","+pseudoTable[i][j].nY+")"
                    );
                    do{
                        ball.moves();
                    } while(ball.isOnContectWith(pseudoTable[i][j]));
                    break outloop;
                }
            }
        }
        
        //Rajouter la balle dans pseudoTable 
        pseudoTable[ball.positionX][ball.positionY] = ball;
        ball.moves();
        if(ball.isOut()){
            isRunning = false;
        }
        return isRunning;
    }

    public String toString(){
        return "Gmae is running : " + isRunning + "\n";
    }
    
    public void run(){

    }


}