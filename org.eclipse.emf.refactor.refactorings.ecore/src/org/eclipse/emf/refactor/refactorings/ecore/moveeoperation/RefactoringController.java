/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringController.java,v 1.1 2011/01/19 12:04:36 tarendt Exp $
 */
 package org.eclipse.emf.refactor.refactorings.ecore.moveeoperation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.refactor.common.core.EmfRefactoring;
import org.eclipse.emf.refactor.common.core.IController;
import org.eclipse.emf.refactor.common.core.IDataManagement;
import org.eclipse.emf.refactor.refactorings.ecore.RefactoringHelper;
import org.eclipse.emf.refactor.runtime.ltk.LtkEmfRefactoringProcessorAdapter;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;

public final class RefactoringController implements IController{

	/**
	 * EmfRefactoring supported by the controller.
	 * @generated
	 */
	private EmfRefactoring parent;
	
	/**
	 * DataManagement object of the model refactoring.
	 * @generated
	 */
	private RefactoringDataManagement dataManagement = 
									new RefactoringDataManagement();
	
	/**
	 * Invocation context of the model refactoring.
	 * @generated
	 */	
	private List<EObject> selection = new ArrayList<EObject>();
	
	/**
	 * Ltk RefactoringProcessor of the model refactoring.
	 * @generated
	 */
	private InternalRefactoringProcessor refactoringProcessor = null;
	
	/**
	 * Gets the EmfRefactoring supported by the controller.
	 * @return EmfRefactoring supported by the controller.
	 * @see org.eclipse.emf.refactor.common.core.IController#getParent()
	 * @generated
	 */
	@Override
	public EmfRefactoring getParent() {
		return this.parent;
	}
	
	/**
	 * Sets the EmfRefactoring supported by the controller.
	 * @param emfRefactoring EmfRefactoring supported by the controller.
	 * @see org.eclipse.emf.refactor.common.core.IController#
	 * setParent(org.eclipse.emf.refactor.common.core.EmfRefactoring)
	 * @generated
	 */
	@Override
	public void setParent(EmfRefactoring emfRefactoring) {
		this.parent = emfRefactoring;
	}
	
	/**
	 * Returns the DataManagement object of the model refactoring.
	 * @return DataManagement object of the model refactoring.
	 * @see org.eclipse.emf.refactor.henshin.core.IHenshinController#
	 * getDataManagementObject()
	 * @generated
	 */
	@Override
	public IDataManagement getDataManagementObject() {
		return this.dataManagement;
	}

	/**
	 * Returns the ltk RefactoringProcessor of the model refactoring.
	 * @return Ltk RefactoringProcessor of the model refactoring.
	 * @see org.eclipse.emf.refactor.common.core.IController#
	 * getLtkRefactoringProcessor()
	 * @generated
	 */
	@Override
	public RefactoringProcessor getLtkRefactoringProcessor() {
		return this.refactoringProcessor;
	}
	
	/**
	 * Sets the selected EObject (invocation context of the model refactoring).
	 * @param selection Invocation context of the model refactoring.
	 * @see org.eclipse.emf.refactor.common.core.IController#
	 * setSelection(java.util.List)
	 * @generated
	 */
	@Override
	public void setSelection(List<EObject> selection) {
		this.selection = selection;
		this.refactoringProcessor = 
				new InternalRefactoringProcessor(this.selection);
	}	
	
	/**
	 * Returns a Runnable object that executes the model refactoring.
	 * @return Runnable object that executes the model refactoring.
	 * @generated
	 */
	private Runnable applyRefactoring() {
		return new Runnable() {				
			/**
			 * @see java.lang.Runnable#run()
			 * @generated
			 */
			@Override
			public void run() {
				// begin custom code
				EOperation selectedEOperation = (EOperation) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
				String eClassName = 
					(String) dataManagement.getInPortByName("eClassName").getValue();
				EClass containingClass = selectedEOperation.getEContainingClass();
				for (org.eclipse.emf.ecore.EReference eRef : containingClass.getEReferences()){   
					if (eRef.getEReferenceType().getName().equals(eClassName)){
						containingClass.getEOperations().remove(selectedEOperation);
						eRef.getEReferenceType().getEOperations().add(selectedEOperation);
						break;
					}
				}	
				// end custom code
			}
		};
	}

	/**
	 * Internal class for providing an instance of a LTK RefactoringProcessor 
	 * used for EMF model refactorings using Henshin transformations.	 
	 * @generated
	 */
	public final class InternalRefactoringProcessor extends 
									LtkEmfRefactoringProcessorAdapter {

		/**
		 * Constructor using the invocation context of the model refactoring.
		 * @param selection Invocation context of the model refactoring.
		 * @generated
		 */
		private InternalRefactoringProcessor(List<EObject> selection){
				super(getParent(), selection, applyRefactoring());				
		}
			
		/**
		 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#
	 	 * checkInitialConditions(org.eclipse.core.runtime.IProgressMonitor)
		 * @generated
		 */	
		@Override
		public RefactoringStatus checkInitialConditions(){
				RefactoringStatus result = new RefactoringStatus();
				// begin custom code
				EOperation selectedEOperation = (EOperation) dataManagement.
				getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
				// initial check: the containing EClass must have at least one EReference
				if (selectedEOperation.getEContainingClass().getEReferences().isEmpty()) {
					result.addFatalError("The selected EOperation cannot be moved " +
							"since its containing EClass does not have any EReferences!");
				}
				// end custom code
				return result;
		}
		
		/**
		 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#
	     * checkFinalConditions(org.eclipse.core.runtime.IProgressMonitor, 
	     * org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext)
		 * @generated
		 */	
		@Override
		public RefactoringStatus checkFinalConditions(){
				RefactoringStatus result = new RefactoringStatus();
				// begin custom code
				EOperation selectedEOperation = (EOperation) dataManagement.
					getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
				String eClassName = 
					(String) dataManagement.getInPortByName("eClassName").getValue();
				// final check 1: the containing EClass must be referencing an EClass 
				// with the specified name
				EClass referencedEClass = null;
				for (org.eclipse.emf.ecore.EReference eRef : 
								selectedEOperation.getEContainingClass().getEReferences()){
					if (eRef.getEReferenceType().getName().equals(eClassName)){
						referencedEClass = eRef.getEReferenceType();
					}
				}				
				if (null == referencedEClass) {
					result.addFatalError("The containing EClass is not referencing " +
											"an EClass named '" + eClassName + "'!");
				}
				// final check 2: the referenced EClass must not have an 
				// EOperation with the same name as the selected EAttribute
				else {
					for (EOperation eOperation : referencedEClass.getEAllOperations()){
						if (RefactoringHelper.haveSameSignatures
																		(eOperation, selectedEOperation)) {
							result.addFatalError("There is already an EOperation in the " +
								"containing EClass named '" + selectedEOperation.getName() + 
								"' having the same signature!");
						}
					}
				}
				// end custom code
				return result;
		}
		
	}

}