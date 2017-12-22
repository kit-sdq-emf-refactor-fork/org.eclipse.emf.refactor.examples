 
/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringDataManagement.javajet,v 1.2 2011/01/21 13:08:06 tarendt Exp $
 */
package org.eclipse.emf.refactor.refactorings.uml24.inlineclass;

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.refactoring.core.Port;
import org.eclipse.emf.refactor.refactoring.runtime.DataManagementAdapter;
import org.eclipse.emf.refactor.refactorings.uml24.compositional.Activator;
import org.osgi.framework.Bundle;

/**
 * Class for specific data concerning a model refactoring.
 * @generated
 */
public class RefactoringDataManagement extends DataManagementAdapter {

	protected final String SELECTEDEOBJECT = "selectedEObject";
	protected final String COMRELMODELS = "comrelmodels";
	protected final String COMRELMODELSSLASH = "\\comrelmodels\\";
	
	private String comrelFileName = "inlineclass.comrel";

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
	}
	
	/**
	 * @see org.eclipse.emf.refactor.common.core.IDataManagement#
	 * preselect(java.util.List)
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void preselect(List<EObject> selection) {
		getInPortByName(SELECTEDEOBJECT).
				setValue((org.eclipse.uml2.uml.Class) selection.get(0));
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
	