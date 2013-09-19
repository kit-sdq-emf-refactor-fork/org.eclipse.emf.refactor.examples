 
/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringDataManagement.javajet,v 1.2 2011/01/21 13:08:06 tarendt Exp $
 */
package org.eclipse.emf.refactor.refactorings.uml24.introduceparameterobject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.refactoring.core.Port;
import org.eclipse.emf.refactor.refactoring.runtime.DataManagementAdapter;
import org.eclipse.emf.refactor.refactorings.uml24.compositional.Activator;
import org.eclipse.emf.refactor.refactorings.uml24.createclasswithattributesfromparameterlist.UmlParameterList;
import org.eclipse.uml2.uml.Parameter;
import org.osgi.framework.Bundle;

/**
 * Class for specific data concerning a model refactoring.
 * @generated
 */
public class RefactoringDataManagement extends DataManagementAdapter {

	protected final String SELECTEDEOBJECT = "selectedEObject";
	protected final String COMRELMODELS = "comrelmodels";
	protected final String COMRELMODELSSLASH = "\\comrelmodels\\";
	
	private String comrelFileName = "introduceparameterobject.comrel";

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
			(new Port<UmlParameterList>
				(SELECTEDEOBJECT, UmlParameterList.class));
		this.inPorts.add
			(new Port<String>
				("className", String.class, "unspecified"));
		this.inPorts.add
			(new Port<String>
				("paramName", String.class, "unspecified"));		
	}
	
	/**
	 * @see org.eclipse.emf.refactor.common.core.IDataManagement#
	 * preselect(java.util.List)
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void preselect(List<EObject> selection) {
		ArrayList<Parameter> umlParameters = new ArrayList<Parameter>();
		for (EObject eObject : selection) {
			if (eObject instanceof Parameter) umlParameters.add((Parameter) eObject);
		}		
		getInPortByName(SELECTEDEOBJECT).setValue(new UmlParameterList(umlParameters));
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

}
	