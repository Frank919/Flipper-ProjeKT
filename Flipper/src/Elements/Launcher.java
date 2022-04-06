package Elements;

public class Launcher extends ElementKinetic {
    public Launcher(){
        nX = 0;
        nY = 1;
    }
    public Launcher(ElementBasic start, ElementBasic end){
        GameTable.table[start.positionX][start.positionY] = new Launcher();
    }
}

