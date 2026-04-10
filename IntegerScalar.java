public class IntegerScalar extends Scalar {
    private int number;

    public IntegerScalar(int num) {
        this.number = num;
    }

    @Override
    public Scalar add(Scalar other) {
        int num = number * other.getDenominator() + other.getNumerator();
        int den = other.getDenominator();
        Scalar ans = new RationalScalar(num, den).reduce();
        return ans;
    }

    @Override
    public Scalar mul(Scalar other) {
        int num = number * other.getNumerator();
        int den = other.getDenominator();
        Scalar ans = new RationalScalar(num, den).reduce();
        return ans;
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
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public int getNumerator() {
        return number;
    }

    @Override
    public int getDenominator() {
        return 1;
    }

}