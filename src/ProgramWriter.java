import java.util.*;
import java.io.FileWriter;

import terms.*;

// Output the program into a new file
public class ProgramWriter {
    public ProgramWriter(List<Term> terms, String fname){
        try {
            FileWriter writer = new FileWriter(fname);
            writer.write("prog\n");
            for(Term t : terms){
                if(t instanceof Label)
                    writer.write(t.output_rep() + ":\n");           // Labels are special and are outputted on their own
                else
                    writer.write("\t" + t.output_rep() + ";\n");    // Just uses an output_rep method, which is forcibly implemented in all terms through inheritance.
            }
            writer.write("endprog");
            writer.close();
        } catch (Exception e) { 
            System.out.println("Choose a different file name");     // File writter couldn't write to that file
        }
    }
}