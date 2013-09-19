/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringController.javajet,v 1.3 2011/01/21 13:08:06 tarendt Exp $
 */
package org.eclipse.emf.refactor.refactorings.uml24.removeemptyassociatedclass;

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
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;


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
				org.eclipse.uml2.uml.Class selectedEObject = 
					(org.eclipse.uml2.uml.Class) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
				// execute: remove all associations from class
				List<Association> associations = UmlUtils.getAssociations(selectedEObject);
				List<Class> owningClasses = new ArrayList<Class>();
				List<Property> attributesToDelete = new ArrayList<Property>();
				List<Package> owningPackages = new ArrayList<Package>();
				List<Association> associationsToDelete = new ArrayList<Association>();
				for (Association assoc : associations) {
					for (Property ae : assoc.getMemberEnds()) {
						if (! assoc.getOwnedEnds().contains(ae)) {
							owningClasses.add(ae.getClass_());
							attributesToDelete.add(ae);
						}
					}
					owningPackages.add(assoc.getPackage());
					associationsToDelete.add(assoc);
				}
				// remove association end as class attributes
				for (int i=0; i < owningClasses.size(); i++) {
					owningClasses.get(i).getOwnedAttributes().remove(attributesToDelete.get(i));
				}
				// remove associations from owning packages
				for (int i=0; i < owningPackages.size(); i++) {
					owningPackages.get(i).getPackagedElements().remove(associationsToDelete.get(i));
				}
				// execute: remove selected class from owning package
				Package owningPackage = selectedEObject.getPackage();
				owningPackage.getPackagedElements().remove(selectedEObject);
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
				org.eclipse.uml2.uml.Class selectedEObject = 
					(org.eclipse.uml2.uml.Class) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
				// test: the selected class must be owned by a package
				String msg = "This refactoring can only be applied" +
								" on classes which are owned by a package!";
				if (selectedEObject.getPackage() == null) {
					result.addFatalError(msg);
					return result;
				}
				// test: the selected class must be associated to at least one class
				List<Class> associatedclasses = 
									UmlUtils.getOtherAssociatedClasses(selectedEObject);
				msg = "Class '" + selectedEObject.getName() + 
											"' is not associated to any classes!";
				if (associatedclasses.isEmpty()) {
					result.addFatalError(msg);
					return result;
				}
				// test: the selected class must be associated to at most one class
				msg = "Class '" + selectedEObject.getName() + 
						"' is associated to more than one class!";
				if (associatedclasses.size() > 1) { 
					result.addFatalError(msg);
					return result;
				}
				// test: the selected class must not have any attributes
				// except for those that are owned by associations
				msg = "Class '" + selectedEObject.getName() + "' has further " +
						"attributes except for those that are owned by associations!";
				for (Property attr : selectedEObject.getOwnedAttributes()) {
					if (attr.getAssociation() == null) result.addFatalError(msg); 
				}
				// test: the selected class  must not own any operations
				msg = "Class '" + selectedEObject.getName() + "' owns at least one operation!";
				if (UmlUtils.hasOperations(selectedEObject)) result.addFatalError(msg);
				// test: the class must not have any inner classes
				msg = "Class '" + selectedEObject.getName() + "' has at least one inner class!";
				if (UmlUtils.hasInnerClasses(selectedEObject)) result.addFatalError(msg);
				// test: the selected class must not have any subclasses
				msg = "Class '" + selectedEObject.getName() + "' has at least one subclass!";
				if (UmlUtils.hasSubclasses(selectedEObject)) result.addFatalError(msg);
				// test: the selected class must not have any superclasses
				msg = "Class '" + selectedEObject.getName() + "' has at least one superclass!";
				if (UmlUtils.hasSuperclasses(selectedEObject)) result.addFatalError(msg);
				// test: the class must not implement any interfaces
				msg = "Class '" + selectedEObject.getName() + "' implements at least one interface!";
				if (UmlUtils.implementsInterfaces(selectedEObject)) result.addFatalError(msg);
				// test: the class must not use any interfaces
				msg = "Class '" + selectedEObject.getName() + "' uses at least one interface!";
				if (UmlUtils.usesInterfaces(selectedEObject)) result.addFatalError(msg);
				// test: the class must not be used as attribute type
				msg = "Class '" + selectedEObject.getName() +  "' is used as attribute type!";
				if (UmlUtils.isUsedAsFurtherAttributeType(selectedEObject)) result.addFatalError(msg);
				// test: the class must not be used as operation/parameter type
				msg = "Class '" + selectedEObject.getName() + "' is used as operation/parameter type!";
				if (UmlUtils.isUsedAsParameterType(selectedEObject)) result.addFatalError(msg);				
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
				// no final checks
				return result;
		}
		
	}

}