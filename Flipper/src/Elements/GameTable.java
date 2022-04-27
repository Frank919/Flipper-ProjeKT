package Elements;

public class GameTable{
    /**
     * Fois d'actualisation en 1 seconde
     */
    public static int refreshRate = 60;
    /**
     * Interval temporel entre 2 actualisations 
     */
    public static float refreshTime = (float)1.0/refreshRate;
    /**
     * Interval en milliseconde
     */
    public static long refreshTimeMS = (long)(1000*refreshTime);


    /**
     * C'est aussi le margin du pseudoTable
     */
    public static int detectionRange = 5;
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
    public Ball ball; 

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

        table = new ElementBasic[width][height];
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
                table[i][j]=new ElementBasic();
            }
            if(i%(w/100)==0){
                System.out.print("-");
            }
        }
        System.out.println("\nThe game table generated with success");

        pseudoTable = new ElementBasic[pseudoWidth][pseudoHeight];
        for(int i=0;i<pseudoWidth;i++){
            for(int j=0;j<pseudoHeight;j++){
                pseudoTable[i][j]=new ElementBasic();
            }
            if(i%(w/100)==0){
                System.out.print("-");
            }
        }
        System.out.println("\nThe pseudo game table generated with success");

        /**
         * 在此处构造所有障碍物，障碍物构造器会将其填入table[][]当中
         * ...
         */
        new Boundary(1, (float)0.5);
        float smooth=(float)0.5;
        float elasticity = (float)0.6;
        flipperLeft = new Flipper(new ElementBasic(6, 6), new ElementBasic(2, 3), (float)0.5,(float)0.6, false);
        flipperRight = new Flipper(new ElementBasic(6, 6), new ElementBasic(2, 3), (float)0.5,(float)0.6, true);
        /*Curve curve1 = new Curve(new ElementBasic(115,90), new ElementBasic(115,90), new ElementBasic(161,90), 46,smooth,elasticity);
        Curve curve2 = new Curve(new ElementBasic(376,107), new ElementBasic(376,107), new ElementBasic(285,107), 91,smooth,elasticity);
        Curve curve3 = new Curve(new ElementBasic(127,165), new ElementBasic(1,82), new ElementBasic(151,-2), 172,smooth,elasticity); 
        Curve curve4 = new Curve(new ElementBasic(2,283), new ElementBasic(246,268), new ElementBasic(125,285), 119,smooth,elasticity);
        Curve curve5 = new Curve(new ElementBasic(138,314), new ElementBasic(190,282), new ElementBasic(141,261), 54,smooth,elasticity);
        Curve curve6 = new Curve(new ElementBasic(380,386), new ElementBasic(380,386), new ElementBasic(306,386), 74,smooth,elasticity);
        Curve curve7 = new Curve(new ElementBasic(39,420), new ElementBasic(39,420), new ElementBasic(39,402), 18,smooth,elasticity);
        Curve curve8 = new Curve(new ElementBasic(122,424), new ElementBasic(122,424), new ElementBasic(105,424), 17,smooth,elasticity);
        Curve curve9 = new Curve(new ElementBasic(74,476), new ElementBasic(74,476), new ElementBasic(57,476), 17,smooth,elasticity);
        Curve curve10 = new Curve(new ElementBasic(313,605), new ElementBasic(418,605), new ElementBasic(365,602), 54,smooth,elasticity);
        */
        ElementBasic[] elm1={new ElementBasic(362,280),new ElementBasic(441,139),new ElementBasic(441,331)};
        Polygone polygone1= new Polygone (elm1,smooth,elasticity);
        ElementBasic[] elm2={new ElementBasic(47,315),new ElementBasic(57,315),new ElementBasic(57,355),new ElementBasic(47,355)};
        Polygone polygone2= new Polygone (elm2,smooth,elasticity);
        ElementBasic[] elm3={new ElementBasic(82,314),new ElementBasic(93,314),new ElementBasic(93,355),new ElementBasic(82,353)};
        Polygone polygone3= new Polygone (elm3,smooth,elasticity);
        ElementBasic[] elm4={new ElementBasic( 38,585),new ElementBasic(84,666),new ElementBasic(21,666)};
        Polygone polygone4= new Polygone (elm4,smooth,elasticity);
        ElementBasic[] elm5={new ElementBasic(115,488),new ElementBasic(169,489),new ElementBasic(164,624)};
        Polygone polygone5= new Polygone (elm5,smooth,elasticity);
        ElementBasic[] elm6={new ElementBasic(212,541),new ElementBasic(255,541),new ElementBasic(231,649)};
        Polygone polygone6= new Polygone (elm6,smooth,elasticity);

        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                pseudoTable[i+margin][j+margin]=table[i][j];
            }
            if(i%(w/100)==0){
                System.out.print("-");
            }
        }
        System.out.println("\nAll Elements copied to pseudoTable with success");

        this.initialize();
    }

    public boolean initialize(){
        
        return true;
    }

    public boolean refresh() { 
        this.isRunning = true;
        //Supprimer la balle de pseudoTable 
        pseudoTable[ball.positionX][ball.positionY] = new ElementBasic();
        
        if(ball.isOnContectWith(flipperLeft)){
            ball.collidesWith(flipperLeft);
        }
        if(ball.isOnContectWith(flipperRight)){
            ball.collidesWith(flipperRight);
        }
        for(int i = ball.positionX - detectionRange; i < ball.positionX + detectionRange; i++){
            for(int j = ball.positionY - detectionRange; j < ball.positionY + detectionRange; j++){
                if (ball.isOnContectWith(pseudoTable[i][j])){
                    ball.collidesWith(pseudoTable[i][j]);
                }
            }
        }
        ball.moves();
        //Rajouter la balle dans pseudoTable 
        pseudoTable[ball.positionX][ball.positionY] = ball;
        System.out.println(ball.toString());
        if(ball.isOut()){
            isRunning = false;
        }
        return isRunning;
    }



    public String toString(){
        return "Gmae is running : " + isRunning + "\n";
    }
    



}