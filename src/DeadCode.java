import java.util.*;

import javax.lang.model.util.ElementScanner6;

import terms.*;

// Find useless variable assignations and remove them
public class DeadCode {

    List<List<Statement>> cleaned_blocks;
    Map<String, Boolean> variables; 

    public DeadCode(List<List<Statement>> blocks, List<List<Integer>> cfg){
        cleaned_blocks = new ArrayList<>();

        // For all blocks
        for(int i = 0; i < blocks.size(); i++){
            List<Statement> cur_block = blocks.get(i);
            cleaned_blocks.add(new ArrayList<>(cur_block));     // duplicate block (Saves us from indexing issues)
            for(int f = 0; f < cur_block.size(); f++){
                if(cur_block.get(f) instanceof Identvariable) {
                    if(FindDead(i, f + 1, blocks, cfg, ((Identvariable)cur_block.get(f)).id)) {     // Statement is dead, remove it from duplicate
                        cleaned_blocks.get(i).remove(cur_block.get(f));
                    }
                }
            }
        }
    }

    // Find if a statment is dead
    public boolean FindDead(Integer b, Integer t, List<List<Statement>> blocks, List<List<Integer>> cfg, String varid){
        List<Integer> seen_blocks = new ArrayList<Integer>();
        Deque<Integer> unseen_blocks = new LinkedList<Integer>();
        unseen_blocks.add(b);
       
        // Check if the rest of current block makes statement dead
        Integer self_res = CheckBlock(blocks.get(b).subList(t, blocks.get(b).size()), varid);   // Subset to only use rest
        if (self_res == 1)
            return false;
        else if (self_res == -1)
            return true;
        
        // ~BFS: Check if all other visited blocks make it dead
        while(!unseen_blocks.isEmpty()) {
            Integer cur_int = unseen_blocks.pop();
            Integer res = 0;
            if(cur_int != 0) {    // Don't run the entirety of first block.
                seen_blocks.add(cur_int);
                res = CheckBlock(blocks.get(cur_int), varid);
            }
            else
                res = self_res;
            if(res == 1)        // Found usage; alive 
                return false;
            if(res == 0)        // found neither usage nor overwriting; still dead
                for(Integer i : cfg.get(cur_int)) {
                    if(!seen_blocks.contains(i) && i < blocks.size()) {
                        unseen_blocks.add(i);
                    }
                }
            if(res == -1)       // Found overwriting; assignation useless
                return true;
        }
        return true;    // Found no  more overwritting or usage, variable dead for rest of program.
    }

    public Integer CheckBlock(List<Statement> block, String varid) { 
        for(Statement cur : block) {
            if(cur instanceof Output){
                if (((Output)cur).expression.contains(varid)) {
                    return 1;                                               // Variable being used, alive
                }
            }
            else if(cur instanceof Input){
                if (((Input)cur).id == varid)
                    return -1;                                            // Variable being overwritten; dead
            }
            else if(cur instanceof Identvariable){
                if (((Identvariable)cur).id.equals(varid)){               // Note: In the case where we are given x = x, this will return false. This is by design
                    return -1;                                            // Variable being overwritten; dead
                }
                else if (((Identvariable)cur).expression.contains(varid))
                    return 1;                                               // Variable being used, alive
            }
            else if(cur instanceof Conditional){
                if(((Conditional)cur).condition.contains(varid))
                    return 1;                                               // Variable being used, alive
            }
        }
        return 0;
    }
}