import java.util.ArrayList;

public class State {
    private String name;

    public State()
    {
        name = "";
    }

    public State(String n)
    {
        name = n;
    }

    public String get_name() 
    {
        return name;
    }

    public void change_name(String str)
    {
        name = str;
    }

    public static void print_state_list(ArrayList<State> states) 
    {
        System.out.print("{");
    	for (int i = 0; i < states.size(); i++) {
            if (i > 0) {
                System.out.print(", ");
            } 
            System.out.print(states.get(i).get_name());
        }
        System.out.println("}");
    }
}
