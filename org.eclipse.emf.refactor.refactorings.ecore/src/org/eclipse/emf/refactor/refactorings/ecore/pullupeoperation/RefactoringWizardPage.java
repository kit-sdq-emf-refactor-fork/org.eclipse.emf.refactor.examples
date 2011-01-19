/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringWizardPage.java,v 1.1 2011/01/19 12:04:37 tarendt Exp $
 */
 package org.eclipse.emf.refactor.refactorings.ecore.pullupeoperation;

import org.eclipse.ltk.ui.refactoring.UserInputWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

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
	 * Label for each parameter.
	 * @generated
	 */	
	private Label eClassNameLabel;
	
	/**
	 * TextField for each parameter.
	 * @generated
	 */
	private Text eClassNameWidget;

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
	@SuppressWarnings("unchecked")
	@Override
	public void handleEvent(Event event) {		
		getWizard().getContainer().updateButtons();
				
		if (eClassNameWidget != null) {
			String eClassName = eClassNameWidget.getText();
			if (!eClassName.isEmpty()){
				((RefactoringDataManagement) 
						this.controller.getDataManagementObject()).
						getInPortByName("eClassName").
						setValue(eClassName);
			} else {
				((RefactoringDataManagement) 
						this.controller.getDataManagementObject()).
						getInPortByName("eClassName").
						setValue("unspecified");
			}
		}

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
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		composite.setLayout(gl);
			
		
		eClassNameLabel = new Label(composite, SWT.NONE);
		eClassNameLabel.setText("Name of the target super EClass: ");
		eClassNameLabel.setEnabled(true);
		
		eClassNameWidget = new Text(composite, SWT.BORDER);
		eClassNameWidget.setToolTipText
				("value of variable 'eClassName'");
		eClassNameWidget.setEnabled(true);
		eClassNameWidget.setLayoutData(gd);
		eClassNameWidget.addListener(SWT.Modify, this);
		
		
		setControl(composite);
	}
	
}
	