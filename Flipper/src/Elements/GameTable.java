package Elements;

import java.awt.*;

public class GameTable implements toBeDrawn{
    /**
     * Fois d'actualisation en 1 seconde
     */
    public static int refreshRate = 480;
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
    public Ball ball1 = new Ball(2, 3, 4, 5, (float)0.3, (float)0.1);
    public Ball ball2 = new Ball(2, 3, 4, 5, (float)0.3, (float)0.1);
    public Ball ball3 = new Ball(2, 3, 4, 5, (float)0.3, (float)0.1);

    /**
     * Zone des éléments
     */
    protected Flipper flipperRight;
    protected Flipper flipperLeft;
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
        flipperLeft = new Flipper(new ElementBasic(6, 6), new ElementBasic(2, 3), (float)0.5,(float)0.6, false);
        flipperRight = new Flipper(new ElementBasic(6, 6), new ElementBasic(2, 3), (float)0.5,(float)0.6, true);
        


        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                pseudoTable[i+margin][j+margin]=table[i][j];
            }
            if(i%(w/100)==0){
                System.out.print("-");
            }
        }
        System.out.println("\nAll Elements copied to pseudoTable with success");
    }

    public boolean startGame() { 
        this.isRunning = true;
        while(true){
            try {
                Thread.sleep(refreshTimeMS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Supprimer la balle de pseudoTable 
            pseudoTable[ball1.positionX][ball1.positionY] = new ElementBasic();
            ball1.falls();
            ball1.moves();
            if(ball1.isOnContectWith(flipperLeft)){
                ball1.collidesWith(flipperLeft);
                ball1.moves();
            }
            if(ball1.isOnContectWith(flipperRight)){
                ball1.collidesWith(flipperRight);
                ball1.moves();
            }
            for(int i = ball1.positionX - detectionRange; i < ball1.positionX + detectionRange; i++){
                for(int j = ball1.positionY - detectionRange; j < ball1.positionY + detectionRange; j++){
                    if (ball1.isOnContectWith(pseudoTable[i][j])){
                        ball1.collidesWith(pseudoTable[i][j]);
                        ball1.moves();
                    }
                }
            }
            //Rajouter la balle dans pseudoTable 
            pseudoTable[ball1.positionX][ball1.positionY] = ball1;
            System.out.println(ball1.toString());
            if(ball1.isOut()){
                isRunning = false;
                break;
            }
            //break;
        }
        return isRunning;
    }

    @Override
    public void drawImage(Graphics g){

    }


    public String toString(){
        return "Gmae is running : " + isRunning + "\n";
    }
    



}