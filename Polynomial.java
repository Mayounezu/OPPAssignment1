import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

public class Polynomial {
    private Collection<Monomial> monomials;

    public Polynomial() {
        this.monomials = new ArrayList<>();
    }

    private void addMonomial(Monomial m) {
        if (m.sign() != 0) {
            Collection<Monomial> updated = new ArrayList<>();
            boolean merged = false;

            for (Monomial current : this.monomials) {
                if (!merged) {
                    Monomial sum = current.add(m);
                    if (sum != null) {
                        if (sum.sign() != 0) {
                            updated.add(sum);
                        }
                        merged = true;
                    } else {
                        updated.add(current);
                    }
                } else {
                    updated.add(current);
                }
            }

            if (!merged) {
                updated.add(m);
            }

            this.monomials = updated;
        }
    }

    public static Polynomial build(String input) {
        Polynomial p = new Polynomial();
        String[] tokens = input.trim().split("\\s+");
        for (int i = 0; i < tokens.length; i++) {
            if (!tokens[i].isEmpty()) {
                Scalar coeff;
                if (tokens[i].contains("/")) {
                    String[] parts = tokens[i].split("/");
                    coeff = new RationalScalar(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
                } else {
                    coeff = new IntegerScalar(Integer.parseInt(tokens[i]));
                }
                p.addMonomial(new Monomial(i, coeff));
            }
        }
        return p;
    }

    public Polynomial add(Polynomial p) {
        Polynomial result = new Polynomial();
        for (Monomial m : this.monomials) {
            result.addMonomial(m);
        }
        for (Monomial m : p.monomials) {
            result.addMonomial(m);
        }
        return result;
    }

    public Polynomial mul(Polynomial p) {
        Polynomial result = new Polynomial();
        for (Monomial m1 : this.monomials) {
            for (Monomial m2 : p.monomials) {
                result.addMonomial(m1.mul(m2));
            }
        }
        return result;
    }

    public Scalar evaluate(Scalar s) {
        Scalar result = new IntegerScalar(0);
        for (Monomial m : this.monomials) {
            result = result.add(m.evaluate(s));
        }
        return result;
    }

    public Polynomial derivative() {
        Polynomial result = new Polynomial();
        for (Monomial m : this.monomials) {
            result.addMonomial(m.derivative());
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Polynomial)) {
            return false;
        }
        Polynomial other = (Polynomial) o;
        if (this.monomials.size() != other.monomials.size()) {
            return false;
        }
        for (Monomial m1 : this.monomials) {
            boolean found = false;
            Iterator<Monomial> it = other.monomials.iterator();
            while (it.hasNext() && !found) {
                if (m1.equals(it.next())) {
                    found = true;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        if (this.monomials.isEmpty()) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Monomial m : this.monomials) {
            String term = m.toString();
            if (first) {
                sb.append(term);
                first = false;
            } else {
                if (m.sign() > 0) {
                    sb.append(" + ").append(term);
                } else if (m.sign() < 0) {
                    sb.append(" - ");
                    if (term.startsWith("-")) {
                        sb.append(term.substring(1));
                    } else {
                        sb.append(term);
                    }
                }
            }
        }
        return sb.toString();
    }
}