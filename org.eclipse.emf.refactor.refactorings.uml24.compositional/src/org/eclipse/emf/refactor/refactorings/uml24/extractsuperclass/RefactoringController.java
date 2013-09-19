/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringController.javajet,v 1.3 2011/01/21 13:08:06 tarendt Exp $
 */
package org.eclipse.emf.refactor.refactorings.uml24.extractsuperclass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.refactoring.core.Refactoring;
import org.eclipse.emf.refactor.refactoring.interfaces.IController;
import org.eclipse.emf.refactor.refactoring.interfaces.IDataManagement;
import org.eclipse.emf.refactor.refactoring.runtime.ltk.LtkEmfRefactoringProcessorAdapter;
import org.eclipse.emf.refactor.refactorings.uml24.extractsuperclass.RefactoringDataManagement.ContextList;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;

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
					ArrayList<org.eclipse.uml2.uml.Class> selectedEObjects = 
						((ContextList) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue()).getElements();
					String className =
						(String) dataManagement.getInPortByName("className").getValue();
					// begin prepare and execute comrel interpreter
					ComrelInterpreter interpreter = new ComrelInterpreter(selectedEObjects.get(0));
					interpreter.loadComrelModel(dataManagement.getComrelFilePath());
					setRootPortValues(interpreter, selectedEObjects,
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
					ArrayList<org.eclipse.uml2.uml.Class> selectedEObjects, 
					String className) {
				RefactoringUnit rootRefactoringUnit = interpreter.getRootRefactoringUnit();
				for (InputPort ip : rootRefactoringUnit.getAllInputPorts()) {
					System.out.println(ip.getName());
					if (ip.getName().equals("selectedEObjects")) {
						((MultiInputPort) ip).getValue().addAll(selectedEObjects);
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
				ArrayList<org.eclipse.uml2.uml.Class> selectedEObjects = 
						((ContextList) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue()).getElements();
				// test: all classes are owned by the same packages
				String msg1 = "At least one class is not owned by a package!"; 
				String msg2 = "The selected classes are not owned by the same package!";
				ArrayList<Package> owningPackages = new ArrayList<Package>();
				for (Class cls : selectedEObjects) {
					if (cls.getPackage() == null) {
						result.addFatalError(msg1);
					} else {
						if (! owningPackages.contains(cls.getPackage())) {
							owningPackages.add(cls.getPackage());
						}
					}
				}
				if (owningPackages.size() > 1) result.addFatalError(msg2);
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
				startTimeRecording();
				RefactoringStatus result = new RefactoringStatus();
				ArrayList<org.eclipse.uml2.uml.Class> selectedEObjects = 
						((ContextList) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue()).getElements();
				String className =
					(String) dataManagement.getInPortByName("className").getValue();
				// test: No selected class must be named like specified in 'newName'
				String msg = "At least one selected class is already named '" + className + "'!"; 
				for (Class cls : selectedEObjects) {
					if (cls.getName().equals(className)) {
						result.addFatalError(msg);
						break;
					}
				}
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