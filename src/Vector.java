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
    public Vector (int n, double val){
        vec = new ArrayList<>();
        for (int i = 0; i < n; i++) vec.add(val);
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
    public void pushBack(double value) {
        vec.add(value);
    }
    public double magnitude() {
        double res = 0.0;
        for (double val : vec)
            res += (pow(val, 2));
        return sqrt(res);
    }
    public double dot(Vector other) {
        double dot = 0.0;
        for (int i = 0; i < other.size(); i++) dot += this.get(i) * other.get(i);
        return dot;
    }
    public static Vector getGradient(Function2 f, Vector x) {
        Vector df = new Vector(x.size());
        for (int i = 0; i < x.size(); i++)  df.set(i, partial(f, x, i));
        return df;
    }

    public static double partial (Function2 f, Vector x, int index) {
        x.set(index,  x.get(index) + Const.eps);
        double f_r = f.getF(x);
        x.set(index,  x.get(index) - 2.0 * Const.eps);
        double f_l = f.getF(x);
        x.set(index,  x.get(index) +  Const.eps);
        return (f_r - f_l) / Const.eps * 0.5;
    }

    public static double partial2(Function2 f, Vector x, int index1, int index2, double eps) {
        x.set(index2,  x.get(index2) - eps);
        double f_l = partial(f, x, index1);
        x.set(index2,  x.get(index2) + 2.0 * eps);
        double f_r = partial(f, x, index1);
        x.set(index2,  x.get(index2) - eps);
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
