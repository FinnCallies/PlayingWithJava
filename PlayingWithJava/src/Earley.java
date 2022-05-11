import java.util.ArrayList;

public class Earley {
    private ArrayList<NonTerminal> v = new ArrayList<NonTerminal>();
    private Alphabet a = new Alphabet();
    private ArrayList<Rule> r = new ArrayList<Rule>();
    private NonTerminal s;

    public Earley()
    {
        v.add(new NonTerminal("S"));
        v.add(new NonTerminal("A"));
        printNonTerminals();

        a.append(new Terminal("a"));
        a.append(new Terminal("b"));
        printAlphabet();

        ArrayList<Object> al = new ArrayList<Object>();

        al.add(a.get(0));
        al.add(v.get(0));
        al.add(a.get(1));
        r.add(new Rule(v.get(0), al));

        al.clear();
        al.add(a.get(a.size() - 1));
        r.add(new Rule(v.get(0), al));

        printRules();

        s = v.get(0);
        printStart();
    }

    public Earley(ArrayList<NonTerminal> nonTerminals, Alphabet alphabet, ArrayList<Rule> rules, NonTerminal start)
    {
        v = nonTerminals;
        a = alphabet;
        r = rules;
        s = start;
    }

    public ArrayList<NonTerminal> getNonTerminals()
    {
        return v;
    }

    public Alphabet getAlphabet()
    {
        return a;
    }

    public ArrayList<Rule> getRules()
    {
        return r;
    }

    public NonTerminal getStart()
    {
        return s;
    }

    public void print()
    {
        printNonTerminals();
        printAlphabet();
        printRules();
        printStart();
    }

    public void printNonTerminals()
    {
        System.out.print("{ " + v.get(0).getValue());

        for (int i = 1; i < v.size(); i++) {
            System.out.print(", " + v.get(i).getValue());
        }
        System.out.println(" }");
    }

    public void printAlphabet()
    {
        System.out.print("{ " + a.get(0).getValue());

        for (int i = 1; i < a.size(); i++) {
            System.out.print(", " + a.get(i).getValue());
        }
        System.out.println(" }");
    }

    public void printRules()
    {
        System.out.println("{ ");
        System.out.print("\t");
        r.get(0).printRule();

        for (int i = 1; i < r.size(); i++) {
            System.out.print(",\n\t");
            r.get(i).printRule();
        }
        System.out.println("\n}");
    }

    public void printStart()
    {
        System.out.println(s.getValue());
    }
}
