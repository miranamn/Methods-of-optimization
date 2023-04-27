public class LR5 {
    public static Vector Simplex(Matrix a, Vector b, Vector c, String[] arr) {
        MinusMul(a, b, arr);
        //двухфазный симплекс - пока затычка
        for(int i = 0; i < arr.length; i++){
            if(arr[i].equals(">=") || arr[i].equals("=")){
                return DoubleSimplex(a, b,c, arr);
            }
        }
        a = simplexTable(a, b, c);
        System.out.println(a.toString());
        //пока в последней строке есть отрицательный элемент
        int MainCol = 0;
        int MainRow = 0;
        while (searchMinus(a)){
            MainCol = searchNewCol(a);
            MainRow = searchNewRow(a, MainCol);
            a = newSimplesTable(MainCol, MainRow);
            System.out.println(MainCol + " " + MainRow);
        }

        return new Vector(b.size());
    }

    //к каноническому виду
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

    public static boolean searchMinus(Matrix a){
        for(int i = 0; i < a.cols(); i++){
            if (a.get(a.rows() - 1, i) < 0) return true;
        }
        return false;
    }

    public static int searchNewCol(Matrix a){
        int k = 0;
        double max = 0;
        double tempMax;
        for (int i = 1; i < a.cols(); i++){
            tempMax = a.get(a.rows()-1, i);
            if(tempMax < 0 && Math.abs(tempMax) > max){
                max = tempMax;
                k = i;
            }
        }
        return k;
    }

    public static Matrix newSimplesTable(int MainCol, int MainRow){
            return new Matrix(MainCol, MainRow);
    }

    public static int searchNewRow(Matrix a, int col){
        int k = 0;
        double min = a.get(0,0) / a.get(0, col);
        double tempMin;
        for (int i = 1; i < a.rows() - 1; i++){
            tempMin = a.get(i,0) / a.get(i, col);
            if(tempMin < min){
                min = tempMin;
                k = i;
            }
        }
        return k;
    }


    //построение симплекс - талблицы
    public static Matrix simplexTable(Matrix a, Vector b, Vector c){
        Matrix basis = new Matrix(b.size(), b.size());
        basis.EMatrix();
        a = Matrix.addFrontVec(b, a);
        for(int i = 0; i < basis.cols(); i++){
            a.addCol(basis.getCol(i));
        }
        Vector f = new Vector(a.cols());
        for(int i = 1; i < f.size() - basis.cols(); i++){
            f.set(i, c.get(i - 1));
        }
        a.addRow(f);
        return a;
    }



    public static Vector DoubleSimplex(Matrix a, Vector b, Vector c, String[] arr) {
        Vector result = new Vector(b.size());
        return result;
    }
}
