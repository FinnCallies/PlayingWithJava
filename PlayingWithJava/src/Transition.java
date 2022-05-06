public class Transition {
    private State start;
    private State end;
    private String string;

    public Transition(State s, State e, String str)
    {
        start = s;
        end = e;
        string = str;
    }

    public Transition()
    {
        start = new State("q0");
        end = new State("q0");
        string = "";
    }

    public State get_start()
    {
        return start;
    }

    public State get_end()
    {
        return end;
    }

    public String get_string()
    {
        return string;
    }
}
