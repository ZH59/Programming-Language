package terms;
//Goto class, inherites from Statement 
public class Goto extends Statement {
    public String label;

    public Goto(String label) {
        this.label = label;
    }
    
    public String output_rep(){
        return "goto " + label;
    }
}
