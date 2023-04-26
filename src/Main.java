import java.util.Scanner;

public class Main {

    public static double testF(double X) {
        return ((X - 5) * (X - 2));
    }
    public static double testF2(Vector x) {
        double val = 0.0;
        for (int i = 0; i < x.size(); i++) {
            val += ((x.get(i) - 0.5 * i) * (x.get(i) - 0.5 * i));
        }
        return val;
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
            case 4:
                testLR4();
                break;
            case 5:
                testLR5();
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
        Vector x0 = new Vector(5.0, 3.0, 6.0);
        Vector x1 = new Vector(0.5, 6.0, -5.0);
        Vector t = new Vector(-123.0, 15.3, 56.3);
        if (x0.size() != x1.size())
            System.out.println("Fake data");
        else {
            System.out.println("Золотое сечение: " + LR2.gRatio(f2, x0, x1, Const.eps).toString());
            System.out.println("Зейделя : " + LR2.descendMethod(f2, t, Const.eps).toString());
        }
    }
    public static void testLR3(){
        Vector t = new Vector(64);
        System.out.println("Градиентный спуск : " + LR3.descendMethod(f2, t, Const.eps).toString());
        System.out.println("Сопряженный градиент : " + LR3.tenseGradientMethod(f2, t, Const.eps).toString());
        System.out.println("Градиентный спуск для Юрия Станиславовича : " + LR3.descendMethodForYuryStrelkov(f2, t, Const.eps).toString());
    }
    public static void testLR4(){
        Vector t = new Vector(5);
        System.out.println("Ньютон Рафсон : " + LR4.newtoneRaphson(f2, t, Const.eps).toString());
    }
    public static void testLR5(){
        Vector a1 = new Vector(5.0, 3.0);
        Vector a2 = new Vector(0.5, 6.0);
        Matrix a = new Matrix(a1, a2);
        Vector b = new Vector(2.5, 5.0);
        Vector c = new Vector(2.0, 3.2);
        String[] arr = {"<=", "<="};
        System.out.println("симплекс : " + LR5.Simplex(a, b, c, arr));
    }


}
