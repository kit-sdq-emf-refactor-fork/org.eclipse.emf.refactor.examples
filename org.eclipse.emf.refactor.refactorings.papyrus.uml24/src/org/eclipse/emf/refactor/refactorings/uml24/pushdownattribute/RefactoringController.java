/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringController.javajet,v 1.3 2011/01/21 13:08:06 tarendt Exp $
 */
package org.eclipse.emf.refactor.refactorings.uml24.pushdownattribute;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.emf.refactor.refactoring.core.Refactoring;
import org.eclipse.emf.refactor.refactoring.interfaces.IController;
import org.eclipse.emf.refactor.refactoring.interfaces.IDataManagement;
import org.eclipse.emf.refactor.refactoring.papyrus.managers.PapyrusManager;
import org.eclipse.emf.refactor.refactoring.runtime.ltk.LtkEmfRefactoringProcessorAdapter;
import org.eclipse.emf.refactor.refactorings.uml24.UmlUtils;
import org.eclipse.gmf.runtime.notation.BasicCompartment;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;
import org.eclipse.uml2.uml.Class;
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
				System.out.println(">>>> hallloooo >>>");
				Class owningClass = selectedEObject.getClass_();
				ArrayList<Class> subClasses = UmlUtils.getAllSubClasses(owningClass);
				// ----- new
				// execute: move selected attribute to specified superclass
				Class firstSubClass = subClasses.get(0);			
				owningClass.getOwnedAttributes().remove(selectedEObject);
				firstSubClass.getOwnedAttributes().add(selectedEObject);
				// if attribute is no association end: move shape to attribute compartment
				View view = null;
				if (selectedEObject.getAssociation() == null) {
					view = getView(selectedEObject);
					if (view != null) {
						System.out.println("==> View: " + view);
						BasicCompartment bcOld = (BasicCompartment) view.eContainer();
						System.out.println("==> bcOld: " + bcOld);
						bcOld.removeChild(view);
						BasicCompartment bcNew = getAttributeCompartment(firstSubClass);
						System.out.println("==> bcNew: " + bcNew);
						System.out.println("==> bcNew: " + bcNew.getElement());
						bcNew.insertChild(view);
					}
				}
				subClasses.remove(0);
				// ----- end new
				for (Class subClass : subClasses) {
					// create copy of the selected attribute
					Copier attCopier = new Copier();
					Property newAttribute = (Property) attCopier.copy(selectedEObject);
					attCopier.copyReferences();
					// add copied attribute to subclass
					subClass.getOwnedAttributes().add(newAttribute);
					// if attribute is no association end: add shape copy to attribute compartments of each subclass
					if (selectedEObject.getAssociation() == null) {
						View v = getView(selectedEObject);
						if (v != null) {
							System.out.println("--> View: " + v);
							Copier viewCopier = new Copier();
							View newView = (View) viewCopier.copy(v);
							newView.setElement(newAttribute);
							BasicCompartment bc = getAttributeCompartment(subClass);
							bc.insertChild(newView);
						}
					}
					
//					if (selectedEObject.getAssociation() != null) {
//						// selected attribute is member end of an association
//						Association assoc = selectedEObject.getAssociation();
//						Property oldOppositeEnd = assoc.getOwnedEnds().get(0);
//						// create copy of the opposite association end
//						Copier aeCopier = new Copier();
//						Property newAssocEnd = (Property) aeCopier.copy(oldOppositeEnd);
//						aeCopier.copyReferences();
//						newAssocEnd.setType(subClass);
//						// create new association and add association ends
//						Package owningPackage = assoc.getPackage();
//						Association newAssociation = UMLFactory.eINSTANCE.createAssociation();
//						String assocName = "a_" + subClass.getName().toLowerCase() + "_" + 
//											newAttribute.getType().getName().toLowerCase();
//						newAssociation.setName(assocName);
//						owningPackage.getPackagedElements().add(newAssociation);
//						newAssociation.getOwnedEnds().add(newAssocEnd);
//						newAssociation.getMemberEnds().add(newAttribute);						
//					}
				}
				// execute: remove selected attribute from owning class
//				if (selectedEObject.getAssociation() != null) {
//					// selected attribute is member end of an association
//					// delete association from owning package
//					Association assoc = selectedEObject.getAssociation();
//					Package owningPackage = assoc.getPackage();
//					owningPackage.getPackagedElements().remove(assoc);
//				}
				// if attribute is no association end: remove shape from attribute compartment
				// -----  new
//				if (selectedEObject.getAssociation() == null) {
//					View v = getView(selectedEObject);
//					if (v != null) {
//						System.out.println("--> View: " + v);
//						BasicCompartment bcOld = (BasicCompartment) v.eContainer();
//						bcOld.removeChild(v);
//					}
//				}
				// ----- end new
				// execute: remove selected attribute from owning class
				owningClass.getOwnedAttributes().remove(selectedEObject);
			}
			
			private View getView(Property property) {
				Diagram diagram = null;
				if (selection.size() > 1) {
					diagram = (Diagram) selection.get(1);
					System.out.println(">> diagram 1: " + diagram);
				} else {
					diagram = PapyrusManager.getInstance().getDiagram();
					System.out.println(">> diagram 2: " + diagram);
				}
				System.out.println(">> diagram: " + diagram);
				if (diagram != null) {
					TreeIterator<EObject> iter = diagram.eAllContents();
					while (iter.hasNext()) {
						EObject eo = iter.next();
						if (eo instanceof View) {
							View view = (View) eo;
							if (view.getElement() == property) {
								System.out.println(">> view: " + view);
								System.out.println(">> diagram: " + diagram);
								return view;
							}
						}
					}
				}
				return null;
			}
			
			private BasicCompartment getAttributeCompartment(Class clazz) {
				Shape shapeNewClass = getShapeNewClass(clazz);
				System.out.println("shapeNewClass : " + shapeNewClass);
				System.out.println("children index 1: " + shapeNewClass.getChildren().get(1));
				return (BasicCompartment) shapeNewClass.getChildren().get(1);
			}

			private Shape getShapeNewClass(Class clazz) {
				EList<Shape> shapes = getAllShapes();
				System.out.println("number of shapes : " + shapes.size());
				for (Shape shape : shapes) {
					if (shape.getElement() == clazz) {
						return shape;
					}
				}
				return null;
			}
			
			private EList<Shape> getAllShapes() {
				EList<Shape> shapes = new BasicEList<Shape>();
				Diagram diagram = null;
				if (selection.size() > 1) {
					diagram = (Diagram) selection.get(1);
				} else {
					diagram = PapyrusManager.getInstance().getDiagram();
				}
				if (diagram != null) {
					for (Object o : diagram.getChildren()) {
						Node node = (Node) o;
						shapes.addAll(getAllShapes(node));
					}
				}
				return shapes;
			}

			private EList<Shape> getAllShapes(Node node) {
				EList<Shape> shapes = new BasicEList<Shape>();
				if (node instanceof Shape) {
					shapes.add((Shape) node);
				}
				for (Object o : node.getChildren()) {
					Node innerNode = (Node) o;
					shapes.addAll(getAllShapes(innerNode));
				}
				return shapes;
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