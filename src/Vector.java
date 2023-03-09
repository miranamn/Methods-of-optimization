import java.util.ArrayList;
import java.util.Arrays;

public class Vector {
    private ArrayList<Double> vec;
    public Vector(Double... args) {
        vec = new ArrayList<>();
        vec.addAll(Arrays.asList(args));
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

    public static Vector add(Vector a, Vector b) {
        Vector res = new Vector(0.0, 0.0);
        for (int i = 0; i < b.size(); i++)
            res.set(i, a.get(i) + b.get(i));
        return res;
    }
    public static Vector sub(Vector a, Vector b) {
        Vector  res = new Vector(0.0, 0.0);;
        for (int i = 0; i < b.size(); i++)
            res.set(i, a.get(i) - b.get(i));
        return res;
    }
    public static Vector mul(Vector a, double b) {
        Vector  res = new Vector(0.0, 0.0);
        for (int i = 0; i < a.size(); i++)
            res.set(i, a.get(i) * b);
        return res;
    }

    public double magnitude() {
        double mag = 0.0;
        for (double element : vec) mag += (element * element);
        return Math.sqrt(mag);
    }
}
