/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringController.javajet,v 1.2 2012/10/16 21:03:02 tarendt Exp $
 */
package org.eclipse.emf.refactor.refactorings.uml24.pullupattribute;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.refactoring.core.Refactoring;
import org.eclipse.emf.refactor.refactoring.interfaces.IController;
import org.eclipse.emf.refactor.refactoring.interfaces.IDataManagement;
import org.eclipse.emf.refactor.refactoring.papyrus.managers.PapyrusManager;
import org.eclipse.emf.refactor.refactoring.runtime.ltk.LtkEmfRefactoringProcessorAdapter;
import org.eclipse.emf.refactor.refactorings.papyrus.uml24.UmlUtils;
import org.eclipse.gmf.runtime.notation.BasicCompartment;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.View;
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
	 * @see org.eclipse.emf.refactor.refactoring.interfaces.IController#getParent()
	 * @generated
	 */
	@Override
	public Refactoring getParent() {
		return this.parent;
	}
	
	/**
	 * Sets the Refactoring supported by the controller.
	 * @param emfRefactoring Refactoring supported by the controller.
	 * @see org.eclipse.emf.refactor.refactoring.interfaces.IController#
	 * setParent(org.eclipse.emf.refactor.refactoring.core.Refactoring)
	 * @generated
	 */
	@Override
	public void setParent(Refactoring emfRefactoring) {
		this.parent = emfRefactoring;
	}
	
	/**
	 * Returns the DataManagement object of the model refactoring.
	 * @return DataManagement object of the model refactoring.
	 * @see org.eclipse.emf.refactor.refactoring.interfaces.IController#
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
	 * @see org.eclipse.emf.refactor.refactoring.interfaces.IController#
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
	 * @see org.eclipse.emf.refactor.refactoring.interfaces.IController#
	 * setSelection(java.util.List)
	 * @generated
	 */
	@Override
	public void setSelection(List<EObject> selection) {
		this.selection = selection;
		System.out.println("===> selection: " + this.selection);
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
				
				
				// execute: move selected attribute to specified superclass
				Class oldClass = selectedEObject.getClass_();
				Class newClass = oldClass.getSuperClass(className);				
				oldClass.getOwnedAttributes().remove(selectedEObject);
				newClass.getOwnedAttributes().add(selectedEObject);
				// if attribute is no association end: move shape to attribute compartment
				View view = null;
				if (selectedEObject.getAssociation() == null) {
					view = getView(selectedEObject);
					if (view != null) {
						System.out.println("--> View: " + view);
						BasicCompartment bcOld = (BasicCompartment) view.eContainer();
						bcOld.removeChild(view);
						BasicCompartment bcNew = getAttributeCompartment(newClass);
						bcNew.insertChild(view);
					}
				}
				
				// if attribute is association end: change opposite end type
//				if (selectedEObject.getAssociation() != null) {
//					Property associationEndToMove = null;
//					for (Property ae : selectedEObject.getAssociation().getMemberEnds()) {
//						if ((! ae.equals(selectedEObject)) && (ae.getClass_() != null)) {
//							associationEndToMove = ae;
//							break;
//						}
//					}
//					if (associationEndToMove != null) // change opposite end type
//						associationEndToMove.setType(newClass);
//				}
				
				
				// execute: remove equivalent attributes from subclasses
				ArrayList<Class> classes = UmlUtils.getAllSubClasses(newClass);
				classes.remove(oldClass);
				for (Class cls : classes) {
					Property attributeToRemove = null;
					for (Property attr : cls.getOwnedAttributes()) {
						if (attr.getName().equals(selectedEObject.getName())) {
							attributeToRemove = attr;
							break;
						}
					}
//					if (attributeToRemove.getAssociation() != null) {
//						Association assoc = attributeToRemove.getAssociation();
//						Property associationEndToRemove = null;
//						for (Property ae : attributeToRemove.getAssociation().getMemberEnds()) {
//							if ((! ae.equals(attributeToRemove)) && (ae.getClass_() != null)) {
//								associationEndToRemove = ae;
//								break;
//							}
//						}
//						if (associationEndToRemove != null) { // remove opposite association end if owned by a class
//							Class owningClass = associationEndToRemove.getClass_();
//							owningClass.getOwnedAttributes().remove(associationEndToRemove);
//						} 
//						// remove association from owning package
//						Package owningPackage = assoc.getPackage();
//						owningPackage.getPackagedElements().remove(assoc);
//					}
					// remove equivalent attribute from subclass
					Class owningSubClass = attributeToRemove.getClass_();
					owningSubClass.getOwnedAttributes().remove(attributeToRemove);
					// if attribute is no association end: remove shape from attribute compartment
					if (attributeToRemove.getAssociation() == null) {
						if (view != null) {
							BasicCompartment bc = getAttributeCompartment(owningSubClass);
							View viewToRemove = null;
							for (Object o : bc.getChildren()) {
								if (((View) o).getElement() == attributeToRemove) {
									viewToRemove = (View) o;
								}
							}	
							System.out.println("viewToRemove: " + viewToRemove);
							bc.removeChild(viewToRemove);
						}
					}
				}
			}

			private View getView(Property property) {
				Diagram diagram = null;
				if (selection.size() > 1) {
					diagram = (Diagram) selection.get(1);
				} else {
					diagram = PapyrusManager.getInstance().getDiagram();
				}
				if (diagram != null) {
					TreeIterator<EObject> iter = diagram.eAllContents();
					while (iter.hasNext()) {
						EObject eo = iter.next();
						if (eo instanceof View) {
							View view = (View) eo;
							if (view.getElement() == property) {
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
								" on properties which are owned attributes of a class" +
								" (but no association ends)!";
				if (selectedEObject.getClass_() == null || selectedEObject.getAssociation() != null) {
					result.addFatalError(msg);
				} else {
					// test: the selected attribute must be public
					msg = "This refactoring can only be applied on public class attributes!";
					if (! selectedEObject.getVisibility().equals(VisibilityKind.PUBLIC_LITERAL)) 
															result.addFatalError(msg);
					// test: the selected attribute must not redefine another one
					msg = "This refactoring can not be applied because the selected attribute" +
							" redefines another one in the inheritance hierarchy!";
					if (! selectedEObject.getRedefinedElements().isEmpty()) 
															result.addFatalError(msg);
					// test: the owning class must have at least one superclass
					msg = "This refactoring can not be applied because the owning class " +
							"of the selected attribute does not have any superclasses!";
					if (selectedEObject.getClass_().getSuperClasses().isEmpty()) 
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
				RefactoringStatus result = new RefactoringStatus();
				org.eclipse.uml2.uml.Property selectedEObject = 
					(org.eclipse.uml2.uml.Property) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
				String className =
					(String) dataManagement.getInPortByName("className").getValue();
				// test: the owning class must have a superclass with the specified name
				String msg = "The owning class does not have a superclass named '" +
																		className + "'!";
				Class superClass = selectedEObject.getClass_().getSuperClass(className);
				if (superClass == null) {
					result.addFatalError(msg);
				} else {
					// test: each subclass of the specified superclass must have an
					// attribute equal to the selected attribute
					if (! UmlUtils.subClassesHaveAttribute(superClass, selectedEObject)) {
						ArrayList<String> msgs = 
								UmlUtils.getReasonsWhySubClassesDoNotHaveAttribute(superClass, selectedEObject);
						for (String str : msgs) {
							result.addFatalError(str);
						}
					} else {
						// test: the super class must not own an attribute with the 
						// same name as the selected attribute
						msg = "The superclass already owns an attribute named '" + 
															selectedEObject.getName() + "'!";
						for (Property att : superClass.getOwnedAttributes()) {
							if (att.getName().equals(selectedEObject.getName())) 
																	result.addFatalError(msg);
						}
						// test: the superclass must already inherit an element with the 
						// same name as the selected attribute
						msg = "The superclass already inherits an element named '" + 
															selectedEObject.getName() + "'!";
						for (NamedElement att : superClass.getInheritedMembers()) {
							if (att.getName().equals(selectedEObject.getName())) 
																	result.addFatalError(msg);
						}
					}
				}
				return result;
		}
		
	}

}