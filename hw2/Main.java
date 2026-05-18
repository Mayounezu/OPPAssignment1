package hw2;

public class Main {

    public static void main(String[] args) {
        System.out.println("--- Starting Tests ---");

        testIntegerScalar();
        testRationalScalar();
        testRealScalar();
        testMonomial();
        testPolynomial();

        System.out.println("--- Tests Complete ---");
    }

    // --- Core Testing Helper Method ---
    private static void assertEqual(String testName, String expected, String actual) {
        if (expected.equals(actual)) {
            System.out.println("[PASS] " + testName);
        } else {
            System.out.println("[FAIL] " + testName + " | Expected: '" + expected + "', but got: '" + actual + "'");
        }
    }

    // --- The Tests ---
    private static void testIntegerScalar() {
        System.out.println("\nTesting IntegerScalar:");

        Scalar s1 = new IntegerScalar(5);
        Scalar s2 = new IntegerScalar(3);

        assertEqual("Integer Add", "8", s1.add(s2).toString());
        assertEqual("Integer Mul", "15", s1.mul(s2).toString());
        assertEqual("Integer Power (O(log n))", "1024", new IntegerScalar(2).power(10).toString());
        assertEqual("Integer Power Base Case", "1", s1.power(0).toString());
    }

    private static void testRationalScalar() {
        System.out.println("\nTesting RationalScalar:");

        // Tests Reduction and signs
        Scalar s1 = new RationalScalar(10, 4);
        assertEqual("Rational Reduce", "5/2", s1.toString());

        Scalar s2 = new RationalScalar(3, -4);
        assertEqual("Rational Negative Denominator", "-3/4", s2.toString());

        // Mixed Addition
        Scalar r1 = new RationalScalar(1, 2);
        Scalar i1 = new IntegerScalar(3);
        assertEqual("Rational + Integer Mix", "7/2", r1.add(i1).toString());

        // Exception Testing (Manual catch)
        boolean caught = false;
        try {
            new RationalScalar(5, 0);
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        if (caught) {
            System.out.println("[PASS] Rational Div By Zero Exception Caught");
        } else {
            System.out.println("[FAIL] Rational Div By Zero Exception NOT Caught");
        }
    }

    private static void testRealScalar() {
        System.out.println("\nTesting RealScalar:");

        Scalar r1 = new RealScalar(2.5);
        Scalar r2 = new RealScalar(1.5);

        assertEqual("Real Add", "4.0", r1.add(r2).toString());
        assertEqual("Real Mul", "3.75", r1.mul(r2).toString());
    }

    private static void testMonomial() {
        System.out.println("\nTesting Monomial:");

        Monomial m1 = new Monomial(2, new IntegerScalar(3)); // 3x^2
        Monomial m2 = new Monomial(2, new IntegerScalar(4)); // 4x^2

        assertEqual("Monomial Add", "7x^2", m1.add(m2).toString());

        Monomial m3 = new Monomial(3, new IntegerScalar(1));
        if (m1.add(m3) == null) {
            System.out.println("[PASS] Monomial Add Mismatched Exponents Returns Null");
        } else {
            System.out.println("[FAIL] Monomial Add Mismatched Exponents Failed");
        }

        Monomial m4 = new Monomial(3, new IntegerScalar(4)); // 4x^3
        assertEqual("Monomial Derivative", "12x^2", m4.derivative().toString());

        Scalar x = new IntegerScalar(3);
        assertEqual("Monomial Evaluate (2x^3 at x=3)", "54", new Monomial(3, new IntegerScalar(2)).evaluate(x).toString());
    }

    private static void testPolynomial() {
        System.out.println("\nTesting Polynomial:");

        Polynomial p1 = Polynomial.build("1 2 3");
        assertEqual("Polynomial Build", "1+2x+3x^2", p1.toString());

        Polynomial p2 = Polynomial.build("1/2 0 -3");
        assertEqual("Polynomial Build (Rationals & Negatives)", "1/2-3x^2", p2.toString());

        Polynomial pAdd1 = Polynomial.build("1 2"); // 1 + 2x
        Polynomial pAdd2 = Polynomial.build("3 -2 4"); // 3 - 2x + 4x^2
        assertEqual("Polynomial Add (Canceling terms)", "4+4x^2", pAdd1.add(pAdd2).toString());

        Polynomial pMul1 = Polynomial.build("1 1"); // 1 + x
        Polynomial pMul2 = Polynomial.build("1 1"); // 1 + x
        assertEqual("Polynomial Mul", "1+2x+x^2", pMul1.mul(pMul2).toString());

        Polynomial pDeriv = Polynomial.build("5 2 3"); // 5 + 2x + 3x^2
        assertEqual("Polynomial Derivative", "2+6x", pDeriv.derivative().toString());

        Polynomial pEval = Polynomial.build("1 2 1"); // 1 + 2x + x^2
        Scalar x = new IntegerScalar(3);
        assertEqual("Polynomial Evaluate", "16", pEval.evaluate(x).toString());
    }
}