package org.eclipse.emf.refactor.metrics.uml24.umlcl;

import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.metrics.interfaces.IMetricCalculator;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Property;

public class CBC implements IMetricCalculator {

	private List<EObject> context; 
	
	@Override
	public void setContext(List<EObject> context) {
		this.context = context;
	}	
			
	@Override
	public double calculate() {	
		double ret = 0;
		ret += getAttributeUses();
		ret += getAssociationEndUses();
		return ret;
	}

	private double getAttributeUses() {
		double ret = 0;
		Class cl = (Class) context.get(0);
		for (Property p : cl.getOwnedAttributes()) {
			if (p.getType() != null) {
				if (p.getType() instanceof Class) {
					if (((Class)p.getType()) != cl) {
						ret++;
					}
				}
			}
		}
		return ret;
	}

	private double getAssociationEndUses() {
		double ret = 0;
		Class cl = (Class) context.get(0);
		for (Association a : getOutgoingAssociations()) {
			for (Property p : a.getOwnedEnds()) {
				if (p.getType() != cl) {
					ret++;
				}
			}
		}
		return ret;
	}
	
	private EList<Association> getOutgoingAssociations() {
		EList<Association> outgoingAssociations = new BasicEList<Association>();
		Class cl = (Class) context.get(0);
		for (Association a : getAssociations()) {
			for (Property p : a.getMemberEnds()) {
				if (p.getType() == cl) {
					outgoingAssociations.add(a);
					break;
				}
			}
		}
		return outgoingAssociations;
	}

	private EList<Association> getAssociations() {
		EList<Association> associations = new BasicEList<Association>();
		TreeIterator<EObject> iter = context.get(0).eResource().getAllContents();
		while (iter.hasNext()) {
			EObject eObject = iter.next();
			if (eObject instanceof Association) {
				associations.add((Association) eObject);
			}
		}
		return associations;
	}

}
