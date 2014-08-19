package org.sigaim.siie.seql.engine.execution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openehr.am.parser.AttributeValue;
import org.openehr.am.parser.ComplexObjectBlock;
import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.IntegerValue;
import org.openehr.am.parser.KeyedObject;
import org.openehr.am.parser.MultipleAttributeObjectBlock;
import org.openehr.am.parser.ObjectBlock;
import org.openehr.am.parser.PrimitiveObjectBlock;
import org.openehr.am.parser.SimpleValue;
import org.openehr.am.parser.SingleAttributeObjectBlock;
import org.openehr.am.parser.StringValue;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.db.PersistenceManager;
import org.sigaim.siie.db.ReferenceModelObjectId;
import org.sigaim.siie.db.exceptions.PersistenceException;
import org.sigaim.siie.rm.ReferenceModelManager;
import org.sigaim.siie.rm.ReflectorReferenceModelManager;
import org.sigaim.siie.seql.engine.SEQLQueryExecutionStage;
import org.sigaim.siie.seql.model.SEQLEvaluable;
import org.sigaim.siie.seql.model.SEQLException;
import org.sigaim.siie.seql.model.SEQLFromComponent;
import org.sigaim.siie.seql.model.SEQLOperation;
import org.sigaim.siie.seql.model.SEQLPath;
import org.sigaim.siie.seql.model.SEQLPathComponent;
import org.sigaim.siie.seql.model.SEQLPrimitive;
import org.sigaim.siie.seql.model.SEQLQuery;
import org.sigaim.siie.seql.model.SEQLResultSet;
import org.sigaim.siie.seql.model.SEQLSelectCondition;
import org.sigaim.siie.seql.model.SEQLOperation.SEQLBooleanOperator;
import org.sigaim.siie.utils.Utils;



public class SEQLExecutionMemorySolverStage implements SEQLQueryExecutionStage {
	private static org.apache.log4j.Logger log = Logger.getLogger(SEQLExecutionMemorySolverStage.class);
	private PersistenceManager pmngr;
	private ReferenceModelManager rmngr;
	private DADLManager dmngr;
	
	public SEQLExecutionMemorySolverStage(PersistenceManager pmngr, ReferenceModelManager rmngr, DADLManager dmngr) {
		this.pmngr=pmngr;
		this.rmngr=rmngr;
		this.dmngr=dmngr;
	}
	protected void populateFromComponents(SEQLExecutionMemorySolverAssignationContext context, SEQLFromComponent parentComponent, ReferenceModelObjectId parentObject) throws PersistenceException{
		List<SEQLFromComponent> childs=context.getChildsForComponent(parentComponent);
		if(childs==null) return;
		for(SEQLFromComponent child: childs) {
			List<ReferenceModelObjectId> matches;
			boolean isRoot=Utils.classNameEquals(child.getReferenceModelClass(), this.rmngr.getRootClass().getSimpleName());
			if(isRoot) {
				matches=this.pmngr.selectMatchingObjectsForComponentAndParent(child.getReferenceModelClass(), child.getArchetypeId(), null,child.getUseAllVersions());
			} else {
				matches=this.pmngr.selectMatchingObjectsForComponentAndParent(child.getReferenceModelClass(), child.getArchetypeId(), parentObject,child.getUseAllVersions());	
			}
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
				ContentObject obj=this.pmngr.selectFromReferenceModelObjectId(match,false);
				SingleAttributeObjectBlock sblock=this.rmngr.getSingleAttributeObjectBlockFromContentObject(obj);
				ObjectBlock finalMatch=this.rmngr.solveReferenceModelPath(sblock, pathComponents);
				if(finalMatch!=null) {
					finalMatches.add(finalMatch);
				}
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
			Class<?> type=this.rmngr.getPathType(component.getReferenceModelClass(), path);
			List<SEQLPathComponent> pathComponents= path.getPathComponents();
			try {
				List<ReferenceModelObjectId> matches=this.pmngr.getDeepestRMObjectsForParentAndPath(assignation, pathComponents);
				log.debug("Matches: "+matches);
				//Deserialize all matches
				//Multiple matches have 2 possible meanings
				//Meaning one is that there are indeed multiple matches of a single object 
				//Multiple matches, but we do not support returning a multipleobjectblock
				//Instead, we use the match to solve the proper path and return all matching objects, each on a different row
				try {
					List<ContentObject> finalMatches=new ArrayList<ContentObject>();
					for(ReferenceModelObjectId match : matches) {
						ContentObject obj=this.pmngr.selectFromReferenceModelObjectId(match,cond.getWithDescendants());
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
					if(finalMatches.size()==0) {
						//null
						return new ContentObject(null,new SingleAttributeObjectBlock(null,new ArrayList<AttributeValue>()));
					} if(finalMatches.size()==1) {
						return finalMatches.get(0);
					} else {
						List<KeyedObject> keyedObjects=new ArrayList<KeyedObject>();
						int index=0;
						for(ContentObject finalMatch : finalMatches) {
							SimpleValue<Integer> key=new IntegerValue(index++);
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
				log.debug("Number of interpretations: "+interpretations.size());
				if(query.isMerged()) {
					Set<ReferenceModelObjectId> matches=new HashSet<ReferenceModelObjectId>();
					for(Map<SEQLFromComponent,ReferenceModelObjectId> interpretation : interpretations) {
						log.debug("Interpretation: "+interpretation);
						//Check if the interpretation is a model
						if(this.interpretationMatchesEvaluable(interpretation, context, query, evaluable)) {
							log.debug(">Interpretation is from clause model");
							if(this.interpretationMatchesEvaluable(interpretation, context, query, query.getWhereCondition().getRoot())) {
								//If we arrive here, now we can use the where clause to further eliminate interpretations
								log.debug(">>Interpretation is where clause model. Appending to result");
								for(SEQLSelectCondition scond : query.getSelectConditions()) {
									for(SEQLFromComponent comp : interpretation.keySet()) {
										String k1=comp.getIdentifiedVariable();
										String k2=scond.getIdentifiedVariableId();
										if(k1!= null && k2!= null && k1.equals(k2)) {
											matches.add(interpretation.get(comp));
										}
									}
								}
							}
						}
					}
					log.debug("Number of merged matching objects: "+matches.size());
					//Now we have all the matching objects and no repetition
					//Find the topmost parent
					String topMost=null;
					for(ReferenceModelObjectId id : matches) {
						if(topMost==null) {
							topMost=this.pmngr.getReferenceModelPathFoRMObject(id).getFullPath();
						} else {
							SEQLPath newPath=this.pmngr.getReferenceModelPathFoRMObject(id);
							topMost=Utils.longestCommonPrefix(newPath.getFullPath(), topMost);
						}
					}
					SEQLPath topmostPath;
					if(topMost.endsWith("[")) {
						topMost=topMost.substring(0, topMost.length()-1);
						topmostPath= new SEQLPath(topMost).removeLastPathComponent();
						
					} else {
						topmostPath= new SEQLPath(topMost);				
					}
					log.debug("Topmost string "+topMost);
					log.debug("Topmost path: "+topmostPath);
					//Solve the RM for the topmost object 
					ReferenceModelObjectId ancestor=this.pmngr.getReferenceModelObjectIdFromReferenceModelPath(topmostPath);
					log.debug("Topmost id: "+ancestor);
					matches.add(ancestor);
					//Now, we can select the descendants IF they are in the list 
					ContentObject obj=this.pmngr.selectFromReferenceModelObjectId(ancestor,true,matches);
					SEQLResultSet rs=new SEQLResultSet(1);
					rs.addRow();
					rs.appendToRow(obj);
					rs.compile();
					return rs;
				} else {
					SEQLResultSet rs=new SEQLResultSet(query.getSelectConditions().size());
					for(Map<SEQLFromComponent,ReferenceModelObjectId> interpretation : interpretations) {
						log.debug("Interpretation: "+interpretation);
						//Check if the interpretation is a model
						if(this.interpretationMatchesEvaluable(interpretation, context, query, evaluable)) {
							log.debug(">Interpretation is from clause model");
							if(this.interpretationMatchesEvaluable(interpretation, context, query, query.getWhereCondition().getRoot())) {
								//If we arrive here, now we can use the where clause to further eliminate interpretations
								log.debug(">>Interpretation is where clause model. Appending to result");
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
				}
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
