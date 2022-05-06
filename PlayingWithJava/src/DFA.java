import java.util.ArrayList;

public class DFA {
    private ArrayList<State> states = new ArrayList<State>();
    private ArrayList<String> alphabet = new ArrayList<String>();
    private ArrayList<Transition> delta_transitions = new ArrayList<Transition>();
    private State start_state;
    private ArrayList<State> accepting_states = new ArrayList<State>();



    public DFA()
    {
        
    }

    public DFA(ArrayList<State> s, ArrayList<String> e, ArrayList<Transition> d, State ss, ArrayList<State> a)
    {
        states = s;
        alphabet = e;
        delta_transitions = d;
        start_state = ss;
        accepting_states = a;
    }

    public DFA(ArrayList<Transition> d, State ss, ArrayList<State> a)
    {
        delta_transitions = d;
        start_state = ss;
        accepting_states = a;

        for (int i = 0; i < delta_transitions.size(); i++) {
            Transition temp = delta_transitions.get(i);

            if (is_State_in_DFA(temp.get_start()) < 0) {
                add_State(temp.get_start());
            }
            if (is_State_in_DFA(temp.get_end()) < 0) {
                add_State(temp.get_end());
            }
            if (!is_char_in_alphabet(temp.get_string())) {
                add_char_to_alphabet(temp.get_string());
            }
        }
    }



    public void build_example_DFA()
    {
        states.clear();
        alphabet.clear();
        delta_transitions.clear();
        start_state = new State();
        accepting_states.clear();

        states.add(new State("q0"));
        states.add(new State("q1"));

        alphabet.add("a");
        alphabet.add("b");

        delta_transitions.add(new Transition(states.get(0), states.get(0), "a"));
        delta_transitions.add(new Transition(states.get(0), states.get(1), "b"));
        delta_transitions.add(new Transition(states.get(1), states.get(1), "a"));

        start_state = states.get(0);

        accepting_states.add(states.get(1));
    }



    public int is_State_in_DFA(State s)
    {
        for (int i = 0; i < states.size(); i++) {
            if (states.get(i).get_name().equals(s.get_name())) {
                return i;
            }
        }
        return -1;
    }

    public int is_State_in_DFA(String str)
    {
        for (int i = 0; i < states.size(); i++) {
            if (states.get(i).get_name().equals(str)) {
                return i;
            }
        }
        return -1;
    }

    public Boolean is_Transition_in_DFA(Transition t)
    {
        ArrayList<Transition> transitions = get_Transition_from_State(t.get_start());
        for (int i = 0; i < transitions.size(); i++) {
            if (transitions.get(i).get_string().equals(t.get_string())) {
                return true;
            } 
        }
        return false;
    }

    public Boolean is_char_in_alphabet(String str) 
    {
        for (int i = 0; i < alphabet.size(); i++) {
            if (alphabet.get(i).equals(str)) {
                return true;
            } 
        }
        return false;
    }

    public Boolean is_in_lang(String str)
    {
        State current_state = start_state;
        for (int i = 0; i < str.length(); i++) {
            String c = String.valueOf(str.charAt(i));
            Transition t = get_Transition(current_state, c);

            if (t.get_string().equals("")) {
                return false;
            }

            current_state = t.get_end();
        }

        if (accepting_states.contains(current_state)) {
            return true;
        } else {
            return false;
        }
    }



    public ArrayList<Transition> get_Transition_from_State(State s)
    {
        ArrayList<Transition> ret = new ArrayList<Transition>();

        for (int i = 0; i < delta_transitions.size(); i++) {
            Transition current_Transition = delta_transitions.get(i);
            if (current_Transition.get_start().get_name().equals(s.get_name())) {
                ret.add(current_Transition);
            }
        }

        return ret;
    }

    public Transition get_Transition(State s, String str)
    {
        ArrayList<Transition> transitions_from_s = get_Transition_from_State(s);

        for (int i = 0; i < transitions_from_s.size(); i++) {
            if (transitions_from_s.get(i).get_string().equals(str)) {
                return transitions_from_s.get(i);
            }
        }

        return new Transition();
    }

    public State get_state(String str)
    {
        if (is_State_in_DFA(new State(str)) >= 0) {
            return states.get(is_State_in_DFA(new State(str)));
        } else {
            return new State();
        }
    }



    public Boolean add_State(State s)
    {
        if (is_State_in_DFA(s) < 0) {
            states.add(s);
            return true;
        } else {
            return false;
        }
    }

    public Boolean add_State(State s, Boolean b)
    {
        if (is_State_in_DFA(s) < 0) {
            states.add(s);
            accepting_states.add(s);
            return true;
        } else {
            return false;
        }
    }

    public Boolean add_Transition(Transition t)
    {
        if (is_State_in_DFA(t.get_start()) >= 0) {
            if (is_State_in_DFA(t.get_end()) >= 0) {
                if (is_char_in_alphabet(t.get_string())) {
                    if (!is_Transition_in_DFA(t)) {
                        delta_transitions.add(t);
                    } else {
                        error_Transition_in_DFA(t);
                    }
                } else {
                    error_char_not_in_alphabet(t.get_string());
                }
            } else {
                error_State_not_in_DFA(t.get_end(), t);
            }
        } else {
            error_State_not_in_DFA(t.get_start(), t);
        }
        
        return true;
    }

    public Boolean add_char_to_alphabet(String str)
    {
        if (!is_char_in_alphabet(str)) {
            alphabet.add(str);
            return true;
        } else {
            return false;
        }
    }



    public Boolean change_name_of_State(String old_name, String new_name)
    {
        if (is_State_in_DFA(new_name) < 0) {
            get_state(old_name).change_name(new_name);
        } else {
            error_State_already_in_DFA(get_state(old_name));
        }
        
        return true;
    }


    
    public void print_states()
    {
        System.out.print("Q = ");
    	State.print_state_list(states);
    }

    public void print_alphabet()
    {
        System.out.print("\u03A3 = {");
    	for (int i = 0; i < alphabet.size(); i++) {
            if (i > 0) {
                System.out.print(", ");
            } 
            System.out.print(alphabet.get(i));
        }
        System.out.println("}");
    }

    public void print_transition(Transition t)
    {
        System.out.print(t.get_start().get_name() + "--" + t.get_string() + "->" + t.get_end().get_name());
    }

    public void print_delta()
    {
        System.out.println("\u03B4 = {");
    	for (int i = 0; i < delta_transitions.size(); i++) {
            if (i > 0) {
                System.out.println(", ");
            } 
            System.out.print("\t");
            print_transition(delta_transitions.get(i));
        }
        System.out.println();
        System.out.println("}");
    }

    public void print_start_state()
    {
        System.out.print("q0 = ");
        if (start_state != null) {
            System.out.println(start_state.get_name());
        } else {
            System.out.println("NULL");
        }
    }

    public void print_accepting_states()
    {
        System.out.print("A = ");
    	State.print_state_list(accepting_states);
    }

    public void print_DFA() 
    {
        print_states();
        print_alphabet();
        print_delta();
        print_start_state();
        print_accepting_states();
    }

    public void print_is_in_lang(String str)
    {
        if (is_in_lang(str)) {
            System.out.println("The String \"" + str + "\" is accepted by the DFA.");
        } else {
            System.out.println("The String \"" + str + "\" is NOT accepted by the DFA.");
        }
    }



    public void error_State_not_in_DFA(State s, Transition t) 
    {
        System.out.println("ERROR: STATE \"" + s.get_name() + "\" of TRANSITION \"" + t.get_start().get_name() + "--" + t.get_string() + "->" + t.get_end().get_name() + "\" is not in DFA!");
    }

    public void error_State_already_in_DFA(State s)
    {
        System.out.println("ERROR: STATE \"" + s.get_name() + "\" already exists in DFA!");
    }

    public void error_char_not_in_alphabet(String str)
    {
        System.out.println("ERROR: CHAR \"" + str + "\"  is not in alphabet!");
    }

    public void error_Transition_in_DFA(Transition t)
    {
        System.out.println("ERROR: TRANSITION \"" + t.get_start().get_name() + "--" + t.get_string() + "->" + t.get_end().get_name() + "\" is already in DFA!");
    }
}
