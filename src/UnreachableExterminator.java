import terms.*;
import java.util.*;
import java.util.function.UnaryOperator;


// Finds unreachable code blocks and removes them
public class UnreachableExterminator {
    Deque<Integer> reachables;
    List<List<Statement>> reachable_blocks; 
    List<List<Integer>> new_cfg;

    public UnreachableExterminator(List<List<Integer>> cfg, List<List<Statement>> blocks) {
        reachables = new LinkedList<>();
        reachable_blocks = new ArrayList<>();
        new_cfg = new ArrayList<>();
        Map<Integer, Integer> block_map = new HashMap<>();  // Original Index, new index

        Deque<Integer> unseen = new LinkedList<>();
        unseen.add(0);
        // Performs BFS over the cfg, adds all blocks that are found
        while(!unseen.isEmpty()) {
            Integer cur_int = unseen.pop();
            block_map.put(cur_int, reachables.size());
            reachables.add(cur_int);
            reachable_blocks.add(blocks.get(cur_int));
            System.out.print(cur_int + " can access: ");
            for(Integer f : cfg.get(cur_int)) {
                System.out.print(f + ", ");
                if(!reachables.contains(f) && f < blocks.size())    // If it's an unseen block, and not the special end block
                    unseen.add(f);  
            }
            System.out.println();
        }
        block_map.put(blocks.size(), reachables.size());
        System.out.println(block_map);
        System.out.println(cfg);
        for(Integer i : reachables){
            cfg.get(i).replaceAll(new MyOperator(block_map));
            new_cfg.add(cfg.get(i));
        }
        System.out.println(new_cfg);
    }
}

class MyOperator implements UnaryOperator<Integer> 
{
    Map<Integer, Integer> block_map;

    public MyOperator(Map<Integer, Integer> bm){
        block_map = new HashMap<>(bm);  // Original Index, new index 
    }
 
    @Override
    public Integer apply(Integer t) {
        return block_map.get(t);
   }

}