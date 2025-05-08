package expression.parser;

public class Main {
    public static void main(String[] args) {
        System.out.println(new ExpressionParser().parse("-((6))").toMiniString());
//        System.out.println(Math.log10(Integer.MAX_VALUE) / Math.log10(2));
    }
}
