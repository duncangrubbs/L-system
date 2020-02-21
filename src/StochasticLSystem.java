import java.util.ArrayList;
import java.util.Random;

/**
 * @author Duncan Grubbs
 * @date 2020-01-30
 * @license MIT
 */

public class StochasticLSystem extends LSystem {
    private String axiom;
    private ArrayList<Rule> rules;

    /**
     * Formal implementation of an L-System
     * @param axiom Starting character of the system
     * @param rules Set of rules to be applied when transforming strings
     *              in the system.
     */
    public StochasticLSystem(String axiom, ArrayList<Rule> rules) {
        super(axiom, rules);

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
    public Rule chooseRule(ArrayList<Rule> rules) {
        double rnum = new Random().nextDouble();
        double rsum = 0.0;

        /*
        For every rule, if our random number is
        between the current running sum value and
        our rule probability, then chose that rule,
        otherwise continue summing. This allows for
        a weighted random choice of rules.
        */
        for (Rule r : rules) {
            if (rnum > rsum && rnum <= rsum + r.getP()) {
                return r;
            }
            rsum += r.getP();
        }
        // by default return the first (applicable) rule
        return rules.get(0);
    }

    /**
     * Iteratively updates the axiom string according the rules
     * of the L-System, and does this process repeatedly.
     * @param iterations Number of times to repeat the process of transforming the
     *                   current string according to the rules.
     * @return Fully transformed string.
     */
    public String generate(int iterations) {
        // begin with only the axiom string
        String current = this.axiom;

        /*
        Every iteration will generate a "transformed string"
        by applying the rules to the "current" string.
        At the end of the iteration, the "current" string is replaced
        by the new "transformed" string and the process is repeated.
         */
        for (int i = 0; i < iterations; i++) {
            String transformed = "";

            /*
            We iterate through every character, getting a list
            of applicable rules and then randomly chose one rule
            based on its weight (probability). Applying this rule
            to the character, thus changing the string. If no rule
            is applied, then the character is not changed but still
            added to the "transformed" string.
             */
            for (int k = 0; k < current.length(); k++) {
                boolean charHasBeenTransformed = false;
                ArrayList<Rule> applicableRules = this.applicableRules(current.charAt(k));

                if (!applicableRules.isEmpty()) {
                    charHasBeenTransformed = true;
                    Rule rule = this.chooseRule(applicableRules);
                    // A -> B so we add B to the transformed string
                    transformed += rule.getB();
                }
                if (!charHasBeenTransformed) {
                    transformed += current.charAt(k);
                }
            }
            current = transformed;
        }
        return current;
    }
}
