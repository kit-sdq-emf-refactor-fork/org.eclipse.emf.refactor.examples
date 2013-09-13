/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringWizardPage.javajet,v 1.1 2010/07/15 13:08:44 tarendt Exp $
 */
 package org.eclipse.emf.refactor.refactorings.uml24.addparameter;

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
	private Label paramNameLabel;
	
	/**
	 * TextField for each parameter.
	 * @generated
	 */
	private Text paramNameWidget;
	/**
	 * Label for each parameter.
	 * @generated
	 */	
	private Label paramTypeLabel;
	
	/**
	 * TextField for each parameter.
	 * @generated
	 */
	private Text paramTypeWidget;

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
				
		if (paramNameWidget != null) {
			String paramName = paramNameWidget.getText();
			if (!paramName.isEmpty()){
				((RefactoringDataManagement) 
						this.controller.getDataManagementObject()).
						getInPortByName("paramName").
						setValue(paramName);
			} else {
				((RefactoringDataManagement) 
						this.controller.getDataManagementObject()).
						getInPortByName("paramName").
						setValue("unspecified");
			}
		}				
		if (paramTypeWidget != null) {
			String paramType = paramTypeWidget.getText();
			if (!paramType.isEmpty()){
				((RefactoringDataManagement) 
						this.controller.getDataManagementObject()).
						getInPortByName("paramType").
						setValue(paramType);
			} else {
				((RefactoringDataManagement) 
						this.controller.getDataManagementObject()).
						getInPortByName("paramType").
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
			
		
		paramNameLabel = new Label(composite, SWT.NONE);
		paramNameLabel.setText("Name of the new parameter: ");
		paramNameLabel.setEnabled(true);
		
		paramNameWidget = new Text(composite, SWT.BORDER);
		paramNameWidget.setToolTipText
				("value of variable 'paramName'");
		paramNameWidget.setEnabled(true);
		paramNameWidget.setLayoutData(gd);
		paramNameWidget.addListener(SWT.Modify, this);
			
		
		paramTypeLabel = new Label(composite, SWT.NONE);
		paramTypeLabel.setText("Type of the new parameter: ");
		paramTypeLabel.setEnabled(true);
		
		paramTypeWidget = new Text(composite, SWT.BORDER);
		paramTypeWidget.setToolTipText
				("value of variable 'paramType'");
		paramTypeWidget.setEnabled(true);
		paramTypeWidget.setLayoutData(gd);
		paramTypeWidget.addListener(SWT.Modify, this);
		
		List<IInputPageButtonCreator> buttonCreators = InputPageButtonLoader.iNSTANCE.getInputPageButtonCreatorClasses();
		for(IInputPageButtonCreator creator : buttonCreators){
			creator.createButton(composite, controller, (AbstractRefactoringWizard)this.getWizard());
		}
		
		setControl(composite);
	}
	
}
	