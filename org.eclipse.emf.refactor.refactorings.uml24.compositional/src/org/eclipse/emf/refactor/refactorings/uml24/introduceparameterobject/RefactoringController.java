/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringController.javajet,v 1.3 2011/01/21 13:08:06 tarendt Exp $
 */
package org.eclipse.emf.refactor.refactorings.uml24.introduceparameterobject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.refactoring.core.Refactoring;
import org.eclipse.emf.refactor.refactoring.interfaces.IController;
import org.eclipse.emf.refactor.refactoring.interfaces.IDataManagement;
import org.eclipse.emf.refactor.refactoring.runtime.ltk.LtkEmfRefactoringProcessorAdapter;
import org.eclipse.emf.refactor.refactorings.uml24.compositional.UmlUtils;
import org.eclipse.emf.refactor.refactorings.uml24.createclasswithattributesfromparameterlist.UmlParameterList;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;

import comrel.InputPort;
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
					ArrayList<org.eclipse.uml2.uml.Parameter> selectedEObjects = 
							((UmlParameterList) dataManagement.
									getInPortByName(dataManagement.SELECTEDEOBJECT).getValue()).
																		getUmlParameters();
					String className =
						(String) dataManagement.getInPortByName("className").getValue();
					String paramName =
						(String) dataManagement.getInPortByName("paramName").getValue();
					// begin prepare and execute comrel interpreter
					ComrelInterpreter interpreter = new ComrelInterpreter(selectedEObjects.get(0));
					interpreter.loadComrelModel(dataManagement.getComrelFilePath());
					setRootPortValues(interpreter, selectedEObjects,
										className,
										paramName);
					interpreter.execute();
					// end prepare and execute comrel interpreter
					stopTimeRecording();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			private void setRootPortValues(
					ComrelInterpreter interpreter,
					ArrayList<Parameter> selectedEObjects, 
					String className,
					String parameterName) {
				RefactoringUnit rootRefactoringUnit = interpreter.getRootRefactoringUnit();
				for (InputPort ip : rootRefactoringUnit.getAllInputPorts()) {
					System.out.println(ip.getName());
					if (ip.getName().equals("selectedEObject")) {
						((SingleInputPort) ip).setValue(new UmlParameterList(selectedEObjects));
						System.out.println("single value set");
					}
					if (ip.getName().equals("className")) {
						((SingleInputPort) ip).setValue(className);
						System.out.println("single value set");
					}
					if (ip.getName().equals("paramName")) {
						((SingleInputPort) ip).setValue(parameterName);
						System.out.println("single value set");
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
				ArrayList<org.eclipse.uml2.uml.Parameter> selectedEObjects = 
						((UmlParameterList) dataManagement.
								getInPortByName(dataManagement.SELECTEDEOBJECT).getValue()).
																	getUmlParameters();
					// test: the selected parameters must be owned by one single operation
					String msg = "The selected parameters do not belong to one operation!";
					ArrayList<Operation> operations = new ArrayList<Operation>();
					for (Parameter param : selectedEObjects) {
						Operation op = param.getOperation();
						if (! operations.contains(op)) operations.add(op);
					}
					if (operations.size() > 1) {
						result.addFatalError(msg);
						return result;
					}
					// test: each selected parameter must be an input parameter
					msg = "This refactoring can only be applied on input parameters!";
					for (Parameter param : selectedEObjects) {
						if (! UmlUtils.isInputParameter(param)) {
							result.addFatalError(msg);
							return result;
						}
					}
					// test: the owning operation must be owned by a class
					msg = "This refactoring can only be applied on parameters whose " +
							"owning operation is owned by a class!";
					Operation owningOperation = operations.get(0);
					if (owningOperation.getClass_() == null) {
						result.addFatalError(msg);
						return result;
					}
					// test: the owning class must be owned by a package
					msg = "This refactoring can only be applied on parameters whose " +
							"owning class is owned by a package!";
					Class owningClass = owningOperation.getClass_();
					if (owningClass.getPackage() == null) {
						result.addFatalError(msg);
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
			startTimeRecording();
				RefactoringStatus result = new RefactoringStatus();
				ArrayList<org.eclipse.uml2.uml.Parameter> selectedEObjects = 
						((UmlParameterList) dataManagement.
								getInPortByName(dataManagement.SELECTEDEOBJECT).getValue()).
																	getUmlParameters();
				String className =
					(String) dataManagement.getInPortByName("className").getValue();
				String parameterName =
					(String) dataManagement.getInPortByName("paramName").getValue();
				// test: there must be no class in the model named as specified in 'className'
				String msg = "There is already a class in the model named '" + className + "'!";
				ArrayList<Class> allClasses = 
						UmlUtils.getAllClasses(selectedEObjects.get(0).getModel());
				for (Class cls : allClasses) {
					if ((cls.getName() != null) && (cls.getName().equals(className))) {
						result.addFatalError(msg); 
						break;
					}
				}
				// test: no selected parameter must be named as specified in 'parameterName'
				msg = "One selected parameter is already named '" + parameterName + "'!";
				for (Parameter param : selectedEObjects) {
					if ((param.getName() != null) && (param.getName().equals(parameterName))) {
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