public class LR3 {
    public static Vector descentMethod(Function2 f, Vector x, double eps) {
        // блендер делает смузи
        //функция делает градиент
        // только трехмерный случай
        Vector nextStep, x2;
        for(int i = 0;;i++){
            x2 = x.getGradient(f, x);
            nextStep = x.sub(x2.mul(0.5));
            if(f.getF(nextStep) - f.getF(x2) < eps)
                return nextStep;
            x = new Vector(nextStep);
        }
    }
}
