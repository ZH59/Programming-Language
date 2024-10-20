package terms;

// a type of Statement, assign an expression to a variable
public class Identvariable extends Statement{
    public String id;
    public Expression expression;

    public Identvariable(String id, Term expression) {
        this.id = id;
        this.expression = (Expression)expression;
    }

    // get the whole assignment statement
    public String output_rep(){
        return id + " := " + expression.output_rep();
    }
}
