/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringWizardPage.java,v 1.1 2011/01/19 12:04:36 tarendt Exp $
 */
 package org.eclipse.emf.refactor.refactorings.ecore.addparameter;

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
	private Label eParameterNameLabel;
	
	/**
	 * TextField for each parameter.
	 * @generated
	 */
	private Text eParameterNameWidget;
	/**
	 * Label for each parameter.
	 * @generated
	 */	
	private Label eTypeLabel;
	
	/**
	 * TextField for each parameter.
	 * @generated
	 */
	private Text eTypeWidget;

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
				
		if (eParameterNameWidget != null) {
			String eParameterName = eParameterNameWidget.getText();
			if (!eParameterName.isEmpty()){
				((RefactoringDataManagement) 
						this.controller.getDataManagementObject()).
						getInPortByName("eParameterName").
						setValue(eParameterName);
			} else {
				((RefactoringDataManagement) 
						this.controller.getDataManagementObject()).
						getInPortByName("eParameterName").
						setValue("unspecified");
			}
		}				
		if (eTypeWidget != null) {
			String eType = eTypeWidget.getText();
			if (!eType.isEmpty()){
				((RefactoringDataManagement) 
						this.controller.getDataManagementObject()).
						getInPortByName("eType").
						setValue(eType);
			} else {
				((RefactoringDataManagement) 
						this.controller.getDataManagementObject()).
						getInPortByName("eType").
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
			
		
		eParameterNameLabel = new Label(composite, SWT.NONE);
		eParameterNameLabel.setText("Name of the new EParameter: ");
		eParameterNameLabel.setEnabled(true);
		
		eParameterNameWidget = new Text(composite, SWT.BORDER);
		eParameterNameWidget.setToolTipText
				("value of variable 'eParameterName'");
		eParameterNameWidget.setEnabled(true);
		eParameterNameWidget.setLayoutData(gd);
		eParameterNameWidget.addListener(SWT.Modify, this);
			
		
		eTypeLabel = new Label(composite, SWT.NONE);
		eTypeLabel.setText("Name of the EClassifier used as eType of the new EParameter (must be in the same EPackage as the containing EClass): ");
		eTypeLabel.setEnabled(true);
		
		eTypeWidget = new Text(composite, SWT.BORDER);
		eTypeWidget.setToolTipText
				("value of variable 'eType'");
		eTypeWidget.setEnabled(true);
		eTypeWidget.setLayoutData(gd);
		eTypeWidget.addListener(SWT.Modify, this);
		
		
		setControl(composite);
	}
	
}
	