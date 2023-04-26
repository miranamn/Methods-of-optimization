
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

}
