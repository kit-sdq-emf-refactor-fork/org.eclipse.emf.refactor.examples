/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringController.javajet,v 1.3 2011/01/21 13:08:06 tarendt Exp $
 */
package org.eclipse.emf.refactor.refactorings.uml24.renameclass;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.refactoring.core.Refactoring;
import org.eclipse.emf.refactor.refactoring.interfaces.IController;
import org.eclipse.emf.refactor.refactoring.interfaces.IDataManagement;
import org.eclipse.emf.refactor.refactoring.runtime.ltk.LtkEmfRefactoringProcessorAdapter;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;
import org.eclipse.uml2.uml.Package;

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
	 * @param Refactoring Refactoring supported by the controller.
	 * @see org.eclipse.emf.refactor.common.core.IController#
	 * setParent(org.eclipse.emf.refactor.common.core.Refactoring)
	 * @generated
	 */
	@Override
	public void setParent(Refactoring Refactoring) {
		this.parent = Refactoring;
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
				org.eclipse.uml2.uml.Class selectedEObject = 
					(org.eclipse.uml2.uml.Class) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
				String newName =
					(String) dataManagement.getInPortByName("newName").getValue();
				// execute: rename selected class to the inserted name
				selectedEObject.setName(newName);
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
				org.eclipse.uml2.uml.Class selectedEObject = 
					(org.eclipse.uml2.uml.Class) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
				// test: the selected class must be owned by a package
				String msg = "This refactoring can only be applied" +
								" on classes which are owned by a package!";
				if (selectedEObject.getPackage() == null) result.addFatalError(msg);
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
				org.eclipse.uml2.uml.Class selectedEObject = 
					(org.eclipse.uml2.uml.Class) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
				String newName =
					(String) dataManagement.getInPortByName("newName").getValue();
				// test: the inserted name must be different from the old class name
				String msg = "The selected class is already named '" + newName + "'!";
				if (selectedEObject.getName().equals(newName)) result.addFatalError(msg);
				// test: the owning package must not own an element with the inserted name
				msg = "The owning package already owns an element named '" + newName + "'!";
				Package p = selectedEObject.getPackage();
				if (p.getPackagedElement(newName) != null) result.addFatalError(msg);
				return result;
		}
		
	}

}