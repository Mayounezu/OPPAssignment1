package calculator;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Polynomial {
    private Collection<Monomial> monomials;

    public Polynomial() {
        this.monomials = new ArrayList<>();
    }

    private void addMonomial(Monomial m) {
        if (m.sign() == 0) {
            return;
        }

        List<Monomial> list = (List<Monomial>) this.monomials;
        boolean merged = false;

        for (int i = 0; i < list.size(); i++) {
            Monomial current = list.get(i);
            Monomial sum = current.add(m);

            if (sum != null) {
                if (sum.sign() == 0) {
                    list.remove(i);
                } else {
                    list.set(i, sum);
                }
                merged = true;
                break;
            } else if (m.getExponent() < current.getExponent()) {
                list.add(i, m);
                merged = true;
                break;
            }
        }

        if (!merged) {
            list.add(m);
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
        
        Iterator<Monomial> it1 = this.monomials.iterator();
        Iterator<Monomial> it2 = other.monomials.iterator();
        
        while (it1.hasNext() && it2.hasNext()) {
            if (!it1.next().equals(it2.next())) {
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
                    sb.append("+").append(term);
                } else if (m.sign() < 0) {
                    sb.append(term);
                }
            }
        }
        return sb.toString();
    }
}