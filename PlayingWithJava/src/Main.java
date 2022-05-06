public class Main {
    public static void main(String args[]) 
    {
        DFA test = new DFA();
        test.build_example_DFA();

        test.print_is_in_lang("aaabaa");
    }
}