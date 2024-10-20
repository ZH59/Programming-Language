import antlr.*;
import terms.*;

import java.util.*;

// Semantics stores variable declaration, definition, uses; Label declaration, uses
// returns corresponding objects to SemanticProgram
public class Semantics extends ExprBaseVisitor<Term>{

    public List<SemData> vars_dec;
    public List<SemData> vars_def;
    public List<SemData> vars_use;

    public List<SemData> labs_dec;
    public List<SemData> labs_use;

    public Semantics(List<SemData> vars_dec, List<SemData> vars_def, List<SemData> vars_use, List<SemData> labs_dec, List<SemData> labs_use){
        this.vars_dec = vars_dec;
        this.vars_def = vars_def;
        this.vars_use = vars_use;
        this.labs_dec = labs_dec;
        this.labs_use = labs_use;
    }

    @Override
    public Term visitDeclaration(ExprParser.DeclarationContext ctx) {
        int line = ctx.getStart().getLine();
        String id = ctx.getChild(1).getText();
        vars_dec.add(new SemData(id, line));
        return new Declaration(id);
    }

    @Override
    public Term visitIdentlabel(ExprParser.IdentlabelContext ctx) {
        int line = ctx.getStart().getLine();
        String id = ctx.getChild(0).getText();
        labs_dec.add(new SemData(id, line));
        return new Label(id);
    }

    @Override
    public Term visitIdentvariable(ExprParser.IdentvariableContext ctx) {
        int line = ctx.getStart().getLine();
        String id = ctx.getChild(0).getText();
        Term expression = visit(ctx.getChild(2));
        vars_def.add(new SemData(id, line));
        return new Identvariable(id, expression);
    }

    @Override
    public Term visitInput(ExprParser.InputContext ctx) {
        int line = ctx.getStart().getLine();
        String id = ctx.getChild(1).getText();
        vars_def.add(new SemData(id, line));
        return new Input(id);
    }

    @Override
    public Term visitOutput(ExprParser.OutputContext ctx) {
        Term expression = visit(ctx.getChild(1));
        return new Output(expression);
    }

    @Override
    public Term visitGoto(ExprParser.GotoContext ctx) {
        int line = ctx.getStart().getLine();
        String label = ctx.getChild(1).getText();
        labs_use.add(new SemData(label, line));
        return new Goto(label);
    }

    @Override
    public Term visitIfgoto(ExprParser.IfgotoContext ctx) {
        int line = ctx.getStart().getLine();
        Expression cond = (Expression)visit(ctx.getChild(2));
        String label = ctx.getChild(5).getText();
        Goto g = new Goto(ctx.getChild(5).getText());
        labs_use.add(new SemData(label, line));
        return new Conditional(cond, g);
    }

    @Override
    public Term visitEnegative(ExprParser.EnegativeContext ctx) {
        Term expression = visit(ctx.getChild(1));
        return new Enegative(expression);
    }

    @Override
    public Term visitIdent(ExprParser.IdentContext ctx) {
        int line = ctx.getStart().getLine();
        String id = ctx.getChild(0).getText();
        vars_use.add(new SemData(id, line));
        return new Variable(id, 0);
    }

    @Override
    public Term visitNum(ExprParser.NumContext ctx) {
        String numText = ctx.getChild(0).getText();
        int number = Integer.parseInt(numText);
        return new Num(number);
    }

    @Override
    public Term visitEminus(ExprParser.EminusContext ctx) {
        Term left = visit(ctx.getChild(0));
        Term right = visit(ctx.getChild(2));
        return new Eminus(left, right);
    }

    @Override
    public Term visitEbrackets(ExprParser.EbracketsContext ctx) {
        Term expression = visit(ctx.getChild(1));
        return new Ebrackets(expression);
    }

    @Override
    public Term visitEadd(ExprParser.EaddContext ctx) {
        Term left = visit(ctx.getChild(0));
        Term right = visit(ctx.getChild(2));
        return new Eadd(left, right);
    }

    @Override
    public Term visitEgreater(ExprParser.EgreaterContext ctx) {
        Term left = visit(ctx.getChild(0));
        Term right = visit(ctx.getChild(2));
        return new Egreater(left, right);
    }

    @Override
    public Term visitEless(ExprParser.ElessContext ctx) {
        Term left = visit(ctx.getChild(0));
        Term right = visit(ctx.getChild(2));
        return new Eless(left, right);
    }

    @Override
    public Term visitEequal(ExprParser.EequalContext ctx) {
        Term left = visit(ctx.getChild(0));
        Term right = visit(ctx.getChild(2));
        return new Eequal(left, right);
    }

    @Override
    public Term visitEmultiple(ExprParser.EmultipleContext ctx) {
        Term left = visit(ctx.getChild(0));
        Term right = visit(ctx.getChild(2));
        return new Emultiple(left, right);
    }

    @Override
    public Term visitEdivide(ExprParser.EdivideContext ctx) {
        Term left = visit(ctx.getChild(0));
        Term right = visit(ctx.getChild(2));
        return new Edivide(left, right);
    }
}
