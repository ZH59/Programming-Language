import java.util.*;
import terms.*;
import de.normalisiert.utils.graphs.*;
import java.util.stream.Collectors;

public class Propagation {

    public List<List<Integer>> cfg_par;
    public HashMap<Integer, HashMap<String, Integer>> in_env;
    public HashMap<Integer, HashMap<String, Integer>> out_env;
    public List<List<Integer>> cfg;
    public List<List<Statement>> blocks;

    public Propagation(List<List<Statement>> blocks_arg, List<List<Integer>> cfg_arg) {
        in_env = new HashMap<Integer, HashMap<String, Integer>>();
        out_env = new HashMap<Integer, HashMap<String, Integer>>();
        cfg = cfg_arg;
        blocks = blocks_arg;
        getParent(cfg);
    }
    // Using list representation of cfg, get a parent list [i:parents of block i]
    public void getParent(List<List<Integer>> cfg) {
        List<List<Integer>> parent = new ArrayList<>(cfg.size() + 1);
        for (int i = 0; i < cfg.size() + 1; ++i) {
            parent.add(new ArrayList<>());
        }
        for (int i = 0; i < cfg.size(); i++) {
            for (int j = 0; j < cfg.get(i).size(); j++) {
                parent.get(cfg.get(i).get(j)).add(i);
            }
        }
        parent.remove(parent.size() - 1);
        cfg_par = parent;
    }

    // Used for checking if the Term still have a variable
    boolean can_simplify = true;

    // Run a block of statements and return out enviroment
    public HashMap<String, Integer> blockUnitEnv(int block, HashMap<String, Integer> env, boolean replace) {
        HashMap<String, Integer> tempenv = new HashMap<String,Integer>(env);

        for (int i = 0; i < blocks.get(block).size(); i++) {
            Statement statement = blocks.get(block).get(i);
            if (statement instanceof Input) {
                Input tempstat = (Input) statement;
                tempenv.remove(tempstat.id);
            } else if (statement instanceof Identvariable) {
                Identvariable tempstat = (Identvariable) statement;
                Term term = replaceVar(tempstat.expression, env, replace, tempstat.id);
                Identvariable identvariable;
                if (can_simplify) {
                    int number = simplifyTerm(term);
                    Num num = new Num(number);
                    identvariable = new Identvariable(tempstat.id, num);
                    tempenv.put(tempstat.id, number);
                } else {
                    identvariable = new Identvariable(tempstat.id, term);
                    tempenv.remove(tempstat.id);
                    can_simplify = true;
                    // System.out.println(can_simplify);
                }

                if (replace)
                    blocks.get(block).set(i, identvariable);
            } else if (statement instanceof Output) {
                Output tempstat = (Output) statement;
                Term term = replaceVar(tempstat.expression, env, true, "");
                Output output;
                if (can_simplify) {
                    int number = simplifyTerm(term);
                    Num num = new Num(number);
                    output = new Output(num);
                } else {
                    output = new Output(term);
                    can_simplify = true;
                }
                if (replace)
                    blocks.get(block).set(i, output);
            } else if (statement instanceof Conditional) {
                Conditional tempstat = (Conditional) statement;
                Term term = replaceVar(tempstat.condition, env, true, "");
                Conditional conditional;
                if (can_simplify) {
                    int number = simplifyTerm(term);
                    Num num = new Num(number);
                    conditional = new Conditional(num, tempstat.g);
                } else {
                    conditional = new Conditional((Expression) term, tempstat.g);
                    can_simplify = true;
                }
                if (replace)
                    blocks.get(block).set(i, conditional);
            }
        }
        return tempenv;
    }

    // Propegate constant to variables if applicable
    private Term replaceVar(Term e, HashMap<String, Integer> env, boolean loop_block, String var) {
        Term result = e;

        if (e instanceof Num) {
            Num num = (Num) e;
            result = num;
        } else if (e instanceof Variable) {
            Variable variable = (Variable) e;
            if (env.get(variable.id) == null || (!loop_block && var.equals(variable.id))){
                result = variable;
                can_simplify = false;
            } else {
                result = new Num(env.get(variable.id));
            }
        } else if (e instanceof Eadd) {
            Eadd eadd = (Eadd) e;
            Term left = replaceVar(eadd.left, env, loop_block, var);
            Term right = replaceVar(eadd.right, env, loop_block, var);
            result = new Eadd(left, right);
        } else if (e instanceof Eminus) {
            Eminus eminus = (Eminus) e;
            Term left = replaceVar(eminus.left, env, loop_block, var);
            Term right = replaceVar(eminus.right, env, loop_block, var);
            result = new Eminus(left, right);
        } else if (e instanceof Emultiple) {
            Emultiple emultiple = (Emultiple) e;
            Term left = replaceVar(emultiple.left, env, loop_block, var);
            Term right = replaceVar(emultiple.right, env, loop_block, var);
            result = new Emultiple(left, right);
        } else if (e instanceof Edivide) {
            Edivide edivide = (Edivide) e;
            Term left = replaceVar(edivide.left, env, loop_block, var);
            Term right = replaceVar(edivide.right, env, loop_block, var);
            result = new Edivide(left, right);
        } else if (e instanceof Enegative) {
            Enegative enegative = (Enegative) e;
            Term neg = replaceVar(enegative.expression, env, loop_block, var);
            result = new Enegative(neg);
        } else if (e instanceof Ebrackets) {
            Ebrackets ebrackets = (Ebrackets) e;
            Term brac = replaceVar(ebrackets.expression, env, loop_block, var);
            result = new Ebrackets(brac);
        } else if (e instanceof Egreater) {
            Egreater egreater = (Egreater) e;
            Term left = replaceVar(egreater.left, env, loop_block, var);
            Term right = replaceVar(egreater.right, env, loop_block, var);
            result = new Egreater(left, right);
        } else if (e instanceof Eless) {
            Eless eless = (Eless) e;
            Term left = replaceVar(eless.left, env, loop_block, var);
            Term right = replaceVar(eless.right, env, loop_block, var);
            result = new Eless(left, right);
        } else if (e instanceof Eequal) {
            Eequal eequal = (Eequal) e;
            Term left = replaceVar(eequal.left, env, loop_block, var);
            Term right = replaceVar(eequal.right, env, loop_block, var);
            result = new Eequal(left, right);
        }

        return result;

    }

    // simplify an expression if it only contains constants
    private int simplifyTerm(Term e) {
        int result = 0;

        if (e instanceof Num) {
            Num num = (Num) e;
            result = num.number;
        } else if (e instanceof Eadd) {
            Eadd eadd = (Eadd) e;
            int left = simplifyTerm(eadd.left);
            int right = simplifyTerm(eadd.right);
            result = left + right;
        } else if (e instanceof Eminus) {
            Eminus eminus = (Eminus) e;
            int left = simplifyTerm(eminus.left);
            int right = simplifyTerm(eminus.right);
            result = left - right;
        } else if (e instanceof Emultiple) {
            Emultiple emultiple = (Emultiple) e;
            int left = simplifyTerm(emultiple.left);
            int right = simplifyTerm(emultiple.right);
            result = left * right;
        } else if (e instanceof Edivide) {
            Edivide edivide = (Edivide) e;
            int left = simplifyTerm(edivide.left);
            int right = simplifyTerm(edivide.right);
            result = left / right;
        } else if (e instanceof Enegative) {
            Enegative enegative = (Enegative) e;
            int neg = simplifyTerm(enegative.expression);
            result = -neg;
        } else if (e instanceof Ebrackets) {
            Ebrackets ebrackets = (Ebrackets) e;
            int brac = simplifyTerm(ebrackets.expression);
            result = brac;
        } else if (e instanceof Egreater) {
            Egreater egreater = (Egreater) e;
            int left = simplifyTerm(egreater.left);
            int right = simplifyTerm(egreater.right);
            if (left > right) {
                result = 1;
            } else {
                result = 0;
            }
        } else if(e instanceof Eless){
            Eless eless = (Eless) e;
            int left = simplifyTerm(eless.left);
            int right = simplifyTerm(eless.right);
            if (left < right){
                result = 1;
            }
            else{
                result = 0;
            }
        } else if (e instanceof Eequal) {
            Eequal eequal = (Eequal) e;
            int left = simplifyTerm(eequal.left);
            int right = simplifyTerm(eequal.right);
            if (left == right) {
                result = 1;
            } else {
                result = 0;
            }
        }
        return result;
    }

    // calculating a cycle's end node's 'out' enviroment
    public HashMap<String, Integer> seqOut(HashMap<String, Integer> inputEnv, List<Integer> cfgSeq) {
        HashMap<Integer, HashMap<String, Integer>> in_env_seq = new HashMap<>();
        HashMap<Integer, HashMap<String, Integer>> out_env_seq = new HashMap<>();
        in_env_seq.put(cfgSeq.get(0), inputEnv);

        for (int i = 0; i < cfgSeq.size(); i++) {
            if (i != 0) {
                in_env_seq.put(cfgSeq.get(i), out_env_seq.get(i - 1));
            }
            out_env_seq.put(i, blockUnitEnv(cfgSeq.get(i), in_env_seq.get(cfgSeq.get(i)), false));
        }

        return out_env_seq.get(out_env_seq.size() - 1);
    }

    // calculating the intersection of two HashMap (equality of both id and value)
    public HashMap<String, Integer> intersect(HashMap<String, Integer> m1, HashMap<String, Integer> m2) {
        HashMap<String, Integer> out = new HashMap<>();
        for (String key1 : m1.keySet()) {
            for (String key2 : m2.keySet()) {
                if ((key1.equals(key2)) && (m1.get(key1).equals(m2.get(key2)))) {
                    out.put(key1, m1.get(key1));
                }
            }
        }
        return out;
    }

    // calculating the intersection of HashMap list (equality of both id and value)
    public HashMap<String, Integer> intersectList(List<HashMap<String, Integer>> m) {
        HashMap<String, Integer> out = m.get(0);
        for (int i = 1; i < m.size(); i++) {
            out = intersect(out, m.get(i));
        }
        return out;
    }

    // perform constant propegation
    public void programLevelEnvReal() {
        //remove unreachable blocks from parent list, we don't perform propagation on unreachable code 
        Deque<Integer> reachables = new LinkedList<>();
        List<Integer> unreachables = new ArrayList<>();
        List<List<Statement>> reachable_blocks = new ArrayList<>();
        Deque<Integer> unseen = new LinkedList<>();
        unseen.add(0);
        while(!unseen.isEmpty()) {
            Integer cur_int = unseen.pop();
            reachables.add(cur_int);
            reachable_blocks.add(blocks.get(cur_int));
            for(Integer f : cfg.get(cur_int)) {
                if(!reachables.contains(f) && f < blocks.size())
                    unseen.add(f);
            }
        }
        for(List<Integer> l : cfg_par){
            l.retainAll(reachables);
        }
        // find all cycles
        List<List<Integer>> cycles = findCycle();
        for (int i = 0; i < cycles.size(); i++){
            cfg_par.get(cycles.get(i).get(0)).remove(cycles.get(i).get(cycles.get(i).size()-1));
        }
        //while we haven't computed every out, we compute in/out invironments iteratively 
        while (out_env.size() != cfg.size()) {
            for (int i = 0; i < cfg.size(); i++) {
                if (out_env.get(i) == null) {
                    if (in_env.get(i) != null) {
                        HashMap<String,Integer> result = blockUnitEnv(i, in_env.get(i), true); 
                        out_env.put(i, new HashMap<String,Integer>(result));
                    } else {
                        List<HashMap<String, Integer>> environment_list = new ArrayList<>();
                        HashMap<String, Integer> environment = new HashMap<>();
                        boolean flag = true;
                        for (int j = 0; j < cfg_par.get(i).size(); j++) {
                            if (out_env.get(cfg_par.get(i).get(j)) == null) {
                                flag = false;
                            } else {
                                environment_list.add(out_env.get(cfg_par.get(i).get(j)));
                                environment = new HashMap<String,Integer>(intersectList(environment_list));
                            }
                        }
                        if (flag == true) {
                            List<HashMap<String, Integer>> end_node_outs = new ArrayList<>();
                            for (int j = 0; j < cycles.size(); j++) {
                                if (cycles.get(j).get(0) == i) {
                                    end_node_outs.add(seqOut(environment, cycles.get(j)));
                                }
                            }
                            if(end_node_outs.isEmpty()){
                                in_env.put(i, new HashMap<String, Integer>(environment));
                            }else{
                                in_env.put(i, new HashMap<String, Integer>(intersect(environment,intersectList(end_node_outs))));
                            }
                        }
                    }
                }
            }
        }
    }

    //find all cycles in the program
    public List<List<Integer>> findCycle() {
        Integer nodes[] = new Integer[cfg.size()];
        boolean adjMatrix[][] = new boolean[cfg.size()][cfg.size()];

        for (int i = 0; i < cfg.size(); i++) {
            nodes[i] = i;
        }
        for (int i = 0; i < cfg.size(); i++) {
            for (int j = 0; j < cfg.get(i).size(); j++) {
                if (cfg.get(i).get(j) != cfg.size()) {
                    adjMatrix[i][cfg.get(i).get(j)] = true;
                }
            }
        }

        /***************************************************************************************
        *    Title: <graphs>
        *    Author: <Frank Meyer>
        *    Date: <2012> 
        *    Availability: <src/de/normalisiert/utils/graphs>
        *
        ***************************************************************************************/
         // We use this to compute cycles 
        ElementaryCyclesSearch ecs = new ElementaryCyclesSearch(adjMatrix, nodes);
        List<List<Integer>> cycles = ecs.getElementaryCycles();
        return cycles;
    }
}