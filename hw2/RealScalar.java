package hw2;

public class RealScalar extends Scalar {
    private double number;

    public RealScalar(double number) {
        this.number = number;
    }
    @Override
    public Scalar add(Scalar other) {
        return other.RealAdd(this);
    }

    @Override
    public Scalar mul(Scalar other) {
        return other.RealMul(this);
    }

    @Override
    public Scalar neg() {
        return new  RealScalar(-number);
    }

    @Override
    public Scalar power(int exponent) {
        Scalar ans = new RealScalar(1.0);
        if (exponent == 0){
            return ans;
        }
        if (exponent == 1){
            return new RealScalar(number);
        }
        Scalar powerHalf = power(exponent/2);
        ans = powerHalf.mul(powerHalf);
        if (exponent%2 == 1){
            ans = ans.mul(new RealScalar(number));
        }
        return ans;
    }

    @Override
    public int sign() {
        if (number < 0) return -1;
        if (number > 0) return 1;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Scalar){
            return (this.neg().add((Scalar) o)).equalZero();
        }
        return false;
    }

    @Override
    public String toString() {
        return "" + number;
    }


    @Override
    public Scalar IntegerAdd(IntegerScalar other) {
        return new RealScalar(number + other.getNumber());
    }

    @Override
    public Scalar IntegerMul(IntegerScalar other) {
        return new RealScalar(number * other.getNumber());
    }

    @Override
    public Scalar RationalAdd(RationalScalar other) {
        return new RealScalar(number + (0.0 + other.getNumerator())/ other.getDenominator());
    }

    @Override
    public Scalar RationalMul(RationalScalar other) {
        return new RealScalar(number * (0.0 + other.getNumerator())/ other.getDenominator());
    }

    @Override
    public Scalar RealAdd(RealScalar other) {
        return new RealScalar(number + other.number);
    }

    @Override
    public Scalar RealMul(RealScalar other) {
        return new RealScalar(number * other.number);
    }

    @Override
    public boolean equalZero() {
        return number == 0;
    }

    public double getNumber() {
        return number;
    }
}
