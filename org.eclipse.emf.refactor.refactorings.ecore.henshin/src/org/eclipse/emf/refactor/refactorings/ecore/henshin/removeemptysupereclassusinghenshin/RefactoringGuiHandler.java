/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringGuiHandler.java,v 1.1 2011/01/19 12:09:31 tarendt Exp $
 */
 package org.eclipse.emf.refactor.refactorings.ecore.henshin.removeemptysupereclassusinghenshin;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.refactoring.core.Refactoring;
import org.eclipse.emf.refactor.refactoring.interfaces.IGuiHandler;
import org.eclipse.ltk.ui.refactoring.RefactoringWizard;

/**
 * Class used for specifying gui topics of a specific model refactoring.
 * @generated
 */
public class RefactoringGuiHandler implements IGuiHandler {

	/**
	 * Refactoring supported by the GuiHandler.
	 * @generated
	 */
	Refactoring parent;
	
	/**
	 * @see org.eclipse.emf.refactor.common.core.ui.IGuiHandler#getParent()
	 * @generated
	 */
	@Override
	public Refactoring getParent() {
		return parent;
	}

	/**
	 * @see org.eclipse.emf.refactor.common.core.ui.IGuiHandler#
	 * setParent(org.eclipse.emf.refactor.common.core.Refactoring)
	 * @generated
	 */
	@Override
	public void setParent(Refactoring refactoring) {
		this.parent = refactoring;
	}
		
	/**
	 * @see org.eclipse.emf.refactor.common.core.ui.IGuiHandler#show()
	 * @generated
	 */
	@Override
	public RefactoringWizard show() {
		return new org.eclipse.emf.refactor.refactorings.ecore.henshin.removeemptysupereclassusinghenshin.RefactoringWizard
		((RefactoringController)this.parent.getController());
	}
	
	/**
	 * @see org.eclipse.emf.refactor.common.core.ui.IGuiHandler#
	 * showInMenu(java.util.List)
	 * @generated
	 */
	@Override
	public boolean showInMenu(List<EObject> selection) {
		for(EObject o:selection){
			if(null != o){
				if (o instanceof org.eclipse.emf.ecore.EClass) {
					return true;
				}
			} 
		}
		return false;
	}

}	
	