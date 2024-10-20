package terms;
//Number class, inherites from Expression 
public class Num extends Expression{

    public int number;

    public Num(int number){
        this.number = number;
    }

    public String output_rep(){
        return Integer.toString(number);
    }

    public boolean contains(String id){
        return false;
    }
}
