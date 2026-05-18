package hw2;

public class IntegerScalar extends Scalar {
    private final int number;

    public IntegerScalar(int num) {
        this.number = num;
    }

    @Override
    public Scalar add(Scalar other) {
        return other.IntegerAdd(this);
    }

    @Override
    public Scalar mul(Scalar other) {
        return other.IntegerMul(this);
    }

    @Override
    public Scalar neg() {
        return new IntegerScalar(-number);
    }

    @Override
    public Scalar power(int exponent) {
        Scalar ans = new IntegerScalar(1);
        if (exponent == 0){
            return ans;
        }
        if (exponent == 1){
            return new IntegerScalar(number);
        }
        Scalar powerHalf = power(exponent/2);
        ans = powerHalf.mul(powerHalf);
        if (exponent%2 == 1){
            ans = ans.mul(new IntegerScalar(number));
        }
        return ans;
    }

    @Override
    public int sign() {
        if (number < 0){
            return -1;
        }
        else if (number > 0){
            return 1;
        }
        else{
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Scalar){
            return (this.neg().add((Scalar)o)).equalZero();
        }
        return false;
    }

    @Override
    public String toString() {
        return "" + number;
    }

    @Override
    public Scalar IntegerAdd(IntegerScalar other) {
        return new IntegerScalar(number + other.number);
    }

    @Override
    public Scalar IntegerMul(IntegerScalar other) {
        return new IntegerScalar(number * other.number);
    }

    @Override
    public Scalar RationalAdd(RationalScalar other) {
        return new RationalScalar(other.getNumerator() + number * other.getDenominator(), other.getDenominator());
    }

    @Override
    public Scalar RationalMul(RationalScalar other) {
        return new RationalScalar(other.getNumerator() * number, other.getDenominator());
    }

    @Override
    public Scalar RealAdd(RealScalar other) {
        return new RealScalar(other.getNumber() + number);
    }

    @Override
    public Scalar RealMul(RealScalar other) {
        return new RealScalar(other.getNumber() * number);
    }

    @Override
    public boolean equalZero() {
        return this.number == 0;
    }

    public int getNumber() {
        return number;
    }
}