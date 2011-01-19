/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringController.java,v 1.1 2011/01/19 12:04:37 tarendt Exp $
 */
 package org.eclipse.emf.refactor.refactorings.ecore.createreferencedeclass;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
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
				String eReferenceName_1 = 
					(String) dataManagement.getInPortByName("eReferenceName_1").getValue();
				String eReferenceName_2 = 
					(String) dataManagement.getInPortByName("eReferenceName_2").getValue();
				EPackage containingEPackage = selectedEClass.getEPackage();
				// create new EClass
				EClass newEClass = EcoreFactory.eINSTANCE.createEClass();
				newEClass.setName(eClassName);
				containingEPackage.getEClassifiers().add(newEClass);
				// create new EReference TO the new EClass
				EReference newEReference_1 = EcoreFactory.eINSTANCE.createEReference();
				newEReference_1.setName(eReferenceName_1);
				newEReference_1.setEType(newEClass);
				newEReference_1.setLowerBound(1);
				selectedEClass.getEStructuralFeatures().add(newEReference_1);
				// create new EReference FROM the new EClass
				EReference newEReference_2 = EcoreFactory.eINSTANCE.createEReference();
				newEReference_2.setName(eReferenceName_2);
				newEReference_2.setEType(selectedEClass);
				newEReference_2.setLowerBound(1);
				newEClass.getEStructuralFeatures().add(newEReference_2);
				// set EOpposite references
				newEReference_1.setEOpposite(newEReference_2);
				newEReference_2.setEOpposite(newEReference_1);
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
				String eReferenceName_1 = 
					(String) dataManagement.getInPortByName("eReferenceName_1").getValue();
				String eReferenceName_2 = 
					(String) dataManagement.getInPortByName("eReferenceName_2").getValue();
				// final check 1: the name of the new EClass must be a valid EClass name
				if (! RefactoringHelper.isValidEClassName(eClassName)) {
					String message = "(Parameter 'eClassName') " +
							RefactoringHelper.getReasonForInvalidEClassName(eClassName);
					result.addFatalError(message);
				}
				// final check 2: the names of the new EReferences must be valid 
				// EReference names
				if (! RefactoringHelper.isValidEReferenceName(eReferenceName_1)) {
					String message = "(Parameter 'eReferenceName_1') " +
							RefactoringHelper.getReasonForInvalidEReferenceName(eReferenceName_1);
					result.addFatalError(message);
				}
				if (! RefactoringHelper.isValidEReferenceName(eReferenceName_2)) {
					String message = "(Parameter 'eReferenceName_2') " +
							RefactoringHelper.getReasonForInvalidEReferenceName(eReferenceName_2);
					result.addFatalError(message);
				}
				// final check 3: the containing EPackage must not contain an EClassifier
				// with the specified EClass name
				if (RefactoringHelper.
						containsEClassifier(selectedEClass.getEPackage(), eClassName)) {
					result.addFatalError("There is already an EClassifier in the " +
											"containing EPackage named '" + eClassName + "'!");
				}
				// final check 4: the selected EClass must not contain an EStructuralFeature
				// with the specified EReference name (also no inherited EStructuralFeatures)
				if (RefactoringHelper.containsEStructuralFeature
										(selectedEClass, eReferenceName_1)) {
					result.addFatalError("There is already an EStructuralFeature in the " +
													"selected EClass named '" + eReferenceName_1 + "'!");
				}
				// end custom code
				return result;
		}
		
	}

}