import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.junit.jupiter.api.Test;

public class GrammarTest {
    @Test
    public void test1() {
        String input = "x + 2*y >= 10 && sin(x) + r != 0";

        CharStream cs = CharStreams.fromString(input);
        ConstraintsLexer lexer = new ConstraintsLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ConstraintsParser parser = new ConstraintsParser(tokens);

        ParseTree tree = parser.formula();
        System.out.println(tree.toStringTree(parser));
    }
}
