public class LR2 {

   public static Vector gRatio(Function2 f, Vector x0, Vector x1, double eps) {
        Vector a = new Vector(x0.get(0), x0.get(1));
        Vector b = new Vector(x1.get(0), x1.get(1));
        Vector dx;
        double iPhi = 1.0 / Const.phi;
        while (Vector.sub(x1, x0).magnitude() >= eps) {
            dx = Vector.sub(b, a);
           /* for (int i = 0; i < dx.size(); i++)
                System.out.println(dx.get(i));*/
            dx = dx.mul(dx, iPhi);
            x0 = Vector.sub(b, dx);
            x1 = Vector.add(a, dx);
            if (f.getF(x0) >= f.getF(x1))
                a = x0;
            else
                b = x1;
            System.out.println();
        }
        dx = Vector.add(x1, x0);
        dx = dx.mul(dx, 0.5);
        return dx;
    }
}
