import java.util.ArrayList;

public class Rule {
    NonTerminal ruleStart;
    ArrayList<Object> ruleMapping = new ArrayList<Object>();

    public Rule()
    {

    }

    public Rule(NonTerminal nt, ArrayList<Object> al)
    {
        ruleStart = nt;
        ruleMapping = al;
        System.out.print(al.toString());
    }

    public ArrayList<Object> getRule()
    {
        ArrayList<Object> al = new ArrayList<Object>();
        al.add(ruleStart);
        al.addAll(ruleMapping);

        return al;
    }

    public NonTerminal getRuleStart()
    {
        return ruleStart;
    }

    public ArrayList<Object> getRuleMapping()
    {
        return ruleMapping;
    }

    public void printRule()
    {
        System.out.print("<" + ruleStart.getValue() + " -> ");
        System.out.print(ruleMapping.size());
        for (int i = 0; i < ruleMapping.size(); i++) {
            if (ruleMapping.get(i) instanceof Terminal) {
                Terminal t = Terminal.class.cast(ruleMapping.get(i));
                System.out.print(", " + t.getValue());
            } else if (ruleMapping.get(i) instanceof NonTerminal) {
                NonTerminal nt = NonTerminal.class.cast(ruleMapping.get(i));
                System.out.print(", " + nt.getValue());
            } 
        }
        System.out.print(">");
    }
}
