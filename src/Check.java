import terms.*;
import java.util.*;
public class Check {
    public List<SemData> vars_dec;
    public List<SemData> vars_def;
    public List<SemData> vars_use;
    public List<SemData> labs_dec;
    public List<SemData> labs_use;

    public Check(List<SemData> vars_dec, List<SemData> vars_def, List<SemData> vars_use, List<SemData> labs_dec, List<SemData> labs_use){
        this.vars_dec = vars_dec;
        this.vars_def = vars_def;
        this.vars_use = vars_use;
        this.labs_dec = labs_dec;
        this.labs_use = labs_use;
    }

    // check if all variable definitions are before their variable declarations
    public List<String> ListcheckDecBeforeDef(){
        List<String> result = new ArrayList<String>();
        for(SemData sd1 : vars_def){
            boolean flag = false;
            for(SemData sd2 : vars_dec){
                if((sd1.tag.equals(sd2.tag)) && (sd1.line > sd2.line)){
                    flag = true; //correct use
                }
            }
            if(flag == false){
                result.add(String.format("variable %s is defined before its declaration at line number: %d",sd1.tag,sd1.line));
            }
        }
        return result;
    }

    // check if all variable uses are before their variable definitions
    public List<String> ListcheckDefBeforeUse(){
        List<String> result = new ArrayList<String>();
        for(SemData sd1 : vars_use){
            boolean flag = false;
            for(SemData sd2 : vars_def){
                if((sd1.tag.equals(sd2.tag)) && (sd1.line > sd2.line)){
                    flag = true; //correct use
                }
            }
            if(flag == false){
                result.add(String.format("variable %s is used before its definition at line number: %d",sd1.tag,sd1.line));
            }
        }
        return result;
    }
    
    // check if all variables have no more than two declarations
    public List<String> varDoubleDec(){
        List<String> result = new ArrayList<String>();
        for(SemData sd1 : vars_dec){
            boolean flag = false;
            for(SemData sd2 : vars_dec){
                if(((!sd1.tag.equals(sd2.tag)) && (!(sd1.equals(sd2)))) || (sd1.equals(sd2))){
                    flag = true; //correct use 
                }
            }
            if(flag == false){
                result.add(String.format("variable %s is declared multiple times at line number: %d",sd1.tag,sd1.line));
            }
        }
        Set<String> set = new HashSet<>(result);
        result.clear();
        result.addAll(set);
        return result;
    }

    // check if all labels have no more than two declarations
    public List<String> LabeldoubleDec(){
        List<String> result = new ArrayList<String>();
        for(SemData sd1 : labs_dec){
            boolean flag = false;
            for(SemData sd2 : labs_dec){
                if((!sd1.tag.equals(sd2.tag)) && (!(sd1.equals(sd2))) || (sd1.equals(sd2))){
                    flag = true; //correct use 
                }
            }
            if(flag == false){
                result.add(String.format("variable %s is declared multiple times at line number: %d",sd1.tag,sd1.line));
            }
        }
        Set<String> set = new HashSet<>(result);
        result.clear();
        result.addAll(set);
        return result;
    }

    // check if all labels uses have their declarations in the program
    public List<String> LablecheckUseDef(){
        List<String> result = new ArrayList<String>();
        for(SemData sd1 : labs_use){
            boolean flag = false;
            for(SemData sd2 : labs_dec){
                if(sd1.tag.equals(sd2.tag)){
                    flag = true;
                }
            }
            if(!flag){
                result.add(String.format("label %s has not been delcared in the program at line number: %d",sd1.tag,sd1.line));
            }
        }
        return result;
    }
    
    // check all possible errors in a row
    public List<String> checkAll(){
        List<String> result = new ArrayList<String>();
        // System.out.println(ListcheckDecBeforeDef());
        result.addAll(ListcheckDecBeforeDef());
        result.addAll(ListcheckDefBeforeUse());
        result.addAll(varDoubleDec());
        result.addAll(LabeldoubleDec());
        result.addAll(LablecheckUseDef());
        return result;
    }
}
