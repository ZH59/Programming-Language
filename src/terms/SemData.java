package terms;

// Semdata class used to storing the variable tag and the line number of that variable
public class SemData {
    public String tag;
    public int line;
    public SemData(String tag, int line){
        this.tag = tag;
        this.line = line;
    }
    public String toString(){
        return String.format("tag: %s, line: %d",tag,line);
    }
}