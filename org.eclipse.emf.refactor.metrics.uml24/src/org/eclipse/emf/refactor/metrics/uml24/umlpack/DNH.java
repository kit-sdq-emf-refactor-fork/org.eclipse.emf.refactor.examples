package org.eclipse.emf.refactor.metrics.uml24.umlpack;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.metrics.interfaces.IMetricCalculator;
import org.eclipse.uml2.uml.Package;

public class DNH implements IMetricCalculator {

	private List<EObject> context; 
	
	@Override
	public void setContext(List<EObject> context) {
		this.context=context;
	}	
		
	@Override
	public double calculate() {	
		Package p = (Package) context.get(0);
		return getNestingDepth(p);
	}

	private int getNestingDepth(Package p) {
		if (p.getNestingPackage() == null) {
			return 0;
		}
		return 1 + getNestingDepth(p.getNestingPackage());
	}

}
