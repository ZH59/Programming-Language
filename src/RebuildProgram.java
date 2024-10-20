import terms.*;
import java.util.*; 

// Put the declarations and statements together again
public class RebuildProgram {
    List<Term> prog;

    public RebuildProgram(List<List<Statement>> statements, List<String> vars) {
        prog = new ArrayList<>();
    
        for (String s : vars) {
            prog.add(new Declaration(s));
        }
        for (List<Statement> l : statements) {
            for(Statement s : l) { 
                prog.add(s);
            }
        }
    }
}
