import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.*;

public class Vector {
    private ArrayList<Double> vec;
    public Vector(Double... val) {
        vec = new ArrayList<>();
        vec.addAll(Arrays.asList(val));
    }
    public Vector (int n){
        vec = new ArrayList<>();
        for (int i = 0; i < n; i++) vec.add(0.0);
    }
    public Vector (Vector n){
        vec = new ArrayList<>();
        for (int i = 0; i < n.size(); i++)
            vec.add(n.get(i));
    }
    public int size(){
        return vec.size();
    }
    public double get(int i) {
        return vec.get(i);
    }
    public void set(int i, double val) {
        vec.set(i, val);
    }
    public Vector add(Vector vector) {
        Vector res = new Vector(vector.size());
        for (int i = 0; i < vector.size(); i++)
            res.set(i, vec.get(i) + vector.get(i));
        return res;
    }
    public Vector sub(Vector vector) {
        Vector  res = new Vector(vector.size());
        for (int i = 0; i < vector.size(); i++)
            res.set(i, vec.get(i) - vector.get(i));
        return res;
    }
    public Vector mul(double val) {
        for (int i = 0; i < vec.size(); i++)
            vec.set(i, vec.get(i) * val);
        return this;
    }
    public Vector normalize()
    {
        double inv_mag = 1.0 / magnitude();

        for (int i = 0; i < size(); i++) this.set(i,  this.get(i) * inv_mag);

        return this;
    }
    public static Vector direction(Vector a, Vector b)
    {
        if (a.size() != b.size()) throw new RuntimeException("Vectors direction :: a.Size()!= b.Size()");

        return b.sub(a).normalize();
    }

    public double magnitude() {
        double res = 0.0;
        for (double val : vec)
            res += (pow(val, 2));
        return sqrt(res);
    }
    //TODO для трехмерного вектора
   /* public Vector getGradient(Function2 f, Vector v) {
        double x, y, z = 0;
        if(v.size() == 2) {
            x = (f.getF(new Vector(v.get(0) + Const.eps, v.get(1)))
                    - f.getF(new Vector(v.get(0), v.get(1)))) / Const.eps;
            y = (f.getF(new Vector(v.get(0), v.get(1) + Const.eps))
                    - f.getF(new Vector(v.get(0), v.get(1)))) / Const.eps;
            v = new Vector(x, y);
        } else if (v.size() == 3){
            x = (f.getF(new Vector(v.get(0) + Const.eps, v.get(1), v.get(2)))
                    - f.getF(new Vector(v.get(0), v.get(1), v.get(2)))) / Const.eps;
            y = (f.getF(new Vector(v.get(0), v.get(1) + Const.eps, v.get(2)))
                    - f.getF(new Vector(v.get(0), v.get(1), v.get(2)))) / Const.eps;
            z = (f.getF(new Vector(v.get(0), v.get(1), v.get(2) + Const.eps))
                    - f.getF(new Vector(v.get(0), v.get(1), v.get(2)))) / Const.eps;
            v = new  Vector(x, y, z);
        }
        return v;
    }
*/
    public static Vector getGradient(Function2 func, Vector x, double eps) {
        Vector df = new Vector(x.size());
        for (int i = 0; i < x.size(); i++)  df.set(i, partial(func, x, i, eps));
        return df;
    }

    public static double partial (Function2 func, Vector x, int index, double eps ) {
        x.set(index,  x.get(index) + eps);
        double f_r = func.getF(x);
        x.set(index,  x.get(index) - 2.0 * eps);
        double f_l = func.getF(x);
        x.set(index,  x.get(index) +  eps);
        return (f_r - f_l) / eps * 0.5;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        for (double val : vec)
            sb.append(val).append(" ");
        sb.append("}");
        return sb.toString();
    }
}
