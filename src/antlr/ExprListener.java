// Generated from Expr.g4 by ANTLR 4.8

	package antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExprParser}.
 */
public interface ExprListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code Program}
	 * labeled alternative in {@link ExprParser#p}.
	 * @param ctx the parse tree
	 */
	void enterProgram(ExprParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Program}
	 * labeled alternative in {@link ExprParser#p}.
	 * @param ctx the parse tree
	 */
	void exitProgram(ExprParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Declaration}
	 * labeled alternative in {@link ExprParser#d}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(ExprParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Declaration}
	 * labeled alternative in {@link ExprParser#d}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(ExprParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Identlabel}
	 * labeled alternative in {@link ExprParser#s}.
	 * @param ctx the parse tree
	 */
	void enterIdentlabel(ExprParser.IdentlabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Identlabel}
	 * labeled alternative in {@link ExprParser#s}.
	 * @param ctx the parse tree
	 */
	void exitIdentlabel(ExprParser.IdentlabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Identvariable}
	 * labeled alternative in {@link ExprParser#s}.
	 * @param ctx the parse tree
	 */
	void enterIdentvariable(ExprParser.IdentvariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Identvariable}
	 * labeled alternative in {@link ExprParser#s}.
	 * @param ctx the parse tree
	 */
	void exitIdentvariable(ExprParser.IdentvariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Input}
	 * labeled alternative in {@link ExprParser#s}.
	 * @param ctx the parse tree
	 */
	void enterInput(ExprParser.InputContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Input}
	 * labeled alternative in {@link ExprParser#s}.
	 * @param ctx the parse tree
	 */
	void exitInput(ExprParser.InputContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Output}
	 * labeled alternative in {@link ExprParser#s}.
	 * @param ctx the parse tree
	 */
	void enterOutput(ExprParser.OutputContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Output}
	 * labeled alternative in {@link ExprParser#s}.
	 * @param ctx the parse tree
	 */
	void exitOutput(ExprParser.OutputContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Goto}
	 * labeled alternative in {@link ExprParser#s}.
	 * @param ctx the parse tree
	 */
	void enterGoto(ExprParser.GotoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Goto}
	 * labeled alternative in {@link ExprParser#s}.
	 * @param ctx the parse tree
	 */
	void exitGoto(ExprParser.GotoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Ifgoto}
	 * labeled alternative in {@link ExprParser#s}.
	 * @param ctx the parse tree
	 */
	void enterIfgoto(ExprParser.IfgotoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Ifgoto}
	 * labeled alternative in {@link ExprParser#s}.
	 * @param ctx the parse tree
	 */
	void exitIfgoto(ExprParser.IfgotoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Eless}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void enterEless(ExprParser.ElessContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Eless}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void exitEless(ExprParser.ElessContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Enegative}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void enterEnegative(ExprParser.EnegativeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Enegative}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void exitEnegative(ExprParser.EnegativeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Ident}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void enterIdent(ExprParser.IdentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Ident}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void exitIdent(ExprParser.IdentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Eminus}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void enterEminus(ExprParser.EminusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Eminus}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void exitEminus(ExprParser.EminusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Ebrackets}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void enterEbrackets(ExprParser.EbracketsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Ebrackets}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void exitEbrackets(ExprParser.EbracketsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Num}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void enterNum(ExprParser.NumContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Num}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void exitNum(ExprParser.NumContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Eadd}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void enterEadd(ExprParser.EaddContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Eadd}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void exitEadd(ExprParser.EaddContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Egreater}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void enterEgreater(ExprParser.EgreaterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Egreater}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void exitEgreater(ExprParser.EgreaterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Eequal}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void enterEequal(ExprParser.EequalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Eequal}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void exitEequal(ExprParser.EequalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Emultiple}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void enterEmultiple(ExprParser.EmultipleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Emultiple}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void exitEmultiple(ExprParser.EmultipleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Edivide}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void enterEdivide(ExprParser.EdivideContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Edivide}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 */
	void exitEdivide(ExprParser.EdivideContext ctx);
}