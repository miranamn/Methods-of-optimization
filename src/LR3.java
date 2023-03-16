public class LR3 {
    public static Vector descentMethod(Function2 f, Vector x, double eps) {
        // блендер делает смузи
        //функция делает градиент
        //TODO для случая нескольких n
        Vector x1, x2;
        for(;;){
            x2 = x.getGradient(f, x);
            x1 = x.sub(x2.mul(0.5));
            if(f.getF(x1) - f.getF(x2) < eps)
                return x1;
            x = new Vector(x1);
        }
    }
    /*public static Vector tenseGradientMethod(Function2 f, Vector x, double eps) {


    }*/

}
