/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringController.javajet,v 1.3 2011/01/21 13:08:06 tarendt Exp $
 */
package org.eclipse.emf.refactor.refactorings.uml24.moveattribute;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.refactoring.core.Refactoring;
import org.eclipse.emf.refactor.refactoring.interfaces.IController;
import org.eclipse.emf.refactor.refactoring.interfaces.IDataManagement;
import org.eclipse.emf.refactor.refactoring.runtime.ltk.LtkEmfRefactoringProcessorAdapter;
import org.eclipse.emf.refactor.refactorings.uml24.UmlUtils;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.VisibilityKind;


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
				org.eclipse.uml2.uml.Property selectedEObject = 
					(org.eclipse.uml2.uml.Property) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
				String className =
					(String) dataManagement.getInPortByName("className").getValue();
				// execute: move selected attribute to specified class
				List<Class> associatedClasses = 
						UmlUtils.getOne2OneAssociatedClasses(selectedEObject.getClass_());
				Class newClass = UmlUtils.getAssociatedClass(associatedClasses, className);
				Class oldClass = selectedEObject.getClass_();
				oldClass.getOwnedAttributes().remove(selectedEObject);
				newClass.getOwnedAttributes().add(selectedEObject);
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
				org.eclipse.uml2.uml.Property selectedEObject = 
					(org.eclipse.uml2.uml.Property) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
				// test: the selected property must be an attribute (owned by a class)
				String msg = "This refactoring can only be applied" +
								" on properties which are owned attributes of a class!";
				if (selectedEObject.getClass_() == null) {
					result.addFatalError(msg);
				} else {
					// test: the selected property must not be involved in any associations
					msg = "This refactoring can only be applied on class attributes " +
							"which are not involved in any associations!";
					if (selectedEObject.getAssociation() != null) result.addFatalError(msg);
					// test: the selected property must be public
					msg = "This refactoring can only be applied on public class attributes!";
					if (! selectedEObject.getVisibility().equals(VisibilityKind.PUBLIC_LITERAL)) 
															result.addFatalError(msg);
					// test: the owning class must be related to at least one other class
					// using a bidirectional association with multiplicity 1:1
					msg = "The owning class is not related to at least one other class " +
							"using a bidirectional association with multiplicity 1:1 " +
							"and public association ends!";
					List<Class> associatedClasses = 
							UmlUtils.getOne2OneAssociatedClasses(selectedEObject.getClass_());
					if (associatedClasses.isEmpty()) result.addFatalError(msg);
				}
//				if (result.hasEntries()) MessageDialog.openInformation(null, "", result.getEntries().toString());
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
				org.eclipse.uml2.uml.Property selectedEObject = 
					(org.eclipse.uml2.uml.Property) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
				String className =
					(String) dataManagement.getInPortByName("className").getValue();
				// test: the owning class must be related to a class with the specified 
				// name using a bidirectional association with multiplicity 1:1
				String msg = "The owning class is not related to a class named '" +
								className + "' using a bidirectional association with " +
								"multiplicity 1:1 and public association ends!";
				List<Class> associatedClasses = 
						UmlUtils.getOne2OneAssociatedClasses(selectedEObject.getClass_());
				if (! UmlUtils.isAssociatedClass(associatedClasses, className)) {
					result.addFatalError(msg);
				} else {
					// test: the associated class must not own an attribute with the 
					// same name as the selected attribute
					msg = "The associated class already owns an attribute named '" + 
														selectedEObject.getName() + "'!";
					Class cl = UmlUtils.getAssociatedClass(associatedClasses, className);
					for (Property att : cl.getOwnedAttributes()) {
						if (att.getName().equals(selectedEObject.getName())) 
																result.addFatalError(msg);
					}
					// test: the associated class must not already inherit an element with the 
					// same name as the selected attribute
					msg = "The associated class already inherits an element named '" + 
														selectedEObject.getName() + "'!";
					for (NamedElement att : cl.getInheritedMembers()) {
						if (att.getName().equals(selectedEObject.getName())) 
																result.addFatalError(msg);
					}
				}
//				if (result.hasEntries()) MessageDialog.openInformation(null, "", result.getEntries().toString());
				return result;
		}
		
	}

}