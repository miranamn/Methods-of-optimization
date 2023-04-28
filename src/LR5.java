public class LR5 {
    public static void Simplex(Matrix a, Vector b, Vector c, String[] arr) {
        MinusMul(a, b, arr);

        int[] basisArr = new int[b.size()];
        int first = a.cols() + 1;
        for(int i = 0; i < b.size(); i++){
            basisArr[i] = first + i;
        }
        int[] freeArr = new int[a.cols()];
        for(int i = 0; i < a.cols(); i++){
            freeArr[i] = i + 1;
        }
        //двухфазный симплекс - пока затычка
        for(int i = 0; i < arr.length; i++){
            if(arr[i].equals(">=") || arr[i].equals("=")){
                //DoubleSimplex(a, b,c, arr);
            }
        }
        Matrix a1 = a;
        c.mul(-1.0);

        a = simplexTable(a, b, c);
        System.out.println(a.toString());
        System.out.println();
        maxSimplex(a, basisArr, freeArr);


        a1 = simplexTable(a1, b, c);
        minSimplex(a1, basisArr, freeArr);
    }

    public static void minSimplex(Matrix a, int[] basisArr, int[] freeArr){
        System.out.println();
        System.out.println("MINIMUM: ");
        int MainCol = 0;
        int MainRow = 0;
        while (searchPlus(a)){
            MainCol = searchNewColMin(a);
            MainRow = searchNewRow(a, MainCol);
            System.out.println("a_main = {x" + basisArr[MainRow] + " x" + freeArr[MainCol - 1] + "} = " + a.get(MainRow, MainCol));
            int tempBasis = basisArr[MainRow];
            basisArr[MainRow] = freeArr[MainCol - 1];
            freeArr[MainCol - 1] = tempBasis;
            a = newSimplesTable(a, MainCol, MainRow);
            System.out.print("Free: ");
            for(int i = 0; i < freeArr.length; i++){
                System.out.print(freeArr[i] + " ");
            }
            System.out.println();
            System.out.print("Basis: ");
            for(int i = 0; i < basisArr.length; i++){
                System.out.print(basisArr[i]+ " ");
            }
            System.out.println();
            System.out.println("target function = {" + a.get(a.rows() - 1, 0) + "}");
        }
    }

    public static void maxSimplex(Matrix a, int[] basisArr, int[] freeArr){
        System.out.println("MAXIMUM: ");
        int MainCol = 0;
        int MainRow = 0;
        while (searchMinus(a)){
            MainCol = searchNewCol(a);
            MainRow = searchNewRow(a, MainCol);
            System.out.println("a_main = {x" + basisArr[MainRow] + " x" + freeArr[MainCol - 1] + "} = " + a.get(MainRow, MainCol));
            int tempBasis = basisArr[MainRow];
            basisArr[MainRow] = freeArr[MainCol - 1];
            freeArr[MainCol - 1] = tempBasis;
            a = newSimplesTable(a, MainCol, MainRow);
            System.out.print("Free: ");
            for(int i = 0; i < freeArr.length; i++){
                System.out.print(freeArr[i] + " ");
            }
            System.out.println();
            System.out.print("Basis: ");
            for(int i = 0; i < basisArr.length; i++){
                System.out.print(basisArr[i]+ " ");
            }
            System.out.println();
            System.out.println("target function = {" + a.get(a.rows() - 1, 0) + "}");
        }
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
    public static boolean searchPlus(Matrix a){
        for(int i = 0; i < a.cols(); i++){
            if (a.get(a.rows() - 1, i) > 0) return true;
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
    public static int searchNewColMin(Matrix a){
        int k = 0;
        double max = 0;
        double tempMax;
        for (int i = 1; i < a.cols(); i++){
            tempMax = a.get(a.rows()-1, i);
            if(tempMax > 0 && tempMax > max){
                max = tempMax;
                k = i;
            }
        }
        return k;
    }

    public static Matrix newSimplesTable(Matrix a, int k, int r){
        Matrix res = new Matrix(a.rows(), a.cols());
        for(int i = 0; i < a.rows() - 1; i++){
            if(i == r) continue;
            for(int j = 1; j < a.cols(); j++){
                res.set(i, j, a.get(i,j) - a.get(r, j) * a.get(i, k) / a.get(r, k));
            }
        }
        //пересчитываю столбец b
        for(int i = 0; i < a.rows() - 1; i++){
            if(i == r) continue;
            res.set(i,0, a.get(i, 0) - (a.get(r, 0) * a.get(i, k) / a.get(r, k)));
        }
        //пересчитываем значение f
        res.set(a.rows() - 1,0, a.get(a.rows() - 1,0) - a.get(r, 0) * a.get(a.rows() - 1, k) / a.get(r, k));
        //пересчитываем все дельты
        for (int i = 1; i < a.cols(); i++){
            res.set(a.rows() - 1, i, a.get(a.rows() - 1, i) - (a.get(r, i) * a.get(a.rows() - 1, k) / a.get(r, k)));
        }
        for(int i = 0; i < a.cols(); i++){
            res.set(r, i, a.get(r, i) / a.get(r, k)); //элементы главной строки необходимо разделить на ведущий элемент
        }
        System.out.println(res.toString());
        System.out.println();
        return res;
    }

    public static int searchNewRow(Matrix a, int col){
        int k = 0;
        double min = Math.abs(a.get(0,0) / a.get(0, col));
        double tempMin;
        for (int i = 1; i < a.rows() - 1; i++){
            tempMin = a.get(i,0) / a.get(i, col);
            if(Math.abs(tempMin) < min){
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

}
