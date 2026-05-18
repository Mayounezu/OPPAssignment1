package hw2;
public class RationalScalar extends Scalar {
    private int numerator;
    private int denominator;

    public RationalScalar(int numerator, int denominator){
        if (denominator == 0){
            throw new IllegalArgumentException("the denominator cannot be 0!");
        }
        this.denominator = denominator;
        this.numerator = numerator;
    }
    public RationalScalar reduce(){
        int gdc = gcd(denominator, numerator);
        if (denominator < 0){
            numerator = - numerator;
            denominator = -denominator;
        }
        return new RationalScalar(numerator/gdc, denominator/gdc);
    }
    @Override
    public Scalar add(Scalar other) {
        return other.RationalAdd(this);
    }

    @Override
    public Scalar mul(Scalar other) {
        return other.RationalMul(this);
    }

    @Override
    public Scalar neg() {
        int num = -numerator;
        return new RationalScalar(num, denominator).reduce();
    }

    @Override
    public Scalar power(int exponent) {
        Scalar ans = new RationalScalar(1,1);
        if (exponent == 0){
            return ans;
        }
        if (exponent == 1){
            return new RationalScalar(numerator,denominator);
        }
        Scalar powerHalf = power(exponent/2);
        ans = powerHalf.mul(powerHalf);
        if (exponent%2 == 1){
            ans = ans.mul(new RationalScalar(numerator,denominator));
        }
        return ans;
    }

    @Override
    public int sign() {
        if (numerator*denominator < 0){
            return -1;
        }
        else if (numerator*denominator > 0){
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
        RationalScalar reduced = reduce();
        numerator = reduce().getNumerator();
        denominator = reduced.getDenominator();
        if (denominator == 1){
            return "" + numerator;
        }
        return numerator + "/" + denominator;
    }

    @Override
    public Scalar IntegerAdd(IntegerScalar other) {
        return new RationalScalar(other.getNumber()*numerator + numerator, denominator);
    }

    @Override
    public Scalar IntegerMul(IntegerScalar other) {
        return new RationalScalar(other.getNumber()*numerator, denominator);
    }

    @Override
    public Scalar RationalAdd(RationalScalar other) {
        return new RationalScalar(numerator*other.denominator + other.numerator * denominator, denominator*other.denominator);
    }

    @Override
    public Scalar RationalMul(RationalScalar other) {
        return new RationalScalar(numerator*other.numerator, denominator*other.denominator);
    }

    @Override
    public Scalar RealAdd(RealScalar other) {
        return new RealScalar(other.getNumber() + (0.0 + numerator)/denominator);
    }

    @Override
    public Scalar RealMul(RealScalar other) {
        return new RealScalar(other.getNumber() * (0.0 + numerator)/denominator);
    }

    @Override
    public boolean equalZero() {
        return numerator == 0;
    }

    // helper methods:
    private int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public int getDenominator() {
        return denominator;
    }

    public int getNumerator() {
        return numerator;
    }
}
