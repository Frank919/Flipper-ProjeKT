package Elements;

public class Curve extends Obstacle{
    
    protected float radius;
    protected int centerX, centerY;

    /**
     * Une constructeur qui permet construit un cercle (une courbe circulaire complète)
     * @param centre
     *      Le centre du cercle
     * @param radius
     *      Le rayon du cercle
     * @param s
     *      La glissance
     * @param e
     *      L'élasticité
     */
    public Curve( ElementBasic centre, int radius, float s, float e){
        this.centerX = centre.positionX;
        this.centerY = centre.positionY;
        this.radius = radius;
        this.elasticity = e;
        this.smoothness = s;
        for(int x = centre.positionX - radius;x < centre.positionX + radius;x++){
            int y1 = 0;
            int y2 = 0;
            int nx = 0;
            int ny = 0;
            
            GameTable.table[x][y1] = new Obstacle(nx, ny, s, e);
            GameTable.table[x][y2] = new Obstacle(nx, ny, s, e);
        }

        

    }
    /**
     * Une constructeur qui permet construit une courbe circulaire partielle
     * @param startPoint
     *      Le point début de la courbe dans le sens horaire
     * @param endPoint
     *      Le point fin de la courbe dans le sens horaire
     * @param centre
     *      Le centre de la courbe circulaire
     * @param radius
     *      Le rayon de la courbe
     * @param s
     *      La glissance
     * @param e
     *      L'élasticité
     */
    public Curve(ElementBasic startPoint, ElementBasic endPoint, ElementBasic centre, int radius, float s, float e){
        this(centre, radius, s, e);




    }
}
