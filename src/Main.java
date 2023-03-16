import java.util.Scanner;

public class Main {

    public static double testF(double X) {
        return ((X - 5) * (X - 2));
    }
    public static double testF2(Vector x) {
        return (x.get(0) - 5) * x.get(0) + (x.get(1) - 3) * x.get(1)+ (x.get(2) - 3) * x.get(2);
    }
    public static final Function f = Main::testF;
    public static final Function2 f2 = Main::testF2;

    public static void main(String[] args) {
        System.out.print("Enter lab work = ");
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        switch (c) {
            case 1:
                testLR1(sc);
                break;
            case 2:
                testLR2();
                break;
            case 3:
                testLR3();
                break;
            default:
                System.out.println("Fake data");
        }
    }
    public static void testLR1(Scanner sc){
        System.out.print("Enter xL = ");
        double xL = sc.nextDouble();
        System.out.print("Enter xR = ");
        double xR = sc.nextDouble();
        if (xR < xL) xR = (xR + xL) - (xL = xR);
        System.out.println("Половинное деление: " + LR1.dihtomia(f, xL, xR, Const.eps));
        System.out.println("Золотое сечение: " + LR1.gRatio(f, xL, xR, Const.eps));
        System.out.println("Фибоначчи: " + LR1.fibonacci(f, xL, xR, Const.eps));
    }

    public static void testLR2(){
        Vector x0 = new Vector(5.0, 3.0, -12.0);
        Vector x1 = new Vector(0.0, 0.0, 7.2 );
        Vector t = new Vector(-12.0, 15.3, 0.0);
        if (x0.size() != x1.size())
            System.out.println("Fake data");
        else {
            System.out.println("Золотое сечение: " + LR2.gRatio(f2, x0, x1, Const.eps).toString());
            System.out.println("Зейделя : " + LR2.descentMethod(f2, t, Const.eps).toString());
        }
    }
    public static void testLR3(){
        Vector t = new Vector(-13.0, 12.5);
        System.out.println("GRAD : " + LR3.descentMethod(f2, t, Const.eps).toString());
    }


}
