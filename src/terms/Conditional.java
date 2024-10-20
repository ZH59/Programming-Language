package terms;
//Conditional goto class, inherites from Statement 
public class Conditional extends Statement{
    public Expression condition;
    public Goto g;

    public Conditional(Expression condition, Goto g){
        this.condition = condition;
        this.g = g;
    }

    public String output_rep(){
        return "if (" + condition.output_rep() + ") goto " + g.label;
    }
}
