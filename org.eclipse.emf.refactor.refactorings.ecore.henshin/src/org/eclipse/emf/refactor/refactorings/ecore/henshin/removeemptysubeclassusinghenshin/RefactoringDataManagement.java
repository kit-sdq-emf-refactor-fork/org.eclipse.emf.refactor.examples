/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringDataManagement.java,v 1.1 2011/01/19 12:09:30 tarendt Exp $
 */
 package org.eclipse.emf.refactor.refactorings.ecore.henshin.removeemptysubeclassusinghenshin;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.henshin.core.IHenshinDataManagement;
import org.eclipse.emf.refactor.henshin.core.IHenshinInformation;
import org.eclipse.emf.refactor.runtime.DataManagementAdapter;
import org.eclipse.emf.refactor.common.core.Port;

/**
 * Class for specific data concerning a model refactoring.
 * @generated
 */
public class RefactoringDataManagement extends DataManagementAdapter 
											implements IHenshinDataManagement {

	private final String SELECTEDEOBJECT = "selectedEObject";
	
	/**
	 * HenshinInformation object used for checking the initial
	 * conditions of the EMF model refactoring.
	 * @generated
	 */
	private RefactoringInformation initialConditionsInformation = 
								new RefactoringInformation(this);
								
	/**
	 * HenshinInformation object used for checking the final
	 * conditions of the EMF model refactoring.
	 * @generated
	 */							
	private RefactoringInformation finalConditionsInformation = 
								new RefactoringInformation(this);
								
	/**
	 * HenshinInformation object used for executing the EMF 
	 * model refactoring.
	 * @generated
	 */							
	private RefactoringInformation createChangeInformation = 
								new RefactoringInformation(this);

	/**
	 * Default constructor.
	 * @generated
	 */
	public RefactoringDataManagement() {
		this.addPorts();
		this.addInformation();
	}
	
	/**
	 * Adds the ports to the data management used for parameter passing.
	 * @generated
	 */
	private void addPorts(){
		this.inPorts.add
			(new Port<org.eclipse.emf.ecore.EClass>
				(SELECTEDEOBJECT, org.eclipse.emf.ecore.EClass.class));		
	}
	
	/**
	 * Adds the Henshin informations to the data management.
	 * @generated
	 */
	private void addInformation() {		
		this.initialConditionsInformation.
				setTransformationFileName("remove_empty_sub_eclass_initialcheck");					
		this.createChangeInformation.
				setTransformationFileName("remove_empty_sub_eclass_execute");
	}
	
	/**
	 * @see org.eclipse.emf.refactor.henshin.core.IHenshinDataManagement#
	 * getCreateChangeHenshinInformation()
	 * @generated
	 */
	@Override
	public IHenshinInformation getCreateChangeHenshinInformation() {
		return this.createChangeInformation;
	}

	/**
	 * @see org.eclipse.emf.refactor.henshin.core.IHenshinDataManagement#
	 * getFinalConditionsHenshinInformation()
	 * @generated
	 */
	@Override
	public IHenshinInformation getFinalConditionsHenshinInformation() {
		return this.finalConditionsInformation;
	}

	/**
	 * @see org.eclipse.emf.refactor.henshin.core.IHenshinDataManagement#
	 * getInitialConditionsHenshinInformation()
	 * @generated
	 */
	@Override
	public IHenshinInformation getInitialConditionsHenshinInformation() {
		return this.initialConditionsInformation;
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
				setValue((org.eclipse.emf.ecore.EClass) selection.get(0));
	}

}
	