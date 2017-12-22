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

public class NASSC implements IMetricCalculator {

	private List<EObject> context; 
	
	@Override
	public void setContext(List<EObject> context) {
		this.context = context;
	}	
			
	@Override
	public double calculate() {	
		
		Class c = (Class) context.get(0);	
		double ret = 0;
		
		for (Association as : getAssociations()) {
			boolean isassocionwithitself = true;
			for (Property pr : as.getMemberEnds()) {
				if (pr.getType() != c) {
					isassocionwithitself = false;
					break;
				}
			}
			if (isassocionwithitself) ret++;
		}
		
		return ret;
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
