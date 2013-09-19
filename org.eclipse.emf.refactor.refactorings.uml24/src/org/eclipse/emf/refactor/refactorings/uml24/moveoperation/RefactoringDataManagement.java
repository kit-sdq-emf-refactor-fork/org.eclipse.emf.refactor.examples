/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringDataManagement.javajet,v 1.2 2011/01/21 13:08:06 tarendt Exp $
 */
 package org.eclipse.emf.refactor.refactorings.uml24.moveoperation;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.refactoring.core.Port;
import org.eclipse.emf.refactor.refactoring.runtime.DataManagementAdapter;

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
			(new Port<org.eclipse.uml2.uml.Operation>
				(SELECTEDEOBJECT, org.eclipse.uml2.uml.Operation.class));
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
		getInPortByName(SELECTEDEOBJECT).
				setValue((org.eclipse.uml2.uml.Operation) selection.get(0));
	}

}
	