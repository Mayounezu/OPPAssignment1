import javax.print.DocFlavor;

public abstract class Scalar {
    public abstract Scalar add(Scalar other);
    public abstract Scalar mul(Scalar other);
    public abstract Scalar neg();
    public abstract Scalar power(int exponent);
    public abstract int sign();

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract String toString(); // this is public so we will not use Object's toString

    // helper methods I added:
    public abstract int getNumerator();
    public abstract int getDenominator();
}
