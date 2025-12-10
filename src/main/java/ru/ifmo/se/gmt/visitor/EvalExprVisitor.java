package ru.ifmo.se.gmt.visitor;

import ru.ifmo.se.gmt.parser.ConstraintsBaseVisitor;
import ru.ifmo.se.gmt.parser.ConstraintsParser;

import java.util.Map;

public class EvalExprVisitor extends ConstraintsBaseVisitor<Double> {
    private final Map<String, Double> vars;

    public EvalExprVisitor(Map<String, Double> vars) {
        this.vars = vars;
    }

    @Override
    public Double visitExpr(ConstraintsParser.ExprContext ctx) {
        return visit(ctx.sum());
    }

    @Override
    public Double visitSum(ConstraintsParser.SumContext ctx) {
        double result = visit(ctx.prod(0));
        for (int i = 1; i < ctx.prod().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();
            double rhs = visit(ctx.prod(i));
            result = op.equals("+") ? result + rhs : result - rhs;
        }
        return result;
    }

    @Override
    public Double visitProd(ConstraintsParser.ProdContext ctx) {
        double result = visit(ctx.pow(0));

        for (int i = 1; i < ctx.pow().size(); i++) {
            String op = ctx.getChild(2 * i - 1).getText();
            double rhs = visit(ctx.pow(i));
            result = op.equals("*") ? result * rhs : result / rhs;
        }
        return result;
    }

    @Override
    public Double visitPow(ConstraintsParser.PowContext ctx) {
        double result = visit(ctx.unary(0));
        for (int i = 1; i < ctx.unary().size(); i++) {
            double rhs = visit(ctx.unary(i));
            result = Math.pow(result, rhs);
        }
        return result;
    }

    @Override
    public Double visitUnary(ConstraintsParser.UnaryContext ctx) {
        double value = visit(ctx.atom());
        if (ctx.getChildCount() == 2) {
            String sign = ctx.getChild(0).getText();
            if ("-".equals(sign)) return -value;
        }
        return value;
    }

    @Override
    public Double visitAtom(ConstraintsParser.AtomContext ctx) {
        if (ctx.X() != null) return vars.getOrDefault("x", 0.0);
        if (ctx.Y() != null) return vars.getOrDefault("y", 0.0);
        if (ctx.R() != null) return vars.getOrDefault("r", 0.0);

        if (ctx.number() != null) return visit(ctx.number());
        if (ctx.expr() != null && ctx.func() == null) return visit(ctx.expr());

        if (ctx.func() != null) {
            String f = ctx.func().getText();
            double arg = visit(ctx.expr());
            return applyFunc(f, arg);
        }

        throw new RuntimeException("Unknown atom: " + ctx.getText());
    }

    @Override
    public Double visitNumber(ConstraintsParser.NumberContext ctx) {
        if (ctx.INT() != null) return Double.parseDouble(ctx.INT().getText());
        return Double.parseDouble(ctx.FLOAT().getText());
    }

    private double applyFunc(String f, double x) {
        switch (f) {
            case "sqrt":
                return Math.sqrt(x);
            case "sin":
                return Math.sin(x);
            case "cos":
                return Math.cos(x);
            case "tan":
                return Math.tan(x);
            case "ln":
                return Math.log(x);
            case "log":
                return Math.log10(x);
            case "exp":
                return Math.exp(x);
            case "abs":
                return Math.abs(x);
            default:
                throw new RuntimeException("Unknown func: " + f);

        }
    }
}
