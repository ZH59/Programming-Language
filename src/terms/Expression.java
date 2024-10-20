package terms;
//Expression class, all classes extends Expression must implement contains 
public abstract class Expression extends Term {
    public abstract boolean contains(String id);
}
