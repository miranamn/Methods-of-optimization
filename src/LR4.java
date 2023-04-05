import java.util.List;

public class LR4 {

    public static Vector newtoneRaphson(Function2 f, Vector x, double eps) {
        Vector x1 = new Vector(x);
        Vector x2;
        int i = 0;
        for (;;i++){
            x2 = x1.sub(Matrix.mul(Matrix.invert(Matrix.hessian(f, x1, eps)),x1.getGradient(f, x1))) ;
            if (x2.sub(x1).magnitude() < eps)
                return (x2.add(x1)).mul(0.5);
            x1 = x2;
        }
    }

    /* public static Vector getPenalty(Function2 f, Vector x, double eps) {
        double r = 1.0;
        double b = 0.4;
        Vector x1;
        for(;;){
            x1 = newtoneRaphson(f, x, eps);
            if((r * f.getF(x1)) < eps) return x1;
            x = x1;
            r  *= b;
        }
    }*/
}
