package terms;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//Program class, a program has a list of expressions, and labels for line number
public class Program {
    public List<Term> expressions;
    public Map<String, Integer> labels;

    public Program(){
        this.expressions = new ArrayList<>();
        this.labels= new HashMap<String, Integer>();
    }

    public void addExpression(Term e) {
        expressions.add(e);
    }

    public void addLabel(Label e, int loc) {
        labels.put(e.id, loc);
    }
}
