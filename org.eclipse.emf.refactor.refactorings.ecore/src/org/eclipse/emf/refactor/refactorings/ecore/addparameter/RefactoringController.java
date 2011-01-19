/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringController.java,v 1.1 2011/01/19 12:04:36 tarendt Exp $
 */
 package org.eclipse.emf.refactor.refactorings.ecore.addparameter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
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
				EOperation selectedEOperation = (EOperation) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
				String eParameterName = 
					(String) dataManagement.getInPortByName("eParameterName").getValue();
				String eType = 
					(String) dataManagement.getInPortByName("eType").getValue();
				EPackage containingEPackage = 
											selectedEOperation.getEContainingClass().getEPackage();
				// create new EParameter
				EParameter newEParameter = EcoreFactory.eINSTANCE.createEParameter();
				newEParameter.setName(eParameterName);
				selectedEOperation.getEParameters().add(newEParameter);
				// add EClassifier named eType to the new EParameter
				newEParameter.setEType(containingEPackage.getEClassifier(eType));
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
				EOperation selectedEOperation = (EOperation) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
				String eParameterName = 
					(String) dataManagement.getInPortByName("eParameterName").getValue();
				String eType = 
					(String) dataManagement.getInPortByName("eType").getValue();
				EPackage containingEPackage = 
											selectedEOperation.getEContainingClass().getEPackage();
				// final check 1: the new name must be a valid EParameter name
				if (! RefactoringHelper.isValidEParameterName(eParameterName)) {
					String message = 
							RefactoringHelper.getReasonForInvalidEParameterName(eParameterName);
					result.addFatalError(message);
				}
				// final check 2: the containing EOperation must not contain an EParameter
				// with the specified name 
				if (RefactoringHelper.containsEParameter
												(selectedEOperation, eParameterName)) {
					result.addFatalError("There is already an EParameter in the " +
											"containing EOperation named '" + eParameterName + "'!");
				}
				// final check 3: there must be an EClassifier named 'eType' in the containing
				// EPackage of the containing EClass of the selected EOperation
				if (containingEPackage.getEClassifier(eType) == null) {
					result.addFatalError("There is no EClassifier named '" + eType + "' in the " +
							"containing EPackage of the EClass owning the selected EOperation!");
				} else {
					// final check 4: the containing EClass of the containing EOperation must 
					// not contain an EOperation having the same signature after adding the
					// selected EParameter (also no inherited EOperations)
					if (RefactoringHelper.containsEOperation(selectedEOperation, 
									eParameterName, containingEPackage.getEClassifier(eType))) {
						result.addFatalError("There is already an EOperation in the " +
										"containing EClass named '" + selectedEOperation.getName() + 
										"' having the same signature!");
					}
				}
				// end custom code
				return result;
		}
		
	}

}