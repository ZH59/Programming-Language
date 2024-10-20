package terms;
//variable declaration class, inherites from Term 
public class Declaration extends Term {
	public String id;

	public Declaration(String id) {
		this.id = id;
	}

    public String output_rep(){
        return "var " + id;
    }
}

