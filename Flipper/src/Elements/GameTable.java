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
            
        }
        System.out.println("\nThe game table generated with success");
        System.out.println("w = " + width);

        pseudoTable = new ElementBasic[pseudoWidth][pseudoHeight];
        for(int i=0;i<pseudoWidth;i++){
            for(int j=0;j<pseudoHeight;j++){
                pseudoTable[i][j]=new Space(i,j);
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
        Curve curve1 = new Curve(new ElementBasic(92,103), new ElementBasic(92,103), new ElementBasic(131,103), 39,smooth,elasticity);//+
        Curve curve2 = new Curve(new ElementBasic(256,144), new ElementBasic(256,144), new ElementBasic(367,144), 111,smooth,elasticity);//+
        Curve curve3 = new Curve(new ElementBasic(170,219), new ElementBasic(1,109), new ElementBasic(202,-3), 234,smooth,elasticity); 
        Curve curve4 = new Curve(new ElementBasic(3,375), new ElementBasic(329,355), new ElementBasic(167,378), 167,smooth,elasticity);
        Curve curve5 = new Curve(new ElementBasic(184,416), new ElementBasic(254,374), new ElementBasic(188,346), 73,smooth,elasticity);
        Curve curve6 = new Curve(new ElementBasic(319,514), new ElementBasic(319,514), new ElementBasic(422,514), 103,smooth,elasticity);//+
        Curve curve7 = new Curve(new ElementBasic(33,533), new ElementBasic(33,533), new ElementBasic(58,533), 25,smooth,elasticity);//+
        Curve curve8 = new Curve(new ElementBasic(124,564), new ElementBasic(124,564), new ElementBasic(147,564), 24,smooth,elasticity);//+
        Curve curve9 = new Curve(new ElementBasic(56,636), new ElementBasic(56,636), new ElementBasic(81,636), 25,smooth,elasticity);//+
        Curve curve10 = new Curve(new ElementBasic(418,802), new ElementBasic(558,802), new ElementBasic(489,798), 72,smooth,elasticity);
        Curve curve11 = new Curve(new ElementBasic(549,1), new ElementBasic(612,64), new ElementBasic(549,64), 63,smooth,elasticity);
        ElementBasic[] elm1={new ElementBasic(480,373),new ElementBasic(583,217),new ElementBasic(583,439)};
        Polygone polygone1= new Polygone (elm1,smooth,elasticity);
        ElementBasic[] elm4={new ElementBasic(281,712),new ElementBasic(337,712),new ElementBasic(218,822)};
        Polygone polygone4= new Polygone (elm4,smooth,elasticity);
        ElementBasic[] elm5={new ElementBasic(154,776),new ElementBasic(225,647),new ElementBasic(219,828)};
        Polygone polygone5= new Polygone (elm5,smooth,elasticity);
        ElementBasic[] elm6={new ElementBasic(32,876),new ElementBasic(54,772),new ElementBasic(114,876)};
        Polygone polygone6= new Polygone (elm6,smooth,elasticity);
        ElementBasic[] elm2={new ElementBasic(112,468),new ElementBasic(112,419),new ElementBasic(124,419),new ElementBasic(125,468)};
        Polygone polygone2= new Polygone (elm2,smooth,elasticity);
        ElementBasic[] elm3={new ElementBasic(65,468),new ElementBasic(65,419),new ElementBasic(78,419),new ElementBasic(78,468)};
        Polygone polygone3= new Polygone (elm3,smooth,elasticity);
        
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
        this.ball.setPosition(600, 200);
        this.ball.setVelocity(-300, 0);
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