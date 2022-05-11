import java.util.ArrayList;

public class Alphabet {
    private ArrayList<Terminal> alphabet = new ArrayList<Terminal>();

    public Alphabet()
    {

    }

    public Alphabet(ArrayList<Terminal> al)
    {
        for (int i = 0; i < al.size(); i++) {
            if (is_Terminal_in_Alphabet(al.get(i)) < 0) {
                alphabet.add(al.get(i));
            }
        }
        alphabet.add(new Terminal("e"));
    }

    public ArrayList<Terminal> getAlphabet()
    {
        return alphabet;
    }

    public Terminal get(int index)
    {
        return alphabet.get(index);
    }

    public int size()
    {
        return alphabet.size();
    }

    public int is_Terminal_in_Alphabet(Terminal t)
    {
        for (int i = 0; i < alphabet.size(); i++) {
            if (t.getValue().equals(alphabet.get(i).getValue())) {
                return i;
            }
        }
        
        return -1;
    }

    public Boolean append(Terminal t)
    {
        if (is_Terminal_in_Alphabet(t) < 0) {
            alphabet.add(t);
        } else {
            return false;
        }

        return true;
    }

    public Boolean remove(Terminal t)
    {
        int t_in_a = is_Terminal_in_Alphabet(t);

        if (t_in_a >= 0) {
            alphabet.remove(t_in_a);
        } else {
            return false;
        }

        return true;
    }
}
