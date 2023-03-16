public class LR2 {

   public static Vector gRatio(Function2 f, Vector x0, Vector x1, double eps) {
        Vector a = new Vector(x0);
        Vector b = new Vector(x1);
        Vector dx;
        while (x1.sub(x0).magnitude() >= eps) {
            dx = (b.sub(a)).mul(Const.iPhi);
            x0 = b.sub(dx);
            x1 = a.add(dx);
            if (f.getF(x0) >= f.getF(x1))
                a = x0;
            else
                b = x1;
        }
        return x1.add(x0).mul(0.5);
   }

    public static Vector descentMethod(Function2 f, Vector x, double eps) {
       double step = 1.0;
       Vector x1 = new Vector(x);
       Vector x2 = new Vector(x);
       double t, y1, y2;
       int id, opt_id = 0;
       for(int i = 0; i < 1000; i++) {
           id = i % x.size();
           x2.set(id, x2.get(id) - eps);
           y1 = f.getF(x2);
           x2.set(id, x2.get(id) + 2.0 * eps);
           y2 = f.getF(x2);
           x2.set(id, x2.get(id) - eps);

           if (y1 > y2) x2.set(id, x2.get(id) + step);
           else x2.set(id, x2.get(id) - step);

           t = x1.get(id);
           x2 = gRatio(f, x1, x2, eps);
           x1 = new Vector(x2);

           if (Math.abs(x2.get(id) - t) < eps ){
               opt_id ++;
               if (opt_id == x.size())
                   break;
               continue;
           }
           opt_id = 0;
       }
       return x1;
    }
}
