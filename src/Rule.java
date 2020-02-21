/**
 * @author Duncan Grubbs
 * @date 2020-01-30
 * @license MIT
 */

public class Rule {
    private String a;
    private String b;
    private double p;

    /**
     * a -> b
     * @param a Initially character
     * @param b Transformed character (could be a string)
     */
    public Rule(String a, String b) {
        this.a = a;
        this.b = b;
    }

    /**
     * a -> b with probability p
     * @param a Initially character
     * @param b Transformed character (could be a string)
     * @param p Probability that the rule will be applied
     */
    public Rule(String a, String b, double p) {
        this.a = a;
        this.b = b;
        this.p = p;
    }

    /**
     * Does this rule apply to the current character.
     * @param c Character to test.
     * @return If the rules applies or not.
     */
    public boolean applies(Character c) {
        return (Character.toString(c).equals(this.a));
    }

    public String getB() {
        return b;
    }

    public double getP() {
        return p;
    }
}
