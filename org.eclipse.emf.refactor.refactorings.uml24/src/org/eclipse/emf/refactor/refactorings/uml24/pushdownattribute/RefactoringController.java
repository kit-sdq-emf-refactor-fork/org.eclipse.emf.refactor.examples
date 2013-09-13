/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringController.javajet,v 1.3 2011/01/21 13:08:06 tarendt Exp $
 */
package org.eclipse.emf.refactor.refactorings.uml24.pushdownattribute;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.emf.refactor.refactoring.core.Refactoring;
import org.eclipse.emf.refactor.refactoring.interfaces.IController;
import org.eclipse.emf.refactor.refactoring.interfaces.IDataManagement;
import org.eclipse.emf.refactor.refactoring.runtime.ltk.LtkEmfRefactoringProcessorAdapter;
import org.eclipse.emf.refactor.refactorings.uml24.UmlUtils;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
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
	 * @param Refactoring Refactoring supported by the controller.
	 * @see org.eclipse.emf.refactor.common.core.IController#
	 * setParent(org.eclipse.emf.refactor.common.core.Refactoring)
	 * @generated
	 */
	@Override
	public void setParent(Refactoring Refactoring) {
		this.parent = Refactoring;
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
				// execute: copy selected attribute to each subclass
				Class owningClass = selectedEObject.getClass_();
				ArrayList<Class> subClasses = UmlUtils.getAllSubClasses(owningClass);
				for (Class subClass : subClasses) {
					// create copy of the selected attribute
					Copier attCopier = new Copier();
					Property newAttribute = (Property) attCopier.copy(selectedEObject);
					attCopier.copyReferences();
					// add copied attribute to subclass
					subClass.getOwnedAttributes().add(newAttribute);
					if (selectedEObject.getAssociation() != null) {
						// selected attribute is member end of an association
						Association assoc = selectedEObject.getAssociation();
						Property oldOppositeEnd = assoc.getOwnedEnds().get(0);
						// create copy of the opposite association end
						Copier aeCopier = new Copier();
						Property newAssocEnd = (Property) aeCopier.copy(oldOppositeEnd);
						aeCopier.copyReferences();
						newAssocEnd.setType(subClass);
						// create new association and add association ends
						Package owningPackage = assoc.getPackage();
						Association newAssociation = UMLFactory.eINSTANCE.createAssociation();
						String assocName = "a_" + subClass.getName().toLowerCase() + "_" + 
											newAttribute.getType().getName().toLowerCase();
						newAssociation.setName(assocName);
						owningPackage.getPackagedElements().add(newAssociation);
						newAssociation.getOwnedEnds().add(newAssocEnd);
						newAssociation.getMemberEnds().add(newAttribute);						
					}
				}
				// execute: remove selected attribute from owning class
				if (selectedEObject.getAssociation() != null) {
					// selected attribute is member end of an association
					// delete association from owning package
					Association assoc = selectedEObject.getAssociation();
					Package owningPackage = assoc.getPackage();
					owningPackage.getPackagedElements().remove(assoc);
				}
				owningClass.getOwnedAttributes().remove(selectedEObject);
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
					// test: the selected attribute must be public
					msg = "This refactoring can only be applied on public class attributes!";
					if (! selectedEObject.getVisibility().equals(VisibilityKind.PUBLIC_LITERAL)) 
															result.addFatalError(msg);
					// test: the owning class must have at least one subclass
					msg = "This refactoring can not be applied because the owning class " +
							"of the selected attribute does not have any subclasses!";
					if (! UmlUtils.hasSubclasses(selectedEObject.getClass_())) 
															result.addFatalError(msg);
					// test: the owning class must not have any subclasses which own
					// attributes with the same name as the selected attribute
					msg = "This refactoring can not be applied because at least one subclass " +
							"already owns an attribute named '" + selectedEObject.getName() +"'!";
					if (UmlUtils.oneSubClassHasEquallyNamedAttribute
						(selectedEObject.getClass_(), selectedEObject)) result.addFatalError(msg);
					// test: the owning class must not have any subclasses which inherit
					// elements with the same name as the selected attribute
					msg = "This refactoring can not be applied because at least one subclass " +
							"already inherits an element named '" + selectedEObject.getName() +"'!";
					if (UmlUtils.oneSubClassInheritsEquallyNamedAttribute
						(selectedEObject.getClass_(), selectedEObject)) result.addFatalError(msg);
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
				// the refactoring has no final checks
				return result;
		}
		
	}

}