/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringDataManagementHenshin.javajet,v 1.2 2012/11/26 15:39:38 tarendt Exp $
 */
package org.eclipse.emf.refactor.refactorings.uml24.renameattribute;

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

	protected final String SELECTEDEOBJECT = "selectedEObject";
		
	private final String INITIALFILENAME = "rename_attribute_initial";		
	private final String FINALFILENAME = "rename_attribute_final";	
	private final String CHANGEFILENAME = "rename_attribute_exe";
	
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
			(new Port<org.eclipse.uml2.uml.Property>
				(SELECTEDEOBJECT, org.eclipse.uml2.uml.Property.class));
		this.inPorts.add
			(new Port<String>
				("newName", String.class, "unspecified"));		
	}
	
	/**
	 * Adds the Henshin informations to the data management.
	 * @generated
	 */
	private void addInformation() {		
		this.initialConditionsInformation.setTransformationFileName(INITIALFILENAME);			
		this.finalConditionsInformation.setTransformationFileName(FINALFILENAME);			
		this.createChangeInformation.setTransformationFileName(CHANGEFILENAME);
	}
	
	/**
	 * @see org.eclipse.emf.refactor.refactoring.henshin.interfaces.IHenshinDataManagement#
	 * getCreateChangeHenshinInformation()
	 * @generated
	 */
	@Override
	public IHenshinInformation getCreateChangeHenshinInformation() {
		return this.createChangeInformation;
	}

	/**
	 * @see org.eclipse.emf.refactor.refactoring.henshin.interfaces.IHenshinDataManagement#
	 * getFinalConditionsHenshinInformation()
	 * @generated
	 */
	@Override
	public IHenshinInformation getFinalConditionsHenshinInformation() {
		return this.finalConditionsInformation;
	}

	/**
	 * @see org.eclipse.emf.refactor.refactoring.henshin.interfaces.IHenshinDataManagement#
	 * getInitialConditionsHenshinInformation()
	 * @generated
	 */
	@Override
	public IHenshinInformation getInitialConditionsHenshinInformation() {
		return this.initialConditionsInformation;
	}
	
	/**
	 * @see org.eclipse.emf.refactor.refactoring.interfaces.IDataManagement#
	 * preselect(java.util.List)
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void preselect(List<EObject> selection) {
		getInPortByName(SELECTEDEOBJECT).
				setValue((org.eclipse.uml2.uml.Property) selection.get(0));
	}

}
	