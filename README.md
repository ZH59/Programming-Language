## Project µ

In this project, I built a parser, compiler and interpreter for an imperative language μ with Java ANTLR. I also implemented automatic program code optimization, such as removing unreachable code, constant propagation, and eliminating dead code.

## task 1 - build parser and semantic checker
To run task 1, type:
java Main tests/task1/[testfile]

where [testfile] is one of the following:
*test_double_jump*: test double definition of jump labels error catch
*test_double_var*: test double definition of variables error catch
*test_use_before_def*: test the use of variable before definition error catch
*test_cond_undef_label*: test the undefined conditional jump labels error catch
*test_input_var_DNE*: test the used variable in an expression without an input statement error catch
*test_normal_parse*: test a valid program parsing

For example:
java Main task1/test_double_jump

## task 2 - write an interpreter for µ
To run task 2, type:
java Main tests/task2/[testfile] --interpreter

where [testfile] is one of the following:
*test_simple*: a simple program of multiplication, will output 200
*test_input*: read two variable values from stdin, and do addition, will output the sum of the two variables
*test_jump*: 1. read one input value and store it in x
             2. goto a subroutine called dowork, which increases x until x>4
             3. goto another subroutine called label when x>4, which outputs x
             4. goto subroutine lexit which calculates i, and output it, then exit the program
*test_complex1*: 1. read one input value and store it in x
                 2. goto subroutine lhead, which increases i that depends on the increase of x, output the value of x
                 3. if i>100, which is what cond is, go to lexit, else, keep looping
                 4. goto subroutine lexit when done from lhead, output the final value of x, then exit the program
*test_complex2*: is the same situation but i is increased twice (from >100 to >10000) in 2 different subroutines, and depends on the value
of x and a, which are asked for input in the beginning 

For example:
java Main task2/test_simple

## task 3 - build the control flow graph
To run task 3, type:
java Main tests/task3/[testfile] tests/task3/[output_graph_file]
This will take the given mu program and produce a corresponding dotty file, which can be transformed into a pdf file like
the one shown on the assignment spec.
*test_input*, *test_jump*, *test_complex1* and *test_complex2* are defined in task2, *test_new* is the one from the assignment spec

For example:
java Main tests/task3/test_complex1 tests/task3/my_graph_complex1
cd tests/task3
dot -Tpdf my_graph_complex > output_graph.pdf

## task 4 - unreachable code
To run task 4, type:
java Main tests/task4/[testfile] tests/task4/[output_graph_file] tests/task4/[output_reachable_mu] --unreachable
This will take the given mu program, produce a corresponding dotty file, and regenerate an optimised mu program after
all unreachable basic blocks are removed.

[testfile] is one of the following:
*test_simple*: one block is unreachable
*test_lec*: the program shown on week 8 lecture slide, where the blocks containing L1 and L2 are unreachable
*test_reachable*: all basic blocks are already reachable, test if any unusual behaviour would happen
*test_complex*: two more unreachable blocks are added to *test_lec*
*test_sep_branch*: there are 2 separate unreachable branches of blocks that should be removed

For example:
java Main tests/task4/test_lec tests/task4/dotty_lec tests/task4/reachable_lec --unreachable

## task 5 - constant propagation and constant folding
To run task 5, type:
java Main tests/task5/[testfile] tests/task5/[output_graph_file] tests/task5/[output_progapated_mu] --constants
This will take the given mu program, produce a corresponding dotty file, and regenerate an optimised mu program after
all constant assignment statements are propagated throughout the program and substituted for the variable.

[testfile] is one of the following:
*test1.txt*: the variables x and i at the end should both be 2 after propagation
*test_simple*: simple test, the expected output is a=5, b=25, c=150, x=3000
*test_new*: there will be recursion so no propagation should be perfomred
*test_complex1*: there will be recursion so no propagation should be perfomred
*test_complex2*: the final output of x won't be propagated because more than 1 blocks can reach lexit

For example:
java Main tests/task5/test_simple tests/task5/my_graph_simple tests/task5/my_propa_simple --constants

## task 6 - deadcode elimination
To run task 6, type:
java Main tests/task6/[testfile] tests/task6/[output_dotty] tests/task6/[output_live_mu] --deadcode

[testfile] is one of the following:
*test_simple*: x is not used so the assignment of x is removed
*test_normal*: x and y are not used so the assignments of x and y are removed
*test_unreachable*: a, b and i are not used in an unreachable block so their assignments are removed
*test_complex1* and *test_complex2*: x and 

## task 7 - phase ordering
A good sequence of optimisation would be:
*--unreachable* -> *--constants* -> *--deadcode* -> *--unreachable* -> *--constants* -> *--deadcode* -> *...*
We first eliminate the unreachable blocks in the program since if a block is unreachable from the start then there
is no need to propagate and eliminate constant assignments on that block. Then we propagate the constant assignments
through the program since deadcode elimination requires checking if the computation is used at a later point when
executing. Then we do deadcode elimination to delete all unused assignments.

To run task 7, type:
java Main tests/task7/[testfile] tests/task7/[output_dotty] tests/task6/[output_optimised_mu] *--unreachable --constants --deadcode ...*
*test_unreach_propa*: the block with label unknown can be removed and the value of x can be propagated
*test_unreach_dead*: the block with label unknown can be removed and the assignment of a can be eliminated
*test_propa_dead*: x and a will be propagated and the assignment i will be eliminated
*test_complex1*: the combination of the above 2 tests
*test_complex2*: combines the 3 optimiser, the block starting with label random should be removed, the constant values of x,y and z
will be propagated and the assignment of i will be eliminated.
