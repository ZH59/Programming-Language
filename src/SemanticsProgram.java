import antlr.*;
import terms.*;

import java.util.ArrayList;
import java.util.List;

// visit all statements, stores all the objects and information 
public class SemanticsProgram extends ExprBaseVisitor<Program> {

    public List<SemData> vars_dec;
    public List<SemData> vars_def;
    public List<SemData> vars_use;

    public List<SemData> labs_dec;
    public List<SemData> labs_use;
    
    @Override
    public Program visitProgram(ExprParser.ProgramContext ctx) {

        Program prog = new Program();
        vars_dec = new ArrayList<>();
        vars_def = new ArrayList<>();
        vars_use = new ArrayList<>();
        labs_dec = new ArrayList<>();
        labs_use = new ArrayList<>();
        Semantics sem = new Semantics(vars_dec, vars_def, vars_use, labs_dec, labs_use);
        for (int i = 1; i < ctx.getChildCount(); i++) {
            if (i != ctx.getChildCount() - 2 && i != ctx.getChildCount() - 1) {
                Term temp = (sem.visit(ctx.getChild(i)));
                prog.addExpression(temp);
                if(temp instanceof Label)
                    prog.addLabel((Label)temp, i-1);
            }
        }
        return prog;
    }
}
