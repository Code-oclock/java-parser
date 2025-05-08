package expression;

public class Main {
    public static void main(String[] args) {
//        System.out.println(new Subtract(
//                new Multiply(
//                        new Const(2),
//                        new Variable("x")
//                ), new Subtract(new Add(new Const(1), new Variable("y")), new Variable("x"))));
//
//        System.out.println(new Add(new Variable("x"), new Variable("x"))
//                .equals(new Add(new Variable("x"), new Variable("x"))));
//        System.out.println(new Subtract(new Const(1), new Subtract(new Const(2), new Const(3))).toMiniString());
//        System.out.println(new Subtract(new Const(1), new Subtract(new Const(2), new Const(3))));
//
//        System.out.println(new Add(new Const(1), new Add(new Const(2), new Const(3))).toMiniString());
//        System.out.println(new Add(new Const(1), new Add(new Const(2), new Const(3))));
        System.out.println(0 - -2147483648);
        System.out.println(Math.abs(-2147483648));
    }
}