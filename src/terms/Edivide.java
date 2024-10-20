package terms;
//division class, inherites from Expression 
public class Edivide extends Expression{
    public Expression left;
    public Expression right;

    public Edivide(Term left, Term right) {
        this.left = (Expression)left;
        this.right = (Expression)right;
    }

    public String output_rep(){
        return left.output_rep() + " / " + right.output_rep();
    }

    public boolean contains(String id){
        return (left.contains(id) || right.contains(id));
    }
}
