public class LR5 {
    public static Vector Simplex(Matrix a, Vector b, Vector c, String[] arr) {
        Vector result = new Vector(b.size());
        MinusMul(a, b, arr);
        //двухфазный симплекс - пока затычка
        for(int i = 0; i < arr.length; i++){
            if(arr[i].equals(">=") || arr[i].equals("=")){
                return DoubleSimplex(a, b,c, arr);
            }
        }
       // Vector temp = new Vector(b.size(), 1.0);

        //составляю симплекс таблицу
        Matrix basis = new Matrix(b.size(), b.size());
        basis.EMatrix();
        a = Matrix.addFrontVec(b, a);
        for(int i = 0; i < basis.cols(); i++){
            a.addCol(basis.getCol(i));
        }
        return result;
    }

    //привожу к каноническому виду
    public static void MinusMul(Matrix a, Vector b, String[] arr){
        int k;
        for(int i = 0; i < b.size(); i++){
            if(b.get(i) < 0) {
                k = i;
                for (int j = 0; j < a.cols(); j++) a.set(k, j, a.get(k, j) * -1);
                if(arr[k].equals(">=")) arr[k] = "<=";
                else if(arr[k].equals("<=")) arr[k] = ">=";
            }
        }
    }

    public static Vector DoubleSimplex(Matrix a, Vector b, Vector c, String[] arr) {
        Vector result = new Vector(b.size());
        return result;
    }
}
