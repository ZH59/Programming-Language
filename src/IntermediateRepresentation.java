import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

import terms.*;
// Creates the intermediate representation for the given program, and outputs the dotty graph representation
public class IntermediateRepresentation {
    public List<List<Statement>> blocks;
    public List<String> vars;
    public List<List<Integer>> cfg;
    public Map<String, Integer> label_lookup;

    public IntermediateRepresentation(List<Term> terms, String fname) {
        int cur_block = 0;
        vars = new ArrayList<>();
        blocks = new ArrayList<>();
        blocks.add(new ArrayList<>());

        // Loop through all terms in program, incrementing the block counter whenever a term which justifies a new block appears
        for(Term t : terms){
            if(t instanceof Label){
                if(blocks.get(cur_block).size() > 0){               // Label, make new block
                    cur_block++;                        
                    blocks.add(new ArrayList<>());
                }
            }
            if(t instanceof Statement){
                blocks.get(cur_block).add((Statement) t);
            }
            if(t instanceof Conditional || t instanceof Goto){      // Goto, make new block
                if(blocks.get(cur_block).size() > 0){
                    cur_block++;
                    blocks.add(new ArrayList<>());
                }
            }
            if(t instanceof Declaration){                       // Add declarartions separately, not stored in blocks as they aren't statements
                vars.add(((Declaration)t).id);
            }
        }

        // Handy debugging code

        // System.out.println("Blocks");
        // for(List<Statement> l : blocks){
        //     for(Statement s : l){
        //         System.out.println(s);
        //     }
        //     System.out.println("");
        // }
        // System.out.println("Vars");
        // for(String s : vars){
        //     System.out.println(s);
        // }

        // Find all labels in program, to enable jumping to later labels
        label_lookup = new HashMap<>();
        for(int cur = 0; cur < blocks.size(); cur++){
            List<Statement> cur_list = blocks.get(cur);
            if(cur_list.size() > 0 ){
                if(cur_list.get(0) instanceof Label) {
                    label_lookup.put(((Label)cur_list.get(0)).id, cur); 
                }
            }
        }

        // Create the cfg 
        cfg = new ArrayList<>();
        for(int cur = 0; cur < blocks.size(); cur++){
            List<Statement> cur_list = blocks.get(cur);
            cfg.add(new ArrayList<>());
            if(cur_list.size() > 0 ){
                Term last = cur_list.get(cur_list.size()-1);
                if(last instanceof Goto) {                                              // If it's a goto, we can't go to next block, only add the goto block
                    cfg.get(cur).add(label_lookup.get(((Goto)last).label));
                } else {                                                                // If it's anything else, we can go to the next block, add it
                    cfg.get(cur).add(cur+1);
                }
                if(last instanceof Conditional){                                        
                    cfg.get(cur).add(label_lookup.get(((Conditional)last).g.label));    // If it's a conditional, both of the above apply
                }
            }
        }

        // Handy debugging code

        // for(int i = 0; i < cfg.size(); i++) {
        //     System.out.println(i + " can access: ");
        //     for(Integer f : cfg.get(i)) {
        //         System.out.println("\t"+ f);
        //     }
        //     System.out.println("");
        // }

        // Output to dotty format
        try {
            FileWriter writer = new FileWriter(fname);
            writer.write("digraph \"main\" {\n");           // Output default stuff
            writer.write("\tstart->B0;\n");                 // We don't store the start state, therefore need to output by default
            for(int i = 0; i < cfg.size(); i++) {
                for(Integer f : cfg.get(i)) {
                    if(f == cfg.size()) 
                        writer.write("\tB" + i +"->end;\n");        // Same as above but end
                    else
                        writer.write("\tB" + i +"->B" + f + ";\n");
                }
            }
            writer.write("}");
            writer.close();

        } catch (IOException e) {
            System.out.println("Choose a different file name");
        }
    }
}
