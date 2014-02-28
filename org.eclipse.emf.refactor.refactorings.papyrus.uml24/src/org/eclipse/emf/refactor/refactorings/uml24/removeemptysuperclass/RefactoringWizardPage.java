/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringWizardPage.javajet,v 1.1 2010/07/15 13:08:44 tarendt Exp $
 */
 package org.eclipse.emf.refactor.refactorings.uml24.removeemptysuperclass;

import java.util.List;

import org.eclipse.emf.refactor.refactoring.runtime.ltk.ui.AbstractRefactoringWizard;
import org.eclipse.emf.refactor.refactoring.runtime.ui.IInputPageButtonCreator;
import org.eclipse.emf.refactor.refactoring.runtime.ui.InputPageButtonLoader;
import org.eclipse.ltk.ui.refactoring.UserInputWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

/**
 * Class for setting model refactoring specific parameters
 * by the user.
 * @generated
 */
public class RefactoringWizardPage extends 
				UserInputWizardPage implements Listener {
	
	/**
	 * Controller of the EMF model refactoring.
	 * @generated
	 */	
	private final RefactoringController controller;
	

	/**
	 * Default constructor using a name and the controller of the 
	 * EMF model refactoring.
	 * @param name Name of the WizardPage.
	 * @param controller Controller of the EMF model refactoring.
	 * @generated
	 */
	public RefactoringWizardPage
		(String name, RefactoringController controller) {
		super(name);
		this.controller = controller;
	}

	/**
	 * @see org.eclipse.swt.widgets.Listener#
	 * handleEvent(org.eclipse.swt.widgets.Event)
	 * @generated
	 */
	@Override
	public void handleEvent(Event event) {		
		getWizard().getContainer().updateButtons();	
	}
	
	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#
	 * createControl(org.eclipse.swt.widgets.Composite)
	 * @generated
	 */
	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout();
		gl.numColumns = 2;
		composite.setLayout(gl);
				
		Label newNameLabel = new Label(composite, SWT.NONE);
		newNameLabel.setText("This refactoring does not have any parameters.");
		newNameLabel.setEnabled(true);
		
//		List<IInputPageButtonCreator> buttonCreators = InputPageButtonLoader.iNSTANCE.getInputPageButtonCreatorClasses();
//		for(IInputPageButtonCreator creator : buttonCreators){
//			creator.createButton(composite, controller, (AbstractRefactoringWizard)this.getWizard());
//		}
		
		setControl(composite);
	}
	
}
	