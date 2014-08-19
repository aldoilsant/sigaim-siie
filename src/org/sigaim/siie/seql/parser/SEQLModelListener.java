package org.sigaim.siie.seql.parser;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.sigaim.siie.seql.model.SEQLFromComponent;
import org.sigaim.siie.seql.model.SEQLOperation;
import org.sigaim.siie.seql.model.SEQLPath;
import org.sigaim.siie.seql.model.SEQLPathComponent;
import org.sigaim.siie.seql.model.SEQLPathPredicate;
import org.sigaim.siie.seql.model.SEQLPrimitive;
import org.sigaim.siie.seql.model.SEQLQuery;
import org.sigaim.siie.seql.model.SEQLOperation.SEQLBooleanOperator;
import org.sigaim.siie.seql.parser.generated.SEQLBaseListener;
import org.sigaim.siie.seql.parser.generated.SEQLParser;
import org.sigaim.siie.seql.parser.generated.SEQLParser.ArchetypedClassExprContext;
import org.sigaim.siie.seql.parser.generated.SEQLParser.AsIdentifierContext;
import org.sigaim.siie.seql.parser.generated.SEQLParser.IdentifiedPathSeqContext;
import org.sigaim.siie.seql.parser.generated.SEQLParser.SelectVarContext;
import org.sigaim.siie.seql.parser.generated.SEQLParser.WithDescendantsContext;

public class SEQLModelListener extends SEQLBaseListener {
	private SEQLQuery query=null;
	private SEQLFromComponent lastFromComponent=null;
	
	public SEQLModelListener() {
		query =new SEQLQuery();
	}
	@Override public void exitSelect(@NotNull SEQLParser.SelectContext ctx) {
		if(ctx.MERGED()!=null && ctx.MERGED().getText()!=null) {
			query.setMerged(true);
		} else {
			query.setMerged(false);
		}
	}

	@Override public void exitSelectExpr(@NotNull SEQLParser.SelectExprContext ctx) { 
		//Retrieve the identified path sequence and build the query
		IdentifiedPathSeqContext sctx=ctx.identifiedPathSeq();
		List<SelectVarContext> lsvc=sctx.selectVar();
		for(SelectVarContext vctx : lsvc) {
			String name=null;
			String path=null;
			Boolean withDescendants=false;
			//Retrieve the path and possibly the name. 
			AsIdentifierContext namectx=vctx.asIdentifier();
			if(namectx!=null) {
				name=namectx.IDENTIFIER().getText();
			}
			WithDescendantsContext descendantsctx=vctx.withDescendants();
			if(descendantsctx!= null && !descendantsctx.isEmpty()){
				withDescendants=true;
			}
			path=vctx.identifiedPath().getText();
			query.addSelectCondition(path, name,withDescendants);
		}
	}
 
	@Override public void exitFrom(@NotNull SEQLParser.FromContext ctx) {
		//create EHR entry
		String topIdentifiedVariable=null;
		if(ctx.IDENTIFIER()!=null && ctx.IDENTIFIER().getText()!=null) {
			topIdentifiedVariable=ctx.IDENTIFIER().getText();
		}
		SEQLFromComponent ehrComponent=null;
		if(ctx.SYSTEM()!=null && ctx.SYSTEM().getText()!=null) {
			ehrComponent=query.getFromCondition().createFromComponent("ehr_system",topIdentifiedVariable,null,false);
		} else {
			ehrComponent=query.getFromCondition().createFromComponent("ehr_extract",topIdentifiedVariable,null,false);
		}
		SEQLOperation op=new SEQLOperation();
		op.setOperator(SEQLBooleanOperator.CONTAINS);
		op.setLeftOperand(ehrComponent);
		if(ctx.containsExpr()!=null) {
			op.setRightOperand(ctx.containsExpr().operation);
		}
		query.getFromCondition().setRoot(op);
	}
 
	private SEQLFromComponent fromComponentFromContext(@NotNull SEQLParser.SimpleClassExprContext ctx) {
		String referenceModelClass;
		String identifiedVariable=null;
		String archetypeId=null;
		List<TerminalNode> identifierNodes=null;
		Boolean useAllVersions=false;

		if(ctx.archetypedClassExpr()!=null) {
			ArchetypedClassExprContext actx=ctx.archetypedClassExpr();
			identifierNodes=actx.IDENTIFIER();
			archetypeId=actx.archetypePredicate().ARCHETYPEID().getText();
			if(actx.allVersions()!=null && ! actx.allVersions().isEmpty()) {
				useAllVersions=true;
			}
			
		} else { //Not archetyped
			identifierNodes=ctx.IDENTIFIER();
		}
		referenceModelClass=identifierNodes.get(0).getText();
		identifiedVariable=null;
		if(identifierNodes.size()==2) { //Identified variable
			identifiedVariable=identifierNodes.get(1).getText();
		}
		if(ctx.allVersions()!=null && ! ctx.allVersions().isEmpty()) {
			useAllVersions=true;
		}
		return query.getFromCondition().createFromComponent(referenceModelClass, identifiedVariable, archetypeId,useAllVersions);
		
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
		ctx.path=new SEQLPath(ctx.IDENTIFIER().getText());
		if(ctx.nodePredicate()!=null) {
			ctx.path.getFirstPathComponent().setPathPredicate(ctx.nodePredicate().pathPredicate);
		}
		if(ctx.objectPath()!=null) {
			for(SEQLPathComponent component : ctx.objectPath().path.getPathComponents()) {
				ctx.path.addPathComponent(component);
			}
		}
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
	protected SEQLBooleanOperator booleanOperatorFromString(String operator) {
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
		return eop;
	}
	@Override public void exitIdentifiedEquality(@NotNull SEQLParser.IdentifiedEqualityContext ctx) { 
		if(ctx.COMPARABLEOPERATOR()!=null) {
			SEQLOperation op=new SEQLOperation();
			String operator=ctx.COMPARABLEOPERATOR().getText();
			SEQLBooleanOperator eop=SEQLBooleanOperator.EQUALITY;
			eop=this.booleanOperatorFromString(operator);
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
	@Override public void exitNodePredicate(@NotNull SEQLParser.NodePredicateContext ctx) {
		if(ctx.nodePredicateOr().evaluable instanceof SEQLPathPredicate) {
			ctx.pathPredicate=(SEQLPathPredicate)ctx.nodePredicateOr().evaluable;
		} else {
			ctx.pathPredicate=new SEQLPathPredicate((SEQLOperation)ctx.nodePredicateOr().evaluable);
		}
	}
	@Override public void exitNodePredicateOr(@NotNull SEQLParser.NodePredicateOrContext ctx) {
		if(ctx.OR().size()>0) {
			SEQLOperation previousOp=null;
			for(int i=0;i<ctx.OR().size();i++) {
				SEQLOperation op=new SEQLOperation();
				op.setOperator(SEQLBooleanOperator.AND);
				if(previousOp==null) {
					op.setLeftOperand(ctx.nodePredicateAnd().get(i).evaluable);
					op.setRightOperand(ctx.nodePredicateAnd().get(i+1).evaluable);	
					previousOp=op;
				} else {
					op.setLeftOperand(previousOp);
					op.setRightOperand(ctx.nodePredicateAnd().get(i+1).evaluable);
					previousOp=op;
				}
			}
			ctx.evaluable=previousOp;
		} else {
			ctx.evaluable=ctx.nodePredicateAnd().get(0).evaluable;
		}
	}
	@Override public void exitNodePredicateAnd(@NotNull SEQLParser.NodePredicateAndContext ctx) {
		if(ctx.AND().size()>0) {
			SEQLOperation previousOp=null;
			for(int i=0;i<ctx.AND().size();i++) {
				SEQLOperation op=new SEQLOperation();
				op.setOperator(SEQLBooleanOperator.AND);
				if(previousOp==null) {
					op.setLeftOperand(ctx.nodePredicateComparable().get(i).evaluable);
					op.setRightOperand(ctx.nodePredicateComparable().get(i+1).evaluable);	
					previousOp=op;
				} else {
					op.setLeftOperand(previousOp);
					op.setRightOperand(ctx.nodePredicateComparable().get(i+1).evaluable);
					previousOp=op;
				}
			}
			ctx.evaluable=previousOp;
		} else {
			ctx.evaluable=ctx.nodePredicateComparable().get(0).evaluable;
		}
	}
	@Override public void exitNodePredicateComparable(@NotNull SEQLParser.NodePredicateComparableContext ctx) {
		if(ctx.NODEID()!=null && ctx.NODEID().getText()!=null) {
			SEQLPathPredicate predicate;
			if(ctx.STRING()!=null && ctx.STRING().getText()!=null) {
				predicate= new SEQLPathPredicate(ctx.NODEID().getText(),ctx.STRING().getText());
			} else {
				predicate= new SEQLPathPredicate(ctx.NODEID().getText(),null);
			}
			ctx.evaluable=predicate;
		} else if(ctx.ARCHETYPEID()!=null && ctx.ARCHETYPEID().getText()!=null) {
			SEQLPathPredicate predicate;
			if(ctx.STRING()!=null && ctx.STRING().getText()!=null) {
				predicate= new SEQLPathPredicate(ctx.ARCHETYPEID().getText(),ctx.STRING().getText());
			} else {
				predicate= new SEQLPathPredicate(ctx.ARCHETYPEID().getText(),null);
			}
			ctx.evaluable=predicate;
		} else if (ctx.STRING()!=null && ctx.STRING().getText()!=null){
			ctx.evaluable=new SEQLPathPredicate(null,ctx.STRING().getText());
		} else { 
			SEQLOperation operation= new SEQLOperation();
			operation.setOperator(this.booleanOperatorFromString(ctx.COMPARABLEOPERATOR().getText()));
			operation.setLeftOperand(ctx.predicateOperand().get(0).evaluable);
			operation.setLeftOperand(ctx.predicateOperand().get(1).evaluable);
			ctx.evaluable=operation;
		}
	}
	@Override public void exitPredicateOperand(@NotNull SEQLParser.PredicateOperandContext ctx) {
		if(ctx.operand()!=null) {
			ctx.evaluable=ctx.operand().primitive;
		} else {
			ctx.evaluable=ctx.objectPath().path;
		}
	}
	public SEQLQuery getQuery() {
		return query;
	}
	@Override public void exitObjectPath(@NotNull SEQLParser.ObjectPathContext ctx) {  
		SEQLPath path=new SEQLPath((String)null);
		for(SEQLParser.PathPartContext pctx: ctx.pathPart()) {
			path.addPathComponent(pctx.pathComponent);
		}
		ctx.path=path;
		
	}
	@Override public void exitPathPart(@NotNull SEQLParser.PathPartContext ctx) {
		SEQLPathComponent component=new SEQLPathComponent(ctx.IDENTIFIER().getText());
		if(ctx.nodePredicate()!=null) {
			component.setPathPredicate(ctx.nodePredicate().pathPredicate);
		}
		ctx.pathComponent=component;
	}
}
