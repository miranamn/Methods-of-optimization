public class LR2 {

   public static Vector gRatio(Function2 f, Vector x0, Vector x1, double eps) {
        Vector a = new Vector(x0.get(0), x0.get(1));
        Vector b = new Vector(x1.get(0), x1.get(1));
        Vector dx;
        while (x1.sub(x0).dimension() >= eps) {
            dx = b.sub(a).mul(Const.iPhi);
            x0 = b.sub(dx);
            x1 = a.add(dx);
            if (f.getF(x0) >= f.getF(x1))
                a = x0;
            else
                b = x1;
        }
        dx = x1.add(x0).mul(0.5);
        return dx;
    }
}
