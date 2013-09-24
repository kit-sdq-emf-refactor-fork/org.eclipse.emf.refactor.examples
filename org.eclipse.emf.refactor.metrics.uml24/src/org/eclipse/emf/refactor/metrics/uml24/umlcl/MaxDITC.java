package org.eclipse.emf.refactor.metrics.uml24.umlcl;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.metrics.interfaces.IMetricCalculator;
import org.eclipse.uml2.uml.Class;


public final class MaxDITC implements IMetricCalculator {
		
	private List<EObject> context; 
		
	@Override
	public void setContext(List<EObject> context) {
		this.context = context;
	}	
		
	@Override
	public double calculate() {	
		Class c = (Class) context.get(0);
		return getMaxDit(c);
	}

	private int getMaxDit(Class c) {
		if (c.getGeneralizations().isEmpty()) {
			return 0;
		}
		int[] dits = new int[c.getGeneralizations().size()];
		for (int i=0; i < dits.length; i++) {
			dits[i] = 1 + getMaxDit((Class) c.getGeneralizations().get(i).getGeneral());
		}
		return max(dits);
	}

	private int max(int[] intArray) {
		int result = 0;
		for (int i=0; i < intArray.length; i++) {
			if (intArray[i] > result) {
				result = intArray[i];
			}
		}
		return result;
	}
}