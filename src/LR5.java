public class LR5 {
    public static Vector Simplex(Matrix a, Vector b, Vector c, String[] arr) {
        //привожу к каноническому виду
        Vector result = new Vector(b.size());
        int k;
        for(int i = 0; i < b.size(); i++){
            if(b.get(i) < 0) {
                k = i;
                for (int j = 0; j < a.cols(); j++) a.set(k, j, a.get(k, j) * -1);
                if(arr[k].equals(">=")) arr[k] = "<=";
                else if(arr[k].equals("<=")) arr[k] = ">=";
            }
        }
        for(int i = 0; i < arr.length; i++){
            if(arr[i].equals(">=") || arr[i].equals("=")){
                return DoubleSimplex(a, b,c, arr);
            }
        }
        Vector temp = new Vector(b.size(), 1.0);
        a.addCol(temp);
        for(int i = 0; i < a.rows(); i++){
            for(int j = 0; j< a.cols(); j++ ){
                System.out.print(a.get(i, j) + " ");
            }
            System.out.println();
        }
        return result;
    }

    public static Vector DoubleSimplex(Matrix a, Vector b, Vector c, String[] arr) {
        Vector result = new Vector(b.size());
        return result;
    }
}
