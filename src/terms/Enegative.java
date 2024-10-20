package terms;
//negatation class, inherites from Expression 
public class Enegative extends Expression{
    public Expression expression;

    public Enegative(Term expression) {
        this.expression = (Expression)expression;
    }
    
    public String output_rep(){
        return "-" + expression.output_rep();
    }

    public boolean contains(String id){
        return expression.contains(id);
    }
}
