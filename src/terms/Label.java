package terms;
//Label class, inherites from Statement 
public class Label extends Statement {
    public String id;

    public Label(String id){
        this.id = id;
    }

    public String output_rep(){
        return id;
    }
}
