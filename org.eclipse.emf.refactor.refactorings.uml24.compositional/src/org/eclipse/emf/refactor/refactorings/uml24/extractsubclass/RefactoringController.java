/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringController.javajet,v 1.3 2011/01/21 13:08:06 tarendt Exp $
 */
package org.eclipse.emf.refactor.refactorings.uml24.extractsubclass;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.refactoring.core.Refactoring;
import org.eclipse.emf.refactor.refactoring.interfaces.IController;
import org.eclipse.emf.refactor.refactoring.interfaces.IDataManagement;
import org.eclipse.emf.refactor.refactoring.runtime.ltk.LtkEmfRefactoringProcessorAdapter;
import org.eclipse.emf.refactor.refactorings.uml24.extractsubclass.RefactoringDataManagement.UmlOperationList;
import org.eclipse.emf.refactor.refactorings.uml24.extractsubclass.RefactoringDataManagement.UmlPropertyList;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;

import comrel.InputPort;
import comrel.MultiInputPort;
import comrel.RefactoringUnit;
import comrel.SingleInputPort;
import comrel.interpreter.ComrelInterpreter;

public final class RefactoringController implements IController{

	/**
	 * Refactoring supported by the controller.
	 * @generated
	 */
	private Refactoring parent;
	
	/**
	 * DataManagement object of the model refactoring.
	 * @generated
	 */
	private RefactoringDataManagement dataManagement = 
									new RefactoringDataManagement();
	
	/**
	 * Invocation context of the model refactoring.
	 * @generated
	 */	
	private List<EObject> selection = new ArrayList<EObject>();
	
	/**
	 * Ltk RefactoringProcessor of the model refactoring.
	 * @generated
	 */
	private InternalRefactoringProcessor refactoringProcessor = null;
	
	/**
	 * Gets the Refactoring supported by the controller.
	 * @return Refactoring supported by the controller.
	 * @see org.eclipse.emf.refactor.common.core.IController#getParent()
	 * @generated
	 */
	@Override
	public Refactoring getParent() {
		return this.parent;
	}
	
	/**
	 * Sets the Refactoring supported by the controller.
	 * @param emfRefactoring Refactoring supported by the controller.
	 * @see org.eclipse.emf.refactor.common.core.IController#
	 * setParent(org.eclipse.emf.refactor.common.core.Refactoring)
	 * @generated
	 */
	@Override
	public void setParent(Refactoring emfRefactoring) {
		this.parent = emfRefactoring;
	}
	
	/**
	 * Returns the DataManagement object of the model refactoring.
	 * @return DataManagement object of the model refactoring.
	 * @see org.eclipse.emf.refactor.common.core.IController#
	 * getDataManagementObject()
	 * @generated
	 */
	@Override
	public IDataManagement getDataManagementObject() {
		return this.dataManagement;
	}

	/**
	 * Returns the ltk RefactoringProcessor of the model refactoring.
	 * @return Ltk RefactoringProcessor of the model refactoring.
	 * @see org.eclipse.emf.refactor.common.core.IController#
	 * getLtkRefactoringProcessor()
	 * @generated
	 */
	@Override
	public RefactoringProcessor getLtkRefactoringProcessor() {
		return this.refactoringProcessor;
	}
	
	/**
	 * Sets the selected EObject (invocation context of the model refactoring).
	 * @param selection Invocation context of the model refactoring.
	 * @see org.eclipse.emf.refactor.common.core.IController#
	 * setSelection(java.util.List)
	 * @generated
	 */
	@Override
	public void setSelection(List<EObject> selection) {
		this.selection = selection;
		this.refactoringProcessor = 
				new InternalRefactoringProcessor(this.selection);
	}	
	
	/**
	 * Returns a Runnable object that executes the model refactoring.
	 * @return Runnable object that executes the model refactoring.
	 * @generated
	 */
	private Runnable applyRefactoring() {
		return new Runnable() {				
			/**
			 * @see java.lang.Runnable#run()
			 * @generated
			 */
			@Override
			public void run() {
				try {
					org.eclipse.uml2.uml.Class selectedEObject = 
						(org.eclipse.uml2.uml.Class) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
					ArrayList<Property> attributesList = 
							((UmlPropertyList) dataManagement.
									getInPortByName("attributesList").getValue()).getUmlProperties();
					ArrayList<Operation> operationsList = 
							((UmlOperationList) dataManagement.
									getInPortByName("operationsList").getValue()).getUmlOperations();
					String className =
						(String) dataManagement.getInPortByName("className").getValue();
					// begin prepare and execute comrel interpreter
					ComrelInterpreter interpreter = new ComrelInterpreter(selectedEObject);
					interpreter.loadComrelModel(dataManagement.getComrelFilePath());
					setRootPortValues(interpreter, selectedEObject,
										attributesList,
										operationsList,
										className);
					interpreter.execute();
					// end prepare and execute comrel interpreter
					stopTimeRecording();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			private void setRootPortValues(
					ComrelInterpreter interpreter,
					org.eclipse.uml2.uml.Class selectedEObject, 
					ArrayList<Property> attributesList,
					ArrayList<Operation> operationsList, 
					String className) {
				RefactoringUnit rootRefactoringUnit = interpreter.getRootRefactoringUnit();
				for (InputPort ip : rootRefactoringUnit.getAllInputPorts()) {
					System.out.println(ip.getName());
					if (ip.getName().equals("selectedEObject")) {
						((SingleInputPort) ip).setValue(selectedEObject);
					}
					if (ip.getName().equals("operationsList")) {
						((MultiInputPort) ip).getValue().addAll(operationsList);
					}
					if (ip.getName().equals("attributesList")) {
						((MultiInputPort) ip).getValue().addAll(attributesList);
					}
					if (ip.getName().equals("className")) {
						((SingleInputPort) ip).setValue(className);
					}
				}
			}
		};
	}

	/**
	 * Internal class for providing an instance of a LTK RefactoringProcessor 
	 * used for EMF model refactorings.	 
	 * @generated
	 */
	public final class InternalRefactoringProcessor extends 
									LtkEmfRefactoringProcessorAdapter {

		/**
		 * Constructor using the invocation context of the model refactoring.
		 * @param selection Invocation context of the model refactoring.
		 * @generated
		 */
		private InternalRefactoringProcessor(List<EObject> selection){
				super(getParent(), selection, applyRefactoring());				
		}
			
		/**
		 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#
	 	 * checkInitialConditions(org.eclipse.core.runtime.IProgressMonitor)
		 * @generated
		 */	
		@Override
		public RefactoringStatus checkInitialConditions(){
				RefactoringStatus result = new RefactoringStatus();
				Class selectedEObject = 
						(Class) dataManagement.getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
				ArrayList<Property> attributesList = ((UmlPropertyList) dataManagement.
									getInPortByName("attributesList").getValue()).getUmlProperties();
				ArrayList<Operation> operationsList = ((UmlOperationList) dataManagement.
									getInPortByName("operationsList").getValue()).getUmlOperations();
				// test: the selected class must be owned by a package
				String msg = "This refactoring can only be applied" +
							" on classes which are owned by a package!";
				if (selectedEObject.getPackage() == null) {
					result.addFatalError(msg);
					return result;
				}
				// test: each property must be an owned attribute of the selected class
				msg = "At least one selected property is not an owned attribute of class '" 
														+ selectedEObject.getName() +"'!";
				for (Property attribute : attributesList) {
					if (! selectedEObject.getOwnedAttributes().contains(attribute)) {
						result.addFatalError(msg);
						return result;
					}
				}
				// test: each operation must be an owned operation of the selected class
				msg = "At least one selected operation is not an owned operation of class '" 
														+ selectedEObject.getName() +"'!";
				for (Operation operation : operationsList) {
					if (! selectedEObject.getOwnedOperations().contains(operation)) {
						result.addFatalError(msg);
						return result;
					}
				}
				return result;
		}
		
		/**
		 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#
	     * checkFinalConditions(org.eclipse.core.runtime.IProgressMonitor, 
	     * org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext)
		 * @generated
		 */	
		@Override
		public RefactoringStatus checkFinalConditions(){
				RefactoringStatus result = new RefactoringStatus();
				// the refactoring has no additional final conditions to be checked
				startTimeRecording();
				return result;
		}
		
	}
	
	long zstVorher;
	long zstNachher;
	
	private void startTimeRecording() {
		zstVorher = System.currentTimeMillis();
	}

	private void stopTimeRecording() {
		zstNachher = System.currentTimeMillis();
		System.out.println("Time needed (withoud loading): " + ((zstNachher - zstVorher)) + " ms");
	}

}