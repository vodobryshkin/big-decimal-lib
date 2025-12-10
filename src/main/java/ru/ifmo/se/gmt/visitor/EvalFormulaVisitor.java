package ru.ifmo.se.gmt.visitor;

import org.antlr.v4.runtime.tree.ParseTree;
import ru.ifmo.se.gmt.parser.ConstraintsBaseVisitor;
import ru.ifmo.se.gmt.parser.ConstraintsParser;

import java.util.List;

public class EvalFormulaVisitor extends ConstraintsBaseVisitor<Boolean> {
    private final EvalExprVisitor expr;

    public EvalFormulaVisitor(EvalExprVisitor expr) {
        this.expr = expr;
    }

    @Override
    public Boolean visitFormula(ConstraintsParser.FormulaContext ctx) {
        // Берём все constraint-контексты без зависимости от ctx.constraint()
        List<ConstraintsParser.ConstraintContext> constraints =
                ctx.getRuleContexts(ConstraintsParser.ConstraintContext.class);

        boolean result = visit(constraints.get(0));

        // Структура: constraint (booleanOp constraint)* EOF
        // Оператор между constraint-ами лежит как текстовый child между ними.
        for (int i = 1; i < constraints.size(); i++) {
            // Находим оператор через детей контекста formula:
            // child-порядок обычно: c0 op c1 op c2 ...
            // Поэтому оператор перед i-м constraint — это child с индексом 2*i - 1
            ParseTree opNode = ctx.getChild(2 * i - 1);
            String op = opNode.getText();

            boolean rhs = visit(constraints.get(i));

            if ("&&".equals(op)) {
                result = result && rhs;
            } else if ("||".equals(op)) {
                result = result || rhs;
            } else if ("xor".equals(op)) {
                result = result ^ rhs;
            } else {
                throw new RuntimeException("Unknown boolean op: " + op);
            }
        }

        return result;
    }

    @Override
    public Boolean visitConstraint(ConstraintsParser.ConstraintContext ctx) {
        // Берём два expr без зависимости от ctx.expr()
        List<ConstraintsParser.ExprContext> exprs =
                ctx.getRuleContexts(ConstraintsParser.ExprContext.class);

        double left = expr.visit(exprs.get(0));
        double right = expr.visit(exprs.get(1));

        // В constraint: expr parSymbol expr
        // средний child — это оператор сравнения
        String op = ctx.getChild(1).getText();

        if (">".equals(op)) return left > right;
        if (">=".equals(op)) return left >= right;
        if ("<".equals(op)) return left < right;
        if ("<=".equals(op)) return left <= right;
        if ("=".equals(op)) return left == right;
        if ("!=".equals(op)) return left != right;

        throw new RuntimeException("Unknown par op: " + op);
    }
}
