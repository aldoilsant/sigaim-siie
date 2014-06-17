package org.sigaim.siie.rm;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openehr.am.parser.AttributeValue;
import org.openehr.am.parser.BooleanValue;
import org.openehr.am.parser.CharacterValue;
import org.openehr.am.parser.ComplexObjectBlock;
import org.openehr.am.parser.ContentObject;
import org.openehr.am.parser.IntegerValue;
import org.openehr.am.parser.KeyedObject;
import org.openehr.am.parser.MultipleAttributeObjectBlock;
import org.openehr.am.parser.ObjectBlock;
import org.openehr.am.parser.PrimitiveObjectBlock;
import org.openehr.am.parser.RealValue;
import org.openehr.am.parser.SimpleValue;
import org.openehr.am.parser.SingleAttributeObjectBlock;
import org.openehr.am.parser.StringValue;
import org.sigaim.siie.dadl.DADLManager;
import org.sigaim.siie.dadl.exceptions.SemanticDADLException;
import org.sigaim.siie.rm.exceptions.ReferenceModelException;
import org.sigaim.siie.seql.parser.model.SEQLPath;
import org.sigaim.siie.seql.parser.model.SEQLPathComponent;
import org.sigaim.siie.utils.Utils;

public class ReflectorReferenceModelManager implements ReferenceModelManager{
	private Map<String,Class<?>> classesForString;
	private List<Class<?>> rmObjects;
	private Class<?> root;
	private DADLManager dadlManager;
	
	public boolean isRMObjectClass(Class<?> tclass) {
		return rmObjects.contains(tclass);
	}
	/**
	 * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
	 *
	 * @param packageName The base package
	 * @return The classes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private static Map<String,Class<?>> getClasses(String packageName)
	        throws ClassNotFoundException, IOException {
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    assert classLoader != null;
	    String path = packageName.replace('.', '/');
	    Enumeration<URL> resources = classLoader.getResources(path);
	    List<File> dirs = new ArrayList<File>();
	    while (resources.hasMoreElements()) {
	        URL resource = resources.nextElement();
	        dirs.add(new File(resource.getFile()));
	    }
	    HashMap<String,Class<?>> classes = new HashMap<String,Class<?>>();
	    for (File directory : dirs) {
	        classes.putAll(findClasses(directory, packageName));
	    }
	    return classes;
	}

	/**
	 * Recursive method used to find all classes in a given directory and subdirs.
	 *
	 * @param directory   The base directory
	 * @param packageName The package name for classes found inside the base directory
	 * @return The classes
	 * @throws ClassNotFoundException
	 */
	private static HashMap<String,Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
	    HashMap<String,Class<?>> classes = new HashMap<String,Class<?>>();
	    if (!directory.exists()) {
	        return classes;
	    }
	    File[] files = directory.listFiles();
	    for (File file : files) {
	        if (file.isDirectory()) {
	            assert !file.getName().contains(".");
	            classes.putAll(findClasses(file, packageName + "." + file.getName()));
	        } else if (file.getName().endsWith(".class")) {
	        	String className=file.getName().substring(0, file.getName().length() - 6);
	            classes.put(className.toLowerCase(),Class.forName(packageName + '.' + className));
	        }
	    }
	    return classes;
	}
	private static List<Class<?>> getRMObjectClasses() {
		List<Class<?>> objects=new ArrayList<Class<?>>();
		objects.add(org.sigaim.siie.iso13606.rm.EHRExtract.class);
		objects.add(org.sigaim.siie.iso13606.rm.RecordComponent.class);
		objects.add(org.sigaim.siie.iso13606.rm.Composition.class);
		objects.add(org.sigaim.siie.iso13606.rm.Folder.class);
		objects.add(org.sigaim.siie.iso13606.rm.Content.class);
		objects.add(org.sigaim.siie.iso13606.rm.Section.class);
		objects.add(org.sigaim.siie.iso13606.rm.Entry.class);
		objects.add(org.sigaim.siie.iso13606.rm.Item.class);
		objects.add(org.sigaim.siie.iso13606.rm.Cluster.class);
		objects.add(org.sigaim.siie.iso13606.rm.Element.class);
		objects.add(org.sigaim.siie.iso13606.rm.ExtractCriteria.class);
		objects.add(org.sigaim.siie.iso13606.rm.AuditInfo.class);
		objects.add(org.sigaim.siie.iso13606.rm.AttestationInfo.class);
		objects.add(org.sigaim.siie.iso13606.rm.FunctionalRole.class);
		objects.add(org.sigaim.siie.iso13606.rm.RelatedParty.class);
		objects.add(org.sigaim.siie.iso13606.rm.Link.class);
		return objects;
	}
	
	public ReflectorReferenceModelManager(DADLManager dadlManager) {
		this.dadlManager=dadlManager;
		try {
			classesForString=ReflectorReferenceModelManager.getClasses("org.sigaim.siie.iso13606.rm");
			rmObjects=ReflectorReferenceModelManager.getRMObjectClasses();
			System.out.println("Loaded "+classesForString.size()+" reference model classes");
		} catch(Exception e){
			
		}
		root=org.sigaim.siie.iso13606.rm.EHRExtract.class;
	}
		
	@Override
	public Class<?> referenceModelClassFromString(String sclass) {
		return classesForString.get(sclass.toLowerCase());
	}
	
	public boolean isReferenceModelClass(Class<?> tclass) {
		String className=tclass.getSimpleName().toLowerCase();
		if(classesForString.get(className)!=null) {
			return true;
		} else return false;
	}

	private List<Class<?>> getSubclassesOrSelf(Class<?> base) {
		ArrayList<Class<?>> ret=new ArrayList<Class<?>>();
		for(Class<?> rmClass : classesForString.values()) {
			if(base.isAssignableFrom(rmClass)) {
				ret.add(rmClass);
			}
		}
		return ret;
	}
	@Override
	public ObjectBlock solveReferenceModelPath(SingleAttributeObjectBlock block, List<SEQLPathComponent> components) throws ReferenceModelException { 
		//Use the path components to solve the serialized value 
		if(components.size()==0) return block;
		List<SEQLPathComponent> subComponents=new ArrayList<SEQLPathComponent>(components);
		SEQLPathComponent component=subComponents.remove(0);
		String attributeName=component.getPathIdentifier().toLowerCase();
		for(AttributeValue value : block.getAttributeValues()) {
			if(value.getId().equals(attributeName)) {
				ObjectBlock child=value.getValue();
				if(child instanceof SingleAttributeObjectBlock) {
					//recurse
					return this.solveReferenceModelPath((SingleAttributeObjectBlock)child, subComponents);
				} else if(child instanceof MultipleAttributeObjectBlock) {
					//Use the predicate as key of the collection
					MultipleAttributeObjectBlock mblock=(MultipleAttributeObjectBlock)child;
					//Iterate over the key objects and find the correct key
					for(KeyedObject keyedObject: mblock.getKeyObjects()) {
						String key=this.dadlManager.serializeSimpleValue(keyedObject.getKey());
						if(key.equals(component.getPathPredicate().getKey2())) {
							ObjectBlock kvalue=keyedObject.getObject();
							if(kvalue instanceof SingleAttributeObjectBlock) {
								return this.solveReferenceModelPath((SingleAttributeObjectBlock)kvalue, subComponents);
							} else { //Primitive
								if(subComponents.size()>0) {
									throw new ReferenceModelException("Found path dereferencing primitive object block");
								}
								return kvalue;
							}
						}
					}
					throw new ReferenceModelException("Unable to solve path "+component+" for block "+block);
				} else if (child instanceof PrimitiveObjectBlock) {
					if(subComponents.size()>0) {
						throw new ReferenceModelException("Found path dereferencing primitive object block");
					}
					return child;
				}
			}
		}
		return null;
		//throw new ReferenceModelException("Unknown path component "+attributeName+" for block "+block);
	}
	private Class<?> getPathTypeForComponent(Class<?> referenceModelClass, List<SEQLPathComponent> components) {
		if(components.size()==0) return referenceModelClass;
		List<SEQLPathComponent> subComponents=new ArrayList<SEQLPathComponent>(components);
		SEQLPathComponent component=subComponents.remove(0);
		String attributeName=component.getPathIdentifier().toLowerCase();
		try {
			PropertyDescriptor[] descriptors=Introspector.getBeanInfo(referenceModelClass).getPropertyDescriptors();
			for(PropertyDescriptor descriptor: descriptors) {
				Method readMethod=descriptor.getReadMethod();
				if(readMethod!=null) {
					String methodName=readMethod.getName().toLowerCase();
					if(methodName.equals("get"+attributeName)) {
						Type t=readMethod.getGenericReturnType();
						if( t instanceof ParameterizedType ) {
					    	ParameterizedType pt=(ParameterizedType)t;
					    	Type[] parameters=pt.getActualTypeArguments();
					    	referenceModelClass=(Class<?>) parameters[0];
						} else if(t instanceof Class) {
						    referenceModelClass=(Class<?>) t;
						}
						List<Class<?>> subclasses=this.getSubclassesOrSelf(referenceModelClass);
						for(Class<?> subclass : subclasses) {
							Class<?> ret=this.getPathTypeForComponent(subclass, subComponents);
							if(ret!=null) {
								return ret;
							}
						}
						return null;
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Class<?> getPathType(String sreferenceModelClass, SEQLPath path) {
		Class<?> referenceModelClass=null;
		boolean isAbsolute=false;
		if(sreferenceModelClass==null) {
			referenceModelClass=root;
			isAbsolute=true;
		} else {
			referenceModelClass=this.referenceModelClassFromString(sreferenceModelClass);
		}
		List<SEQLPathComponent> components=path.getPathComponents();
		if(components==null || components.size()==0) {
			return this.referenceModelClassFromString(sreferenceModelClass);
		}
		path=path.toUppercaseNotation();
		components=path.getPathComponents();
		if(!isAbsolute) { //remove identifiedVariable
			components.remove(0);
		}
		List<Class<?>> subclasses=this.getSubclassesOrSelf(referenceModelClass);
		for(Class<?> subclass : subclasses) {
			Class<?> ret=this.getPathTypeForComponent(subclass, components);
			if(ret!=null) return ret;
		}
		return null;
	}
	private Object bindMultipleAttributeObjectBlock(MultipleAttributeObjectBlock block) throws SemanticDADLException, ReferenceModelException {
		SimpleValue keySample=block.getKeyObjects().get(0).getKey();
		if(keySample instanceof IntegerValue) { //Cast to List
			ArrayList multipleValue=new ArrayList();
			for(KeyedObject obj : block.getKeyObjects()) {
				Object value=this.bindObjectBlock(obj.getObject());
				int key=((IntegerValue)obj.getKey()).getValue().intValue()-1; //Note indexes start with 1
				multipleValue.add(key,value);
			}
			return multipleValue;
		} else { //Cast to map
			HashMap map=new HashMap();
			for(KeyedObject obj : block.getKeyObjects()) {
				Object value=this.bindObjectBlock(obj.getObject());
				Object key=(obj.getKey()).getValue();
				map.put(key,value);
			}
			return map;
		}
	}
	private Object bindComplexObjectBlock(ComplexObjectBlock block) throws SemanticDADLException, ReferenceModelException {
		if(block instanceof MultipleAttributeObjectBlock) {
			return this.bindMultipleAttributeObjectBlock((MultipleAttributeObjectBlock)block);
		} else {
			return this.bindSingleAttributeObjectBlock((SingleAttributeObjectBlock)block);
		}
	}
	private Object bindPrimitiveObjectBlock(PrimitiveObjectBlock block) throws SemanticDADLException, ReferenceModelException  {
		if(block.getSimpleValue()!=null) {
			return block.getSimpleValue().getValue();
		} else {
			throw new ReferenceModelException("Binding of primitive values other than SimpleValue is not yet implemented");
		}
	}
	private Object bindObjectBlock(ObjectBlock block) throws SemanticDADLException, ReferenceModelException {
		if(block instanceof ComplexObjectBlock) {
			return this.bindComplexObjectBlock((ComplexObjectBlock) block);
		} else {
			return this.bindPrimitiveObjectBlock((PrimitiveObjectBlock) block);
		}
	}
	public String getReferenceModelClassName(SingleAttributeObjectBlock block) throws SemanticDADLException {
		String referenceModelClassName=null;
		for(AttributeValue value : block.getAttributeValues()) {
			if(value.getId().equals("reference_model_class_name")) {
				try {
					PrimitiveObjectBlock pblock=(PrimitiveObjectBlock)value.getValue();
					referenceModelClassName=Utils.toUppercaseNotation(pblock.getSimpleValue().getValue().toString().toLowerCase());
				} catch(Exception e) {
					throw new SemanticDADLException("Invalid value for referenceModelClassName");
				}
				break;
			}
		}
		/*if(referenceModelClassName==null) {
			throw new SemanticDADLException("Missing reference model class name for block: "+block);
		}*/
		return referenceModelClassName;
	}
	private Object bindSingleAttributeObjectBlock(SingleAttributeObjectBlock block)  throws SemanticDADLException, ReferenceModelException{
		//Attempt to get the reference model class name
		String referenceModelClassName=null;
		try {
			referenceModelClassName=this.getReferenceModelClassName(block);
		} catch(Exception e) {}
		if(referenceModelClassName==null) {
			return null;
			//throw new SemanticDADLException("Object blocks must have the mandatory field referenceModelClassName");
		}
		Class<?> objectClass=this.referenceModelClassFromString(referenceModelClassName);
		if(objectClass==null) {
			throw new SemanticDADLException("Uknown reference model class: "+referenceModelClassName);
		}
		//Create instance of the class
		Object instance=null;
		try {
			instance=objectClass.newInstance();
		} catch(Exception e) {
			throw new ReferenceModelException("Error creating reference model class instance");
		}
		//Now bind the attributes using setters. For that, we have to recursively instance each value
		try {
			for(AttributeValue value : block.getAttributeValues()) {
				String id=Utils.toUppercaseNotation(value.getId()).toLowerCase();
				if(!id.equals("referencemodelclassname")) {
					Object setterParameter=this.bindObjectBlock(value.getValue());
					if(setterParameter==null) continue;
					//Attempt to find the property setter
					PropertyDescriptor[] descriptors=Introspector.getBeanInfo(objectClass).getPropertyDescriptors();
					boolean found=false;
					for(PropertyDescriptor descriptor: descriptors) {
						if(Collection.class.isAssignableFrom(setterParameter.getClass())) {
							//We must use the getter and add
							Method method=descriptor.getReadMethod();
							if(method!=null) {
								String methodName=method.getName().toLowerCase();
								if(methodName.equals("get"+id)) {
									//Found getter
									Object collection=method.invoke(instance);
									Class collectionClass=collection.getClass();
									for(Method collectionMethod:collectionClass.getMethods()) {
										String addMethodName=collectionMethod.getName();
										if(addMethodName.equals("addAll")) {
											//Call add method
											collectionMethod.invoke(collection, setterParameter);
											found=true;
											break;
										} else if(addMethodName.equals("putAll")) {
											collectionMethod.invoke(collection, setterParameter);
											found=true;
											break;
										}
									}
								}
							}
						} else {
							Method method=descriptor.getWriteMethod();
							if(method!=null) {
								String methodName=method.getName().toLowerCase();
								if(methodName.equals("set"+id)) {
									//Found setter
									if(setterParameter==null) {
										throw new ReferenceModelException("Unable to bind object for attribute"+value.getId());
									}
									if(!method.getParameterTypes()[0].isAssignableFrom(setterParameter.getClass())) {
										//Attempt primitive to object binding
										if(setterParameter instanceof Boolean && method.getParameterTypes()[0].equals(boolean.class)) {
											//Use autoboxing
											method.invoke(instance, setterParameter);
										} else if(setterParameter instanceof String) {
											Class<?> parameterType=method.getParameterTypes()[0];
											if(parameterType.isEnum()) {
												try {
													method.invoke(instance, Enum.valueOf((Class<Enum>) parameterType, (String)setterParameter));
												} catch(Exception e) {
													e.printStackTrace();
													throw new ReferenceModelException("Unable to convert enum value "+setterParameter);
												}
											}
										} else {
											throw new ReferenceModelException("Unable to cast object "+setterParameter+" to "+method.getParameterTypes()[0].getName());
										}
									} else {
										method.invoke(instance, setterParameter);
									}
									found=true;
									break;
								}
							}
						}
					}
					if(!found) throw new ReferenceModelException("Unable to get setter for class "+objectClass.getName()+" and attribute "+value.getId() + " with value "+setterParameter);
				}	
			}
		} 
		catch(ReferenceModelException e) {
			throw e;
		} catch(SemanticDADLException e2) {
			throw e2;
		}
		catch(Exception e3) {
			e3.printStackTrace();
			throw new ReferenceModelException("Error binding attributes to class "+objectClass.getName());
		}
		return instance;
	}
	public Object bind(ContentObject obj) throws SemanticDADLException, ReferenceModelException{
		if(obj.getAttributeValues()!=null) {
			throw new SemanticDADLException("Root of DADL reference model file must be an object block");
		} else {
			ComplexObjectBlock block=obj.getComplexObjectBlock();
			//Find the reference model class
			if(!(block instanceof SingleAttributeObjectBlock)) {
				throw new SemanticDADLException("Root of DADL reference model file must be a single attribute object block");
			} else {
				SingleAttributeObjectBlock sblock=(SingleAttributeObjectBlock) block;
				return this.bindSingleAttributeObjectBlock(sblock);
			}
		}
	}
	
	private PrimitiveObjectBlock createSingleValuePrimitiveObjectBlock(SimpleValue value) {
		return new PrimitiveObjectBlock(null,value,null,null,null,null);
	}
	public SingleAttributeObjectBlock getSingleAttributeObjectBlockFromContentObject(ContentObject obj) throws SemanticDADLException{
		if(obj.getAttributeValues()!=null) {
			throw new SemanticDADLException("Root  must be an object block");
		} else {
			ComplexObjectBlock block=obj.getComplexObjectBlock();
			//Find the reference model class
			if(!(block instanceof SingleAttributeObjectBlock)) {
				throw new SemanticDADLException("Root must be a single attribute object block");
			} else {
				SingleAttributeObjectBlock sblock=(SingleAttributeObjectBlock) block;
				return sblock;
			}
		}
		
	}
	private SimpleValue unbindSimpleValue(Object obj) throws ReferenceModelException  {
		if(obj instanceof Boolean) {
			return  new BooleanValue((Boolean)obj);
		} else if(obj instanceof Character) {
			return new CharacterValue((Character)obj);
		} else if(obj instanceof Integer) {
			return  new IntegerValue((Integer)obj);
		} else if(obj instanceof Double) {
			return new RealValue((Double) obj);
		} else if(obj instanceof Float) {
			//Without precision loss
			Float f=(Float) obj;
			float fv=f.floatValue();
			double dv=(double)fv;
			return new RealValue(dv); //autoboxing
		} else if(obj instanceof String) {
			return new StringValue(""+obj);
		} else if(obj instanceof Enum) {
			return new StringValue(""+((Enum)obj));
		} else {
			throw new ReferenceModelException("Cannot serialize object of class "+obj.getClass().getName()+": not implemented");
		}
	}
	private PrimitiveObjectBlock unbindPrimitiveObject(Object obj) throws ReferenceModelException {
		return this.createSingleValuePrimitiveObjectBlock(this.unbindSimpleValue(obj));
	}
	
	private MultipleAttributeObjectBlock unbindCollection(Collection col) throws ReferenceModelException{
		List<KeyedObject> kobjects=new ArrayList<KeyedObject>();
		if(List.class.isAssignableFrom(col.getClass())) {
			int i=1;
			for(Object contained: (List)col) {
				KeyedObject obj= new KeyedObject(new IntegerValue(new Integer(i++)),this.unbindGeneric(contained));
				kobjects.add(obj);
			}
		} else if(Map.class.isAssignableFrom(col.getClass())) { 
			Map colMap=(Map)col;
			for(Object key : colMap.keySet()) {
				KeyedObject obj= new KeyedObject(this.unbindSimpleValue(key),this.unbindGeneric(colMap.get(key)));
				kobjects.add(obj);
			}
			
		} else throw new ReferenceModelException("Cannot serialize collection of type "+col.getClass().getName()+": not implemented");
		return new MultipleAttributeObjectBlock(null, kobjects);
	}
	private ObjectBlock unbindGeneric(Object obj) throws ReferenceModelException {
		if(this.isReferenceModelClass(obj.getClass()) && !(obj instanceof Enum)) {
			return this.unbindObject(obj);
		} else if(Collection.class.isAssignableFrom(obj.getClass())) {
			return this.unbindCollection((Collection)obj);
		} else {
			return this.unbindPrimitiveObject(obj);
		}
	}
	private SingleAttributeObjectBlock unbindObject(Object object) {
		//Iterate all getters. If it is a primitive create a primitive, if it is a collection
		//create a keyed object, and if not create another single attribute object
		try {
			List<AttributeValue> attributeValues=new ArrayList<AttributeValue>();
			//Add the reference model class name attribute
			attributeValues.add(new AttributeValue("reference_model_class_name",
					new PrimitiveObjectBlock(null,new StringValue(object.getClass().getSimpleName()),null,null,null,null)
					)
			);
			//This way we ignore Object class properties
			PropertyDescriptor[] descriptors=Introspector.getBeanInfo(object.getClass(),Object.class).getPropertyDescriptors();
			for(PropertyDescriptor descriptor : descriptors) {
				Method readMethod=descriptor.getReadMethod();
				if(readMethod!=null) {
					//Invoke the reader 
					Object propertyObject=readMethod.invoke(object);
					ObjectBlock unbinded;
					if(propertyObject!=null) {
						unbinded=this.unbindGeneric(propertyObject);
						if(unbinded!=null) {
							AttributeValue newValue=new AttributeValue(Utils.toUnderscoreNotation(descriptor.getName()),unbinded);
							attributeValues.add(newValue);
						}
					}
				}
			}
			return new SingleAttributeObjectBlock(null, attributeValues);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public ContentObject unbind(Object root) throws ReferenceModelException {
		//Each object is a singleattributeobjectblock
		//Each collection is a multipleattributeobjectblock
		//A primitive is a PrimitiveObjectBlock that has a given value
		SingleAttributeObjectBlock sblock=this.unbindObject(root);
		return new ContentObject(null,sblock);
	}
	private SimpleValue getSimpleValueFromObjectBlock(SingleAttributeObjectBlock block) {
		for(AttributeValue vl: block.getAttributeValues()) {
			if(vl.getId().equals("reference_model_class_name")) {
				return ((PrimitiveObjectBlock) vl.getValue()).getSimpleValue();
			}
		}
		return null;
	}

	private SEQLPathComponent createPathComponentFromSingleAttributeObjectBlock(String id, SingleAttributeObjectBlock block, SimpleValue collectionKey) throws ReferenceModelException{
		//Create the path component. Add the id and possible the collection "index"
		String sPath=id;
		if(collectionKey!=null) {
			sPath+="["+this.dadlManager.serializeSimpleValue(collectionKey)+"]";
		}
		return new SEQLPathComponent(sPath);
	}
	
	@Override
	public Map<SEQLPathComponent,SingleAttributeObjectBlock> splitForRMObjectVsDataObject(SingleAttributeObjectBlock block) throws SemanticDADLException, ReferenceModelException{
		//Recurse and if we find a collection or a single attribute value that is an rmobject add it to the list
		//We need some kind of path to "store" these other objects, that is, save the relation with the input block
		Map<SEQLPathComponent, SingleAttributeObjectBlock> ret=new HashMap<SEQLPathComponent, SingleAttributeObjectBlock>();
		List<AttributeValue> toRemove=new ArrayList<AttributeValue>();
		//First, we iterate all the data values
		for(AttributeValue value : block.getAttributeValues()) {
			ObjectBlock atvalue=value.getValue();
			//The only option for being an rmobject is a ComplexObjectBlock
			if(atvalue instanceof ComplexObjectBlock) {
				//Either a collection or another object
				if(atvalue instanceof SingleAttributeObjectBlock) {
					//Object, directly test for rmobject class
					SingleAttributeObjectBlock sblock=(SingleAttributeObjectBlock) atvalue;
					String referenceModelClassName=this.getReferenceModelClassName(sblock);
					if(referenceModelClassName==null) {
						//Continue: missing value;
						continue;
					}
					Class<?> referenceModelClass=this.referenceModelClassFromString(referenceModelClassName);
					if(this.isRMObjectClass(referenceModelClass)) {
						toRemove.add(value);
						ret.put(this.createPathComponentFromSingleAttributeObjectBlock(value.getId(),sblock,null), sblock);
						//Remove the block and add it to the result. 
					} //Else nothing. A data value that will be serialized in with the container rm object
				} else { //MultipleAttributeObjectBlock, collection
					MultipleAttributeObjectBlock mblock=(MultipleAttributeObjectBlock) atvalue;
					//Test if this is a primitive collection or a rmobject collection
					if(mblock.getKeyObjects().size()>0) {
						ComplexObjectBlock tblock=(ComplexObjectBlock)mblock.getKeyObjects().get(0).getObject();
						if(tblock instanceof SingleAttributeObjectBlock) {
							SingleAttributeObjectBlock sblock=(SingleAttributeObjectBlock) tblock;
							String referenceModelClassName=this.getReferenceModelClassName(sblock);
							Class<?> referenceModelClass=this.referenceModelClassFromString(referenceModelClassName);
							if(this.isRMObjectClass(referenceModelClass)) {
								toRemove.add(value);
								//Iterate the collection and create the paths
								for(KeyedObject keyedObject : mblock.getKeyObjects()) {
									ret.put(this.createPathComponentFromSingleAttributeObjectBlock(value.getId(),sblock, keyedObject.getKey()),(SingleAttributeObjectBlock)keyedObject.getObject());
								}
							}
						}
					}
				}
			} 
		}
		block.getAttributeValues().removeAll(toRemove);
		return ret;
	}
	protected SEQLPath getArchetypeIdPath() {
		return  new SEQLPath("meaning/code_system_name");
	}

	protected SEQLPath getArchetypeNodePath() {
		return new SEQLPath("meaning/code");
	}
	public String getArchetypeIdForRMObject(SingleAttributeObjectBlock block) throws ReferenceModelException {
		ObjectBlock oblock=this.solveReferenceModelPath(block, this.getArchetypeIdPath().getPathComponents());
		if(oblock==null) return null;
		if(!(oblock instanceof PrimitiveObjectBlock)) {
			throw new ReferenceModelException("Archetype id path is not a primitive object block");
		}
		PrimitiveObjectBlock pblock=(PrimitiveObjectBlock) oblock;
		SimpleValue value=pblock.getSimpleValue();
		if(value==null) {
			throw new ReferenceModelException("Invalid primitive object block for archetype id");
		}
		return value.getValue().toString();
	}
	public String getArchetypeNodeIdForRMObject(SingleAttributeObjectBlock block) throws ReferenceModelException {
		ObjectBlock oblock=this.solveReferenceModelPath(block, this.getArchetypeNodePath().getPathComponents());
		if(oblock==null) return null;
		if(!(oblock instanceof PrimitiveObjectBlock)) {
			throw new ReferenceModelException("Archetype id path is not a primitive object block");
		}
		PrimitiveObjectBlock pblock=(PrimitiveObjectBlock) oblock;
		SimpleValue value=pblock.getSimpleValue();
		if(value==null) {
			throw new ReferenceModelException("Invalid primitive object block for archetype id");
		}
		return value.getValue().toString();
	}
	@Override
	public Class<?> getRootClass() {
		return org.sigaim.siie.iso13606.rm.EHRExtract.class;
	}
	public boolean isArchetypedClass(Class<?> tclass) {
		return org.sigaim.siie.iso13606.rm.RecordComponent.class.isAssignableFrom(tclass);
	}
}
