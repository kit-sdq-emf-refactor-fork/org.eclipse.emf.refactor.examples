/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringController.java,v 1.1 2011/01/19 12:09:32 tarendt Exp $
 */
 package org.eclipse.emf.refactor.refactorings.ecore.henshin.moveeattribute;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.refactoring.core.Refactoring;
import org.eclipse.emf.refactor.refactoring.henshin.interfaces.IHenshinController;
import org.eclipse.emf.refactor.refactoring.henshin.interfaces.IHenshinDataManagement;
import org.eclipse.emf.refactor.refactoring.henshin.managers.HenshinRuntimeManager;
import org.eclipse.emf.refactor.refactoring.henshin.runtime.HenshinLtkEmfRefactoringProcessorAdapter;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;

/**
 * Concrete EMF model refactoring class implementing IHenshinController. 
 * @generated
 */
public final class RefactoringController implements IHenshinController{

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
	 * HenshinRuntimeManager that executes Henshin transformations using 
	 * the Henshin interpreter.
	 * @generated
	 */
	private HenshinRuntimeManager henshinRuntimeManager = 
					new HenshinRuntimeManager(new ArrayList<EObject>(0), dataManagement);
	
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
	public IHenshinDataManagement getDataManagementObject() {
		return this.dataManagement;
	}
	
	/**
	 * Gets a HenshinRuntimeManager that executes Henshin transformations using 
	 * the Henshin interpreter.
	 * @return HenshinRuntimeManager that executes Henshin transformations using 
	 * the Henshin interpreter.
	 * @see org.eclipse.emf.refactor.henshin.core.IHenshinController#
	 * getHenshinRuntimeManager()
	 * @generated
	 */
	@Override
	public HenshinRuntimeManager getHenshinRuntimeManager() {
		return this.henshinRuntimeManager;
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
		this.henshinRuntimeManager = new HenshinRuntimeManager(this.selection, this.dataManagement);
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
				henshinRuntimeManager = new HenshinRuntimeManager(selection, dataManagement);
				henshinRuntimeManager.run();
			}
		};
	}

	/**
	 * Internal class for providing an instance of a LTK RefactoringProcessor 
	 * used for EMF model refactorings using Henshin transformations.	 
	 * @generated
	 */
	public final class InternalRefactoringProcessor extends 
									HenshinLtkEmfRefactoringProcessorAdapter {

		/**
		 * Constructor using the invocation context of the model refactoring.
		 * @param selection Invocation context of the model refactoring.
		 * @generated
		 */
		private InternalRefactoringProcessor(List<EObject> selection){
				super(getParent(), selection, applyRefactoring());				
		}
		
	}

}