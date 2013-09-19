/**
 * <copyright>
 * </copyright>
 */
package org.eclipse.emf.refactor.refactorings.uml24.compositional.helper;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.VisibilityKind;

import comrel.ComrelFactory;
import comrel.SingleInputPort;
import comrel.SingleOutputPort;
import comrel.extensions.ISingleFeatureHelper;

/**
 * Helper class for synchronizing ports in 
 * composite model refactorings specified in CoMReL.
 * @generated
 */
public class GetAssociatedClass implements ISingleFeatureHelper {

	final ComrelFactory factory = ComrelFactory.eINSTANCE;
	private SingleInputPort class_in;
	private SingleOutputPort class_out;

	/**
	 * Default constructor.
	 * @generated
	 */
	public GetAssociatedClass() {
		initInputPort();
		initOutputPort();
	}
	
	/**
	 * Method for setting the input port.
	 * @generated
	 */
	private void initInputPort() {
		class_in = factory.createSingleInputPort();
		class_in.setName("class_in");
		class_in.setType(org.eclipse.uml2.uml.Class.class);
	}
	
	/**
	 * Method for setting the output port.
	 * @generated
	 */
	private void initOutputPort(){
		class_out = factory.createSingleOutputPort();
		class_out.setName("class_out");
		class_out.setType(org.eclipse.uml2.uml.Class.class);
	}
	
	/**
	 * @generated
	 */
	@Override
	public SingleInputPort getInputPort() {
		return class_in;
	}

	/**
	 * @generated
	 */
	@Override
	public SingleOutputPort getOutputPort() {
		return class_out;
	}
	
	/**
	 * @generated
	 */
	@Override
	public void run() {
		org.eclipse.uml2.uml.Class in = (org.eclipse.uml2.uml.Class) class_in.getValue();
		org.eclipse.uml2.uml.Class out = null;
		
		List<Class> associatedClasses = getOne2OneAssociatedClasses(in);
		out = associatedClasses.get(0);
		
		class_out.setValue(out);
	}
	
	public static List<Class> getOne2OneAssociatedClasses(Class cl) {
		ArrayList<Class> result = new ArrayList<Class>();
		ArrayList<Association> incomingAssociations = 
									getIncomingAssociations(cl);
		ArrayList<Association> outgoingAssociations = 
									getOutgoingAssociations(cl);
		ArrayList<Class> classesFromIncomingAssociations = 
			getOtherClassesFromAssociations(cl, incomingAssociations);
		ArrayList<Class> classesFromOutgoingAssociations = 
				getOtherClassesFromAssociations(cl, outgoingAssociations);
		ArrayList<Class> associatedClasses = new ArrayList<Class>();
		for (Class associatedClass : classesFromIncomingAssociations) {
			if (classesFromOutgoingAssociations.contains(associatedClass))
				associatedClasses.add(associatedClass);
		}
		for (Class associatedClass : associatedClasses) {
			if (incomingAssociationHasOne2OneMultiplicityAndIsPublic
					(cl, incomingAssociations, associatedClass)
					&& outgoingAssociationHasOne2OneMultiplicityAndIsPublic
						(cl, outgoingAssociations, associatedClass)){
				result.add(associatedClass);
			}
		}
		return result;
	}
	
	private static boolean outgoingAssociationHasOne2OneMultiplicityAndIsPublic(
			Class cl,
			ArrayList<Association> outgoingAssociations, 
			Class associatedClass) {
		for (Association assoc : outgoingAssociations) {
			for (Property ae1 : assoc.getMemberEnds()) {
				if (ae1.getType().equals(cl)) {
					for (Property ae2 : assoc.getMemberEnds()) {
						if (ae2.getType().equals(associatedClass)) {
							if ((ae2.getLower() == 1) 
									&& (ae2.getUpper() == 1)
									&& (ae2.getVisibility().equals(VisibilityKind.PUBLIC_LITERAL)))
								return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private static boolean incomingAssociationHasOne2OneMultiplicityAndIsPublic(
			Class cl, 
			ArrayList<Association> incomingAssociations, 
			Class associatedClass) {
		for (Association assoc : incomingAssociations) {
			for (Property ae1 : assoc.getMemberEnds()) {
				if (ae1.getType().equals(cl)) {
					for (Property ae2 : assoc.getMemberEnds()) {
						if (ae2.getType().equals(associatedClass)) {
							if ((ae1.getLower() == 1) 
									&& (ae1.getUpper() == 1)
									&& (ae2.getVisibility().equals(VisibilityKind.PUBLIC_LITERAL)))
								return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private static ArrayList<Class> getOtherClassesFromAssociations(Class cl, ArrayList<Association> incomingAssociations) {
		ArrayList<Class> result = new ArrayList<Class>();
		for (Association assoc : incomingAssociations) {
			for (Property associationEnd1 : assoc.getMemberEnds()) {
				if (associationEnd1.getType().equals(cl)){
					for (Property associationEnd2 : assoc.getMemberEnds()) {
						if ((! associationEnd1.equals(associationEnd2)) 
								&& associationEnd2.getType() instanceof Class
								&& (! associationEnd2.getType().equals(cl))) {
							result.add((Class) associationEnd2.getType());
						}
					}
				}
			}
		}
		return result;
	}
	
	private static ArrayList<Association> getIncomingAssociations(Class cl) {
		ArrayList<Association> associations = getAllAssociations(cl.getModel());
		ArrayList<Association> result = new ArrayList<Association>();
		for (Association association : associations) {
			for (Property associationEnd : association.getMemberEnds()) {
				if (associationEnd.getType().equals(cl) && associationEnd.isNavigable())
					result.add(association);
			}
		}		
		return result;
	}
	
	private static ArrayList<Association> getOutgoingAssociations(Class cl) {
		ArrayList<Association> associations = getAllAssociations(cl.getModel());
		ArrayList<Association> result = new ArrayList<Association>();
		for (Association association : associations) {
			for (Property associationEnd1 : association.getMemberEnds()) {
				if (associationEnd1.getType().equals(cl)){
					for (Property associationEnd2 : association.getMemberEnds()) {
						if ((! associationEnd1.equals(associationEnd2)) 
								&& associationEnd2.isNavigable()) {
							result.add(association);
						}
					}
				}
			}
		}		
		return result;
	}
	
	private static ArrayList<Association> getAllAssociations(Model model) {
		ArrayList<Association> associations = new ArrayList<Association>();
		TreeIterator<EObject> iter = model.eResource().getAllContents();
		while (iter.hasNext()) {
			EObject eObject = iter.next();
			if (eObject instanceof Association) {
				associations.add((Association) eObject);
			}			
		}
		return associations;
	}

}
	