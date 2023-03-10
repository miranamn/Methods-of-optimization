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
    public double magnitude() {
        double res = 0.0;
        for (double val : vec)
            res += (pow(val, 2));
        return sqrt(res);
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
