/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringWizardHenshin.javajet,v 1.2 2012/11/26 15:39:38 tarendt Exp $
 */
package org.eclipse.emf.refactor.refactorings.uml24.renameattribute;

import org.eclipse.emf.refactor.refactoring.interfaces.IController;
import org.eclipse.emf.refactor.refactoring.runtime.ltk.ui.AbstractRefactoringWizard;

/**
 * Class used for providing an implementation of an LTK
 * RefactoringWizard in EMF Refactor.
 * @generated
 */
public class RefactoringWizard extends AbstractRefactoringWizard {
	
	/**
	 * Default constructor implementation.
	 * @param controller Controller of the EMF model refactoring.
	 * @generated
	 */					
	public RefactoringWizard(IController controller) {
		super(controller);
	}

	/**
	 * @see org.eclipse.ltk.ui.refactoring.RefactoringWizard#
	 * addUserInputPages()
	 * @generated
	 */
	@Override
	protected void addUserInputPages() {
		addPage(new RefactoringWizardPage
			(controller.getParent().getName(), (RefactoringController)controller));
	}

}