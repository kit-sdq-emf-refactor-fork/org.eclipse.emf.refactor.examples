package org.eclipse.emf.refactor.metrics.uml24.umlcl;

import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.metrics.interfaces.IMetricCalculator;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Property;

public final class NEATC implements IMetricCalculator {
		
	private List<EObject> context; 
		
	@Override
	public void setContext(List<EObject> context) {
		this.context=context;
	}	
		
	@Override
	public double calculate() {	
		Class cl = (Class) context.get(0);
		double result = 0.0;
		// custom code - begin
		EList<Class> otherClasses = getOtherClassses(cl);
		if (otherClasses.isEmpty()) return result;
		Double[] maxEqualAttributes = new Double[otherClasses.size()];
		for (int i=0; i < maxEqualAttributes.length; i++) maxEqualAttributes[i] = 0.0;
		for (Property attr : cl.getOwnedAttributes()) {
			for (int i=0; i < otherClasses.size(); i++) {
				if (containsEqualAttribute(otherClasses.get(i), attr)) {
					maxEqualAttributes[i]++;
				}
			}
		}	
		result = max(maxEqualAttributes);
		// custom code - end
		return result;
	}
	
	private double max(Double[] doubleArray) {
		Double result = 0.0;
		for (int i=0; i < doubleArray.length; i++) {
			if (doubleArray[i] > result) {
				result = doubleArray[i];
			}
		}
		return result;
	}

	private boolean containsEqualAttribute(Class c, Property attr) {
		boolean contains = false;
		for (Property att : c.getOwnedAttributes()) {
			if (haveEqualNames(attr, att) 
				&& haveEqualTypes(attr, att)
				&& haveEqualVisibility(attr, att) 
				&& haveEqualMultiplicities(attr, att)) {
					contains = true;
					break;
				}
		}
		return contains;
	}

	private boolean haveEqualMultiplicities(Property attr, Property att) {
		return ((attr.getLower() == att.getLower()) && (attr.getUpper() == att.getUpper()));
	}

	private boolean haveEqualVisibility(Property attr, Property att) {
		return attr.getVisibility().equals(att.getVisibility());
	}

	private boolean haveEqualTypes(Property attr, Property att) {
		return ((att.getType() == null) && (attr.getType() == null)) || 
				((att.getType() != null) && (attr.getType() != null)) && (att.getType().equals(attr.getType()));
	}

	private boolean haveEqualNames(Property attr, Property att) {
		if (attr == null || att == null) return false;
		if (attr.getName() == null || att.getName() == null) return false;
		return att.getName().equals(attr.getName());
	}

	private EList<Class> getOtherClassses(Class cl) {
		EList<Class> otherClasses = new BasicEList<Class>();
		TreeIterator<EObject> iter = context.get(0).eResource().getAllContents();
		while (iter.hasNext()) {
			EObject eObject = iter.next();
			if (eObject instanceof Class) {
				Class otherClass = (Class) eObject;
				if (!otherClass.equals(cl)) {
					otherClasses.add(otherClass);
				}
			}
		}
		return otherClasses;
	}
}