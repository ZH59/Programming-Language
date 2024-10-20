package terms;
//Input class, inherites from Statement 
public class Input extends Statement {
    public String id;

    public Input(String id) {
        this.id = id;
    }

    public String output_rep(){
        return "input " + id;
    }
}
