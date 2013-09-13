/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringController.javajet,v 1.3 2011/01/21 13:08:06 tarendt Exp $
 */
package org.eclipse.emf.refactor.refactorings.uml24.pushdownoperation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.emf.refactor.refactoring.core.Refactoring;
import org.eclipse.emf.refactor.refactoring.interfaces.IController;
import org.eclipse.emf.refactor.refactoring.interfaces.IDataManagement;
import org.eclipse.emf.refactor.refactoring.runtime.ltk.LtkEmfRefactoringProcessorAdapter;
import org.eclipse.emf.refactor.refactorings.uml24.UmlUtils;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.VisibilityKind;


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
	 * @see org.eclipse.emf.refactor.common.core.IController#
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
				org.eclipse.uml2.uml.Operation selectedEObject = 
					(org.eclipse.uml2.uml.Operation) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
				// execute: copy selected operation to each subclass
				Class owningClass = selectedEObject.getClass_();
				ArrayList<Class> subClasses = UmlUtils.getAllSubClasses(owningClass);
				for (Class subClass : subClasses) {
					// create copy of the selected operation
					Copier attCopier = new Copier();
					Operation newOperation = (Operation) attCopier.copy(selectedEObject);
					attCopier.copyReferences();
					// add copied operation to subclass
					subClass.getOwnedOperations().add(newOperation);
				}
				// execute: remove selected operation from owning class
				owningClass.getOwnedOperations().remove(selectedEObject);
			}
		};
	}

	/**
	 * Internal class for providing an instance of a LTK RefactoringProcessor 
	 * used for EMF model refactorings.	 
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
				org.eclipse.uml2.uml.Operation selectedEObject = 
					(org.eclipse.uml2.uml.Operation) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
				// test: the selected operation must be owned by a class
				String msg = "This refactoring can only be applied" +
								" on operations which are owned by a class!";
				if (selectedEObject.getClass_() == null) {
					result.addFatalError(msg);
				} else {
					// test: the selected operation must be public
					msg = "This refactoring can only be applied on public operations!";
					if (! selectedEObject.getVisibility().equals(VisibilityKind.PUBLIC_LITERAL)) 
															result.addFatalError(msg);
					// test: the owning class must have at least one subclass
					msg = "This refactoring can not be applied because the owning class " +
							"of the selected operation does not have any subclasses!";
					if (! UmlUtils.hasSubclasses(selectedEObject.getClass_())) 
															result.addFatalError(msg);
					// test: the owning class must not be used as attribute type
					msg = "This refactoring can not be applied because the owning class " +
							"of the selected operation is used as attribute type!";
					if (UmlUtils.isUsedAsAttributeType(selectedEObject.getClass_()))
															result.addFatalError(msg);
					// test: the owning class must not have any subclasses which own
					// operations with the same name, type and parameter list
					msg = "This refactoring can not be applied because at least one subclass " +
							"already owns an operation named '" + selectedEObject.getName() +"' " +
									"and with the same type and parameter list!";
					if (UmlUtils.oneSubClassHasEquallyOperation
						(selectedEObject.getClass_(), selectedEObject)) result.addFatalError(msg);
					// test: the owning class must not have any subclasses which inherit
					// operations with the same name, type and parameter list
					msg = "This refactoring can not be applied because at least one subclass " +
							"already inherits an operation named '" + selectedEObject.getName() +"' " +
									"and with the same type and parameter list!";
					if (UmlUtils.oneSubClassInheritsEquallyOperation
						(selectedEObject.getClass_(), selectedEObject)) result.addFatalError(msg);
				}
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
				// the refactoring has no final checks
				return result;
		}
		
	}

}