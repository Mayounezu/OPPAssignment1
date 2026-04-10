public class RationalScalar extends Scalar {
    private int numerator;
    private int denominator;
    public RationalScalar(int numerator, int denominator){
        if (denominator == 0){
            throw new IllegalArgumentException("the denominator cannot be 0!");
        }
        this.denominator = denominator;
        this.numerator = numerator;
        reduce();
    }
    public Scalar reduce(){
        int gdc = gcd(denominator, numerator);
        if (gdc == Math.abs(denominator)){
            if (denominator < 0){ numerator = -numerator;}
            return new IntegerScalar(numerator/gdc);
        }
        else{
            return new RationalScalar(numerator/gdc, denominator/gdc);
        }
    }
    @Override
    public Scalar add(Scalar other) {
        int num = numerator * other.getDenominator() + other.getNumerator() * denominator;
        int den = other.getDenominator() * denominator;
        Scalar ans = new RationalScalar(num, den).reduce();
        return ans;
    }

    @Override
    public Scalar mul(Scalar other) {
        int num = numerator * other.getNumerator();
        int den = denominator * other.getDenominator();
        Scalar ans = new RationalScalar(num, den).reduce();
        return ans;
    }

    @Override
    public Scalar neg() {
        int num = -numerator;
        Scalar ans = new RationalScalar(num, denominator).reduce();
        return ans;
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
            if (o instanceof RationalScalar){
                ((RationalScalar) o).reduce(); // isn't a must since by design it should always be reduced but still its for safety
            }
            reduce();
            int den = ((Scalar) o).getDenominator();
            int num = ((Scalar) o).getNumerator();
            return den == denominator && num == numerator;
        }
        return false;
    }

    @Override
    public String toString() {
        return "" + numerator + "/" + denominator;
    }

    @Override
    public int getNumerator() {
        return numerator;
    }

    @Override
    public int getDenominator() {
        return denominator;
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
}
