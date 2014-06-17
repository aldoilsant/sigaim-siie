package org.sigaim.siie.seql.engine.execution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openehr.am.parser.AttributeValue;
import org.openehr.am.parser.ComplexObjectBlock;
import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.KeyedObject;
import org.openehr.am.parser.MultipleAttributeObjectBlock;
import org.openehr.am.parser.ObjectBlock;
import org.openehr.am.parser.PrimitiveObjectBlock;
import org.openehr.am.parser.SimpleValue;
import org.openehr.am.parser.SingleAttributeObjectBlock;
import org.openehr.am.parser.StringValue;
import org.sigaim.siie.db.PersistenceManager;
import org.sigaim.siie.db.ReferenceModelObjectId;
import org.sigaim.siie.db.exceptions.PersistenceException;
import org.sigaim.siie.rm.ReferenceModelManager;
import org.sigaim.siie.seql.engine.SEQLQueryExecutionStage;
import org.sigaim.siie.seql.engine.exceptions.SEQLException;
import org.sigaim.siie.seql.parser.model.SEQLEvaluable;
import org.sigaim.siie.seql.parser.model.SEQLFromComponent;
import org.sigaim.siie.seql.parser.model.SEQLOperation;
import org.sigaim.siie.seql.parser.model.SEQLOperation.SEQLBooleanOperator;
import org.sigaim.siie.seql.parser.model.SEQLPath;
import org.sigaim.siie.seql.parser.model.SEQLPathComponent;
import org.sigaim.siie.seql.parser.model.SEQLPrimitive;
import org.sigaim.siie.seql.parser.model.SEQLQuery;
import org.sigaim.siie.seql.parser.model.SEQLResultSet;
import org.sigaim.siie.seql.parser.model.SEQLSelectCondition;



public class SEQLExecutionMemorySolverStage implements SEQLQueryExecutionStage {
	private PersistenceManager pmngr;
	private ReferenceModelManager rmngr;
	public SEQLExecutionMemorySolverStage(PersistenceManager pmngr, ReferenceModelManager rmngr) {
		this.pmngr=pmngr;
		this.rmngr=rmngr;
	}
	protected void populateFromComponents(SEQLExecutionMemorySolverAssignationContext context, SEQLFromComponent parentComponent, ReferenceModelObjectId parentObject) throws PersistenceException{
		List<SEQLFromComponent> childs=context.getChildsForComponent(parentComponent);
		if(childs==null) return;
		for(SEQLFromComponent child: childs) {
			List<ReferenceModelObjectId> matches=this.pmngr.selectMatchingObjectsForComponentAndParent(child.getReferenceModelClass(), child.getArchetypeId(), parentObject);
			context.addAssignation(child, parentObject, matches);
			for(ReferenceModelObjectId match : matches) {
				this.populateFromComponents(context, child, match);
			}
		}
	}
	protected SEQLFromComponent buildSEQLFromComponentHierarchy(SEQLEvaluable evaluable, SEQLExecutionMemorySolverAssignationContext context, SEQLFromComponent parent) throws SEQLException {
		if(evaluable==null) return null;
		if(evaluable instanceof SEQLOperation) {
			SEQLOperation op=(SEQLOperation)evaluable;
			//Can be a contains or any other operator, we always take the left. 
			SEQLFromComponent subparent=this.buildSEQLFromComponentHierarchy(op.getLeftOperand(), context,parent);
			this.buildSEQLFromComponentHierarchy(op.getRightOperand(), context, subparent);
			return parent;
		} else {
			//Assign this child to the parent
			 SEQLFromComponent component=(SEQLFromComponent)evaluable;
			 if(parent==null) {
				 throw new SEQLException("Orphand child when building component hierarchy "+component);
			 }
			 context.addToComponentHierarchy(parent, component);
			 return component;
		}
	}
	protected List<HashMap<SEQLFromComponent,ReferenceModelObjectId>> createInterpretations(SEQLExecutionMemorySolverAssignationContext context, SEQLFromComponent currentComponent, ReferenceModelObjectId currentId, HashMap<SEQLFromComponent,ReferenceModelObjectId> mapping) {
		//The root component always has the root id
//		mapping.put(currentComponent, currentId);
		//Get the childs
		List<SEQLFromComponent> childs=context.getChildsForComponent(currentComponent);
		if(childs==null) {
			mapping.put(currentComponent, currentId);
			//Bottom. Add to the list 
			ArrayList<HashMap<SEQLFromComponent,ReferenceModelObjectId>> ret=new  ArrayList<HashMap<SEQLFromComponent,ReferenceModelObjectId>>();
			ret.add(mapping);
			return ret;
		}		
		List<HashMap<SEQLFromComponent,ReferenceModelObjectId>> mappings =new ArrayList<HashMap<SEQLFromComponent,ReferenceModelObjectId>>();
		mappings.add((HashMap<SEQLFromComponent,ReferenceModelObjectId>)mapping.clone());
			for(SEQLFromComponent child : childs) {
				Map<ReferenceModelObjectId,List<ReferenceModelObjectId>> assignationMap=context.getAssignation(child);
				if(assignationMap==null) {
					ArrayList<HashMap<SEQLFromComponent,ReferenceModelObjectId>> ret=new  ArrayList<HashMap<SEQLFromComponent,ReferenceModelObjectId>>();
					ret.add(mapping);
					return ret;
				}
				//We use the parent to retrieve the child assignations
				List<ReferenceModelObjectId> childAssignations=assignationMap.get(currentId);
				//Clone the map
				//For each child
				List<HashMap<SEQLFromComponent,ReferenceModelObjectId>> newMappings =new ArrayList<HashMap<SEQLFromComponent,ReferenceModelObjectId>>();
				for(ReferenceModelObjectId childObject : childAssignations) {
					for(HashMap<SEQLFromComponent,ReferenceModelObjectId> priorMapping : mappings) {
						HashMap<SEQLFromComponent,ReferenceModelObjectId> newMapping=(HashMap<SEQLFromComponent,ReferenceModelObjectId>)priorMapping.clone();
						newMapping.put(child, childObject);
						newMappings.addAll(this.createInterpretations(context, child, childObject, newMapping));
					}	
					//Recursion
					//this.createInterpretations(context, child, childObject,newMapping);
					//Add the new assignations 
				}
				if(newMappings.size()>0)  {
					mappings=newMappings;
				}

				//context.addInterpretation(newMapping);
			}
			return mappings;
		//For each of the expanded mappings, repeat the operation with the childs.
	}
	protected String solveOperandValue(SEQLEvaluable operand, SEQLQuery query, Map<SEQLFromComponent,ReferenceModelObjectId> interpretation ) throws PersistenceException{
		if(operand==null) return null;
		if(operand instanceof SEQLPath) {
			SEQLPath path=(SEQLPath) operand;
			List<Object> matches=this.solveObjectMatchesForPath(query, path.getFirstStringPathComponent(),path, interpretation);
			if(matches.size()>1) {
				throw new PersistenceException("More than one value returned for operand  "+path);
			}
			if(matches.size()==0) return null;
			else if(! (matches.get(0) instanceof PrimitiveObjectBlock)) {
				throw new PersistenceException("Comparison of non-primitive values is not supported");
			} else {
				PrimitiveObjectBlock pblock=(PrimitiveObjectBlock)matches.get(0);
				return pblock.getSimpleValue().getValue().toString();
			}
		} else {
			SEQLPrimitive primitive=(SEQLPrimitive) operand;
			return primitive.getValue();
		}
	}
	protected boolean interpretationMatchesEvaluable(Map<SEQLFromComponent,ReferenceModelObjectId> interpretation, SEQLExecutionMemorySolverAssignationContext context, SEQLQuery query, SEQLEvaluable evaluable) throws PersistenceException{
		if(evaluable instanceof SEQLOperation) {
			SEQLOperation op=(SEQLOperation) evaluable;
			if(op.getOperator()!=SEQLBooleanOperator.CONTAINS) {
				//Evaluate the operators. AND, OR XOR. 
				switch(op.getOperator()) {
					case AND:
						//Then recurse 
						return this.interpretationMatchesEvaluable(interpretation, context, query, op.getLeftOperand())
								&& this.interpretationMatchesEvaluable(interpretation, context,query, op.getRightOperand());
					case OR:
						//Then recurse 
						return this.interpretationMatchesEvaluable(interpretation, context,query, op.getLeftOperand())
								|| this.interpretationMatchesEvaluable(interpretation, context,query, op.getRightOperand());
					case XOR: 
						return this.interpretationMatchesEvaluable(interpretation, context,query, op.getLeftOperand())
								^ this.interpretationMatchesEvaluable(interpretation, context,query, op.getRightOperand());
					case NOT: 
						return !this.interpretationMatchesEvaluable(interpretation, context,query, op.getLeftOperand());
					default:
						Object left=this.solveOperandValue(op.getLeftOperand(),query,interpretation);
						Object right=this.solveOperandValue(op.getRightOperand(),query,interpretation);
						switch(op.getOperator()) {
						case EXISTS: 
							return left!=null;
						case EQUALITY: 
							return (left==null && right==null) || (left!=null && left.equals(right));
						case INEQUALITY: 
							return (left!=null || right!=null) && !left.equals(right);
						default: 
							throw new IllegalArgumentException("Comparison operators not yet implemented");
						}
				}
			}  else {
				return this.interpretationMatchesEvaluable(interpretation, context, query,op.getLeftOperand())
						&& this.interpretationMatchesEvaluable(interpretation, context, query,op.getRightOperand());
			}
		} else { //SEQLFromComponent
			SEQLFromComponent component=(SEQLFromComponent) evaluable;
			boolean contained=component==null || interpretation.containsKey(component);
			return contained;
		}
	}
	protected List<Object> solveObjectMatchesForPath(SEQLQuery query, String identifiedVariable, SEQLPath path, Map<SEQLFromComponent,ReferenceModelObjectId> interpretation) throws PersistenceException {
		try {
			path=(SEQLPath)path.clone();
			SEQLFromComponent component=query.getFromCondition().getFromComponentFromIdentifiedVariable(identifiedVariable);
			ReferenceModelObjectId assignation=interpretation.get(component);
			if(assignation==null) {
				return new ArrayList<Object>();
			}
			List<SEQLPathComponent> pathComponents= path.getPathComponents();
			List<ReferenceModelObjectId> matches=this.pmngr.getDeepestRMObjectsForParentAndPath(assignation, pathComponents);
			List<Object> finalMatches=new ArrayList<Object>();
			for(ReferenceModelObjectId match : matches) {
				ContentObject obj=this.pmngr.selectFromReferenceModelObjectId(match);
				SingleAttributeObjectBlock sblock=this.rmngr.getSingleAttributeObjectBlockFromContentObject(obj);
				ObjectBlock finalMatch=this.rmngr.solveReferenceModelPath(sblock, pathComponents);
				finalMatches.add(finalMatch);
			}
			return finalMatches;
		} catch(Exception e) {
			throw new PersistenceException(e.getMessage());
		}
	}
	protected ContentObject solveSelectConditionForInterpretation(SEQLQuery query, SEQLSelectCondition cond, Map<SEQLFromComponent,ReferenceModelObjectId> interpretation) throws SEQLException{
		SEQLPath path=(SEQLPath)cond.getPath().clone();
		String identifiedVariable=cond.getIdentifiedVariableId();
		if(identifiedVariable!=null) {
			SEQLFromComponent component=query.getFromCondition().getFromComponentFromIdentifiedVariable(identifiedVariable);
			ReferenceModelObjectId assignation=interpretation.get(component);
			if(assignation==null) {
				//Null value
				return new ContentObject(null,new SingleAttributeObjectBlock(null,new ArrayList<AttributeValue>()));
			}
			List<SEQLPathComponent> pathComponents= path.getPathComponents();
			try {
				List<ReferenceModelObjectId> matches=this.pmngr.getDeepestRMObjectsForParentAndPath(assignation, pathComponents);
				System.out.println("Matches: "+matches);
				//Deserialize all matches
				//Multiple matches have 2 possible meanings
				//Meaning one is that there are indeed multiple matches of a single object 
				//Multiple matches, but we do not support returning a multipleobjectblock
				//Instead, we use the match to solve the proper path and return all matching objects, each on a different row
				try {
					List<ContentObject> finalMatches=new ArrayList<ContentObject>();
					for(ReferenceModelObjectId match : matches) {
						ContentObject obj=this.pmngr.selectFromReferenceModelObjectId(match);
						SingleAttributeObjectBlock sblock=this.rmngr.getSingleAttributeObjectBlockFromContentObject(obj);
						ObjectBlock finalMatch=this.rmngr.solveReferenceModelPath(sblock, pathComponents);
						if(finalMatch instanceof ComplexObjectBlock) {
							finalMatches.add(new ContentObject(null,(ComplexObjectBlock) finalMatch));
						} else {
							//Create a dummy content object 
							StringValue svalue=new StringValue("seql_primitive");
							PrimitiveObjectBlock pblock=new PrimitiveObjectBlock(null,svalue,null,null,null,null);
							AttributeValue dummyValue=new AttributeValue("reference_model_class_name", pblock);
							ArrayList<AttributeValue> values=new ArrayList<AttributeValue>();
							values.add(dummyValue);
							dummyValue=new AttributeValue("value",(PrimitiveObjectBlock)finalMatch);
							values.add(dummyValue);
							SingleAttributeObjectBlock dummyBlock=new SingleAttributeObjectBlock(null,values);
							finalMatches.add(new ContentObject(null,dummyBlock));
						}
					}
					if(finalMatches.size()==1) {
						return finalMatches.get(0);
					} else {
						List<KeyedObject> keyedObjects=new ArrayList<KeyedObject>();
						int index=0;
						for(ContentObject finalMatch : finalMatches) {
							SimpleValue<String> key=new StringValue(""+index++);
							keyedObjects.add(new KeyedObject(key, finalMatch.getComplexObjectBlock()));
						}
						return new ContentObject(null,new MultipleAttributeObjectBlock(null,keyedObjects));
					}
				} catch(Exception e) {
					throw new SEQLException(e.getMessage());
				}
			
				//They are singleattributeobjectblocks 
				//We need  to rebuild the structure for lists. That is a 
				//Now the remaning path components are to be applied to returned object ids in memory
			} catch(PersistenceException e) {
				throw new SEQLException(e.getMessage());
			}
		}
		return null;

	}
	@Override
	public SEQLResultSet runQuery(SEQLQuery query) throws SEQLException {
		SEQLEvaluable evaluable=query.getFromCondition().getRoot();
		SEQLExecutionMemorySolverAssignationContext context=new SEQLExecutionMemorySolverAssignationContext();
		ReferenceModelObjectId rootId=this.pmngr.getReferenceModelRoot(); //There is a single root
		try {
			/*
			this.evaluateContext(context);*/
			//Build the component hierarchy
			SEQLFromComponent rootComponent=new SEQLFromComponent(null,null,null);
			this.buildSEQLFromComponentHierarchy(evaluable, context, rootComponent);
			try {
				this.populateFromComponents(context,rootComponent,rootId);
				HashMap<SEQLFromComponent,ReferenceModelObjectId> mapping=new HashMap<SEQLFromComponent,ReferenceModelObjectId> ();
				mapping.put(rootComponent, rootId);
				List<HashMap<SEQLFromComponent,ReferenceModelObjectId>> interpretations=this.createInterpretations(context, rootComponent, rootId,mapping);
				System.out.println("Number of interpretations: "+interpretations.size());
				SEQLResultSet rs=new SEQLResultSet(query.getSelectConditions());
				for(Map<SEQLFromComponent,ReferenceModelObjectId> interpretation : interpretations) {
					System.out.println("Interpretation: "+interpretation);
					//Check if the interpretation is a model
					if(this.interpretationMatchesEvaluable(interpretation, context, query, evaluable)) {
						System.out.println(">Interpretation is from clause model");
						if(this.interpretationMatchesEvaluable(interpretation, context, query, query.getWhereCondition().getRoot())) {
							//If we arrive here, now we can use the where clause to further eliminate interpretations
							System.out.println(">>Interpretation is where clause model. Appending to result");
							rs.addRow();
							for(SEQLSelectCondition scond : query.getSelectConditions()) {
								ContentObject r=this.solveSelectConditionForInterpretation(query,scond,interpretation);
								rs.appendToRow(r);
							}
						}
					}
				}
				//Finished. Compile the resultset
				rs.compile();
				return rs;

			} catch(PersistenceException e) {
				e.printStackTrace();
				throw new SEQLException(e.getMessage());
			}
		}catch(SEQLException e) {
			e.printStackTrace();
			throw new SEQLException(e.getMessage());
		}
		
 	}
 
	protected class SEQLExecutionMemorySolverAssignationContext {
		private Map<SEQLEvaluable, Map<ReferenceModelObjectId,List<ReferenceModelObjectId>>> assignations;
		private Map<SEQLFromComponent, List<SEQLFromComponent>> componentHierarchy;
		private List<Map<SEQLFromComponent, ReferenceModelObjectId>> interpretations;

		public List<SEQLFromComponent> getChildsForComponent(SEQLFromComponent component) {
			return this.componentHierarchy.get(component);
		}
		public SEQLExecutionMemorySolverAssignationContext() {
			this.componentHierarchy=new HashMap<SEQLFromComponent, List<SEQLFromComponent>>();
			this.assignations=new HashMap<SEQLEvaluable, Map<ReferenceModelObjectId,List<ReferenceModelObjectId>>>();
		}
		public void addInterpretation(Map<SEQLFromComponent, ReferenceModelObjectId> interpretation) {
			this.interpretations.add(interpretation);
		}
		public void addInterpretations(List<Map<SEQLFromComponent, ReferenceModelObjectId>> interpretations) {
			this.interpretations.addAll(interpretations);
		}
		public List<Map<SEQLFromComponent, ReferenceModelObjectId>> getInterpretations() {
			return interpretations;
		}
		public void addToComponentHierarchy(SEQLFromComponent parent, SEQLFromComponent child) {
			List<SEQLFromComponent> childs=this.componentHierarchy.get(parent);
			if(childs==null) {
				childs=new ArrayList<SEQLFromComponent>();
				this.componentHierarchy.put(parent, childs);
			}
			childs.add(child);
		}
		public List<SEQLFromComponent> getComponentChilds(SEQLFromComponent component) {
			return this.componentHierarchy.get(component);
		}
		public void addAssignation(SEQLEvaluable evaluable, ReferenceModelObjectId newId, List<ReferenceModelObjectId> matches) {
			Map<ReferenceModelObjectId,List<ReferenceModelObjectId>> matchMap;
			matchMap=this.assignations.get(evaluable);
			if(matchMap==null) {
				matchMap=new HashMap<ReferenceModelObjectId,List<ReferenceModelObjectId>>();
				this.assignations.put(evaluable,matchMap);
			}
			matchMap.put(newId, matches);
		}
		public Map<ReferenceModelObjectId,List<ReferenceModelObjectId>> getAssignation(SEQLEvaluable evaluable) {
			return this.assignations.get(evaluable);
		}
		public void copyAssignation(SEQLEvaluable from, SEQLEvaluable to) {
			this.assignations.put(from, this.assignations.get(to));
		}
	}
	
}
