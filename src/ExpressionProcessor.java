import java.util.*;

import terms.*;

public class ExpressionProcessor {

    public List<Term> list;
    public Map<String, Integer> var_values;
    public List<Integer> outputs;
    public Map<String, Integer> labels;
    //class to compute the result of an expression if possible
    public ExpressionProcessor(List<Term> list, Map<String, Integer> labels){
        this.list = list;
        var_values = new HashMap<>();
        outputs = new ArrayList<>();
        this.labels = labels;
        Scanner scanner = null;

        Integer i = 0;
        while(i < list.size()){
            Term e = list.get(i);
            if(e instanceof Declaration){
                Declaration decl = (Declaration) e;
                var_values.put(decl.id, 0);
            }
            else if(e instanceof Output){
                Output output = (Output) e;
                int result = getOperationResult(output.expression);
                outputs.add(result);
            }
            else if(e instanceof Input){
                Input input = (Input) e;
                String id = input.id;
                scanner = new Scanner(System.in);
                System.out.println(id + " = ?");
                int num = Integer.parseInt(scanner.nextLine());
                // scanner.close();
                var_values.put(id, num);
            }
            else if(e instanceof Identvariable){
                Identvariable identvariable = (Identvariable) e;
                String id = identvariable.id;
                int num = getOperationResult(identvariable.expression);
                var_values.put(id, num);
            }
            else if(e instanceof Goto){
                Goto g = (Goto)e;
                i = labels.get(g.label);
            }
            else if(e instanceof Conditional){
                Conditional cond = (Conditional)e;
                int result = getOperationResult(cond.condition);
                if (result >= 1)
                    i = labels.get(cond.g.label);
            }
            else if(e instanceof Label){
                Label l = (Label)e;
                labels.put(l.id, i);
            }
            i++;
            //Remember to fill for all other instances of statement 's'!!!
        }
        if(scanner!=null){
            scanner.close();
        }
    }

    private int getOperationResult(Term e){
        int result = 0;

        if(e instanceof Num){
            Num num = (Num) e;
            result = num.number;
        }
        else if(e instanceof Variable){
            Variable variable = (Variable) e;
            result = var_values.get(variable.id);
        }
        else if(e instanceof Eadd){
            Eadd eadd = (Eadd) e;
            int left = getOperationResult(eadd.left);
            int right = getOperationResult(eadd.right);
            result = left + right;
        }
        else if(e instanceof Eminus){
            Eminus eminus = (Eminus) e;
            int left = getOperationResult(eminus.left);
            int right = getOperationResult(eminus.right);
            result = left - right;
        }
        else if(e instanceof Emultiple){
            Emultiple emultiple = (Emultiple) e;
            int left = getOperationResult(emultiple.left);
            int right = getOperationResult(emultiple.right);
            result = left * right;
        }
        else if(e instanceof Edivide){
            Edivide edivide = (Edivide) e;
            int left = getOperationResult(edivide.left);
            int right = getOperationResult(edivide.right);
            result = left / right;
        }
        else if(e instanceof Enegative){
            Enegative enegative = (Enegative) e;
            int neg = getOperationResult(enegative.expression);
            result = -neg;
        }
        else if(e instanceof Ebrackets){
            Ebrackets ebrackets = (Ebrackets) e;
            int brac = getOperationResult(ebrackets.expression);
            result = brac;
        }
        else if(e instanceof Egreater){
            Egreater egreater = (Egreater) e;
            int left = getOperationResult(egreater.left);
            int right = getOperationResult(egreater.right);
            if (left > right){
                result = 1;
            }
            else{
                result = 0;
            }
        }

        else if(e instanceof Eless){
            Eless eless = (Eless) e;
            int left = getOperationResult(eless.left);
            int right = getOperationResult(eless.right);
            if (left < right){
                result = 1;
            }
            else{
                result = 0;
            }
        }
        else if(e instanceof Eequal){
            Eequal eequal = (Eequal) e;
            int left = getOperationResult(eequal.left);
            int right = getOperationResult(eequal.right);
            if (left == right){
                result = 1;
            }
            else{
                result = 0;
            }
        }
        //Remember to fill for other operations!!!

        return result;
    }
}
