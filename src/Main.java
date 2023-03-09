import java.util.Scanner;

public class Main {

    public static double testF(double X) {
        return ((X - 5) * (X - 2));
    }
    public static final Function f = Main::testF;

    public static void main(String[] args) {
        System.out.print("Enter xL = ");
        Scanner sc = new Scanner(System.in);
        double xL = sc.nextDouble();
        System.out.print("Enter xR = ");
        double xR = sc.nextDouble();
        if (xR < xL) xR = (xR + xL) - (xL = xR);
        double eps = 0.00001;
        System.out.println("Половинное деление: " + LR1.dihtomia(f, xL, xR, eps));
        System.out.println("Золотое сечение: " + LR1.gRatio(f, xL, xR, eps));
        System.out.println("Фибоначчи: " + LR1.fibonacci(f, xL, xR, eps));
    }

}
