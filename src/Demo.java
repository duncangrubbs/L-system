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

        LSystem system = new LSystem("X", rules);

        String genString = system.generate( 5);

        return genString;
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

        LSystem system = new LSystem("F", rules);

        String genString = system.generate(5);

        return genString;
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

        StochasticLSystem sls = new StochasticLSystem("F", rules);

        String t = sls.generate(5);
        return t;
    }


    public static void main(String[] args) {
        String tree1 = Demo.Tree1();
        String tree2 = Demo.Tree2();
        String tree3 = Demo.Tree3();

        LSystemInterpreter inter = new LSystemInterpreter(10, 22.5);
        // here you can change which demo tree is being displayed
        inter.interpret(tree3);
    }

}
