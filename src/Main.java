import antlr.ExprLexer;
import antlr.ExprParser;
import terms.Program;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import terms.*;

import terms.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // initialise the input mu program, graph file and optimised file
        String optimised_file = null;
        String graph_file = null;
        String input_file = args[0];
        // the case for task 3 and further
        if (args.length > 1){
            graph_file = args[1];
        }
        // the checkers for different optimiser and interpreter flags
        boolean interpreter_enable = false;
        boolean unreachable_enable = false;
        boolean deadcode_enable = false;
        boolean constant_enable = false;
        // enable interpreter
        if (Arrays.asList(args).contains("--interpreter")) {
            interpreter_enable = true;
        }
        // enable unreachable code deletion optimiser
        if (Arrays.asList(args).contains("--unreachable")) {
            unreachable_enable = true;
        }
        // enable constant propagation optimiser
        if (Arrays.asList(args).contains("--constants")) {
            constant_enable = true;
        }
        // enable deadcode elimination optimiser
        if(Arrays.asList(args).contains("--deadcode")){
            deadcode_enable = true;
        }
        // the optimised file is used for either of the 3 optimisers
        if(unreachable_enable || deadcode_enable || constant_enable){
            optimised_file = args[2];
        }
        //produce parse tree from the start node 'p'
        ExprParser parser = getParser(input_file);
        ParseTree antlrAST = parser.p();
        List<String> ruleNamesList = Arrays.asList(parser.getRuleNames());
        // produce a pretty tree which stores the AST
        String prettyTree = TreeUtils.toPrettyTree(antlrAST, ruleNamesList);

        // convert parse tree into expression objects
        SemanticsProgram prog_visitor = new SemanticsProgram();
        // produce the mu program
        Program prog = prog_visitor.visit(antlrAST);
        // check for semantic errors
        Check check = new Check(prog_visitor.vars_dec, prog_visitor.vars_def, prog_visitor.vars_use,
        prog_visitor.labs_dec, prog_visitor.labs_use);
        List<String> semanticerrors = check.checkAll();


        //if there is no semantic error print the outputs, otherwise print errors only
        if (graph_file!=null && semanticerrors.isEmpty()) {
            // object ie write to the dotty graph
            IntermediateRepresentation ie = new IntermediateRepresentation(prog.expressions, graph_file);
            // basic blocks generation
            List<List<Statement>> global_blocks = ie.blocks;
            List<List<Integer>> global_cfg = ie.cfg;
            // update blocks for unreachable blocks deletion
            if(unreachable_enable) {
                UnreachableExterminator ue = new UnreachableExterminator(global_cfg, global_blocks);
                global_blocks = ue.reachable_blocks;
                global_cfg = ue.new_cfg;
            }
            // update blocks for deadcode elimination
            if(deadcode_enable) {
                DeadCode dc = new DeadCode(global_blocks, global_cfg);
                global_blocks = dc.cleaned_blocks;
            }
            // update blocks for constant propagation
            if (constant_enable) {
                Propagation prop = new Propagation(global_blocks, global_cfg);
                prop.programLevelEnvReal();
                global_blocks = prop.blocks;
            }
            // compute the output for the program if interpreter is enabled
            if(interpreter_enable){
                ExpressionProcessor ep = new ExpressionProcessor(prog.expressions, prog.labels);
                for (int output : ep.outputs) {
                    System.out.println(output);
                }
            }
            // do compiler optimisation
            if(unreachable_enable || deadcode_enable || constant_enable){
                RebuildProgram rb = new RebuildProgram(global_blocks, ie.vars);
                ProgramWriter pw = new ProgramWriter(rb.prog, optimised_file);
            }
        } else {
            // else, print out each error at corresponding line
            for (String err : semanticerrors) {
                System.out.println(err);
            }
        }
    }
    // specific to the grammar name for Expr.g4
    private static ExprParser getParser(String fileName) throws IOException {
        CharStream input = CharStreams.fromFileName(fileName);
        ExprLexer lexer = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser parser = new ExprParser(tokens);
        return parser;
    }
}