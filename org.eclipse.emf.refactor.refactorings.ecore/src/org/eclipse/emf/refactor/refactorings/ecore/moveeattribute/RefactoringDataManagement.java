/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringDataManagement.java,v 1.2 2011/01/21 19:17:36 tarendt Exp $
 */
 package org.eclipse.emf.refactor.refactorings.ecore.moveeattribute;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.runtime.DataManagementAdapter;
import org.eclipse.emf.refactor.common.core.Port;

/**
 * Class for specific data concerning a model refactoring.
 * @generated
 */
public class RefactoringDataManagement extends DataManagementAdapter {

	protected final String SELECTEDEOBJECT = "selectedEObject";

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
			(new Port<org.eclipse.emf.ecore.EAttribute>
				(SELECTEDEOBJECT, org.eclipse.emf.ecore.EAttribute.class));
		this.inPorts.add
			(new Port<String>
				("eClassName", String.class, "unspecified"));		
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
				setValue((org.eclipse.emf.ecore.EAttribute) selection.get(0));
	}

}
	