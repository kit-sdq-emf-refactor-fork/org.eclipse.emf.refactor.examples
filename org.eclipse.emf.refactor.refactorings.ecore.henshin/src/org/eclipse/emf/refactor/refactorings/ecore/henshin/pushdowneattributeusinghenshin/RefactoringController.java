/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringController.java,v 1.1 2011/01/19 12:09:32 tarendt Exp $
 */
 package org.eclipse.emf.refactor.refactorings.ecore.henshin.pushdowneattributeusinghenshin;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.henshin.runtime.HenshinLtkEmfRefactoringProcessorAdapter;
import org.eclipse.emf.refactor.henshin.runtime.HenshinRunner;
import org.eclipse.emf.refactor.henshin.core.IHenshinController;
import org.eclipse.emf.refactor.henshin.core.IHenshinDataManagement;
import org.eclipse.emf.refactor.common.core.EmfRefactoring;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;

/**
 * Concrete EMF model refactoring class implementing IHenshinController. 
 * @generated
 */
public final class RefactoringController implements IHenshinController{

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
	 * HenshinRunner that executes Henshin transformations using 
	 * the Henshin interpreter.
	 * @generated
	 */
	private HenshinRunner henshinRunner = 
					new HenshinRunner(new ArrayList<EObject>(0), dataManagement);
	
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
	public IHenshinDataManagement getDataManagementObject() {
		return this.dataManagement;
	}
	
	/**
	 * Gets a HenshinRunner that executes Henshin transformations using 
	 * the Henshin interpreter.
	 * @return HenshinRunner that executes Henshin transformations using 
	 * the Henshin interpreter.
	 * @see org.eclipse.emf.refactor.henshin.core.IHenshinController#
	 * getHenshinRunner()
	 * @generated
	 */
	@Override
	public HenshinRunner getHenshinRunner() {
		return this.henshinRunner;
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
		this.henshinRunner = new HenshinRunner(this.selection, this.dataManagement);
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
				henshinRunner = new HenshinRunner(selection, dataManagement);
				henshinRunner.run();
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