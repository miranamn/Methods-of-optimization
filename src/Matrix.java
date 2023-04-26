import java.util.ArrayList;
import java.util.Collections;

public class Matrix {
    private final ArrayList<Vector> rows;

    public Vector row(int rowId) {
        return rows.get(rowId);
    }

    public int rows() {
        return rows.size();
    }

    public int cols() {
        if (rows() == 0) return 0;
        return row(0).size();
    }

    @Override
    public String toString() {
        StringBuilder sb =new StringBuilder();
        sb.append("{\n");
        for (int i = 0; i < rows.size() - 1; i++) {
            sb.append(" ").append(rows.get(i).toString());
            sb.append(",\n");
        }
        sb.append(" ").append(rows.get(rows.size() - 1).toString());
        sb.append("\n}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return rows.equals(matrix.rows);
    }

    public double get(int i, int j) {
        return row(i).get(j);
    }

    public void set(int i, int j, double value) {
        row(i).set(j,value);
    }

    public Matrix(int n_rows, int n_cols) {
        rows = new ArrayList<>(n_rows);
        for (int i = 0; i < n_rows; i++) rows.add(new Vector(n_cols));
    }

    public Matrix(Vector...  rows) {
        if (rows == null) throw new RuntimeException("Data is null...");
        if (rows.length == 0) throw new RuntimeException("Data is empty...");
        int rowSizeMax = Integer.MIN_VALUE;
        int rowSizeMin = Integer.MAX_VALUE;
        for(Vector row:rows) {
            if(row.size() > rowSizeMax)rowSizeMax = row.size();
            if(row.size() < rowSizeMin)rowSizeMin = row.size();
        }
        if(rowSizeMax != rowSizeMin)throw new RuntimeException("Incorrect matrix data");
        this.rows = new ArrayList<>(rows.length);
        Collections.addAll(this.rows, rows);
    }

    public static Matrix hessian(Function2 f, Vector x, double eps) {
        Matrix res = new Matrix(x.size(), x.size());
        int row, col;
        double val;
        for (row = 0; row < res.rows(); row++) {
            for (col = 0; col <= row; col++) {
                val = Vector.partial2(f, x, row, col, eps);
                res.set(row,col,val);
                res.set(col,row,val);
            }
        }
        return res;
    }

    public static Matrix zeros(int n_rows, int n_cols) {
        return new Matrix(n_rows, n_cols);
    }

    public static Matrix zeros(int size) {
        return zeros(size, size);
    }
    public static Matrix[] lu( Matrix src) {
        Matrix low,  up;
        if (src.cols() != src.rows()) throw new RuntimeException("LU decomposition error::non square matrix");
        low = new Matrix(src.cols(), src.cols());
        up = new Matrix(src.cols(), src.cols());
        int i, j, k;
        for (i = 0; i < src.cols(); i++) {
            for (j = 0; j < src.cols(); j++) {
                if (j >= i) {
                    low.set(j,i,src.get(j,i));
                    for (k = 0; k < i; k++)  low.set(j,i,low.get(j,i) - low.get(j,k) * up.get(k,i));
                }
            }
            for (j = 0; j < src.cols(); j++) {
                if (j < i) continue;
                if (j == i) {
                    up.set(i,j,1.0);
                    continue;
                }
                up.set(i,j,src.get(i,j) / low.get(i,i));
                for (k = 0; k < i; k++)  up.set(i,j,up.get(i,j) - low.get(i,k) * up.get(k,j) / low.get(i,i));
            }
        }
        return  new Matrix[]{low,up};
    }

    private static Vector linsolve( Matrix low,  Matrix up,  Vector b) {
        double det = 1.0;
        Vector x, z;
        for (int i = 0; i < up.rows(); i++) det *= (up.get(i,i) * up.get(i,i));
        if (Math.abs(det) < 1e-12) return null;
        z = new Vector(up.rows());
        double tmp;
        for (int i = 0; i < z.size(); i++) {
            tmp = 0.0;
            for (int j = 0; j < i; j++) tmp += z.get(j) * low.get(i,j);
            z.set(i, (b.get(i) - tmp )/ low.get(i,i));
        }
        x = new Vector(up.rows());
        for (int i = z.size() - 1; i >= 0; i--) {
            tmp = 0.0;
            for (int j = i + 1; j < z.size(); j++) tmp += x.get(j) * up.get(i,j);
            x.set(i, z.get(i) - tmp);
        }
        return x;
    }

    public static Matrix invert(Matrix mat) {
        if (mat.rows() != mat.cols()) throw new RuntimeException("non square matrix");
        Matrix[]lu_ = lu( mat);
        double det = 1.0;
        for (int i = 0; i < lu_[0].rows(); i++) det *= (lu_[0].get(i,i) * lu_[0].get(i,i));
        if (Math.abs(det) < 1e-12) return null;
        Vector b, col;
        b = new Vector(mat.rows());Matrix inv = zeros(mat.rows());

        for (int i = 0; i < mat.cols(); i++) {
            b.set(i,1.0);
            col = linsolve( lu_[0], lu_[1], b);
            if (col == null) throw new RuntimeException("unable to find matrix inversion");
            if (col.size() == 0) throw new RuntimeException("unable to find matrix inversion");
            b.set(i, 0.0);
            for (int j = 0; j < mat.rows(); j++) inv.set(j,i,col.get(j));
        }
        return inv;
    }

    public Matrix mul(double other) {
        for (int i = 0; i < rows(); i++)  rows.get(i).mul(other);
        return  this;
    }

    public static Vector mul(Matrix mat, Vector vec) {
        if (mat.cols() != vec.size())  throw new RuntimeException("unable to matrix and vector multiply");
        Vector result = new Vector(mat.rows());
        int cntr = 0;
        for (Vector row : mat.rows) result.set(cntr++, row.dot(vec));
        return result;
    }
    public Matrix addCol(Vector col) {
        for (int i = 0; i < rows.size(); i++) row(i).pushBack(col.get(i));
        return this;
    }
    public Matrix addRow(Vector row) {
        rows.add(row);
        return this;
    }

}