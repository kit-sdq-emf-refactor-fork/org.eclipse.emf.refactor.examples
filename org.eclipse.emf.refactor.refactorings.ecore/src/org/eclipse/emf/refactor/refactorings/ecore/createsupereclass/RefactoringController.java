/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringController.java,v 1.1 2011/01/19 12:04:36 tarendt Exp $
 */
 package org.eclipse.emf.refactor.refactorings.ecore.createsupereclass;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
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
				EClass selectedEClass = (EClass) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
				String eClassName = 
					(String) dataManagement.getInPortByName("eClassName").getValue();
				EPackage containingEPackage = selectedEClass.getEPackage();
				EClass newEClass = (EClass) containingEPackage.getEClassifier(eClassName);
				// two cases
				if (newEClass == null) {
					// create new EClass
					newEClass = EcoreFactory.eINSTANCE.createEClass();
					newEClass.setName(eClassName);
					containingEPackage.getEClassifiers().add(newEClass);
				} 			
				// add new EClass as eSuperType of selected EClass
				selectedEClass.getESuperTypes().add(newEClass);
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
				// this refactoring has no initial checks
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
				EClass selectedEClass = (EClass) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
				String eClassName = 
					(String) dataManagement.getInPortByName("eClassName").getValue();
				EPackage containingEPackage = selectedEClass.getEPackage();
				// final check 1: the name of the new EClass must not be equal to the 
				// name of the selected EClass
				if (selectedEClass.getName().equals(eClassName)) {
					result.addFatalError("The name of the new EClass is equal to the  " +
																				"name of the selected EClass!");
				}
				// final check 2: the name of the new EClass must be a valid EClass name
				if (! RefactoringHelper.isValidEClassName(eClassName)) {
					String message = "(Parameter 'eClassName') " +
							RefactoringHelper.getReasonForInvalidEClassName(eClassName);
					result.addFatalError(message);
				}
				// final check 3: if the containing EPackage contains an EClassifier
				// with the specified EClass name it must be an empty EClass
				if (containingEPackage.getEClassifier(eClassName) != null) {
					if (! (containingEPackage.getEClassifier(eClassName) instanceof EClass)) {
						result.addFatalError("There is already an EDataType in the " +
												"containing EPackage named '" + eClassName + "'!");
					} else {
						EClass existingEClass = (EClass) 
												containingEPackage.getEClassifier(eClassName);
						if (! RefactoringHelper.isEmptyEClass(existingEClass)) {
							String message = "There is already an non-empty EClass in the " +
									"containing EPackage named '" + eClassName + "'! " +
									RefactoringHelper.getReasonForNonEmptyEClass(existingEClass);
							result.addFatalError(message);
						}
					}
				}
				// end custom code
				return result;
		}
		
	}

}