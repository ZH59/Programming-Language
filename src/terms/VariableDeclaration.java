package terms;

public class VariableDeclaration extends Expression{
	public String id;
	public int value;

	public VariableDeclaration(String id, String type, int value) {
		this.id = id;
		this.value = value;
	}
	public String output_rep(){
		return "";
	}
	public boolean contains(String oid){
		return(oid.equals(id));
	}
}


