public class LR1 {

    public static final double phi = 1.61803398874989484820;

    public static double dihtomia(Function f, double xL, double xR, double eps) {
        double xC = 0.0;
        while (Math.abs(xR - xL) >= eps) {
            xC = (xR + xL) * 0.5;
            if (f.getF(xC - eps) > f.getF(xC + eps))
                xL = xC + eps;
            else
                xR = xC - eps;
        }
        return xC;
    }

    public static double gRatio(Function f, double xL, double xR, double eps) {
        double x1 = xL, x2 = xR, iPhi = 1 / phi, d;
        while (Math.abs(xR - xL) >= eps) {
            d = x2 - x1;
            xL = x2 - d * iPhi;
            xR = x1 + d * iPhi;
            if (f.getF(xL) >= f.getF(xR))
                x1 = xL;
            else
                x2 = xR;
        }
        double xC = (xR + xL) * 0.5;
        return xC;
    }

    public static int[] closed_fib(double arg) {
        int fn = 0, fn1 = 1, temp;
        if (arg < 1) return new int[]{0, 0};
        if (arg < 2) return new int[]{0, 1};
        while (fn < arg) {
            temp = fn;
            fn = fn1;
            fn1 += temp;
        }
        return new int[]{fn, fn1};
    }

   /* public static int getFib(double index){
        return (int)(Math.pow(phi, index) - (Math.pow((1 - phi), index)) / Math.sqrt(5));
    } */

    public static double fibonacci(Function f, double x1, double x2, double eps) {
        double xL = x1, xR = x2;
        int fTmp;
        int[] ff = closed_fib((x2 - x1) / eps);
        int f_n = ff[0];
        int f_n_1 = ff[1];

        while (f_n != f_n_1) {
            fTmp = f_n_1 - f_n;
            xL = x1 + ((double)fTmp / f_n_1) * (x2 - x1);
            xR = x1 + ((double)f_n / f_n_1) * (x2 - x1);
            f_n_1 = f_n;
            f_n = fTmp;
            if (f.getF(xL) >= f.getF(xR))
                x1 = xL;
            else
                x2 = xR;
        }
        return (x1 + x2) * 0.5;

    }
}
