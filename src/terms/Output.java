package terms;
//output(print) class, inherites from Statement 
public class Output extends Statement {
    public Expression expression;

    public Output(Term expression) {
        this.expression = (Expression)expression;
    }

    public String output_rep(){
        return "output " + expression.output_rep();
    }
}
