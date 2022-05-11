public class Terminal {
    private String name;

    public Terminal(String str)
    {
        name = str.toLowerCase();
    }

    public String getValue()
    {
        return name;
    }
}
