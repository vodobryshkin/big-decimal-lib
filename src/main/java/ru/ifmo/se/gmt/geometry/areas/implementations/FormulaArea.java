package ru.ifmo.se.gmt.geometry.areas.implementations;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import ru.ifmo.se.gmt.geometry.areas.interfaces.Area;
import ru.ifmo.se.gmt.geometry.model.Point;
import ru.ifmo.se.gmt.parser.ConstraintsLexer;
import ru.ifmo.se.gmt.parser.ConstraintsParser;
import ru.ifmo.se.gmt.visitor.EvalExprVisitor;
import ru.ifmo.se.gmt.visitor.EvalFormulaVisitor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Map;

/**
 * Класс, реализующий область, заданную по функции.
 */
public class FormulaArea implements Area {
    private final ParseTree tree;
    private final BigDecimal radius;

    private final MathContext mc = new MathContext(50);

    public FormulaArea(String formula, BigDecimal radius) {
        CharStream cs = CharStreams.fromString(formula);
        tree = new ConstraintsParser(new CommonTokenStream(new ConstraintsLexer(cs))).formula();
        this.radius = radius;
    }

    /**
     * Метод для проверки вхождения точки внутрь заданной формулой области.
     * Чтобы точка лежала внутри заданной формулой области, нужно чтобы она соответствовала уравнению.
     *
     * @param point точка для проверки.
     * @return информацию входит ли точка в область (true) или нет (false).
     */
    @Override
    public boolean checkPoint(Point point) {
        BigDecimal x = point.getX();
        BigDecimal y = point.getY();

        Map<String, BigDecimal> vars = Map.of("x", x, "y", y, "r", radius);
        EvalExprVisitor exprVisitor = new EvalExprVisitor(vars, mc);
        EvalFormulaVisitor formulaVisitor = new EvalFormulaVisitor(exprVisitor);

        return formulaVisitor.visit(tree);
    }
}
