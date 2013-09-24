package org.eclipse.emf.refactor.metrics.uml24.umlcl;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.metrics.interfaces.IMetricCalculator;
import org.eclipse.uml2.uml.Class;

public final class NSUPC2 implements IMetricCalculator {

		
	private List<EObject> context; 
		
	@Override
	public void setContext(List<EObject> context) {
		this.context=context;
	}	
		
	@Override
	public double calculate() {	
		Class c = (Class) context.get(0);		
		return c.allParents().size();
	}
}