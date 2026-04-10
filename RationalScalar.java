public class RationalScalar extends Scalar {
    private int numerator;
    private int denominator;
    public RationalScalar(int numerator, int denominator){
        this.denominator = denominator;
        this.numerator = numerator;
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
        return null;
    }

    @Override
    public Scalar mul(Scalar other) {
        return null;
    }

    @Override
    public Scalar neg() {
        return null;
    }

    @Override
    public Scalar power(int exponent) {
        return null;
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
        return 0;
    }

    @Override
    public int getDenominator() {
        return 0;
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
