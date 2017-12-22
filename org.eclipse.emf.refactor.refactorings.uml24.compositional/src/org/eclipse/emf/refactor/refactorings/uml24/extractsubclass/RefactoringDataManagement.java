 
/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringDataManagement.javajet,v 1.2 2011/01/21 13:08:06 tarendt Exp $
 */
package org.eclipse.emf.refactor.refactorings.uml24.extractsubclass;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.refactoring.core.Port;
import org.eclipse.emf.refactor.refactoring.runtime.DataManagementAdapter;
import org.eclipse.emf.refactor.refactorings.uml24.compositional.Activator;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.osgi.framework.Bundle;

import comrel.interpreter.IEObjectGetter;

/**
 * Class for specific data concerning a model refactoring.
 * @generated
 */
public class RefactoringDataManagement extends DataManagementAdapter {

	protected final String SELECTEDEOBJECT = "selectedEObject";
	protected final String COMRELMODELS = "comrelmodels";
	protected final String COMRELMODELSSLASH = "\\comrelmodels\\";
	
	private String comrelFileName = "extractsubclass.comrel";

	/**
	 * Default constructor.
	 * @generated
	 */
	public RefactoringDataManagement() {
		this.addPorts();
	}
	
	/**
	 * Adds the ports to the data management used for parameter passing.
	 * @generated
	 */
	private void addPorts(){
		this.inPorts.add
			(new Port<org.eclipse.uml2.uml.Class>
				(SELECTEDEOBJECT, org.eclipse.uml2.uml.Class.class));
		this.inPorts.add
			(new Port<UmlOperationList>
				("operationsList", UmlOperationList.class));
		this.inPorts.add
			(new Port<UmlPropertyList>
				("attributesList", UmlPropertyList.class));
		this.inPorts.add
			(new Port<String>
				("className", String.class, "unspecified"));		
	}
	
	/**
	 * @see org.eclipse.emf.refactor.common.core.IDataManagement#
	 * preselect(java.util.List)
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void preselect(List<EObject> selection) {
		ArrayList<Property> properties = new ArrayList<Property>();
		ArrayList<Operation> operations = new ArrayList<Operation>();
		Class selectedClass = null;
		for (EObject eObject : selection) {
			if (eObject instanceof Property) properties.add((Property) eObject);
			if (eObject instanceof Operation) operations.add((Operation) eObject);
			if (eObject instanceof Class) selectedClass = (Class) eObject;
		}		
		getInPortByName(SELECTEDEOBJECT).setValue(selectedClass);
		getInPortByName("attributesList").setValue(new UmlPropertyList(properties));
		getInPortByName("operationsList").setValue(new UmlOperationList(operations));
	}
	
	public String getComrelFilePath() {
		String path = "";
		final Bundle bundle = Platform.getBundle(Activator.PLUGIN_ID);
		try {
			if (bundle != null) {
				path = FileLocator.toFileURL(bundle.getEntry(COMRELMODELS)).getFile();
				path += this.comrelFileName;
			} else {
				path = new File(".").getCanonicalPath() 
						+  COMRELMODELSSLASH + this.comrelFileName;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return path;
	}
	
	public class UmlOperationList implements IEObjectGetter{
		
		public UmlOperationList(ArrayList<Operation> umlOperations) {
			super();
			this.umlOperations = umlOperations;
		}

		private ArrayList<Operation> umlOperations = new ArrayList<Operation>();
		
		public ArrayList<Operation> getUmlOperations() {
			return umlOperations;
		}

		public void setUmlOperations(ArrayList<Operation> umlOperations) {
			this.umlOperations = umlOperations;
		}

		@Override
		public EObject getEObject() {
			return umlOperations.get(0);
		}

	}
	
	public class UmlPropertyList implements IEObjectGetter{
		
		public UmlPropertyList(ArrayList<Property> umlProperties) {
			super();
			this.umlProperties = umlProperties;
		}

		private ArrayList<Property> umlProperties = new ArrayList<Property>();
		
		public ArrayList<Property> getUmlProperties() {
			return umlProperties;
		}

		public void setUmlProperties(ArrayList<Property> umlProperties) {
			this.umlProperties = umlProperties;
		}

		@Override
		public EObject getEObject() {
			return umlProperties.get(0);
		}

	}
	
	

}
	