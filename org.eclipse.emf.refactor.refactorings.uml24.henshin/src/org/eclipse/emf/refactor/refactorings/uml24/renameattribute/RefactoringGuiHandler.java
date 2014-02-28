/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringGuiHandlerHenshin.javajet,v 1.2 2012/11/26 15:39:38 tarendt Exp $
 */
package org.eclipse.emf.refactor.refactorings.uml24.renameattribute;

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
	private Refactoring parent;
	
	/**
	 * @see org.eclipse.emf.refactor.refactoring.interfaces.IGuiHandler#getParent()
	 * @generated
	 */
	@Override
	public Refactoring getParent() {
		return parent;
	}

	/**
	 * @see org.eclipse.emf.refactor.refactoring.interfaces.IGuiHandler#
	 * setParent(org.eclipse.emf.refactor.refactoring.core.Refactoring)
	 * @generated
	 */
	@Override
	public void setParent(Refactoring refactoring) {
		this.parent = refactoring;
	}
		
	/**
	 * @see org.eclipse.emf.refactor.refactoring.interfaces.IGuiHandler#show()
	 * @generated
	 */
	@Override
	public RefactoringWizard show() {
		return new org.eclipse.emf.refactor.refactorings.uml24.renameattribute.RefactoringWizard
		((RefactoringController) this.parent.getController());
	}
	
	/**
	 * @see org.eclipse.emf.refactor.refactoring.interfaces.IGuiHandler#
	 * showInMenu(java.util.List)
	 * @generated
	 */
	@Override
	public boolean showInMenu(List<EObject> selection) {
		if (selection.size() > 2) return false;
		EObject o = selection.get(0);
		if(null != o){
			if (o instanceof org.eclipse.uml2.uml.Property) {
				if (selection.size() == 2) {
					EObject eo = selection.get(1);
					if (eo.eClass().getName().equals("Diagram")) return true;
				} else {
					return true;
				}
			}
		} 
		return false;
	}

}	
	