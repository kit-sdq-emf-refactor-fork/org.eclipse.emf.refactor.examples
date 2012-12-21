/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringDataManagement.java,v 1.1 2011/01/19 12:09:31 tarendt Exp $
 */
 package org.eclipse.emf.refactor.refactorings.ecore.henshin.renameeenumliteralusinghenshin;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.refactoring.core.Port;
import org.eclipse.emf.refactor.refactoring.henshin.interfaces.IHenshinDataManagement;
import org.eclipse.emf.refactor.refactoring.henshin.interfaces.IHenshinInformation;
import org.eclipse.emf.refactor.refactoring.runtime.DataManagementAdapter;

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
			(new Port<org.eclipse.emf.ecore.EEnumLiteral>
				(SELECTEDEOBJECT, org.eclipse.emf.ecore.EEnumLiteral.class));
		this.inPorts.add
			(new Port<String>
				("newName", String.class, "unspecified"));		
	}
	
	/**
	 * Adds the Henshin informations to the data management.
	 * @generated
	 */
	private void addInformation() {			
		this.finalConditionsInformation.
				setTransformationFileName("rename_eenumliteral_finalcheck");			
		this.createChangeInformation.
				setTransformationFileName("rename_eenumliteral_execute");
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
				setValue((org.eclipse.emf.ecore.EEnumLiteral) selection.get(0));
	}

}
	