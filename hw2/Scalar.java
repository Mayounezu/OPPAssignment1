package hw2;

public abstract class Scalar {
    public abstract Scalar add(Scalar other);
    public abstract Scalar mul(Scalar other);
    public abstract Scalar neg();
    public abstract Scalar power(int exponent);
    public abstract int sign();

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract String toString();

    public abstract Scalar IntegerAdd(IntegerScalar other);
    public abstract Scalar IntegerMul(IntegerScalar other);

    public abstract Scalar RationalAdd(RationalScalar other);
    public abstract Scalar RationalMul(RationalScalar other);

    public abstract Scalar RealAdd(RealScalar other);
    public abstract Scalar RealMul(RealScalar other);

    public abstract boolean equalZero();
}
