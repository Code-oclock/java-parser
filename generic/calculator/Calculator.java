package expression.generic.calculator;

public interface Calculator<T> {
    T parseConst(String value);

    T add(T left, T right);

    T divide(T left, T right);

    T multiply(T left, T right);

    T subtract(T left, T right);

    T negate(T a);
}
