import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static double testF(double X) {
        return ((X - 5) * (X - 2));
    }
    public static double testF2(Vector x) {
        return (x.get(0) - 5) * x.get(0) + (x.get(1) - 3) * x.get(1); // min at point x = 2.5, y = 1.5
    }
    public static final Function f = Main::testF;
    public static final Function2 f2 = Main::testF2;


    public static void main(String[] args) {
        System.out.print("Enter lab work = ");
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
        switch (c){
            case 1:
                System.out.print("Enter xL = ");
                double xL = sc.nextDouble();
                System.out.print("Enter xR = ");
                double xR = sc.nextDouble();
                if (xR < xL) xR = (xR + xL) - (xL = xR);
                System.out.println("Половинное деление: " + LR1.dihtomia(f, xL, xR, Const.eps));
                System.out.println("Золотое сечение: " + LR1.gRatio(f, xL, xR, Const.eps));
                System.out.println("Фибоначчи: " + LR1.fibonacci(f, xL, xR, Const.eps));
            case 2:
                Vector x0 = new Vector(5.0, 3.0);
                Vector x1 = new Vector(0.0, 0.0);
                if(x0.size()!= x1.size())
                    System.out.println("Fake data");
                else{
                    System.out.println("Золотое сечение: " + LR2.gRatio(f2, x0, x1, Const.eps).get(1));
                }
        }
    }

}
