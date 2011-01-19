/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringWizardPage.java,v 1.1 2011/01/19 12:09:31 tarendt Exp $
 */
 package org.eclipse.emf.refactor.refactorings.ecore.henshin.createreferencedeclassusinghenshin;

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
	 * Label for each parameter.
	 * @generated
	 */
	private Label eReferenceName_1Label;
	
	/**
	 * TextField for each parameter.
	 * @generated
	 */
	private Text eReferenceName_1Widget;
	/**
	 * Label for each parameter.
	 * @generated
	 */
	private Label eReferenceName_2Label;
	
	/**
	 * TextField for each parameter.
	 * @generated
	 */
	private Text eReferenceName_2Widget;

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
		if (eReferenceName_1Widget != null) {
			String eReferenceName_1 = eReferenceName_1Widget.getText();
			if (!eReferenceName_1.isEmpty()){
				((RefactoringDataManagement) 
						this.controller.getDataManagementObject()).
						getInPortByName("eReferenceName_1").
						setValue(eReferenceName_1);
			} else {
				((RefactoringDataManagement) 
						this.controller.getDataManagementObject()).
						getInPortByName("eReferenceName_1").
						setValue("unspecified");
			}
		}				
		if (eReferenceName_2Widget != null) {
			String eReferenceName_2 = eReferenceName_2Widget.getText();
			if (!eReferenceName_2.isEmpty()){
				((RefactoringDataManagement) 
						this.controller.getDataManagementObject()).
						getInPortByName("eReferenceName_2").
						setValue(eReferenceName_2);
			} else {
				((RefactoringDataManagement) 
						this.controller.getDataManagementObject()).
						getInPortByName("eReferenceName_2").
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
		eClassNameLabel.setText("Name of the new EClass: ");
		eClassNameLabel.setEnabled(true);
		
		eClassNameWidget = new Text(composite, SWT.BORDER);
		eClassNameWidget.setToolTipText
				("value of variable 'eClassName'");
		eClassNameWidget.setEnabled(true);
		eClassNameWidget.setLayoutData(gd);
		eClassNameWidget.addListener(SWT.Modify, this);
			
		
		eReferenceName_1Label = new Label(composite, SWT.NONE);
		eReferenceName_1Label.setText("Name of the new EReference TO the new EClass: ");
		eReferenceName_1Label.setEnabled(true);
		
		eReferenceName_1Widget = new Text(composite, SWT.BORDER);
		eReferenceName_1Widget.setToolTipText
				("value of variable 'eReferenceName_1'");
		eReferenceName_1Widget.setEnabled(true);
		eReferenceName_1Widget.setLayoutData(gd);
		eReferenceName_1Widget.addListener(SWT.Modify, this);
			
		
		eReferenceName_2Label = new Label(composite, SWT.NONE);
		eReferenceName_2Label.setText("Name of the new EReference FROM the new EClass: ");
		eReferenceName_2Label.setEnabled(true);
		
		eReferenceName_2Widget = new Text(composite, SWT.BORDER);
		eReferenceName_2Widget.setToolTipText
				("value of variable 'eReferenceName_2'");
		eReferenceName_2Widget.setEnabled(true);
		eReferenceName_2Widget.setLayoutData(gd);
		eReferenceName_2Widget.addListener(SWT.Modify, this);
		
		
		setControl(composite);
	}
	
}
	