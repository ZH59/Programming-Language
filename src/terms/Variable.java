package terms;
//A variable has id and its value, variable calss inherits from Expression
public class Variable extends Expression{
    public String id;
    public int number;

    public Variable(String id, int number) {
        this.id = id;
        this.number = number;
    }

    public String output_rep(){
        return id;
    }

    public boolean contains(String oid){
        return oid.equals(id);
    }
}