package practice.functionalInterface.p1_lambda;


@FunctionalInterface
interface ISum {
    public abstract int sum(int x);

    static void printDescription(){
        System.out.println("SAM Functional Interface Demo");
    }
}


public class LambdaDemo1 {

    public static void main(String[] args) {

        ISum.printDescription();

        // Implementing SAM Interface using Anonymous Class

        ISum r = new ISum() {
            @Override
            public int sum(int x) {
                return x+1;
            }
        };

        System.out.println("Sum: "+ r.sum(100));


        // Implementing SAM Interface using Lambda
        ISum l = x->x+2;

        System.out.println("Sum: "+l.sum(2));

    }
}