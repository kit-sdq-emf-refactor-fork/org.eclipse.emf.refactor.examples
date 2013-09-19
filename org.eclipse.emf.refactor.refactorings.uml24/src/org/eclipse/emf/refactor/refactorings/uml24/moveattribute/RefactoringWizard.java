/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringWizard.javajet,v 1.1 2010/07/15 13:08:44 tarendt Exp $
 */
 package org.eclipse.emf.refactor.refactorings.uml24.moveattribute;

import org.eclipse.emf.refactor.refactoring.interfaces.IController;
import org.eclipse.emf.refactor.refactoring.runtime.ltk.ui.AbstractRefactoringWizard;

/**
 * Class used for providing an implementation of an LTK
 * RefactoringWizard in EMF Refactor.
 * @generated
 */
public class RefactoringWizard 
    extends AbstractRefactoringWizard {
		
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
