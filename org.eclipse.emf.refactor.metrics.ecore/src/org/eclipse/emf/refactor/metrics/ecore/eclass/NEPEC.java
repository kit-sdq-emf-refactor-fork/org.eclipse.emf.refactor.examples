package org.eclipse.emf.refactor.metrics.ecore.eclass;

import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.metrics.interfaces.IMetricCalculator;
import org.eclipse.emf.ecore.*;


public final class NEPEC implements IMetricCalculator {

		
	private List<EObject> context; 
		
	@Override
	public void setContext(List<EObject> context) {
		this.context=context;
	}	
		
	@Override
	public double calculate() {	
		EClass in = (EClass) context.get(0);
		double ret = 0.0;
		
		for (EOperation eOp : in.getEOperations()) {
			ret += eOp.getEParameters().size();
		}
		
		return ret;
	}
}