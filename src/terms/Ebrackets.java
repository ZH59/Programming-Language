package terms;
//brackets class, inherites from Expression 
public class Ebrackets extends Expression{
    public Expression expression;

    public Ebrackets(Term expression) {
        this.expression = (Expression)expression;
    }
    
    public String output_rep(){
        return '(' + expression.output_rep() + ')';
    }

    public boolean contains(String id){
        return expression.contains(id);
    }
}
