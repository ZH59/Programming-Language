// Generated from Expr.g4 by ANTLR 4.8

	package antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExprParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExprVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code Program}
	 * labeled alternative in {@link ExprParser#p}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(ExprParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Declaration}
	 * labeled alternative in {@link ExprParser#d}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(ExprParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Identlabel}
	 * labeled alternative in {@link ExprParser#s}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentlabel(ExprParser.IdentlabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Identvariable}
	 * labeled alternative in {@link ExprParser#s}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentvariable(ExprParser.IdentvariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Input}
	 * labeled alternative in {@link ExprParser#s}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInput(ExprParser.InputContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Output}
	 * labeled alternative in {@link ExprParser#s}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutput(ExprParser.OutputContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Goto}
	 * labeled alternative in {@link ExprParser#s}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoto(ExprParser.GotoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Ifgoto}
	 * labeled alternative in {@link ExprParser#s}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfgoto(ExprParser.IfgotoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Eless}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEless(ExprParser.ElessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Enegative}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnegative(ExprParser.EnegativeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Ident}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdent(ExprParser.IdentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Eminus}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEminus(ExprParser.EminusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Ebrackets}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEbrackets(ExprParser.EbracketsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Num}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNum(ExprParser.NumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Eadd}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEadd(ExprParser.EaddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Egreater}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEgreater(ExprParser.EgreaterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Eequal}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEequal(ExprParser.EequalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Emultiple}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmultiple(ExprParser.EmultipleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Edivide}
	 * labeled alternative in {@link ExprParser#e}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEdivide(ExprParser.EdivideContext ctx);
}