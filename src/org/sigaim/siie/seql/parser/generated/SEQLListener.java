// Generated from SEQL.g4 by ANTLR 4.2
package org.sigaim.siie.seql.parser.generated;

import org.sigaim.siie.seql.parser.model.SEQLFromComponent;
import org.sigaim.siie.seql.parser.model.SEQLOperation;
import org.sigaim.siie.seql.parser.model.SEQLEvaluable;
import org.sigaim.siie.seql.parser.model.SEQLPrimitive;
import org.sigaim.siie.seql.parser.model.SEQLPath;
import org.sigaim.siie.seql.parser.model.SEQLPathComponent;
import org.sigaim.siie.seql.parser.model.SEQLPathPredicate;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SEQLParser}.
 */
public interface SEQLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SEQLParser#pathPart}.
	 * @param ctx the parse tree
	 */
	void enterPathPart(@NotNull SEQLParser.PathPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#pathPart}.
	 * @param ctx the parse tree
	 */
	void exitPathPart(@NotNull SEQLParser.PathPartContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#boolOp}.
	 * @param ctx the parse tree
	 */
	void enterBoolOp(@NotNull SEQLParser.BoolOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#boolOp}.
	 * @param ctx the parse tree
	 */
	void exitBoolOp(@NotNull SEQLParser.BoolOpContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#asIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterAsIdentifier(@NotNull SEQLParser.AsIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#asIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitAsIdentifier(@NotNull SEQLParser.AsIdentifierContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(@NotNull SEQLParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(@NotNull SEQLParser.QueryContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#containsExpr}.
	 * @param ctx the parse tree
	 */
	void enterContainsExpr(@NotNull SEQLParser.ContainsExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#containsExpr}.
	 * @param ctx the parse tree
	 */
	void exitContainsExpr(@NotNull SEQLParser.ContainsExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#contains}.
	 * @param ctx the parse tree
	 */
	void enterContains(@NotNull SEQLParser.ContainsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#contains}.
	 * @param ctx the parse tree
	 */
	void exitContains(@NotNull SEQLParser.ContainsContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#from}.
	 * @param ctx the parse tree
	 */
	void enterFrom(@NotNull SEQLParser.FromContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#from}.
	 * @param ctx the parse tree
	 */
	void exitFrom(@NotNull SEQLParser.FromContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#where}.
	 * @param ctx the parse tree
	 */
	void enterWhere(@NotNull SEQLParser.WhereContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#where}.
	 * @param ctx the parse tree
	 */
	void exitWhere(@NotNull SEQLParser.WhereContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#identifiedOperand}.
	 * @param ctx the parse tree
	 */
	void enterIdentifiedOperand(@NotNull SEQLParser.IdentifiedOperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#identifiedOperand}.
	 * @param ctx the parse tree
	 */
	void exitIdentifiedOperand(@NotNull SEQLParser.IdentifiedOperandContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#selectVar}.
	 * @param ctx the parse tree
	 */
	void enterSelectVar(@NotNull SEQLParser.SelectVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#selectVar}.
	 * @param ctx the parse tree
	 */
	void exitSelectVar(@NotNull SEQLParser.SelectVarContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#nodePredicateOr}.
	 * @param ctx the parse tree
	 */
	void enterNodePredicateOr(@NotNull SEQLParser.NodePredicateOrContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#nodePredicateOr}.
	 * @param ctx the parse tree
	 */
	void exitNodePredicateOr(@NotNull SEQLParser.NodePredicateOrContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#archetypedClassExpr}.
	 * @param ctx the parse tree
	 */
	void enterArchetypedClassExpr(@NotNull SEQLParser.ArchetypedClassExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#archetypedClassExpr}.
	 * @param ctx the parse tree
	 */
	void exitArchetypedClassExpr(@NotNull SEQLParser.ArchetypedClassExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#identifiedExprAnd}.
	 * @param ctx the parse tree
	 */
	void enterIdentifiedExprAnd(@NotNull SEQLParser.IdentifiedExprAndContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#identifiedExprAnd}.
	 * @param ctx the parse tree
	 */
	void exitIdentifiedExprAnd(@NotNull SEQLParser.IdentifiedExprAndContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#containExpressionBool}.
	 * @param ctx the parse tree
	 */
	void enterContainExpressionBool(@NotNull SEQLParser.ContainExpressionBoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#containExpressionBool}.
	 * @param ctx the parse tree
	 */
	void exitContainExpressionBool(@NotNull SEQLParser.ContainExpressionBoolContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#nodePredicateAnd}.
	 * @param ctx the parse tree
	 */
	void enterNodePredicateAnd(@NotNull SEQLParser.NodePredicateAndContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#nodePredicateAnd}.
	 * @param ctx the parse tree
	 */
	void exitNodePredicateAnd(@NotNull SEQLParser.NodePredicateAndContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#nodePredicateComparable}.
	 * @param ctx the parse tree
	 */
	void enterNodePredicateComparable(@NotNull SEQLParser.NodePredicateComparableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#nodePredicateComparable}.
	 * @param ctx the parse tree
	 */
	void exitNodePredicateComparable(@NotNull SEQLParser.NodePredicateComparableContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#simpleClassExpr}.
	 * @param ctx the parse tree
	 */
	void enterSimpleClassExpr(@NotNull SEQLParser.SimpleClassExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#simpleClassExpr}.
	 * @param ctx the parse tree
	 */
	void exitSimpleClassExpr(@NotNull SEQLParser.SimpleClassExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#identifiedPathSeq}.
	 * @param ctx the parse tree
	 */
	void enterIdentifiedPathSeq(@NotNull SEQLParser.IdentifiedPathSeqContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#identifiedPathSeq}.
	 * @param ctx the parse tree
	 */
	void exitIdentifiedPathSeq(@NotNull SEQLParser.IdentifiedPathSeqContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#top}.
	 * @param ctx the parse tree
	 */
	void enterTop(@NotNull SEQLParser.TopContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#top}.
	 * @param ctx the parse tree
	 */
	void exitTop(@NotNull SEQLParser.TopContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#selectExpr}.
	 * @param ctx the parse tree
	 */
	void enterSelectExpr(@NotNull SEQLParser.SelectExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#selectExpr}.
	 * @param ctx the parse tree
	 */
	void exitSelectExpr(@NotNull SEQLParser.SelectExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#select}.
	 * @param ctx the parse tree
	 */
	void enterSelect(@NotNull SEQLParser.SelectContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#select}.
	 * @param ctx the parse tree
	 */
	void exitSelect(@NotNull SEQLParser.SelectContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#objectPath}.
	 * @param ctx the parse tree
	 */
	void enterObjectPath(@NotNull SEQLParser.ObjectPathContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#objectPath}.
	 * @param ctx the parse tree
	 */
	void exitObjectPath(@NotNull SEQLParser.ObjectPathContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#operand}.
	 * @param ctx the parse tree
	 */
	void enterOperand(@NotNull SEQLParser.OperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#operand}.
	 * @param ctx the parse tree
	 */
	void exitOperand(@NotNull SEQLParser.OperandContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#identifiedExpr}.
	 * @param ctx the parse tree
	 */
	void enterIdentifiedExpr(@NotNull SEQLParser.IdentifiedExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#identifiedExpr}.
	 * @param ctx the parse tree
	 */
	void exitIdentifiedExpr(@NotNull SEQLParser.IdentifiedExprContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#nodePredicate}.
	 * @param ctx the parse tree
	 */
	void enterNodePredicate(@NotNull SEQLParser.NodePredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#nodePredicate}.
	 * @param ctx the parse tree
	 */
	void exitNodePredicate(@NotNull SEQLParser.NodePredicateContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#identifiedEquality}.
	 * @param ctx the parse tree
	 */
	void enterIdentifiedEquality(@NotNull SEQLParser.IdentifiedEqualityContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#identifiedEquality}.
	 * @param ctx the parse tree
	 */
	void exitIdentifiedEquality(@NotNull SEQLParser.IdentifiedEqualityContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#predicateOperand}.
	 * @param ctx the parse tree
	 */
	void enterPredicateOperand(@NotNull SEQLParser.PredicateOperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#predicateOperand}.
	 * @param ctx the parse tree
	 */
	void exitPredicateOperand(@NotNull SEQLParser.PredicateOperandContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#archetypePredicate}.
	 * @param ctx the parse tree
	 */
	void enterArchetypePredicate(@NotNull SEQLParser.ArchetypePredicateContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#archetypePredicate}.
	 * @param ctx the parse tree
	 */
	void exitArchetypePredicate(@NotNull SEQLParser.ArchetypePredicateContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#orOp}.
	 * @param ctx the parse tree
	 */
	void enterOrOp(@NotNull SEQLParser.OrOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#orOp}.
	 * @param ctx the parse tree
	 */
	void exitOrOp(@NotNull SEQLParser.OrOpContext ctx);

	/**
	 * Enter a parse tree produced by {@link SEQLParser#identifiedPath}.
	 * @param ctx the parse tree
	 */
	void enterIdentifiedPath(@NotNull SEQLParser.IdentifiedPathContext ctx);
	/**
	 * Exit a parse tree produced by {@link SEQLParser#identifiedPath}.
	 * @param ctx the parse tree
	 */
	void exitIdentifiedPath(@NotNull SEQLParser.IdentifiedPathContext ctx);
}