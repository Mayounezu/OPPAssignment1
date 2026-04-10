public Class Monomial{
    private int exponent;
    private Scalar coefficient;

    public Monomial(int exponent, Scalar coefficient) {
        if( exponent <= 0 ){
            throw new IllegalArgumentException("Exponent must be non-negative.");        
        } 
        private int exponent = exponent;
        private Scalar coefficient= coefficient;   
    }


    public Monomial add(Monomial m) {
        if (this.exponent == m.exponent) {
            Scalar newCoefficient = this.coefficient.add(m.coefficient);
            return new Monomial(this.exponent, newCoefficient);
        } else {
            return null;
        }
    }

    public Monomial mul(Monomial m) {
        Scalar newExponent = this.newExponent.add(m.newExponent);
        Scalar newCoefficient = this.coefficient.mul(m.coefficient);
        return new Monomial(this.exponent, newCoefficient);
    }

    Scalar evaluate(Scalar s){
        
    }




}


