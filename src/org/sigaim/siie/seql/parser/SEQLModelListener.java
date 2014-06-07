package org.sigaim.siie.seql.parser;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.sigaim.siie.seql.parser.generated.SEQLBaseListener;
import org.sigaim.siie.seql.parser.generated.SEQLParser;
import org.sigaim.siie.seql.parser.generated.SEQLParser.ArchetypedClassExprContext;
import org.sigaim.siie.seql.parser.generated.SEQLParser.AsIdentifierContext;
import org.sigaim.siie.seql.parser.generated.SEQLParser.ContainExpressionBoolContext;
import org.sigaim.siie.seql.parser.generated.SEQLParser.ContainsExprContext;
import org.sigaim.siie.seql.parser.generated.SEQLParser.IdentifiedPathSeqContext;
import org.sigaim.siie.seql.parser.generated.SEQLParser.SelectVarContext;
import org.sigaim.siie.seql.parser.model.SEQLOperation;
import org.sigaim.siie.seql.parser.model.SEQLOperation.SEQLBooleanOperator;
import org.sigaim.siie.seql.parser.model.SEQLEvaluable;
import org.sigaim.siie.seql.parser.model.SEQLFromCondition;
import org.sigaim.siie.seql.parser.model.SEQLFromCondition.SEQLFromComponent;
import org.sigaim.siie.seql.parser.model.SEQLPath;
import org.sigaim.siie.seql.parser.model.SEQLPrimitive;
import org.sigaim.siie.seql.parser.model.SEQLQuery;

public class SEQLModelListener extends SEQLBaseListener {
	private SEQLQuery query=null;
	private SEQLFromComponent lastFromComponent=null;
	
	public SEQLModelListener() {
		query =new SEQLQuery();
	}
	@Override public void exitSelectExpr(@NotNull SEQLParser.SelectExprContext ctx) { 
		//Retrieve the identified path sequence and build the query
		IdentifiedPathSeqContext sctx=ctx.identifiedPathSeq();
		List<SelectVarContext> lsvc=sctx.selectVar();
		for(SelectVarContext vctx : lsvc) {
			String name=null;
			String path=null;
			//Retrieve the path and possibly the name. 
			AsIdentifierContext namectx=vctx.asIdentifier();
			if(namectx!=null) {
				name=namectx.IDENTIFIER().getText();
			}
			path=vctx.identifiedPath().getText();
			query.addSelectCondition(path, name);
		}
	}
 
	@Override public void exitFrom(@NotNull SEQLParser.FromContext ctx) {
		//create EHR entry
		SEQLFromComponent ehrComponent=query.getFromCondition().createFromComponent("ehr_extract",null,null);
		SEQLOperation op=new SEQLOperation();
		op.setOperator(SEQLBooleanOperator.CONTAINS);
		op.setLeftOperand(ehrComponent);
		op.setRightOperand(ctx.containsExpr().operation);
		query.getFromCondition().setRoot(op);
	}
 
	private SEQLFromComponent fromComponentFromContext(@NotNull SEQLParser.SimpleClassExprContext ctx) {
		String referenceModelClass;
		String identifiedVariable=null;
		String archetypeId=null;
		List<TerminalNode> identifierNodes=null;
		
		if(ctx.archetypedClassExpr()!=null) {
			ArchetypedClassExprContext actx=ctx.archetypedClassExpr();
			identifierNodes=actx.IDENTIFIER();
			archetypeId=actx.archetypePredicate().ARCHETYPEID().getText();
			
		} else { //Not archetyped
			identifierNodes=ctx.IDENTIFIER();
		}
		referenceModelClass=identifierNodes.get(0).getText();
		identifiedVariable=null;
		if(identifierNodes.size()==2) { //Identified variable
			identifiedVariable=identifierNodes.get(1).getText();
		}
		return query.getFromCondition().createFromComponent(referenceModelClass, identifiedVariable, archetypeId);
		
	}
	@Override public void exitContainExpressionBool(@NotNull SEQLParser.ContainExpressionBoolContext ctx) {
		if(ctx.contains()!=null) {
			ctx.operation=ctx.contains().containsOperation;
		} else {
			ctx.operation=ctx.containsExpr().operation;
		}
	}
	@Override public void exitContainsExpr(@NotNull SEQLParser.ContainsExprContext ctx) { 
		if(ctx.boolOp()!=null) { //Boolean operation
			//Build a boolean operation
			SEQLOperation op=new SEQLOperation();
			op.setOperator(SEQLBooleanOperator.valueOf(SEQLBooleanOperator.class, ctx.boolOp().getText()));
			op.setLeftOperand(ctx.containExpressionBool().operation);
			op.setRightOperand(ctx.containsExpr().operation);
			ctx.operation=op;
		} else {
			//Just propagate the contains operation
			ctx.operation=ctx.containExpressionBool().operation;
		}
	}
	@Override public void exitContains(@NotNull SEQLParser.ContainsContext ctx) {
		SEQLOperation op=new SEQLOperation();
		op.setOperator(SEQLBooleanOperator.CONTAINS);
		op.setLeftOperand(ctx.simpleClassExpr().component);
		
		if(ctx.containsExpr()!=null) { //Nested contains expression
			op.setRightOperand(ctx.containsExpr().operation);
		}
		ctx.containsOperation=op;
	}
	@Override public void exitSimpleClassExpr(@NotNull SEQLParser.SimpleClassExprContext ctx) { 
		ctx.component=this.fromComponentFromContext(ctx);
	}
	@Override public void exitIdentifiedPath(@NotNull SEQLParser.IdentifiedPathContext ctx) { 
		ctx.path=new SEQLPath(ctx.getText());
	}
	@Override public void exitOperand(@NotNull SEQLParser.OperandContext ctx) { 
		ctx.primitive=new SEQLPrimitive(ctx.getText());
	}
	@Override public void exitIdentifiedOperand(@NotNull SEQLParser.IdentifiedOperandContext ctx) { 
		if(ctx.operand()!=null) {
			ctx.evaluable=ctx.operand().primitive;
		} else {
			ctx.evaluable=ctx.identifiedPath().path;
		}
	}
	@Override public void exitIdentifiedEquality(@NotNull SEQLParser.IdentifiedEqualityContext ctx) { 
		if(ctx.COMPARABLEOPERATOR()!=null) {
			SEQLOperation op=new SEQLOperation();
			String operator=ctx.COMPARABLEOPERATOR().getText();
			SEQLBooleanOperator eop=SEQLBooleanOperator.EQUALITY;
			if(operator.equals("=")) {
				eop=SEQLBooleanOperator.EQUALITY;
			} else if(operator.equals("!=")) {
				eop=SEQLBooleanOperator.INEQUALITY;
			} else if (operator.equals(">")) {
				eop=SEQLBooleanOperator.GT;
			}  else if (operator.equals(">=")) {
				eop=SEQLBooleanOperator.GE;
			} else if (operator.equals("<")) {
				eop=SEQLBooleanOperator.LT;
			} else {
				eop=SEQLBooleanOperator.LE;
			}
			op.setOperator(eop);
			op.setLeftOperand(ctx.identifiedOperand().get(0).evaluable);
			op.setRightOperand(ctx.identifiedOperand().get(1).evaluable);
			ctx.operation=op;
		} else if (ctx.EXISTS()!=null) {
			SEQLOperation op=new SEQLOperation();
			op.setOperator(SEQLBooleanOperator.EXISTS);
			op.setLeftOperand(ctx.identifiedPath().path);
			ctx.operation=op;
		} else if(ctx.NOT()!=null) {
			SEQLOperation op=new SEQLOperation();
			op.setOperator(SEQLBooleanOperator.NOT);
			op.setLeftOperand(ctx.identifiedEquality().operation);
			ctx.operation=op;
		} else {
			//Parenthesis
			ctx.operation=ctx.identifiedExpr().operation;
		}
	}
	@Override public void exitIdentifiedExprAnd(@NotNull SEQLParser.IdentifiedExprAndContext ctx) {  
		if(ctx.AND().size()>0) {
			SEQLOperation previousOp=null;
			for(int i=0;i<ctx.AND().size();i++) {
				SEQLOperation op=new SEQLOperation();
				op.setOperator(SEQLBooleanOperator.AND);
				if(previousOp==null) {
					op.setLeftOperand(ctx.identifiedEquality().get(i).operation);
					op.setRightOperand(ctx.identifiedEquality().get(i+1).operation);	
					previousOp=op;
				} else {
					op.setLeftOperand(previousOp);
					op.setRightOperand(ctx.identifiedEquality().get(i+1).operation);
					previousOp=op;
				}
			}
			ctx.operation=previousOp;
		} else {
			ctx.operation=ctx.identifiedEquality().get(0).operation;
		}
	}
	@Override public void exitIdentifiedExpr(@NotNull SEQLParser.IdentifiedExprContext ctx) {  
		if(ctx.orOp().size()>0 ){
			SEQLOperation previousOp=null;
			for(int i=0;i<ctx.orOp().size();i++) {
				SEQLOperation op=new SEQLOperation();
				if(ctx.orOp().get(i).getText().equals("OR")) {
					op.setOperator(SEQLBooleanOperator.OR);					
				} else {
					op.setOperator(SEQLBooleanOperator.XOR);					
				}
				if(previousOp==null) {
					op.setLeftOperand(ctx.identifiedExprAnd().get(i).operation);
					op.setRightOperand(ctx.identifiedExprAnd().get(i+1).operation);	
					previousOp=op;
				} else {
					op.setLeftOperand(previousOp);
					op.setRightOperand(ctx.identifiedExprAnd().get(i+1).operation);
					previousOp=op;
				}
			}
			ctx.operation=previousOp;
		} else {
			ctx.operation=ctx.identifiedExprAnd().get(0).operation;
		}
	}
	@Override public void exitWhere(@NotNull SEQLParser.WhereContext ctx) { 
		query.getWhereCondition().setRoot(ctx.identifiedExpr().operation);
	}
	public SEQLQuery getQuery() {
		return query;
	}
}
