package Elements;

public class Curve extends Obstacle{
    
    protected float radius;
    protected int centreX, centreY;

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
        this.centreX = centre.positionX;
        this.centreY = centre.positionY;
        this.radius = radius;
        this.elasticity = e;
        this.smoothness = s;
        for(int x = centre.positionX - radius;x <= centre.positionX + radius;x++){
            //Pour un x fixé, on trouve 2 y  à travers de l'équation circulaire
            int y1 = centreY + (int)Math.sqrt(radius * radius - (x - centreX) * (x - centreX));
            int y2 = centreY - (int)Math.sqrt(radius * radius - (x - centreX) * (x - centreX));
            //Pour calculer le vecteur normal, on prend chaque point sur le cercle,et puis construit 
            //un vecteur avec celui-ci et le centre, donc il est toujour vers l'extérieur.
            //On rappel que le vecteur normal est unitaire, dans ce cas la il suffit de diviser par le rayon qui est la module.
            float nx1 = (x - centreX)/radius;
            float ny1 = (y1 - centreY)/radius;
            float nx2 = nx1;
            float ny2 = (y2 - centreY)/radius;
            GameTable.table[x][y1] = new Obstacle(nx1, ny1, s, e);
            GameTable.table[x][y2] = new Obstacle(nx2, ny2, s, e);
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
        //Une courbe n'est pas fermé, elle a donc une surface intérieur qui a des vecteurs normals vers l'intérieur.
        this(centre, radius, s, e);
        //Le rayon intérieur
        int innerRadius = radius - 1;
        for(int x = centre.positionX - innerRadius;x <= centre.positionX + innerRadius;x++){
            //Pour un x fixé, on trouve 2 y  à travers de l'équation circulaire
            int y1 = centreY + (int)Math.sqrt(innerRadius * innerRadius - (x - centreX) * (x - centreX));
            int y2 = centreY - (int)Math.sqrt(innerRadius * innerRadius - (x - centreX) * (x - centreX));
            /**
             * Pour calculer le vecteur normal, on prend chaque point sur le cercle intérieur,et puis construit 
             * un vecteur avec celui-ci et le centre, donc il est toujour vers l'intérieur.
             * On rappel que le vecteur normal est unitaire, dans ce cas la il suffit de diviser par le rayon intérieur qui est la module.
             */
            float nx1 = - (x - centreX)/innerRadius;
            float ny1 = - (y1 - centreY)/innerRadius;
            float nx2 = nx1;
            float ny2 = - (y2 - centreY)/innerRadius;
            GameTable.table[x][y1] = new Obstacle(nx1, ny1, s, e);
            GameTable.table[x][y2] = new Obstacle(nx2, ny2, s, e);

            //Puis on supprime les points non sur la courbe, on les remplace par ElementBasic. 


        }




    }
}
