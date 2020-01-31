import java.util.ArrayList;

/**
 * @author Duncan Grubbs
 * @date 2020-01-31
 * @license MIT
 */

public class Demo {
    // Basic demo tree.
    public static String Tree1() {
        Rule rule1 = new Rule("F", "FF");
        Rule rule2 = new Rule("X", "F-[[X]+X]+F[+FX]-X");

        ArrayList<Rule> rules = new ArrayList<>();
        rules.add(rule1);
        rules.add(rule2);

        ArrayList<Character> al = new ArrayList<>();
        al.add('F');
        al.add('+');
        al.add('-');
        al.add('X');
        al.add('[');
        al.add(']');
        LSystem l = new LSystem(al, "X", rules);

        String t = l.generate( 7);

        return t;
    }

    // More advanced tree.
    public static String Tree2() {
        Rule rule1 = new Rule("F", "FF-[XY]+[XY]");
        Rule rule2 = new Rule("X", "+FY");
        Rule rule3 = new Rule("Y", "-FX");

        ArrayList<Rule> rules = new ArrayList<>();
        rules.add(rule1);
        rules.add(rule2);
        rules.add(rule3);

        ArrayList<Character> alphabet = new ArrayList<>();
        alphabet.add('F');
        alphabet.add('+');
        alphabet.add('-');
        alphabet.add('X');
        alphabet.add('Y');
        alphabet.add('[');
        alphabet.add(']');
        LSystem l = new LSystem(alphabet, "F", rules);

        String t = l.generate(5);

        return t;
    }

    // Tree to demo stochastic systems
    public static String Tree3() {
        Rule rule1 = new Rule("F", "F[+F]F[−F]F", .5);
        Rule rule2 = new Rule("F", "F[+F]F", .3);
        Rule rule3 = new Rule("F", "F[-F]F", .2);

        ArrayList<Rule> rules = new ArrayList<>();
        rules.add(rule1);
        rules.add(rule2);
        rules.add(rule3);

        ArrayList<Character> al = new ArrayList<>();
        al.add('F');
        al.add('+');
        al.add('-');
        al.add('[');
        al.add(']');
        StochasticLSystem sl = new StochasticLSystem(al, "F", rules);

        String t = sl.generate(6);
        return t;
    }

    public static void main(String[] args) {
        String tree1 = Demo.Tree1();
        String tree2 = Demo.Tree2();
        String tree3 = Demo.Tree3();

        LSystemInterpreter inter = new LSystemInterpreter(4, 22.5);
        inter.interpret(tree3);
    }

}
