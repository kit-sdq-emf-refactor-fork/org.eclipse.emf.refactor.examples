/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringController.java,v 1.1 2011/01/19 12:04:37 tarendt Exp $
 */
 package org.eclipse.emf.refactor.refactorings.ecore.pullupeoperation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.refactor.refactoring.core.Refactoring;
import org.eclipse.emf.refactor.refactoring.interfaces.IController;
import org.eclipse.emf.refactor.refactoring.interfaces.IDataManagement;
import org.eclipse.emf.refactor.refactoring.runtime.ltk.LtkEmfRefactoringProcessorAdapter;
import org.eclipse.emf.refactor.refactorings.ecore.RefactoringHelper;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;

public final class RefactoringController implements IController{

	/**
	 * Refactoring supported by the controller.
	 * @generated
	 */
	private Refactoring parent;
	
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
	 * Gets the Refactoring supported by the controller.
	 * @return Refactoring supported by the controller.
	 * @see org.eclipse.emf.refactor.common.core.IController#getParent()
	 * @generated
	 */
	@Override
	public Refactoring getParent() {
		return this.parent;
	}
	
	/**
	 * Sets the Refactoring supported by the controller.
	 * @param emfRefactoring Refactoring supported by the controller.
	 * @see org.eclipse.emf.refactor.common.core.IController#
	 * setParent(org.eclipse.emf.refactor.common.core.Refactoring)
	 * @generated
	 */
	@Override
	public void setParent(Refactoring emfRefactoring) {
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
				String eClassName = (String) dataManagement.
													getInPortByName("eClassName").getValue();
				EClass containingEClass = selectedEOperation.getEContainingClass();
				// add the selected EOperation to the specified super EClass
				EClass superEClass = 
					RefactoringHelper.getESuperClass(containingEClass, eClassName);
				superEClass.getEOperations().add(selectedEOperation);
				// remove corresponding EOperations from all sub EClasses of the
				// specified super EClass
				EList <EClass> eSubClasses = 
							RefactoringHelper.getESubClasses(superEClass);
				for (EClass eClass : eSubClasses) {
					EOperation eOperationToRemove = null;
					for (EOperation eOperation : eClass.getEOperations()) {
						if (RefactoringHelper.haveSameSignatures
													(eOperation, selectedEOperation)) {
							eOperationToRemove = eOperation;
							break;
						}
					}
					eClass.getEOperations().remove(eOperationToRemove);
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
				// initial check 1: the containing EClass of the selected EAttribute must
				// have at least one super EClass
				EClass containingEClass = selectedEOperation.getEContainingClass();
				if (containingEClass.getESuperTypes().isEmpty()) {
					result.addFatalError("EClass '" + containingEClass.getName() + 
														"' has no eSuperTypes!");
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
				String eClassName = (String) dataManagement.
													getInPortByName("eClassName").getValue();
				EClass containingEClass = selectedEOperation.getEContainingClass();
				// final check 1: the containing EClass of the selected EAttribute must
				// have a super EClass with the specified name
				EClass eSuperClass = null;
				for (EClass eClass : containingEClass.getESuperTypes()) {
					if (eClass.getName().equals(eClassName)) {
						eSuperClass = eClass;
					}
				}
				if (eSuperClass == null) {
					result.addFatalError("EClass '" + containingEClass.getName() + 
					"' does not have an eSuperType named '" + eClassName + "'!");
				}
				// final check 2: the super EClass must not have an equivalent 
				// EOperation (even no inherited)
				for (EOperation eOperation : eSuperClass.getEAllOperations()) {
					if (RefactoringHelper.haveSameSignatures
															(eOperation, selectedEOperation)) {
						result.addFatalError("EClass '" + eSuperClass.getName() + 
												"' already has an equivalent EOperation!");
					}
				}
				// final check 3: each sub EClass of the super EClass must have 
				// an EOperation equivalent to the selected EOperation (i.e. they
				// have the same signature)
				EList <EClass> eSubClasses = 
									RefactoringHelper.getESubClasses(eSuperClass);
				for (EClass eClass : eSubClasses) {
					boolean hasEqualEOperation = false;
					for (EOperation eOperation : eClass.getEOperations()) {
						if (RefactoringHelper.haveSameSignaturesAndETypes
															(eOperation, selectedEOperation)) {
							hasEqualEOperation = true;
							break;
						}
					}
					if (! hasEqualEOperation) {
						result.addFatalError("The sibling EClass '" + eClass.getName() 
												+ "' does not have an equivalent EOperation!");
					}
				}
				// end custom code
				return result;
		}
		
	}

}