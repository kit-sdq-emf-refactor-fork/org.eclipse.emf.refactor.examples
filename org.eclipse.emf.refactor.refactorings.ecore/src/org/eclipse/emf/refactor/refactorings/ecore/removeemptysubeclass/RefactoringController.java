/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringController.java,v 1.1 2011/01/19 12:04:37 tarendt Exp $
 */
 package org.eclipse.emf.refactor.refactorings.ecore.removeemptysubeclass;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.refactor.common.core.EmfRefactoring;
import org.eclipse.emf.refactor.common.core.IController;
import org.eclipse.emf.refactor.common.core.IDataManagement;
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
				EClass selectedEClass = (EClass) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
				// remove selected EClass from containing EPackage
				EPackage containingEPackage = selectedEClass.getEPackage();
				containingEPackage.getEClassifiers().remove(selectedEClass);
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
		@SuppressWarnings("rawtypes")
		@Override
		public RefactoringStatus checkInitialConditions(){
				RefactoringStatus result = new RefactoringStatus();
				// begin custom code
				EClass selectedEClass = (EClass) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
				// initial check 1: the selected EClass must have at least one eSuperType
				if (selectedEClass.getESuperTypes().isEmpty()) {
					result.addFatalError("The selected EClass has no eSuperTypes!");		
				}
				// initial check 2: the selected EClass must not have any EStructuralFeatures
				// (EAttributes or EReferences)
				if (! selectedEClass.getEStructuralFeatures().isEmpty()) {
					result.addFatalError("The selected EClass has at least one " +
													"EStructuralFeature (EAttribute or EReference)!");		
				}
				// initial check 3: the selected EClass must not have any EOperations
				if (! selectedEClass.getEOperations().isEmpty()) {
					result.addFatalError("The selected EClass has at least one EOperation!");
				}
				// initial check 4: the selected EClass must not be eSuperType of 
				// another EClass
				for (TreeIterator iter = selectedEClass.eResource().getAllContents(); iter.hasNext(); ) {
					EObject eObject = (EObject) iter.next();
					if (eObject instanceof EClass) {
						EClass eClass = (EClass) eObject;
						if (eClass.getESuperTypes().contains(selectedEClass)) {
							result.addFatalError("The selected EClass is eSuperType of at " +
																									"least one other EClass!");
							break;
						}
					}
				}
				// initial check 5: the selected EClass must not be eType an ETypedElement
				for (TreeIterator iter = selectedEClass.eResource().getAllContents(); iter.hasNext(); ) {
					EObject eObject = (EObject) iter.next();
					if (eObject instanceof ETypedElement) {
						ETypedElement eTypedElement = (ETypedElement) eObject;
						if (eTypedElement.getEType() != null 
															&& eTypedElement.getEType().equals(selectedEClass)) {
							result.addFatalError("The selected EClass is eType an ETypedElement! " +
																				"(EReference, EOperation or EParameter)");
							break;
						}
					}
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
				// this refactoring has no final checks
				return result;
		}
		
	}

}