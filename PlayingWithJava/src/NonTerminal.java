public class NonTerminal {
    private String name;

    public NonTerminal(String str)
    {
        name = str.toUpperCase();
    }

    public String getValue()
    {
        return name;
    }
}
