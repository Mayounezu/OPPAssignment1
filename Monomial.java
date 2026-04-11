public class Monomial {
    private int exponent;
    private Scalar coefficient;

    public Monomial(int exponent, Scalar coefficient) {
        if (exponent < 0) {
            throw new IllegalArgumentException();
        }
        this.exponent = exponent;
        this.coefficient = coefficient;
    }

    public Monomial add(Monomial m) {
        if (this.exponent == m.exponent) {
            Scalar newCoefficient = this.coefficient.add(m.coefficient);
            return new Monomial(this.exponent, newCoefficient);
        }
        return null;
    }

    public Monomial mul(Monomial m) {
        Scalar newCoefficient = this.coefficient.mul(m.coefficient);
        int newExponent = this.exponent + m.exponent;
        return new Monomial(newExponent, newCoefficient);
    }

    public Scalar evaluate(Scalar s) {
        Scalar value = s.power(this.exponent);
        return this.coefficient.mul(value);
    }

    public Monomial derivative() {
        if (this.exponent == 0) {
            return new Monomial(0, new IntegerScalar(0));
        }
        Scalar expScalar = new IntegerScalar(this.exponent);
        return new Monomial(this.exponent - 1, this.coefficient.mul(expScalar));
    }

    public int sign() {
        return this.coefficient.sign();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Monomial)) {
            return false;
        }
        Monomial other = (Monomial) o;
        return this.exponent == other.exponent && this.coefficient.equals(other.coefficient);
    }

    @Override
    public String toString() {
        if (this.exponent == 0) {
            return this.coefficient.toString();
        }
        String coeffStr = this.coefficient.toString();
        if (coeffStr.equals("1")) {
            coeffStr = "";
        } else if (coeffStr.equals("-1")) {
            coeffStr = "-";
        }

        if (this.exponent == 1) {
            return coeffStr + "x";
        }
        return coeffStr + "x^" + this.exponent;
    }

    //helper function
    public int getExponent() {
        return this.exponent;
    }
}