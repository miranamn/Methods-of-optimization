public class LR3 {
    public static Vector descendMethod(Function2 f, Vector x, double eps) {
        Vector x1, x2;
        for(;;){
            x2 = x.getGradient(f, x);
            x1 = x.sub(x2.mul(0.5));
            if(f.getF(x1) - f.getF(x2) < eps)
                return x1;
            x = new Vector(x1);
        }
    }
    public static Vector descendMethodForYuryStrelkov(Function2 f, Vector x, double eps) {
        Vector x1 = new Vector(x);
        for (;;) {
            x = x1.sub(Vector.getGradient(f, x1));
            x = LR2.gRatio(f, x1, x, eps);
            if (f.getF(x1) - f.getF(x) < eps)
                return x.add(x1).mul(0.5);
            x1 = x;
        }
    }
    public static Vector tenseGradientMethod(Function2 f, Vector x, double eps) {
        Vector antigradient = (x.getGradient(f, x)).mul(-1.0);
        Vector x1 = new Vector(x), x2;
        double step;
        for(;;){
            x = x1.add(antigradient);
            x = LR2.gRatio(f, x1, x, eps);
            if(f.getF(x1) - f.getF(x) < eps)
                return (x1.add(x)).mul(0.5);
            x2 = x.getGradient(f, x);
            step = Math.pow((x2).magnitude(), 2) / Math.pow((antigradient).magnitude(), 2);
            antigradient.mul(step).sub(x2);
            x1 = x;
        }
    }
}
