import java.util.ArrayList;
import java.util.Random;

/**
 * @author Duncan Grubbs
 * @date 2020-01-30
 * @license MIT
 */

public class StochasticLSystem extends LSystem {
    private ArrayList<Character> alphabet;
    private String axiom;
    private ArrayList<Rule> rules;

    /**
     * Formal implementation of an L-System
     * @param alphabet List of valid characters in the system
     * @param axiom Starting character of the system
     * @param rules Set of rules to be applied when transforming strings
     *              in the system.
     */
    public StochasticLSystem(ArrayList<Character> alphabet, String axiom, ArrayList<Rule> rules) {
        super(alphabet, axiom, rules);

        this.alphabet = alphabet;
        this.axiom = axiom;
        this.rules = rules;
    }

    /**
     * Creates a list of rules that can be applied on
     * a given character. (e.g. 'F' -> '[F]' can be applied
     * on 'F', but not on '['.
     * @param c Character to check against.
     * @return Array List of applicable rules.
     */
    public ArrayList<Rule> applicableRules(Character c) {
        ArrayList<Rule> applicableRules = new ArrayList<>();
        for (Rule r : this.rules) {
            if (r.applies(c)) {
                applicableRules.add(r);
            }
        }
        return applicableRules;
    }

    /**
     * Randomly chooses between the current set of rules
     * weighted by their probabilities.
     * @return A chosen rule.
     */
    public Rule chooseRule() {
        double rnum = new Random().nextDouble();
        double rsum = 0;
        for (Rule r : this.rules) {
            if (rnum > rsum && rnum <= rsum + r.getP()) {
                return r;
            }
            rsum += r.getP();
        }
        return this.rules.get(0);
    }

    /**
     * Iteratively updates the axiom string according the rules
     * Does process repeatedly
     * @param iterations Number of times to repeat the process of transforming the
     *                   current string according to the rules.
     * @return Fully transformed string.
     */
    public String generate(int iterations) {
        String current = this.axiom;

        for (int i = 0; i < iterations; i++) {
            String transformed = "";

            for (int k = 0; k < current.length(); k++) {
                boolean trans = false;
                ArrayList<Rule> apr = this.applicableRules(current.charAt(k));

                if (!apr.isEmpty()) {
                    trans = true;
                    Rule r = this.chooseRule();
                    transformed += r.getB();
                }
                if (!trans) {
                    transformed += current.charAt(k);
                }
            }
            current = transformed;
        }
        return current;
    }
}
