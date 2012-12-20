/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringController.java,v 1.1 2011/01/19 12:04:37 tarendt Exp $
 */
 package org.eclipse.emf.refactor.refactorings.ecore.removeemptyreferencedeclass;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.refactor.refactoring.core.Refactoring;
import org.eclipse.emf.refactor.refactoring.interfaces.IController;
import org.eclipse.emf.refactor.refactoring.interfaces.IDataManagement;
import org.eclipse.emf.refactor.refactoring.runtime.ltk.LtkEmfRefactoringProcessorAdapter;
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
			@SuppressWarnings("rawtypes")
			@Override
			public void run() {
				// begin custom code
				EClass selectedEClass = (EClass) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
				// remove incoming EReference TO the selected EClass
				for (TreeIterator iter = selectedEClass.eResource().getAllContents(); iter.hasNext(); ) {
					EObject eObject = (EObject) iter.next();
					if (eObject instanceof EClass) {
						EClass eClass = (EClass) eObject;
						EReference eReferenceToRemove = null;
						for (EReference eReference : eClass.getEReferences()) {
							if (eReference.getEReferenceType().equals(selectedEClass)) {
								eReferenceToRemove = eReference;
								break;
							}
						}
						eClass.getEStructuralFeatures().remove(eReferenceToRemove);
					}
				}
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
				// initial check 1: the selected EClass has exactly one outgoing EReference
				if (selectedEClass.getEReferences().size() != 1) {
					result.addFatalError("The selected EClass must have exactly one " +
																	"outgoing EReference to another EClass!");		
				} else {
					// initial check 2: there must be an opposite EReference
					EReference eReference = selectedEClass.getEReferences().get(0);
					if (eReference.getEOpposite() == null) {
						result.addFatalError("There is no opposite EReference!");		
					}
				}
				// initial check 3: the selected EClass has exactly one incoming EReference
				int numberOfIncomingEReferences = 0;
				for (TreeIterator iter = selectedEClass.eResource().getAllContents(); iter.hasNext(); ) {
					EObject eObject = (EObject) iter.next();
					if (eObject instanceof EClass) {
						EClass eClass = (EClass) eObject;
						for (EReference eReference : eClass.getEReferences()) {
							if (eReference.getEReferenceType().equals(selectedEClass)) {
								numberOfIncomingEReferences++;
							}
						}
					}
				}
				if (numberOfIncomingEReferences != 1) {
					result.addFatalError("The selected EClass must have exactly one " +
													"incoming EReference from another EClass!");		
				}
				// initial check 4: the selected EClass must not have EAttributes
				if (! selectedEClass.getEAttributes().isEmpty()) {
					result.addFatalError("The selected EClass has at least one EAttribute!");
				}
				// initial check 5: the selected EClass must not have EOperations
				if (! selectedEClass.getEOperations().isEmpty()) {
					result.addFatalError("The selected EClass has at least one EOperation!");
				}
				// initial check 6: the selected EClass must not have eSuperTypes
				if (! selectedEClass.getESuperTypes().isEmpty()) {
					result.addFatalError("The selected EClass has at least one eSuperType!");
				}
				// initial check 7: the selected EClass must not be eSuperType of another EClass
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
				// initial check 8: the selected EClass must not be eType of more than one ETypedElement
				// (the only one is the opposite EReference)
				int numberOfETypes = 0;
				for (TreeIterator iter = selectedEClass.eResource().getAllContents(); iter.hasNext(); ) {
					EObject eObject = (EObject) iter.next();
					if (eObject instanceof ETypedElement) {
						ETypedElement eTypedElement = (ETypedElement) eObject;
						if (eTypedElement.getEType() != null 
															&& eTypedElement.getEType().equals(selectedEClass)) {
							numberOfETypes++;
						}
					}
				}
				if (numberOfETypes != 1) {
					result.addFatalError("The selected EClass is EType of more than one ETypedElement!");		
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