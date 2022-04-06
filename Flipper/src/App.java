import IHM.MainWindow;

public class App {
    public static void main(String[] args) throws Exception {
        new MainWindow();
        new A(2);
        
    }
}
class A{
    int a;
    public A(){
        a = 3;
        System.out.print(a);
    }
    public A(int a){
        this.a = a;
        new A();
    }
}
