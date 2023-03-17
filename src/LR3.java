public class LR3 {
    public static Vector descentMethod(Function2 f, Vector x, double eps) {
        Vector x1, x2;
        for(;;){
            x2 = x.getGradient(f, x);
            x1 = x.sub(x2.mul(0.5));
            if(f.getF(x1) - f.getF(x2) < eps)
                return x1;
            x = new Vector(x1);
        }
    }
    public static Vector tenseGradientMethod(Function2 f, Vector x, double eps) {
        Vector start = (x.getGradient(f, x)).mul(-1.0); //антиградиент
        Vector x1 = new Vector(x),x2, xs;
        double omega;
        for(;;){
            x2 = x1.add(start);
            x2 = LR2.gRatio(f, x1, x2, eps);
            if(f.getF(x1) - f.getF(x2) < eps) return (x1.add(x2)).mul(0.5);
            xs = x2.getGradient(f, x2);
            omega = Math.pow((xs).magnitude(), 2) / Math.pow((start).magnitude(), 2);
            start.mul(omega).sub(xs);
            x1 = x2;
        }
    }

}
