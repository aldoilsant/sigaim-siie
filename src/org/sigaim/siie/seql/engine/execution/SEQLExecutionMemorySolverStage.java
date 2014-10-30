package org.sigaim.siie.seql.engine.execution;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.openehr.am.parser.AttributeValue;
import org.openehr.am.parser.BooleanValue;
import org.openehr.am.parser.CharacterValue;
import org.openehr.am.parser.ComplexObjectBlock;
import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.DateTimeValue;
import org.openehr.am.parser.DateValue;
import org.openehr.am.parser.DurationValue;
import org.openehr.am.parser.IntegerValue;
import org.openehr.am.parser.KeyedObject;
import org.openehr.am.parser.MultipleAttributeObjectBlock;
import org.openehr.am.parser.ObjectBlock;
import org.openehr.am.parser.PrimitiveObjectBlock;
import org.openehr.am.parser.RealValue;
import org.openehr.am.parser.SimpleValue;
import org.openehr.am.parser.SingleAttributeObjectBlock;
import org.openehr.am.parser.StringValue;
import org.openehr.am.parser.TimeValue;
import org.openehr.am.parser.UriValue;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.db.DBDeserializer;
import org.sigaim.siie.db.PersistenceManager;
import org.sigaim.siie.db.ReferenceModelObjectId;
import org.sigaim.siie.db.exceptions.PersistenceException;
import org.sigaim.siie.db.sql.SQLReferenceModelObjectId;
import org.sigaim.siie.interfaces.IDDBSerializer;
import org.sigaim.siie.rm.ReferenceModelManager;
import org.sigaim.siie.rm.ReflectorReferenceModelManager;
import org.sigaim.siie.rm.exceptions.ReferenceModelException;
import org.sigaim.siie.seql.engine.QueryDomainHelper;
import org.sigaim.siie.seql.engine.SEQLQueryExecutionStage;
import org.sigaim.siie.seql.model.SEQLEvaluable;
import org.sigaim.siie.seql.model.SEQLException;
import org.sigaim.siie.seql.model.SEQLFromComponent;
import org.sigaim.siie.seql.model.SEQLOperation;
import org.sigaim.siie.seql.model.SEQLPath;
import org.sigaim.siie.seql.model.SEQLPathComponent;
import org.sigaim.siie.seql.model.SEQLPrimitive;
import org.sigaim.siie.seql.model.SEQLPrimitive.SEQLPrimitiveType;
import org.sigaim.siie.seql.model.SEQLQuery;
import org.sigaim.siie.seql.model.SEQLResultSet;
import org.sigaim.siie.seql.model.SEQLSelectCondition;
import org.sigaim.siie.seql.model.SEQLOperation.SEQLBooleanOperator;
import org.sigaim.siie.utils.Utils;

public class SEQLExecutionMemorySolverStage implements SEQLQueryExecutionStage,
		DBDeserializer {
	private static org.apache.log4j.Logger log = Logger
			.getLogger(SEQLExecutionMemorySolverStage.class);
	private PersistenceManager pmngr;
	private ReferenceModelManager rmngr;
	private DADLManager dmngr;
	private QueryDomainHelper helper;

	public SEQLExecutionMemorySolverStage(PersistenceManager pmngr,
			ReferenceModelManager rmngr, DADLManager dmngr) {
		this.pmngr = pmngr;
		this.rmngr = rmngr;
		this.dmngr = dmngr;
		this.helper=new IDDBSerializer(this.rmngr,this.dmngr);
	}
 
	protected void populateFromComponents(
			SEQLExecutionMemorySolverAssignationContext context,
			SEQLFromComponent parentComponent,
			ReferenceModelObjectId parentObject, SEQLQuery query)
			throws PersistenceException {
		List<SEQLFromComponent> childs = context
				.getChildsForComponent(parentComponent);
		if (childs == null)
			return;
		for (SEQLFromComponent child : childs) {
			List<ReferenceModelObjectId> matches;
			boolean isRoot = Utils.classNameEquals(child
					.getReferenceModelClass(), this.rmngr.getRootClass()
					.getSimpleName());
			if (isRoot) {
				matches = this.pmngr
						.selectMatchingObjectsForComponentAndParent(
								child.getReferenceModelClass(),
								child.getArchetypeId(), null,
								child.getUseAllVersions());
			} else {
				matches = this.pmngr
						.selectMatchingObjectsForComponentAndParent(
								child.getReferenceModelClass(),
								child.getArchetypeId(), parentObject,
								child.getUseAllVersions());
			}
			matches = context.addAssignation(child, parentObject, matches,
					query);
			for (ReferenceModelObjectId match : matches) {
				this.populateFromComponents(context, child, match, query);
			}
		}
	}

	protected SEQLFromComponent buildSEQLFromComponentHierarchy(
			SEQLEvaluable evaluable,
			SEQLExecutionMemorySolverAssignationContext context,
			SEQLFromComponent parent) throws SEQLException {
		if (evaluable == null)
			return null;
		if (evaluable instanceof SEQLOperation) {
			SEQLOperation op = (SEQLOperation) evaluable;
			// Can be a contains or any other operator, we always take the left.
			SEQLFromComponent subparent = this.buildSEQLFromComponentHierarchy(
					op.getLeftOperand(), context, parent);
			this.buildSEQLFromComponentHierarchy(op.getRightOperand(), context,
					subparent);
			return parent;
		} else {
			// Assign this child to the parent
			SEQLFromComponent component = (SEQLFromComponent) evaluable;
			if (parent == null) {
				throw new SEQLException(
						"Orphand child when building component hierarchy "
								+ component);
			}
			context.addToComponentHierarchy(parent, component);
			return component;
		}
	}

	protected List<HashMap<SEQLFromComponent, ReferenceModelObjectId>> createInterpretations(
			SEQLExecutionMemorySolverAssignationContext context,
			SEQLFromComponent currentComponent,
			ReferenceModelObjectId currentId,
			HashMap<SEQLFromComponent, ReferenceModelObjectId> mapping) {
		// The root component always has the root id
		// mapping.put(currentComponent, currentId);
		// Get the childs
		List<SEQLFromComponent> childs = context
				.getChildsForComponent(currentComponent);
		if (childs == null) {
			mapping.put(currentComponent, currentId);
			// Bottom. Add to the list
			ArrayList<HashMap<SEQLFromComponent, ReferenceModelObjectId>> ret = new ArrayList<HashMap<SEQLFromComponent, ReferenceModelObjectId>>();
			ret.add(mapping);
			return ret;
		}
		List<HashMap<SEQLFromComponent, ReferenceModelObjectId>> mappings = new ArrayList<HashMap<SEQLFromComponent, ReferenceModelObjectId>>();
		mappings.add((HashMap<SEQLFromComponent, ReferenceModelObjectId>) mapping
				.clone());
		for (SEQLFromComponent child : childs) {
			Map<ReferenceModelObjectId, List<ReferenceModelObjectId>> assignationMap = context
					.getAssignation(child);
			if (assignationMap == null) {
				ArrayList<HashMap<SEQLFromComponent, ReferenceModelObjectId>> ret = new ArrayList<HashMap<SEQLFromComponent, ReferenceModelObjectId>>();
				ret.add(mapping);
				return ret;
			}
			// We use the parent to retrieve the child assignations
			List<ReferenceModelObjectId> childAssignations = assignationMap
					.get(currentId);
			// Clone the map
			// For each child
			List<HashMap<SEQLFromComponent, ReferenceModelObjectId>> newMappings = new ArrayList<HashMap<SEQLFromComponent, ReferenceModelObjectId>>();
			for (ReferenceModelObjectId childObject : childAssignations) {
				for (HashMap<SEQLFromComponent, ReferenceModelObjectId> priorMapping : mappings) {
					HashMap<SEQLFromComponent, ReferenceModelObjectId> newMapping = (HashMap<SEQLFromComponent, ReferenceModelObjectId>) priorMapping
							.clone();
					newMapping.put(child, childObject);
					newMappings.addAll(this.createInterpretations(context,
							child, childObject, newMapping));
				}
				// Recursion
				// this.createInterpretations(context, child,
				// childObject,newMapping);
				// Add the new assignations
			}
			if (newMappings.size() > 0) {
				mappings = newMappings;
			}

			// context.addInterpretation(newMapping);
		}
		return mappings;
		// For each of the expanded mappings, repeat the operation with the
		// childs.
	}

	protected Object coerceFromString(String stringValue, SEQLPrimitiveType type)
			throws Exception {
		Object ret = null;
		StringBuilder builder;
		switch (type) {
		case STRING:
			builder = new StringBuilder(stringValue);
			if (builder.charAt(0) == '\'' || builder.charAt(0) == '"') {
				builder.deleteCharAt(0);
				builder.deleteCharAt(builder.length() - 1);
			}
			ret = builder.toString();
			break;
		case BOOLEAN:
			ret = Boolean.parseBoolean(stringValue);
			break;
		case DATE:
			builder = new StringBuilder(stringValue);
			if (builder.charAt(0) == '\'' || builder.charAt(0) == '"') {
				builder.deleteCharAt(0);
				builder.deleteCharAt(builder.length() - 1);
			}
			stringValue = builder.toString();
			ret = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(stringValue).toGregorianCalendar();
			break;
		case INTEGER:
			ret = Integer.parseInt(stringValue);
			break;
		case FLOAT:
			ret = Double.parseDouble(stringValue);
			break;
		}
		return ret;
	}

	SEQLPrimitiveType convertOpenEHRTypeToSEQLType(Class<?> valueClass) {
		SEQLPrimitiveType type = SEQLPrimitiveType.STRING;
		if (valueClass.equals(BooleanValue.class)) {
			type = SEQLPrimitiveType.BOOLEAN;
		} else if (valueClass.equals(CharacterValue.class)) {
			type = SEQLPrimitiveType.STRING;
		} else if (valueClass.equals(DateTimeValue.class)) {
			type = SEQLPrimitiveType.DATE;
		} else if (valueClass.equals(DateValue.class)) {
			type = SEQLPrimitiveType.DATE;
		} else if (valueClass.equals(DurationValue.class)) {
			type = SEQLPrimitiveType.STRING;
		} else if (valueClass.equals(IntegerValue.class)) {
			type = SEQLPrimitiveType.INTEGER;
		} else if (valueClass.equals(RealValue.class)) {
			type = SEQLPrimitiveType.FLOAT;
		} else if (valueClass.equals(StringValue.class)) {
			type = SEQLPrimitiveType.STRING;
		} else if (valueClass.equals(TimeValue.class)) {
			type = SEQLPrimitiveType.STRING;
		} else if (valueClass.equals(UriValue.class)) {
			type = SEQLPrimitiveType.STRING;
		}
		return type;
	}

	protected Object solveOperandValueFromContentObject(SEQLEvaluable operand,
			SingleAttributeObjectBlock obj) throws ReferenceModelException,
			PersistenceException {
		if (operand == null)
			return null;
		String stringValue = null;
		SEQLPrimitiveType type = SEQLPrimitiveType.STRING;
		if (operand instanceof SEQLPath) {
			SEQLPath path = (SEQLPath) operand;
			ObjectBlock match = this.rmngr.solveReferenceModelPath(obj, path
					.removeFirstPathComponent().getPathComponents());
			if (match == null)
				return null;
			if (!(match instanceof PrimitiveObjectBlock)) {
				throw new PersistenceException(
						"Comparison of non-primitive values is not supported");
			} else {
				PrimitiveObjectBlock pblock = (PrimitiveObjectBlock) match;
				stringValue = pblock.getSimpleValue().getValue().toString();
				Class<?> valueClass = pblock.getSimpleValue().getClass();
				/*
				 * BooleanValue CharacterValue DateTimeValue DateValue
				 * DurationValue IntegerValue RealValue StringValue TimeValue
				 * UriValue
				 */
				type = this.convertOpenEHRTypeToSEQLType(valueClass);
			}
		} else {
			SEQLPrimitive primitive = (SEQLPrimitive) operand;
			/*
			 * StringBuilder builder= new StringBuilder(primitive.getValue());
			 * if(builder.charAt(0)=='\'' || builder.charAt(0)=='"') {
			 * builder.deleteCharAt(0);
			 * builder.deleteCharAt(builder.length()-1); }
			 */
			stringValue = primitive.getValue();
			type = primitive.getType();
		}
		Object ret = null;
		if (stringValue != null) {
			// Create the objects!
			try {
				ret = this.coerceFromString(stringValue, type);
			} catch (Exception e) {
				e.printStackTrace();
				throw new PersistenceException("Invalid value coercion");
			}
		}
		return ret;
	}

	protected Object solveOperandValue(SEQLEvaluable operand, SEQLQuery query,
			Map<SEQLFromComponent, ReferenceModelObjectId> interpretation, SEQLExecutionMemorySolverAssignationContext context)
			throws PersistenceException {
		if (operand == null)
			return null;
		String stringValue = null;
		SEQLPrimitiveType type = SEQLPrimitiveType.STRING;
		if (operand instanceof SEQLPath) {
			SEQLPath path = (SEQLPath) operand;
			List<Object> matches = this.solveObjectMatchesForPath(query,
					path.getFirstStringPathComponent(), path, interpretation,context);
			if (matches.size() > 1) {
				throw new PersistenceException(
						"More than one value returned for operand  " + path);
			}
			if (matches.size() == 0)
				return null;
			else if (!(matches.get(0) instanceof PrimitiveObjectBlock)) {
				throw new PersistenceException(
						"Comparison of non-primitive values is not supported");
			} else {
				PrimitiveObjectBlock pblock = (PrimitiveObjectBlock) matches
						.get(0);
				stringValue = pblock.getSimpleValue().getValue().toString();
				Class<?> valueClass = pblock.getSimpleValue().getClass();
				/*
				 * BooleanValue CharacterValue DateTimeValue DateValue
				 * DurationValue IntegerValue RealValue StringValue TimeValue
				 * UriValue
				 */
				type = this.convertOpenEHRTypeToSEQLType(valueClass);
			}
		} else {
			SEQLPrimitive primitive = (SEQLPrimitive) operand;
			/*
			 * StringBuilder builder= new StringBuilder(primitive.getValue());
			 * if(builder.charAt(0)=='\'' || builder.charAt(0)=='"') {
			 * builder.deleteCharAt(0);
			 * builder.deleteCharAt(builder.length()-1); }
			 */
			stringValue = primitive.getValue();
			type = primitive.getType();
		}
		Object ret = null;
		if (stringValue != null) {
			// Create the objects!
			try {
				ret = this.coerceFromString(stringValue, type);
			} catch (Exception e) {
				e.printStackTrace();
				throw new PersistenceException("Invalid value coercion");
			}
		}
		return ret;
	}

	protected boolean interpretationMatchesEvaluable(
			Map<SEQLFromComponent, ReferenceModelObjectId> interpretation,
			SEQLExecutionMemorySolverAssignationContext context,
			SEQLQuery query, SEQLEvaluable evaluable, boolean partial)
			throws PersistenceException {
		if (evaluable instanceof SEQLOperation) {
			SEQLOperation op = (SEQLOperation) evaluable;
			if (op.getOperator() != SEQLBooleanOperator.CONTAINS) {
				// Evaluate the operators. AND, OR XOR.
				switch (op.getOperator()) {
				case AND:
					// Then recurse
					return this.interpretationMatchesEvaluable(interpretation,
							context, query, op.getLeftOperand(), partial)
							&& this.interpretationMatchesEvaluable(
									interpretation, context, query,
									op.getRightOperand(), partial);
				case OR:
					// Then recurse
					return this.interpretationMatchesEvaluable(interpretation,
							context, query, op.getLeftOperand(), partial)
							|| this.interpretationMatchesEvaluable(
									interpretation, context, query,
									op.getRightOperand(), partial);
				case XOR:
					return this.interpretationMatchesEvaluable(interpretation,
							context, query, op.getLeftOperand(), partial)
							^ this.interpretationMatchesEvaluable(
									interpretation, context, query,
									op.getRightOperand(), partial);
				case NOT:
					return !this.interpretationMatchesEvaluable(interpretation,
							context, query, op.getLeftOperand(), partial);
				default:
					Object left = this.solveOperandValue(op.getLeftOperand(),
							query, interpretation,context);
					if (left == null && partial)
						return true;
					Object right = this.solveOperandValue(op.getRightOperand(),
							query, interpretation,context);
					if (right != null && left != null
							&& !left.getClass().equals(right.getClass())) {
						// Attempt to coerce a string left value to the right
						// operator
						try {
							if (left.getClass().equals(String.class)) {
								if (right.getClass().equals(
										GregorianCalendar.class)) {
									left = this.coerceFromString((String) left,
											SEQLPrimitiveType.DATE);
								} else {
									throw new PersistenceException(
											"Unable to perform type coercion");
								}
							} else {
								throw new PersistenceException(
										" Type mismatch when using comparison operator");
							}
						} catch (Exception e) {
							throw new PersistenceException(
									" Error while attempting type coercion");
						}
					}
					switch (op.getOperator()) {
					case EXISTS:
						return left != null;
					case EQUALITY:
						return (left == null && right == null)
								|| (left != null && left.equals(right));
					case INEQUALITY:
						return (left != null || right != null)
								&& !left.equals(right);
						// For comparison, we NEED typed objects
					default:
						Comparable cl,
						cr;
						if (!(left instanceof Comparable)
								|| !(right instanceof Comparable)) {
							throw new PersistenceException("Values " + left
									+ " and " + right + " are not comparable");
						}
						cl = (Comparable) left;
						cr = (Comparable) right;
						int comparison = cl.compareTo(cr);
						switch (op.getOperator()) {
						case GE:
							return comparison >= 0;
						case GT:
							return comparison > 0;
						case LE:
							return comparison <= 0;
						case LT:
							return comparison < 0;
						default:
							throw new PersistenceException(
									"Invalid comparison operator");
						}
					}
				}
			} else {
				return this.interpretationMatchesEvaluable(interpretation,
						context, query, op.getLeftOperand(), partial)
						&& this.interpretationMatchesEvaluable(interpretation,
								context, query, op.getRightOperand(), partial);
			}
		} else { // SEQLFromComponent
			SEQLFromComponent component = (SEQLFromComponent) evaluable;
			boolean contained = component == null
					|| interpretation.containsKey(component);
			return contained;
		}
	}
	protected boolean pathIsObjectId(ReferenceModelObjectId oid, SEQLPath path) {
		try {
			String referenceModelClassName=this.rmngr.getReferenceModelClassName(this.pmngr.getClassFromRMID(oid));
			return this.helper.pathIsObjectId(referenceModelClassName, path);
		} catch(Exception e){
			return false;
		}
	}
	protected List<Object> solveObjectMatchesForPath(SEQLQuery query,
			String identifiedVariable, SEQLPath path,
			Map<SEQLFromComponent, ReferenceModelObjectId> interpretation, SEQLExecutionMemorySolverAssignationContext context)
			throws PersistenceException {
		try {
			path = (SEQLPath) path.clone();
			SEQLFromComponent component = query.getFromCondition()
					.getFromComponentFromIdentifiedVariable(identifiedVariable);
			ReferenceModelObjectId assignation = interpretation.get(component);
			if (assignation == null) {
				return new ArrayList<Object>();
			}
			List<SEQLPathComponent> pathComponents = path.getPathComponents();
			List<ReferenceModelObjectId> matches = this.pmngr
					.getDeepestRMObjectsForParentAndPath(assignation,
							pathComponents);
			List<Object> finalMatches = new ArrayList<Object>();
			for (ReferenceModelObjectId match : matches) {
				boolean useIdentifierOptimization=false;
				if(useIdentifierOptimization) {
					//Check if the path components actually lead to 
					//the unique id of the object. If so, use it to return
					//faster and avoid a DB query
					if(this.pathIsObjectId(match, path)) {
						long unique_id=this.pmngr.getUniqueIdFromReferenceModelObjectId(match);
						log.debug("Optimizing database query using proxy id: "+unique_id);
						PrimitiveObjectBlock proxyMatch= new PrimitiveObjectBlock(null, new StringValue(unique_id+""), null, null, null, null);
						finalMatches.add(proxyMatch);
						continue;
					} else {
						log.debug("Cannot use optimization for path "+path.toString());
					}
				}
				log.debug("Checking object cache");
				ContentObject obj;
				boolean usesObjectCache=true;
				if(usesObjectCache &&context.objectIsCached(match)) {
					log.debug("Using cached object");
					obj=(ContentObject)context.getCachedObject(match);
				} else {
					obj = this.pmngr
						.selectFromReferenceModelObjectId(match, false);
					if(usesObjectCache) {
						context.pushCachedObject(match,obj);
					}
				}
				SingleAttributeObjectBlock sblock = this.rmngr
						.getSingleAttributeObjectBlockFromContentObject(obj);
				ObjectBlock finalMatch = this.rmngr.solveReferenceModelPath(
						sblock, pathComponents);
				if (finalMatch != null) {
					finalMatches.add(finalMatch);
				}
			}
			return finalMatches;
		} catch (Exception e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	protected ContentObject solveSelectConditionForInterpretation(
			SEQLQuery query, SEQLSelectCondition cond,
			Map<SEQLFromComponent, ReferenceModelObjectId> interpretation)
			throws SEQLException {
		SEQLPath path = (SEQLPath) cond.getPath().clone();
		String identifiedVariable = cond.getIdentifiedVariableId();
		if (identifiedVariable != null) {
			SEQLFromComponent component = query.getFromCondition()
					.getFromComponentFromIdentifiedVariable(identifiedVariable);
			ReferenceModelObjectId assignation = interpretation.get(component);
			if (assignation == null) {
				// Null value
				return new ContentObject(null, new SingleAttributeObjectBlock(
						null, new ArrayList<AttributeValue>()));
			}
			Class<?> type = this.rmngr.getPathType(
					component.getReferenceModelClass(), path);
			List<SEQLPathComponent> pathComponents = path.getPathComponents();
			try {
				List<ReferenceModelObjectId> matches = this.pmngr
						.getDeepestRMObjectsForParentAndPath(assignation,
								pathComponents);
				log.debug("Matches: " + matches);
				// Deserialize all matches
				// Multiple matches have 2 possible meanings
				// Meaning one is that there are indeed multiple matches of a
				// single object
				// Multiple matches, but we do not support returning a
				// multipleobjectblock
				// Instead, we use the match to solve the proper path and return
				// all matching objects, each on a different row
				try {
					List<ContentObject> finalMatches = new ArrayList<ContentObject>();
					for (ReferenceModelObjectId match : matches) {
						ContentObject obj = this.pmngr
								.selectFromReferenceModelObjectId(match,
										cond.getWithDescendants());
						SingleAttributeObjectBlock sblock = this.rmngr
								.getSingleAttributeObjectBlockFromContentObject(obj);
						ObjectBlock finalMatch = this.rmngr
								.solveReferenceModelPath(sblock, pathComponents);
						if (finalMatch instanceof ComplexObjectBlock) {
							finalMatches.add(new ContentObject(null,
									(ComplexObjectBlock) finalMatch));
						} else {
							// Create a dummy content object
							StringValue svalue = new StringValue(
									"seql_primitive");
							PrimitiveObjectBlock pblock = new PrimitiveObjectBlock(
									null, svalue, null, null, null, null);
							AttributeValue dummyValue = new AttributeValue(
									"reference_model_class_name", pblock);
							ArrayList<AttributeValue> values = new ArrayList<AttributeValue>();
							values.add(dummyValue);
							dummyValue = new AttributeValue("value",
									(PrimitiveObjectBlock) finalMatch);
							values.add(dummyValue);
							SingleAttributeObjectBlock dummyBlock = new SingleAttributeObjectBlock(
									null, values);
							finalMatches
									.add(new ContentObject(null, dummyBlock));
						}
					}
					if (finalMatches.size() == 0) {
						// null
						return new ContentObject(null,
								new SingleAttributeObjectBlock(null,
										new ArrayList<AttributeValue>()));
					}
					if (finalMatches.size() == 1) {
						return finalMatches.get(0);
					} else {
						List<KeyedObject> keyedObjects = new ArrayList<KeyedObject>();
						int index = 0;
						for (ContentObject finalMatch : finalMatches) {
							SimpleValue<Integer> key = new IntegerValue(index++);
							keyedObjects.add(new KeyedObject(key, finalMatch
									.getComplexObjectBlock()));
						}
						return new ContentObject(null,
								new MultipleAttributeObjectBlock(null,
										keyedObjects));
					}
				} catch (Exception e) {
					throw new SEQLException(e.getMessage());
				}

				// They are singleattributeobjectblocks
				// We need to rebuild the structure for lists. That is a
				// Now the remaning path components are to be applied to
				// returned object ids in memory
			} catch (PersistenceException e) {
				throw new SEQLException(e.getMessage());
			}
		}
		return null;
	}

	protected long timeTaken(long start, String message) {
		log.debug("Time taken for " + message + ": "
				+ (System.currentTimeMillis() - start));
		return System.currentTimeMillis();
	}

	@Override
	public SEQLResultSet runQuery(SEQLQuery query) throws SEQLException {
		SEQLEvaluable evaluable = query.getFromCondition().getRoot();
		SEQLExecutionMemorySolverAssignationContext context = new SEQLExecutionMemorySolverAssignationContext();
		ReferenceModelObjectId rootId = this.pmngr.getReferenceModelRoot(); 
		try {
			/*
			 * this.evaluateContext(context);
			 */
			// Build the component hierarchy
			SEQLFromComponent rootComponent = new SEQLFromComponent(null, null,
					null);
			long start = System.currentTimeMillis();
			this.buildSEQLFromComponentHierarchy(evaluable, context,
					rootComponent);
			start = timeTaken(start, "SEQLFromComponentHierarchy");
			try {
				this.populateFromComponents(context, rootComponent, rootId,
						query);
				start = timeTaken(start, "populateFromComponents");
				HashMap<SEQLFromComponent, ReferenceModelObjectId> mapping = new HashMap<SEQLFromComponent, ReferenceModelObjectId>();
				mapping.put(rootComponent, rootId);
				List<HashMap<SEQLFromComponent, ReferenceModelObjectId>> interpretations = this
						.createInterpretations(context, rootComponent, rootId,
								mapping);
				start = timeTaken(start, "createInterpretations");
				log.debug("Number of interpretations: "
						+ interpretations.size());
				if (query.isMerged()) {
					log.debug("Merged query");
					Set<ReferenceModelObjectId> matches = new HashSet<ReferenceModelObjectId>();
					for (Map<SEQLFromComponent, ReferenceModelObjectId> interpretation : interpretations) {
						log.debug("Interpretation: " + interpretation);
						// Check if the interpretation is a model
						if (this.interpretationMatchesEvaluable(interpretation,
								context, query, evaluable, false)) {
							log.debug(">Interpretation is from clause model");
							if (this.interpretationMatchesEvaluable(
									interpretation, context, query, query
											.getWhereCondition().getRoot(),
									false)) {
								// If we arrive here, now we can use the where
								// clause to further eliminate interpretations
								log.debug(">>Interpretation is where clause model. Appending to result");
								for (SEQLSelectCondition scond : query
										.getSelectConditions()) {
									for (SEQLFromComponent comp : interpretation
											.keySet()) {
										String k1 = comp
												.getIdentifiedVariable();
										String k2 = scond
												.getIdentifiedVariableId();
										if (k1 != null && k2 != null
												&& k1.equals(k2)) {
											matches.add(interpretation
													.get(comp));
										}
									}
								}
							}
						}
					}
					log.debug("Number of merged matching objects: "
							+ matches.size());
					if (matches.size() == 0) {
						SEQLResultSet rs = new SEQLResultSet(1);
						rs.compile();
						return rs;
					}
					// Now we have all the matching objects and no repetition
					// Find the topmost parent
					String topMost = null;
					for (ReferenceModelObjectId id : matches) {
						if (topMost == null) {
							topMost = this.pmngr
									.getReferenceModelPathFoRMObject(id)
									.getFullPath();
						} else {
							SEQLPath newPath = this.pmngr
									.getReferenceModelPathFoRMObject(id);
							topMost = Utils.longestCommonPrefix(
									newPath.getFullPath(), topMost);
						}
					}
					SEQLPath topmostPath;
					if (topMost.endsWith("[")) {
						topMost = topMost.substring(0, topMost.length() - 1);
						topmostPath = new SEQLPath(topMost)
								.removeLastPathComponent();

					} else {
						topmostPath = new SEQLPath(topMost);
					}
					log.debug("Topmost string " + topMost);
					log.debug("Topmost path: " + topmostPath);
					// Solve the RM for the topmost object
					ReferenceModelObjectId ancestor = this.pmngr
							.getReferenceModelObjectIdFromReferenceModelPath(topmostPath);
					log.debug("Topmost id: " + ancestor);
					matches.add(ancestor);
					List deserializerContext = new ArrayList();
					deserializerContext.add(matches);
					if (query.getMergedVariable() != null) {
						deserializerContext.add(query);
					}
					// Now, we can select the descendants IF they are in the
					// list
					ContentObject obj = this.pmngr
							.selectFromReferenceModelObjectId(ancestor, true,
									this, deserializerContext);
					SEQLResultSet rs = new SEQLResultSet(1);
					rs.addRow();
					rs.appendToRow(obj);
					rs.compile();
					return rs;
				} else {
					log.debug("Regular query");
					SEQLResultSet rs = new SEQLResultSet(query
							.getSelectConditions().size());
					for (Map<SEQLFromComponent, ReferenceModelObjectId> interpretation : interpretations) {
						log.debug("Interpretation: " + interpretation);
						// Check if the interpretation is a model
						boolean matches = this.interpretationMatchesEvaluable(
								interpretation, context, query, evaluable,
								false);
						start = timeTaken(start,
								"interpretationMatchesEvaluable");
						if (matches) {
							log.debug(">Interpretation is from clause model");
							if (this.interpretationMatchesEvaluable(
									interpretation, context, query, query
											.getWhereCondition().getRoot(),
									false)) {
								// If we arrive here, now we can use the where
								// clause to further eliminate interpretations
								log.debug(">>Interpretation is where clause model. Appending to result");
								rs.addRow();
								for (SEQLSelectCondition scond : query
										.getSelectConditions()) {
									ContentObject r = this
											.solveSelectConditionForInterpretation(
													query, scond,
													interpretation);
									start = timeTaken(start,
											"solveSelectConditionForInterpretation");
									rs.appendToRow(r);
								}
							}
						}
					}
					// Finished. Compile the resultset
					rs.compile();
					log.info("Done query");
					return rs;
				}
			} catch (PersistenceException e) {
				e.printStackTrace();
				throw new SEQLException(e.getMessage());
			}
		} catch (SEQLException e) {
			e.printStackTrace();
			throw new SEQLException(e.getMessage());
		}

	}

	protected class SEQLExecutionMemorySolverAssignationContext {
		private Map<SEQLEvaluable, Map<ReferenceModelObjectId, List<ReferenceModelObjectId>>> assignations;
		private Map<SEQLFromComponent, List<SEQLFromComponent>> componentHierarchy;
		private List<Map<SEQLFromComponent, ReferenceModelObjectId>> interpretations;
		private Map<ReferenceModelObjectId, Object> objectCache;
		
		public List<SEQLFromComponent> getChildsForComponent(
				SEQLFromComponent component) {
			return this.componentHierarchy.get(component);
		}

		public SEQLExecutionMemorySolverAssignationContext() {
			this.componentHierarchy = new HashMap<SEQLFromComponent, List<SEQLFromComponent>>();
			this.assignations = new HashMap<SEQLEvaluable, Map<ReferenceModelObjectId, List<ReferenceModelObjectId>>>();
			this.objectCache=new HashMap<ReferenceModelObjectId,Object>();
		}
		public boolean objectIsCached(ReferenceModelObjectId oid) {
			return this.objectCache.containsKey(oid);
		}
		public Object getCachedObject(ReferenceModelObjectId oid) {
			return this.objectCache.get(oid);
		}
		public void pushCachedObject(ReferenceModelObjectId oid, Object value) {
			this.objectCache.put(oid, value);
		}
		public void clearCache() {
			this.objectCache.clear();
		}
		public void addInterpretation(
				Map<SEQLFromComponent, ReferenceModelObjectId> interpretation) {
			this.interpretations.add(interpretation);
		}

		public void addInterpretations(
				List<Map<SEQLFromComponent, ReferenceModelObjectId>> interpretations) {
			this.interpretations.addAll(interpretations);
		}

		public List<Map<SEQLFromComponent, ReferenceModelObjectId>> getInterpretations() {
			return interpretations;
		}

		public void addToComponentHierarchy(SEQLFromComponent parent,
				SEQLFromComponent child) {
			List<SEQLFromComponent> childs = this.componentHierarchy
					.get(parent);
			if (childs == null) {
				childs = new ArrayList<SEQLFromComponent>();
				this.componentHierarchy.put(parent, childs);
			}
			childs.add(child);
		}

		public List<SEQLFromComponent> getComponentChilds(
				SEQLFromComponent component) {
			return this.componentHierarchy.get(component);
		}

		public List<ReferenceModelObjectId> addAssignation(
				SEQLEvaluable evaluable, ReferenceModelObjectId newId,
				List<ReferenceModelObjectId> matches, SEQLQuery query)
				throws PersistenceException {
			Map<ReferenceModelObjectId, List<ReferenceModelObjectId>> matchMap;
			matchMap = this.assignations.get(evaluable);
			if (matchMap == null) {
				matchMap = new HashMap<ReferenceModelObjectId, List<ReferenceModelObjectId>>();
				this.assignations.put(evaluable, matchMap);
			}
			List<ReferenceModelObjectId> newMatches = new ArrayList<ReferenceModelObjectId>();
			for (ReferenceModelObjectId match : matches) {
				boolean useLookAhead = false;
				if (useLookAhead) {
					HashMap<SEQLFromComponent, ReferenceModelObjectId> mapping = new HashMap<SEQLFromComponent, ReferenceModelObjectId>();
					ReferenceModelObjectId rootId = SEQLExecutionMemorySolverStage.this.pmngr
							.getReferenceModelRoot(); // There is a single root
					SEQLFromComponent rootComponent = new SEQLFromComponent(
							null, null, null, null);
					mapping.put(rootComponent, rootId);
					mapping.put((SEQLFromComponent) evaluable, match);
					log.debug("Using lookahead optimization");
					if (SEQLExecutionMemorySolverStage.this
							.interpretationMatchesEvaluable(mapping, this,
									query, query.getWhereCondition().getRoot(),
									true)) {
						newMatches.add(match);
					} else {
						log.debug("Cutting branch for " + match);
					}
				} else {
					newMatches.add(match);
				}
			}
			matchMap.put(newId, newMatches);
			return newMatches;
		}

		public Map<ReferenceModelObjectId, List<ReferenceModelObjectId>> getAssignation(
				SEQLEvaluable evaluable) {
			return this.assignations.get(evaluable);
		}

		public void copyAssignation(SEQLEvaluable from, SEQLEvaluable to) {
			this.assignations.put(from, this.assignations.get(to));
		}
	}

	@Override
	public boolean acceptRMID(PersistenceManager persistenceManager,
			ReferenceModelObjectId id, Object context)
			throws PersistenceException {
		SEQLPath testPath = persistenceManager
				.getReferenceModelPathFoRMObject(id);
		Set<ReferenceModelObjectId> matches = (Set<ReferenceModelObjectId>) ((List) context)
				.get(0);
		for (ReferenceModelObjectId testId : matches) {
			if (persistenceManager
					.getReferenceModelPathFoRMObject(testId)
					.getFullPath()
					.startsWith(
							persistenceManager.getReferenceModelPathFoRMObject(
									id).getFullPath())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean acceptDeserializedObject(
			PersistenceManager persistenceManager, ReferenceModelObjectId id,
			ContentObject deserialized, Object context)
			throws PersistenceException {
		List contextArray = (List) context;
		if (contextArray.size() > 1) {
			SEQLQuery query = (SEQLQuery) contextArray.get(1);
			if (query.getHavingCondition().getRoot() != null) {
				return this.contentObjectMatchesEvaluable(deserialized, query
						.getHavingCondition().getRoot());
			}
		}
		return true;
	}

	protected boolean contentObjectMatchesEvaluable(ContentObject obj,
			SEQLEvaluable evaluable) throws PersistenceException {
		if (evaluable instanceof SEQLOperation) {
			SEQLOperation op = (SEQLOperation) evaluable;
			if (op.getOperator() != SEQLBooleanOperator.CONTAINS) {
				// Evaluate the operators. AND, OR XOR.
				switch (op.getOperator()) {
				case AND:
					// Then recurse
					return this.contentObjectMatchesEvaluable(obj,
							op.getLeftOperand())
							&& this.contentObjectMatchesEvaluable(obj,
									op.getRightOperand());
				case OR:
					// Then recurse
					return this.contentObjectMatchesEvaluable(obj,
							op.getLeftOperand())
							|| this.contentObjectMatchesEvaluable(obj,
									op.getRightOperand());
				case XOR:
					return this.contentObjectMatchesEvaluable(obj,
							op.getLeftOperand())
							^ this.contentObjectMatchesEvaluable(obj,
									op.getRightOperand());
				case NOT:
					return !this.contentObjectMatchesEvaluable(obj,
							op.getLeftOperand());
				default:
					Object left = null;
					Object right = null;
					try {
						left = this
								.solveOperandValueFromContentObject(
										op.getLeftOperand(),
										this.rmngr
												.getSingleAttributeObjectBlockFromContentObject(obj));
						right = this
								.solveOperandValueFromContentObject(
										op.getRightOperand(),
										this.rmngr
												.getSingleAttributeObjectBlockFromContentObject(obj));
						if (left != null && right != null
								&& !left.getClass().equals(right.getClass())) {
							// Attempt to coerce a string left value to the
							// right operator
							if (left.getClass().equals(String.class)) {
								if (right.getClass().equals(
										GregorianCalendar.class)) {
									left = this.coerceFromString((String) left,
											SEQLPrimitiveType.DATE);
								} else {
									throw new PersistenceException(
											"Unable to perform type coercion");
								}
							} else {
								throw new PersistenceException(
										" Type mismatch when using comparison operator");
							}
						}
					} catch (Exception e) {
						throw new PersistenceException(
								" Error while attempting type coercion");
					}
					switch (op.getOperator()) {
					case EXISTS:
						return left != null;
					case EQUALITY:
						return (left == null && right == null)
								|| (left != null && left.equals(right));
					case INEQUALITY:
						return (left != null || right != null)
								&& !left.equals(right);
						// For comparison, we NEED typed objects
					default:
						Comparable cl,
						cr;
						if (!(left instanceof Comparable)
								|| !(right instanceof Comparable)) {
							throw new PersistenceException("Values " + left
									+ " and " + right + " are not comparable");
						}
						cl = (Comparable) left;
						cr = (Comparable) right;
						int comparison = cl.compareTo(cr);
						switch (op.getOperator()) {
						case GE:
							return comparison >= 0;
						case GT:
							return comparison > 0;
						case LE:
							return comparison <= 0;
						case LT:
							return comparison < 0;
						default:
							throw new PersistenceException(
									"Invalid comparison operator");
						}
					}
				}
			} else {
				return this.contentObjectMatchesEvaluable(obj,
						op.getLeftOperand())
						&& this.contentObjectMatchesEvaluable(obj,
								op.getRightOperand());
			}
		} else
			return true;
	}
}
