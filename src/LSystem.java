import java.util.ArrayList;

/**
 * @author Duncan Grubbs
 * @date 2020-01-30
 * @license MIT
 */

public class LSystem {
    private String axiom;
    private ArrayList<Rule> rules;

    /**
     * Formal implementation of an L-System
     * @param axiom Starting character of the system
     * @param rules Set of rules to be applied when transforming strings
     *              in the system.
     */
    public LSystem(String axiom, ArrayList<Rule> rules) {
        this.axiom = axiom;
        this.rules = rules;
    }

    /**
     * Iteratively updates the axiom string according the rules
     * of the L-System, and does this process repeatedly.
     * @param iterations Number of times to repeat the process of transforming the
     *                   current string according to the rules.
     * @return Fully transformed string.
     */
    public String generate(int iterations) {
        String current = this.axiom;

        for (int i = 0; i < iterations; i++) {
            String transformed = "";

            for (int k = 0; k < current.length(); k++) {
                boolean charHasBeenTransformed = false;
                for (Rule r : this.rules) {
                    if (r.applies(current.charAt(k))) {
                        charHasBeenTransformed = true;
                        transformed += r.getB();
                        break;
                    }
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
