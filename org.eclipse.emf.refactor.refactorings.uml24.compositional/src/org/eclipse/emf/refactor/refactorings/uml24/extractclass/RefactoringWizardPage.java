/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringWizardPage.javajet,v 1.1 2010/07/15 13:08:44 tarendt Exp $
 */
 package org.eclipse.emf.refactor.refactorings.uml24.extractclass;

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
	private Label classNameLabel;
	
	/**
	 * TextField for each parameter.
	 * @generated
	 */
	private Text classNameWidget;
	/**
	 * Label for each parameter.
	 * @generated
	 */	
	private Label associationNameLabel;
	
	/**
	 * TextField for each parameter.
	 * @generated
	 */
	private Text associationNameWidget;
	/**
	 * Label for each parameter.
	 * @generated
	 */	
	private Label roleName1Label;
	
	/**
	 * TextField for each parameter.
	 * @generated
	 */
	private Text roleName1Widget;
	/**
	 * Label for each parameter.
	 * @generated
	 */	
	private Label roleName2Label;
	
	/**
	 * TextField for each parameter.
	 * @generated
	 */
	private Text roleName2Widget;

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
		if (classNameWidget != null) {
			String className = classNameWidget.getText();
			if (!className.isEmpty()){
				((RefactoringDataManagement) 
						this.controller.getDataManagementObject()).
						getInPortByName("className").
						setValue(className);
			} else {
				((RefactoringDataManagement) 
						this.controller.getDataManagementObject()).
						getInPortByName("className").
						setValue("unspecified");
			}
		}				
		if (associationNameWidget != null) {
			String associationName = associationNameWidget.getText();
			if (!associationName.isEmpty()){
				((RefactoringDataManagement) 
						this.controller.getDataManagementObject()).
						getInPortByName("associationName").
						setValue(associationName);
			} else {
				((RefactoringDataManagement) 
						this.controller.getDataManagementObject()).
						getInPortByName("associationName").
						setValue("unspecified");
			}
		}				
		if (roleName1Widget != null) {
			String roleName1 = roleName1Widget.getText();
			if (!roleName1.isEmpty()){
				((RefactoringDataManagement) 
						this.controller.getDataManagementObject()).
						getInPortByName("roleName1").
						setValue(roleName1);
			} else {
				((RefactoringDataManagement) 
						this.controller.getDataManagementObject()).
						getInPortByName("roleName1").
						setValue("unspecified");
			}
		}				
		if (roleName2Widget != null) {
			String roleName2 = roleName2Widget.getText();
			if (!roleName2.isEmpty()){
				((RefactoringDataManagement) 
						this.controller.getDataManagementObject()).
						getInPortByName("roleName2").
						setValue(roleName2);
			} else {
				((RefactoringDataManagement) 
						this.controller.getDataManagementObject()).
						getInPortByName("roleName2").
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
		
		classNameLabel = new Label(composite, SWT.NONE);
		classNameLabel.setText("Name of the new associated class: ");
		classNameLabel.setEnabled(true);
		
		classNameWidget = new Text(composite, SWT.BORDER);
		classNameWidget.setToolTipText
				("value of variable 'className'");
		classNameWidget.setEnabled(true);
		classNameWidget.setLayoutData(gd);
		classNameWidget.addListener(SWT.Modify, this);
		classNameWidget.setFocus();
			
		
		associationNameLabel = new Label(composite, SWT.NONE);
		associationNameLabel.setText("Name of the new association: ");
		associationNameLabel.setEnabled(true);
		
		associationNameWidget = new Text(composite, SWT.BORDER);
		associationNameWidget.setToolTipText
				("value of variable 'associationName'");
		associationNameWidget.setEnabled(true);
		associationNameWidget.setLayoutData(gd);
		associationNameWidget.addListener(SWT.Modify, this);
			
		
		roleName1Label = new Label(composite, SWT.NONE);
		roleName1Label.setText("Name of the new association end role: ");
		roleName1Label.setEnabled(true);
		
		roleName1Widget = new Text(composite, SWT.BORDER);
		roleName1Widget.setToolTipText
				("value of variable 'roleName1'");
		roleName1Widget.setEnabled(true);
		roleName1Widget.setLayoutData(gd);
		roleName1Widget.addListener(SWT.Modify, this);
			
		
		roleName2Label = new Label(composite, SWT.NONE);
		roleName2Label.setText("Name of the opposite association end role: ");
		roleName2Label.setEnabled(true);
		
		roleName2Widget = new Text(composite, SWT.BORDER);
		roleName2Widget.setToolTipText
				("value of variable 'roleName2'");
		roleName2Widget.setEnabled(true);
		roleName2Widget.setLayoutData(gd);
		roleName2Widget.addListener(SWT.Modify, this);
		
		List<IInputPageButtonCreator> buttonCreators = InputPageButtonLoader.iNSTANCE.getInputPageButtonCreatorClasses();
		for(IInputPageButtonCreator creator : buttonCreators){
			creator.createButton(composite, controller, (AbstractRefactoringWizard)this.getWizard());
		}
		
		setControl(composite);
	}
	
}
	