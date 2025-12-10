import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.junit.jupiter.api.Test;
import ru.ifmo.se.gmt.parser.ConstraintsLexer;
import ru.ifmo.se.gmt.parser.ConstraintsParser;
import ru.ifmo.se.gmt.visitor.EvalExprVisitor;
import ru.ifmo.se.gmt.visitor.EvalFormulaVisitor;

import java.util.Map;

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

    @Test
    public void test2() {
        String input = "x^2 + y^2 <= r^2 && sin(x) = 0";

        CharStream cs = CharStreams.fromString(input);
        ConstraintsLexer lexer = new ConstraintsLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ConstraintsParser parser = new ConstraintsParser(tokens);

        ParseTree tree = parser.formula();

        var vars = Map.of("x", 0.0, "y", 6.0, "r", 5.0);
        EvalExprVisitor exprVisitor = new EvalExprVisitor(vars);
        EvalFormulaVisitor formulaVisitor = new EvalFormulaVisitor(exprVisitor);

        boolean ok = formulaVisitor.visit(tree);
        System.out.println("Result: " + ok);
    }

    @Test
    public void test3() {
        String input = "sqrt(x^2 - 5*r) <= sin(y)";

        CharStream cs = CharStreams.fromString(input);
        ConstraintsLexer lexer = new ConstraintsLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ConstraintsParser parser = new ConstraintsParser(tokens);

        ParseTree tree = parser.formula();

        var vars = Map.of("x", 3.9, "y", 0.476, "r", 3.0);
        EvalExprVisitor exprVisitor = new EvalExprVisitor(vars);
        EvalFormulaVisitor formulaVisitor = new EvalFormulaVisitor(exprVisitor);

        boolean ok = formulaVisitor.visit(tree);
        System.out.println("Result: " + ok);
    }

    @Test
    public void test4() {
        String input = "sqrt(x^2 - 5*r) <= sin(y)";

        CharStream cs = CharStreams.fromString(input);
        ConstraintsLexer lexer = new ConstraintsLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ConstraintsParser parser = new ConstraintsParser(tokens);

        ParseTree tree = parser.formula();
        System.out.println(tree.toStringTree(parser));
    }
}
